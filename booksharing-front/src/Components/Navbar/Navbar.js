import React from "react";
import logo from "../../images/logo.jpg";
import { Link, NavLink } from "react-router-dom";
import "./Navbar.css";
import AuthenticationService from "../../service/AuthenticationService";

class Navbar extends React.Component {
    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();

        return (
            <nav className="navbar">
                <Link to="/" className="navbar-logo-container">
                    <img
                        src={logo}
                        alt="Fresh Air Community"
                        className="navbar-logo"
                    />
                </Link>
                <div className="navbar-login">
                    {isUserLoggedIn ? (
                        <div>
                            <p>
                                <b>
                                    {AuthenticationService.getLoggedInUserName()}
                                </b>
                            </p>
                            <Link to="/profile">Mój profil</Link>
                            <Link to="/logout">Wyloguj</Link>
                        </div>
                    ) : (
                        <div>
                            <Link to="/login">Zaloguj się</Link>
                            <Link to="/register">Utwórz konto</Link>
                        </div>
                    )}
                </div>
                <div className="navbar-list-container">
                    <ul className="navbar-list">
                        <li>
                            <NavLink exact to="/" activeClassName="active">
                                Strona Główna
                            </NavLink>
                        </li>
                        <li>
                            <NavLink to="/faq" activeClassName="active">
                                FAQ
                            </NavLink>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}

export default Navbar;
