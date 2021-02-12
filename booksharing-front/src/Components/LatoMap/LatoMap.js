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

class LatoMap extends React.Component {
    constructor(props) {
        super(props);
        this.vectorSource = new VectorSource({});
        this.state = {
            books: this.props.data,
            updateMap: false,
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
        this.addMarker = this.addMarker.bind(this);
    }

    addMarker(lon, lat) {
        var iconFeature = new Feature({
            geometry: new Point(olProj.fromLonLat([lon, lat])),
            // geometry: new Point(olProj.transform([18, 54], 'EPSG:4326','EPSG:3857')),
            name: "Sharepoint",
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
    }

    componentDidUpdate() {
        if (this.state.updateMap !== this.props.updateMap) {
            if (this.props.data.length > 0) {
                // console.log(this.props.data.length);
                this.vectorSource.clear();

                // dla kazdej znalezionej ksiazki wyszukaj adres
                for (var i = 0; i < this.props.data.length; i++) {
                    // zamien adres na lon lat
                    var city = this.props.data[i].sharePoint.address.city;
                    var street = this.props.data[i].sharePoint.address.street;
                    var houseNumber = this.props.data[i].sharePoint.address
                        .houseNumber;

                    var url =
                        "https://nominatim.openstreetmap.org/search?city=" +
                        city +
                        "&street=" +
                        houseNumber +
                        "%20" +
                        street +
                        "&format=json";

                    axios
                        .get(url)
                        .then((res) => {
                            let lon = res.data[0].lon;
                            let lat = res.data[0].lat;

                            // dodaj marker
                            this.addMarker(lon, lat);
                        })
                        .catch((error) => {
                            console.log("Nie znaleziono wynikow nominati");
                            console.log(error);
                        });

                    // if (i == 1) {
                    //   this.map.getView().setCenter(olProj.fromLonLat([lon, lat]));
                    //   console.log("centruje mape");
                    // }
                }
            }
            this.setState({ updateMap: this.props.updateMap });
        }
    }

    render() {
        return <div id="map"></div>;
    }
}

export default LatoMap;
