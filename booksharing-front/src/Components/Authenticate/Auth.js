import React from "react";
import "./Auth.css";
import { Route } from "react-router-dom";
import Login from "./Login";
import Register from "./Register";

class Auth extends React.Component {
    render() {
        return (
            <div className="min-container">
                <div className="auth-container">
                    <div className="auth-background"></div>
                    <div className="auth-form">
                        <Route
                            path="/login"
                            exact
                            component={Login}
                            changeIsLogged={this.props.changeIsLogged}
                        />
                        <Route path="/register" exact component={Register} />
                    </div>
                </div>
            </div>
        );
    }
}

export default Auth;
