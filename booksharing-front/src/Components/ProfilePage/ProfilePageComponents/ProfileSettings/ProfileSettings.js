import React from "react";
import axios from "axios";
import "./ProfileSettings.css";
import AuthenticationService from "../../../../service/AuthenticationService";
import { Redirect } from "react-router-dom";

class ProfileSettings extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
            userData: null,
            userName: AuthenticationService.getLoggedInUserName(),
            currentPassword: "",
            firstPassword: "",
            secondPassword: "",
            isPassworedChanged: false,
            firstName: "",
            lastName: "",
            phoneNumber: "",
            email: "",
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
        this.onUserDataChangeSumbit = this.onUserDataChangeSumbit.bind(this);
        this.sendPassword = this.sendPassword.bind(this);
        this.onPasswordCheckSubmit = this.onPasswordCheckSubmit.bind(this);
    }

    componentDidMount() {
        this.loadUserBooks();
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value,
        });
    }

    onPasswordCheckSubmit(event) {
        if (
            this.state.firstPassword === this.state.secondPassword &&
            this.state.firstPassword !== this.state.currentPassword
        ) {
            console.log("weszło");
            this.sendPassword(event);
        } else {
            console.log("lipa");
        }
    }

    loadUserBooks() {
        const USER_NAME = AuthenticationService.getLoggedInUserName();
        const BASE_URL = "http://localhost:8889/api/sharepoint/email/";
        const EXPANDED_URL = BASE_URL + USER_NAME;

        axios
            .get(EXPANDED_URL, {
                headers: {
                    authorization: "Basic " + localStorage.getItem("userToken"),
                },
            })
            .then((res) => {
                console.log(res.data);
                if (res.status >= 200 && res.status <= 210) {
                    console.log("działa: dane użytkownika");
                    this.setState({ userData: res.data, loading: false });
                }
            })
            .catch((error) => {
                console.log("NIE DZIAŁA: DANE UŻYTKOWNIKA");
                console.log(error);
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
                    window.location.reload();
                }
            })
            .catch((error) => {
                console.log("NIE ZMIENIONO ADRESU");
                console.log(error);
            });
    }

    async onUserDataChangeSumbit(event) {
        event.preventDefault();
        //
        const USER_NAME = AuthenticationService.getLoggedInUserName();
        const BASE_URL = "http://localhost:8889/api/user/update/";
        const EXPANDED_URL = BASE_URL + USER_NAME;

        axios
            .patch(
                EXPANDED_URL,
                {
                    firstName: this.state.firstName,
                    lastName: this.state.lastName,
                    phoneNumber: this.state.phoneNumber,
                    email: this.state.email,
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
                    window.alert("Zmieniono dane użytkownika");
                    if (this.state.email !== "") {
                        this.setState({ isRegistered: true });
                    }
                    window.location.reload();
                }
            })
            .catch((error) => {
                console.log("NIE ZMIENIONO DANYCH UŻYTKOWNIKA");
                console.log(error);
            });
    }

    async sendPassword(event) {
        event.preventDefault();
        const BASE_URL = "http://localhost:8889/api/user/update/password";

        axios
            .patch(
                BASE_URL,
                {
                    password: this.state.firstPassword,
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
                    window.alert("Zmieniono hasło użytkownika");
                    this.setState({ isRegistered: true });
                }
            })
            .catch((error) => {
                console.log("NIE ZMIENIONO HASŁA UŻYTKOWNIKA");
                console.log(error);
            });
    }

    render() {
        if (this.state.isRegistered) {
            return <Redirect to="/logout" />;
        } else {
            return (
                <div>
                    <h1>Ustawienia konta</h1>
                    <div>
                        <div id="databox">
                            <h2>Dane:</h2>
                            {this.state.loading || !this.state.userData ? (
                                <div></div>
                            ) : (
                                <div>
                                    <p>
                                        Imię:{" "}
                                        {this.state.userData.user.firstName}
                                    </p>
                                    <p>
                                        Nazwisko:{" "}
                                        {this.state.userData.user.lastName}
                                    </p>
                                    <p>
                                        Email: {this.state.userData.user.email}
                                    </p>
                                    <p>
                                        Numer telefonu:{" "}
                                        {this.state.userData.user.phoneNumber}
                                    </p>
                                    <p>Aktualny adres</p>
                                    <p>
                                        Kraj:{" "}
                                        {this.state.userData.address.country}
                                    </p>
                                    <p>
                                        Miasto:{" "}
                                        {this.state.userData.address.city}
                                    </p>
                                    <p>
                                        Ulica:{" "}
                                        {this.state.userData.address.street +
                                            " " +
                                            this.state.userData.address
                                                .houseNumber}
                                    </p>
                                    <p>
                                        Kod pocztowy:{" "}
                                        {this.state.userData.address.postalCode}
                                    </p>
                                </div>
                            )}
                        </div>
                        <div id="formsbox">
                            <div id="formcolumn1">
                                <h2>Zmień dane</h2>
                                <form id="userdata-form">
                                    <label>
                                        <p>Imię</p>
                                        <input
                                            type="text"
                                            name="firstName"
                                            onChange={this.onChange}
                                        />
                                        <p>Nazwisko</p>
                                        <input
                                            type="text"
                                            name="lastName"
                                            onChange={this.onChange}
                                        />
                                        <p>Numer telefonu</p>
                                        <input
                                            type="text"
                                            name="phoneNumber"
                                            onChange={this.onChange}
                                        />
                                        <p>email</p>
                                        <input
                                            type="text"
                                            name="email"
                                            onChange={this.onChange}
                                        />
                                    </label>
                                    <br />
                                    <button
                                        onClick={this.onUserDataChangeSumbit}
                                    >
                                        Ustaw Dane
                                    </button>
                                </form>
                            </div>
                            <div id="formcolumn2">
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
                            <div id="formcolumn3">
                                <h2>Zmień dane</h2>
                                <form id="userpassword-form">
                                    <label>
                                        <p>Aktualne hasło</p>
                                        <input
                                            type="password"
                                            name="currentPassword"
                                            onChange={this.onChange}
                                        />
                                        <p>Nowe hasło</p>
                                        <input
                                            type="password"
                                            name="firstPassword"
                                            onChange={this.onChange}
                                        />
                                        <p>Powtórz nowe hasło</p>
                                        <input
                                            type="password"
                                            name="secondPassword"
                                            onChange={this.onChange}
                                        />
                                    </label>
                                    <br />
                                    <button
                                        onClick={this.onPasswordCheckSubmit}
                                    >
                                        Zmień hasło
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}

export default ProfileSettings;
