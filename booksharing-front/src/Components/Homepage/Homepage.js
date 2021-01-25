import React from "react";
import "./Homepage.css";
import MyMap from "../MyMap/MyMap";

class Homepage extends React.Component {
    render() {
        return (
            <div>
                <h1>Homepage</h1>
                <MyMap />
            </div>
        );
    }
}

export default Homepage;
