import React from "react";
import "./App.css";
import Navbar from "./Components/Navbar/Navbar";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Homepage from "./Components/Homepage/Homepage";
import AboutUs from "./Components/AboutUs/AboutUs";
import Faq from "./Components/Faq/Faq";
import ProfilePage from "./Components/ProfilePage/ProfilePage";
import AuthenticatedRoute from "./Components/Authenticate/AuthenticatedRoute";
import Logout from "./Components/Logout/Logout";
import Auth from "./Components/Authenticate/Auth";
import Welcome from "./Components/Welcome/Welcome";

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
                        <Route path="/login" component={Auth} />
                        <Route path="/register" component={Auth} />
                        <Route path="/welcome" component={Welcome} />
                        <AuthenticatedRoute
                            path="/logout"
                            exact
                            component={Logout}
                        />
                        <AuthenticatedRoute
                            path="/profile"
                            exact
                            component={ProfilePage}
                        />
                        <AuthenticatedRoute
                            path="/searchbook"
                            exact
                            component={ProfilePage}
                        />
                        <AuthenticatedRoute
                            path="/mylibrary"
                            exact
                            component={ProfilePage}
                        />
                        <AuthenticatedRoute
                            path="/addbook"
                            exact
                            component={ProfilePage}
                        />
                        <AuthenticatedRoute
                            path="/profilesettings"
                            exact
                            component={ProfilePage}
                        />
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default App;
