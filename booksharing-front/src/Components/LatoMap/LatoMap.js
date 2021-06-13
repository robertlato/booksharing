import React from "react";

import Map from "ol/Map";
import View from "ol/View";
import TileLayer from "ol/layer/Tile";
import OSM from "ol/source/OSM";
import * as olProj from "ol/proj";
import "./LatoMap.css";
import VectorLayer from "ol/layer/Vector";
import VectorSource from "ol/source/Vector";
import { Icon, Style } from "ol/style";
import Point from "ol/geom/Point";
import Feature from "ol/Feature";
import mybook from "./mybook.png";
import axios from "axios";
import "ol/ol.css";
import Overlay from "ol/Overlay";
import ReactDOM from "react-dom";
import LineString from "ol/geom/LineString";
import Stroke from "ol/style/Stroke";
import AuthenticationService from "../../service/AuthenticationService";


class LatoMap extends React.Component {
    constructor(props) {
        super(props);
        this.vectorSource = new VectorSource({});
        this.routeVectorSource = new VectorSource({});
        this.state = {
            books: this.props.data,
            updateMap: false,
            sharepointInfo: "test",
            sharepointBooks: [],
            createRoute: false,
            toPoint: null,
            fromPoint: null,
        };

        this.map = new Map({
            view: new View({
                center: olProj.fromLonLat([18.6667, 54.35]),
                zoom: 9,
            }),
            layers: [
                new TileLayer({
                    source: new OSM(),
                }),
                new VectorLayer({
                    source: this.vectorSource,
                }),
                new VectorLayer({
                    source: this.routeVectorSource,
                }),
            ],
            target: null,
        });
        this.popup = new Overlay({});
        this.addMarker = this.addMarker.bind(this);
        this.onClickBorrowBook = this.onClickBorrowBook.bind(this);

    }

    addMarker(lon, lat, city, street, houseNumber, email, title, borrowed, sharePointId, bookId) {

        let myCoords = olProj.fromLonLat([lon, lat]);

        if (this.vectorSource.getFeaturesAtCoordinate(myCoords).length > 0) {
            let myFeature = this.vectorSource.getFeaturesAtCoordinate(myCoords)[0];
            myFeature.get("borrowed").push(borrowed);
            myFeature.get("title").push(title);
            myFeature.get("bookId").push(bookId);
        } else {

            var iconFeature = new Feature({
                geometry: new Point(olProj.fromLonLat([lon, lat])),
                // geometry: new Point(olProj.transform([18, 54], 'EPSG:4326','EPSG:3857')),
                name: "Sharepoint",
                sharePointId: sharePointId,
                city: city,
                street: street,
                houseNumber: houseNumber,
                email: email,
                title: [title],
                borrowed: [borrowed],
                bookId: [bookId],
            });

            var iconStyle = new Style({
                image: new Icon({
                    scale: 0.5,
                    anchor: [0.5, 46],
                    anchorXUnits: "fraction",
                    anchorYUnits: "pixels",
                    src: mybook,
                }),
            });
            iconFeature.setStyle(iconStyle);
            this.vectorSource.addFeature(iconFeature);
        }

    }

    componentDidMount() {
        this.map.setTarget("map");

        this.popup.setElement(
            ReactDOM.findDOMNode(this).querySelector("#popup")
        );

        this.map.on("click", (e) => {
            var pixel = this.map.getEventPixel(e.originalEvent);
            if (this.map.hasFeatureAtPixel(pixel)) {
                let myFeatures = this.map.getFeaturesAtPixel(pixel);

                this.setState({
                    toPoint: olProj.transform(
                        myFeatures[0].getGeometry().getCoordinates(),
                        "EPSG:3857",
                        "EPSG:4326"
                    ),
                });

                this.setState({ sharepointBooks: [] });
                let sharepointInfoQuery =
                    myFeatures[0].get("city") +
                    ", " +
                    myFeatures[0].get("street") +
                    ", " +
                    myFeatures[0].get("houseNumber") +
                    ", " +
                    myFeatures[0].get("email");


                var books = {
                    title: [],
                    borrowed: [],
                    sharePointId: [],
                    bookId: []
                };

                for (let i = 0; i < myFeatures[0].get("borrowed").length; i++) {

                    books.title = myFeatures[0].get("title")[i];
                    books.borrowed = myFeatures[0].get("borrowed")[i];
                    books.sharePointId = myFeatures[0].get("sharePointId");
                    books.bookId = myFeatures[0].get("bookId")[i];

                    // this.setState(state => {
                    //     const sharepointBooks = state.sharepointBooks.concat(books);

                    //     return {sharepointBooks};
                    // });
                    this.setState(state => ({
                        sharepointBooks: [...state.sharepointBooks, books]
                    }));

                    books = [];
                }

                this.setState({ sharepointInfo: sharepointInfoQuery });
                this.popup.setPosition(e.coordinate);
                this.map.addOverlay(this.popup);
            } else if (this.map.getOverlays && !this.state.createRoute) {
                this.map.removeOverlay(this.popup);
                this.setState({
                    toPoint: null,
                });
            } else if (this.map.getOverlays && this.state.createRoute) {
                // wyznacz trase:

                // wyznacz punkty koncowe trasy
                let myPoint = this.map.getCoordinateFromPixel(pixel);
                this.setState({
                    fromPoint: olProj.transform(
                        myPoint,
                        "EPSG:3857",
                        "EPSG:4326"
                    ),
                });

                // wyswietl trase z fromPoint do toPoint
                this.getRoute();
                // dezaktywuj wyznaczanie trasy
                this.setState({ createRoute: false });
                // usun punkty
                this.setState({
                    fromPoint: null,
                    toPoint: null,
                });
            }
        });
    }

    fetchDataSync = () => {
        for (const myData of this.props.data) {
            this.addMarker(
                myData.sharePoint.address.lon,
                myData.sharePoint.address.lat,
                myData.sharePoint.address.city,
                myData.sharePoint.address.street,
                myData.sharePoint.address.houseNumber,
                myData.sharePoint.user.email,
                myData.title,
                myData.borrowed,
                myData.sharePoint.id,
                myData.id
            );
            this.map.renderSync();
        }
    };

    componentDidUpdate() {
        if (this.state.updateMap !== this.props.updateMap) {
            if (this.props.data.length > 0) {
                this.vectorSource.clear();
                this.routeVectorSource.clear();
                if (this.map.getOverlays) {
                    this.map.removeOverlay(this.popup);
                }

                // dla kazdej znalezionej ksiazki wyszukaj adres
                // zamien adres na lon lat
                this.fetchDataSync();
            }
            this.setState({ updateMap: this.props.updateMap });
        }
    }

    routeCreationTrigger = () => {
        this.setState({ createRoute: true });
        // usun popup
        this.map.removeOverlay(this.popup);
        this.routeVectorSource.clear();
    };

    fetchRouteCoords = async () => {
        try {
            const url = "http://h2096617.stratoserver.net:443/brouter?lonlats="
                + this.state.fromPoint[0]
                + ","
                + this.state.fromPoint[1]
                + "|"
                + this.state.toPoint[0]
                + ","
                + this.state.toPoint[1]
                + "&profile=car-fast&alternativeidx=0&format=geojson";
            const res = await axios.get(url);
            return res;
        } catch (error) {
            console.log(error);
        }
    };

    getRoute = async () => {
        const result = await this.fetchRouteCoords();
        this.getRoutePolyline(result);
    };

    getRoutePolyline = async (result) => {
        var pointList = [];
        let route = result.data.features[0].geometry.coordinates;
        let routeLength = route.length;
        for (var i = 0; i < routeLength; i++) {
            let point = olProj.fromLonLat([route[i][0], route[i][1]]);
            pointList.push(point);
        }
        let lineFeature = new Feature({
            geometry: new LineString(pointList),
        });
        let lineStyle = new Style({
            stroke: new Stroke({
                color: "blue",
                width: 4,
            }),
        });
        lineFeature.setStyle(lineStyle);
        this.routeVectorSource.addFeature(lineFeature);
    };

    async onClickBorrowBook(bookId, sharePointId) {

        console.log("inside onClickBorrowBook");
        if (AuthenticationService.isUserLoggedIn() === false) {
            window.alert("Musisz się zalogować!");
        } else {
            var userIsDecided = window.confirm("Czy na pewno chcesz Wypożyczyć wybraną książkę?");

            if (userIsDecided) {
                axios
                    .post(
                        "http://localhost:8889/api/borrowing",
                        {
                            book: {
                                id: bookId,
                            },
                            sharePoint: {
                                id: sharePointId,
                            },
                        },
                        {
                            headers: {
                                authorization:
                                    "Basic " + localStorage.getItem("userToken"),
                            },
                        }
                    )
                    .then((res) => {
                        if (res.status === 200) {
                            window.alert("Wypożyczono!");
                            console.log("Wypożyczono!");
                            window.location.reload();
                        }
                    })
                    .catch((error) => {
                        console.log("NIE WYPOŻYCZONO");
                        console.log(error);
                    });
            }
        }
    };

    render() {
        return (
            <div id="map" class="map">
                <div id="popup">
                    <div id="sharepointInfo">
                        <p>{this.state.sharepointInfo}</p>
                        <div id="books">
                            <ul>
                                {this.state.sharepointBooks.map(book => (
                                    <li key={book}>
                                        {book.title}  
                                        {book.borrowed ? (<p style={{display: "inline-block"}}>, Wypożyczona</p>)
                                        : (<button onClick={() => this.onClickBorrowBook(book.bookId, book.sharePointId)} type="button">Wypożycz</button>)
                                        }
                                    </li>
                                ))}
                            </ul>
                        </div>
                        <button onClick={this.routeCreationTrigger} type="button">Wyznacz trasę</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default LatoMap;
