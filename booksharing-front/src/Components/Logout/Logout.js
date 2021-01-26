import React from "react";
import "./Logout.css";
import AuthenticationService from "../../service/AuthenticationService";

class Logout extends React.Component {
    componentDidMount() {
        AuthenticationService.logout();
    }

    render() {
        return (
            <div>
                <h1>You are logged out</h1>
                <h3>Thank You for Using Our Application.</h3>
            </div>
        );
    }
}

export default Logout;
