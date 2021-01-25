import React from "react";
import "./App.css";
import Navbar from "./Components/Navbar/Navbar";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Homepage from "./Components/Homepage/Homepage";
import AboutUs from "./Components/AboutUs/AboutUs";
import Faq from "./Components/Faq/Faq";
import Auth from "./Components/Authenticate/Auth";

class App extends React.Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <Navbar />
                    <Switch>
                        <Route path="/" exact component={Homepage} />
                        <Route path="/faq" component={Faq} />
                        <Route path="/aboutus" component={AboutUs} />
                        <Route path="/auth" component={Auth} />
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default App;
