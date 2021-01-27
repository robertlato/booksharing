import React from "react";
import "./ProfilePage.css";
import AuthenticatedRoute from "../../Components/Authenticate/AuthenticatedRoute";
import ProfileNavbar from "./ProfileNavbar";
import SearchBook from "./ProfilePageComponents/SearchBook/SearchBook";
import AddBook from "./ProfilePageComponents/AddBook/AddBook";
import MyLibrary from "./ProfilePageComponents/MyLibrary/MyLibrary";
import ProfileSettings from "./ProfilePageComponents/ProfileSettings/ProfileSettings";

class ProfilePage extends React.Component {
    render() {
        return (
            <div className="AppProfile">
                <ProfileNavbar />
                <AuthenticatedRoute
                    path="/searchbook"
                    exact
                    component={SearchBook}
                />
                <AuthenticatedRoute
                    path="/mylibrary"
                    exact
                    component={MyLibrary}
                />
                <AuthenticatedRoute path="/addbook" exact component={AddBook} />
                <AuthenticatedRoute
                    path="/profilesettings"
                    exact
                    component={ProfileSettings}
                />
            </div>
        );
    }
}

export default ProfilePage;
