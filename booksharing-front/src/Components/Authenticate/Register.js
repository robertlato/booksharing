import React from "react";
import { Link, Redirect } from "react-router-dom";
import AuthenticationService from "../../service/AuthenticationService";
import axios from "axios";

class Register extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            firstname: "",
            lastname: "",
            email: "",
            password: "",
            password2: "",
            phonenumber: "",
            isRegistered: false,
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value,
        });
    }

    async onSubmit(event) {
        event.preventDefault();

        console.log(this.state.firstname);
        console.log(this.state.lastname);
        console.log(this.state.email);
        console.log(this.state.password);
        console.log(this.state.phonenumber);
        // let res = await axios.post(
        //     "https://jsonplaceholder.typicode.com/posts",
        //     {
        //         title: "nazwa moja",
        //         body: "treść moja",
        //         userId: 100,
        //     }
        // );
        // console.log(res);

        axios
            .post("http://localhost:8889/api/register", {
                firstName: this.state.firstname,
                lastName: this.state.lastname,
                email: this.state.email,
                password: this.state.password,
                phoneNumber: this.state.phonenumber,
            })
            .then((res) => {
                console.log("udało się zarejestrować");
                console.log(res);
                console.log(res.data);
                this.setState({ isRegistered: true });
            })
            .catch((error) => {
                console.log("nie przeszła rejestracja");
                console.log(error);
            });
    }

    render() {
        if (this.state.isRegistered) {
            return <Redirect to="/welcome" />;
        } else {
            return (
                <div>
                    <h1>Rejestracja</h1>
                    <h2>
                        Masz już konto? <Link to="/login">Zaloguj się</Link>
                    </h2>
                    <form>
                        <label>
                            <p>Imię</p>
                            <input
                                type="text"
                                name="firstname"
                                onChange={this.onChange}
                            />
                        </label>
                        <label>
                            <p>Nazwisko</p>
                            <input
                                type="text"
                                name="lastname"
                                onChange={this.onChange}
                            />
                        </label>
                        <label>
                            <p>Email</p>
                            <input
                                type="email"
                                name="email"
                                onChange={this.onChange}
                            />
                        </label>
                        <label>
                            <p>Hasło</p>
                            <input
                                type="password"
                                name="password"
                                onChange={this.onChange}
                            />
                        </label>
                        <label>
                            <p>Powtórz hasło</p>
                            <input
                                type="password"
                                name="password2"
                                onChange={this.onChange}
                            />
                        </label>
                        <label>
                            <p>Numer telefonu</p>
                            <input
                                type="password"
                                name="phonenumber"
                                onChange={this.onChange}
                            />
                        </label>
                        <button onClick={this.onSubmit}>Zarejestruj się</button>
                    </form>
                </div>
            );
        }
    }
}

export default Register;
