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

class LatoMap extends React.Component {
    constructor(props) {
        super(props);
        this.pointList = [];
        this.vectorSource = new VectorSource({});
        this.state = {
            books: this.props.data,
            updateMap: false,
            myText: "test",
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
            ],
            target: null,
        });
        this.popup = new Overlay({});
        this.addMarker = this.addMarker.bind(this);
    }

    addMarker(lon, lat, city, street, houseNumber, email, title, borrowed) {
        var iconFeature = new Feature({
            geometry: new Point(olProj.fromLonLat([lon, lat])),
            // geometry: new Point(olProj.transform([18, 54], 'EPSG:4326','EPSG:3857')),
            name: "Sharepoint",
            // data: data,
            city: city,
            street: street,
            houseNumber: houseNumber,
            email: email,
            title: title,
            borrowed: borrowed,
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

    componentDidMount() {
        this.map.setTarget("map");

        this.popup.setElement(
            ReactDOM.findDOMNode(this).querySelector("#popup")
        );

        this.map.on("click", (e) => {
            var pixel = this.map.getEventPixel(e.originalEvent);
            if (this.map.hasFeatureAtPixel(pixel)) {
                console.log(this.map.getFeaturesAtPixel(pixel));
                let myFeatures = this.map.getFeaturesAtPixel(pixel);
                let isBorrowed = myFeatures[0].get("borrowed")
                    ? "wypożyczona"
                    : "dostępna";
                let query =
                    myFeatures[0].get("city") +
                    ", " +
                    myFeatures[0].get("street") +
                    ", " +
                    myFeatures[0].get("houseNumber") +
                    ", " +
                    myFeatures[0].get("email") +
                    ", " +
                    myFeatures[0].get("title") +
                    ", książka " +
                    isBorrowed;

                this.setState({ myText: query });
                console.log(myFeatures[0].get("data"));
                this.popup.setPosition(e.coordinate);
                this.map.addOverlay(this.popup);
            } else if (this.map.getOverlays) {
                this.map.removeOverlay(this.popup);
            }
        });
    }

    fetchCoords = async (city, street, houseNumber) => {
        try {
            const url =
                "https://nominatim.openstreetmap.org/search?city=" +
                city +
                "&street=" +
                houseNumber +
                "%20" +
                street +
                "&format=json";
            const res = await axios.get(url);
            return res;
        } catch (error) {
            console.log(error);
        }
    };

    fetchDataSync = async () => {
        for (const myData of this.props.data) {
            const result = await this.fetchCoords(
                myData.sharePoint.address.city,
                myData.sharePoint.address.street,
                myData.sharePoint.address.houseNumber
            );
            console.log(result);
            console.log(myData);
            let lon = result.data[0].lon;
            let lat = result.data[0].lat;
            this.addMarker(
                lon,
                lat,
                myData.sharePoint.address.city,
                myData.sharePoint.address.street,
                myData.sharePoint.address.houseNumber,
                myData.sharePoint.user.email,
                myData.title,
                myData.borrowed
            );
        }
    };

    componentDidUpdate() {
        if (this.state.updateMap != this.props.updateMap) {
            if (this.props.data.length > 0) {
                // console.log(this.props.data.length);
                this.vectorSource.clear();
                if (this.map.getOverlays) {
                    this.map.removeOverlay(this.popup);
                }

                // dla kazdej znalezionej ksiazki wyszukaj adres
                // zamien adres na lon lat
                this.fetchDataSync();
                console.log("po wywolaniu pobierzDaneSync");
            }
            this.setState({ updateMap: this.props.updateMap });
        }
    }
    render() {
        return (
            <div id="map" class="map">
                <div id="popup">
                    <div id="mytext">
                        <p>{this.state.myText}</p>
                    </div>
                </div>
            </div>
        );
    }
}

export default LatoMap;
