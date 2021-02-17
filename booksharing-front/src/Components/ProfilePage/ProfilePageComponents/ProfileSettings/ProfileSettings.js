import React from "react";
import axios from "axios";
import "./ProfileSettings.css";
import AuthenticationService from "../../../../service/AuthenticationService";

class ProfileSettings extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            userName: AuthenticationService.getLoggedInUserName(),
            country: "",
            city: "",
            street: "",
            houseNumber: "",
            postalCode: "",
            lon: null,
            lat: null,
        };

        this.onChange = this.onChange.bind(this);
        this.onAdressSubmit = this.onAdressSubmit.bind(this);
    }
    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value,
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

    async onAdressSubmit(event) {
        event.preventDefault();
        //
        const BASE_URL = "http://localhost:8889/api/sharepoint/";
        const EXPANDED_URL = BASE_URL + this.state.userName;

        if (
            this.state.city !== "" &&
            this.state.street !== "" &&
            this.state.houseNumber !== ""
        ) {
            const result = await this.fetchCoords(
                this.state.city,
                this.state.street,
                this.state.houseNumber
            );

            this.setState({
                lon: result.data[0].lon,
                lat: result.data[0].lat,
            });
        }

        axios
            .patch(
                EXPANDED_URL,
                {
                    country: this.state.country,
                    city: this.state.city,
                    street: this.state.street,
                    houseNumber: this.state.houseNumber,
                    postalCode: this.state.postalCode,
                    lon: this.state.lon,
                    lat: this.state.lat,
                },
                {
                    headers: {
                        authorization:
                            "Basic " + localStorage.getItem("userToken"),
                    },
                }
            )
            .then((res) => {
                if (res.status === 200 || res.status === 204) {
                    window.alert("Zmieniono adres");
                    document.getElementById("sharepoint-form").reset();
                }
            })
            .catch((error) => {
                console.log("NIE ZMIENIONO ADRESU");
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <h1>Ustawienia konta</h1>
                <div>
                    <h2>Ustaw adres</h2>
                    <form id="sharepoint-form">
                        <label>
                            <p>Kraj</p>
                            <input
                                type="text"
                                name="country"
                                onChange={this.onChange}
                            />
                            <p>Miasto</p>
                            <input
                                type="text"
                                name="city"
                                onChange={this.onChange}
                            />
                            <p>Ulica</p>
                            <input
                                type="text"
                                name="street"
                                onChange={this.onChange}
                            />
                            <p>Numer budynku</p>
                            <input
                                type="text"
                                name="houseNumber"
                                onChange={this.onChange}
                            />
                            <p>Kod pocztowy</p>
                            <input
                                type="text"
                                name="postalCode"
                                onChange={this.onChange}
                            />
                        </label>
                        <br />
                        <button onClick={this.onAdressSubmit}>
                            Ustaw adres
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}

export default ProfileSettings;
