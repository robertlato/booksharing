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
                        <Route path="/auth/login" exact component={Login} />
                        <Route
                            path="/auth/register"
                            exact
                            component={Register}
                        />
                    </div>
                </div>
            </div>
        );
    }
}

export default Auth;
