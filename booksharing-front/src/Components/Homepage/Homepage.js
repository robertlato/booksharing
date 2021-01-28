import React from "react";
import "./Homepage.css";
import MyMap from "../MyMap/MyMap";
import AuthenticationService from "../../service/AuthenticationService";

class Homepage extends React.Component {
    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        return (
            <div>
                <h1>Homepage</h1>
                {isUserLoggedIn ? (
                    <div> JEST ZALOGOWANY </div>
                ) : (
                    <div>NIE JEST ZALOGOWANY</div>
                )}
                <MyMap />
            </div>
        );
    }
}

export default Homepage;
