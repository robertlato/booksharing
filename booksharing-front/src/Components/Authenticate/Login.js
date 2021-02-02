import React from "react";
import { Link, Redirect } from "react-router-dom";
import AuthenticationService from "../../service/AuthenticationService";

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            usernameErrorCheck: false,
            passwordErrorCheck: false,
            isLogged: false,
        };

        this.isInputFromUserValid = this.isInputFromUserValid.bind(this);
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value,
        });
    }

    isInputFromUserValid() {
        let usernameErrorCheck = false;
        let passwordErrorCheck = false;

        console.log("EO0");
        if (this.state.username.length < 6 || this.state.username.length > 32) {
            usernameErrorCheck = true;
            this.setState({ usernameErrorCheck: true });
            console.log("EO1 " + this.state.usernameErrorCheck);
        }
        if (this.state.password.length < 6 || this.state.password.length > 32) {
            passwordErrorCheck = true;
            this.setState({ passwordErrorCheck: true });
            console.log("EO1 " + this.state.passwordErrorCheck);
        }

        if (usernameErrorCheck || passwordErrorCheck) {
            return false;
        }

        return true;
    }

    async onSubmit(event) {
        event.preventDefault();

        if (this.isInputFromUserValid()) {
            AuthenticationService.executeBasicAuthenticationService(
                this.state.username,
                this.state.password
            )
                .then(() => {
                    AuthenticationService.registerSuccessfulLogin(
                        this.state.username,
                        this.state.password
                    );
                    console.log("dobre dane");
                })
                .then(() => this.setState({ isLogged: true }))
                .then(() => window.location.reload())
                .catch(() => {
                    console.log("złe dane");
                });
        }
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
                            {this.state.usernameErrorCheck && (
                                <div>Zły email</div>
                            )}
                        </label>
                        <label>
                            <p>Hasło</p>
                            <input
                                type="password"
                                name="password"
                                onChange={this.onChange}
                            />
                            {this.state.passwordErrorCheck && (
                                <div>Złe hasło</div>
                            )}
                        </label>
                        <button onClick={this.onSubmit}>Zaloguj się</button>
                    </form>
                </div>
            );
        }
    }
}

export default Login;
