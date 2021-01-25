import React from "react";
import { Link } from "react-router-dom";

class Register extends React.Component {
    render() {
        return (
            <>
                <h1>Rejestracja</h1>
                <h2>
                    Masz już konto? <Link to="/auth/login">Zaloguj się</Link>
                </h2>
                <form>
                    <label>
                        <p>Login</p>
                        <input type="text" name="login" />
                    </label>
                    <label>
                        <p>Adres e-mail</p>
                        <input type="email" name="email" />
                    </label>
                    <label>
                        <p>Hasło</p>
                        <input type="password" name="password" />
                    </label>
                    <label>
                        <p>Powtórz hasło</p>
                        <input type="password" name="password2" />
                    </label>
                    <button>Rejestracja</button>
                </form>
            </>
        );
    }
}

export default Register;
