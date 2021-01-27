import React from "react";
import "./Logout.css";
import AuthenticationService from "../../service/AuthenticationService";

class Logout extends React.Component {
    componentDidMount() {
        AuthenticationService.logout();
        window.location.reload();
    }

    render() {
        return <div></div>;
    }
}

export default Logout;
