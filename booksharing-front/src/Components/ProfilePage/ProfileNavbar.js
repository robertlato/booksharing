import React from "react";
import { NavLink } from "react-router-dom";
import "./ProfileNavbar.css";

class ProfileNavbar extends React.Component {
    render() {
        return (
            <nav className="profile-navbar">
                <div className="profile-navbar-list-container">
                    <ul className="profile-navbar-list">
                        <li>
                            <NavLink
                                to="/myborrowedbooks"
                                activeClassName="active"
                            >
                                Wypożyczone książki
                            </NavLink>
                        </li>
                        <li>
                            <NavLink to="/mylibrary" activeClassName="active">
                                Moja biblioteka
                            </NavLink>
                        </li>
                        <li>
                            <NavLink to="/addbook" activeClassName="active">
                                Dodaj książkę
                            </NavLink>
                        </li>
                        <li>
                            <NavLink
                                to="/profilesettings"
                                activeClassName="active"
                            >
                                Ustawienia konta
                            </NavLink>
                        </li>
                        <li>
                            <NavLink to="/deleteaccount" activeClassName="active">
                                Usuń konto
                            </NavLink>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}

export default ProfileNavbar;
