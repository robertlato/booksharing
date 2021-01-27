import React from "react";
import { Link, Redirect } from "react-router-dom";
import AuthenticationService from "../../service/AuthenticationService";

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            isLogged: false,
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

        AuthenticationService.executeBasicAuthenticationService(
            this.state.username,
            this.state.password
        )
            .then(() => {
                AuthenticationService.registerSuccessfulLogin(
                    this.state.username,
                    this.state.password
                );
                console.log("dobre pasy");
            })
            .then(() => this.setState({ isLogged: true }))
            .then(() => window.location.reload())
            .catch(() => {
                console.log(this.state.username);
                console.log(this.state.password);
                console.log("złe pasy");
                // this.setState({ showSuccessMessage: false });
                // this.setState({ hasLoginFailed: true });
            });
    }

    render() {
        if (this.state.isLogged) {
            return <Redirect to="/" />;
        } else {
            return (
                <div>
                    <h1>Logowanie</h1>
                    <h2>
                        Nie masz konta?
                        <Link to="/register">Zarejestruj się</Link>
                    </h2>
                    <form>
                        <label>
                            <p>Login</p>
                            <input
                                type="text"
                                name="username"
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
                        <button onClick={this.onSubmit}>Zaloguj się</button>
                    </form>
                </div>
            );
        }
    }
}

export default Login;
