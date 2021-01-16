//externals
import ReactDOM from 'react-dom';
import React from 'react';
import './MyMap.css'

//open layers and styles
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import XYZ from 'ol/source/XYZ';

class MyMap extends React.Component {
 
  constructor(props) {
    super(props);

    this.state = { center: [0, 0], zoom: 2 };

    this.map = new Map({
      target: null,
      layers: [
        new TileLayer({
          source: new XYZ({
            url: 'https://{a-c}.tile.openstreetmap.org/{z}/{x}/{y}.png'
          })
        })
      ],
      view: new View({
        center: this.state.center,
        zoom: this.state.zoom
      })

    });
  }

  componentDidMount() {
    this.map.setTarget("map"); 
  }


  render() {
    return (
      <div id="map">
      </div>
    );
  }
}

export default MyMap;
