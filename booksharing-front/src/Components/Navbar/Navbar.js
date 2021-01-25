import React from "react";
import logo from "../../images/logo.jpg";
import { Link, NavLink } from "react-router-dom";
import "./Navbar.css";

class Navbar extends React.Component {
    render() {
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
                    {/* {auth.isAuthenticated ? (
                    <Link to="/auth/profile">Mój profil</Link>
                ) : (
                        <Link to="/auth/login">Zaloguj się</Link>
                    )} */}
                    <Link to="/auth/login">Zaloguj się</Link>
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
                        <li>
                            <NavLink
                                exact
                                to="/aboutus"
                                activeClassName="active"
                            >
                                O nas
                            </NavLink>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}

export default Navbar;
