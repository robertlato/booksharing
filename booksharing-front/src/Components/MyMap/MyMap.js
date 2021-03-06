//externals
//api-key AIzaSyC06P_1LI2SP5bz3D_iUxgiBPzD5xpkUt8
import React, { Component } from 'react';
import axios from "axios";
import { Map, InfoWindow, Marker, GoogleApiWrapper } from 'google-maps-react';
import "./MyMap.css";

const API_URL = "http://localhost:8889";

const mapStyles = {
  width: '60%',
  height: '60%'
};


function getSharepointsData(){
  axios.get(`${API_URL}/api/sharepoints`)
  .then(function(response){
    console.log(response);
      console.log(response.data[0].address.street);
      console.log(response.data[0].address.houseNumber);
      console.log(response.data[0].address.postalCode);
      console.log(response.data[0].address.city);
      console.log(response.data[0].address.country);
  })
  .catch(function(error){
    console.log(error);
  })
}
getSharepointsData();

var formattedAddress;
var latAddress;
var lngAddress;
geocode();

function geocode(){
    const location = 'Zielony Trójkąt 4 80-869 Gdańsk';
    axios.get('https://maps.googleapis.com/maps/api/geocode/json', {
        params: {
            address: location,
            key: 'AIzaSyC06P_1LI2SP5bz3D_iUxgiBPzD5xpkUt8'
        }
    }).then(function(response){
        console.log(response);

        formattedAddress = response.data.results[0].formatted_address;
        latAddress = response.data.results[0].geometry.location.lat;
        lngAddress = response.data.results[0].geometry.location.lng;

        console.log(formattedAddress);
        console.log(latAddress);
        console.log(lngAddress);
    })
    .catch(function(error){
        console.log(error);
    });
}

export class MyMap extends Component {
    state = {
        showingInfoWindow: false,  
        activeMarker: {},          
        selectedPlace: {}        
      };

    onMarkerClick = (props, marker, e) =>
    this.setState({
      selectedPlace: props,
      activeMarker: marker,
      showingInfoWindow: true
    });

  onClose = props => {
    if (this.state.showingInfoWindow) {
      this.setState({
        showingInfoWindow: false,
        activeMarker: null
      });
    }
  };

  render() {
    return (
      <Map
        google={this.props.google}
        zoom={12}
        style={mapStyles}
        initialCenter={
          {
            lat: 54.352229015069526,
            lng: 18.646201672142887
          }
        }
      > 
       <Marker
          onClick={this.onMarkerClick}
          position={{lat: 54.3780294,lng: 18.6290507}} // tutaj dac z bazy adresy
          name={formattedAddress} // dac z bazy nazwe w dymku
        />
         <Marker
          onClick={this.onMarkerClick}
          position={{lat: 54.38435,lng: 18.6293214}} // tutaj dac z bazy adresy
          name={formattedAddress} // dac z bazy nazwe w dymku
        />
        
        <InfoWindow
          marker={this.state.activeMarker}
          visible={this.state.showingInfoWindow}
          onClose={this.onClose}
        >
          <div>
            <h4>{this.state.selectedPlace.name}</h4>
          </div>
        </InfoWindow>
      </Map>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: 'AIzaSyC06P_1LI2SP5bz3D_iUxgiBPzD5xpkUt8'
})(MyMap);


