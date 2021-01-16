import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { registerUser } from '../../actions/auth';
import { setAlert, clearAlerts, deleteAlert } from '../../actions/alert';
import { Link } from 'react-router-dom';

const Register = ({ registerUser, setAlert, clearAlerts, alert, deleteAlert }) => {
    const [formData, setFormData] = useState({
        login: '',
        email: '',
        password: '',
        password2: ''
    });

    const onChange = e => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
        let alertIndex = -1;
        alert.filter((item, index) => {
            if (item.param === e.target.name) alertIndex = index;
        });
        if (alertIndex > -1) deleteAlert(alertIndex);
    }

    const onSumbit = e => {
        e.preventDefault();
        clearAlerts();
        const { login, email, password, password2 } = formData;
        if (!login || !email || !password || !password2 || password !== password2) {
            if (!login) setAlert({ msg: 'Wprowadź login!', param: 'login' })
            if (!email) setAlert({ msg: 'Wprowadź e-mail!', param: 'email' })
            if (!password) setAlert({ msg: 'Wprowadź hasło!', param: 'password' })
            if (!password2) setAlert({ msg: 'Powtórz hasło!', param: 'password2' })
            if (password !== password2) setAlert({ msg: 'Hasła się różnią!', param: 'password2' });
            return;
        }
        registerUser({ login, email, password });
    }

    const loginAlert = alert.filter(item => item.param === 'login');
    const emailAlert = alert.filter(item => item.param === 'email');
    const passwordAlert = alert.filter(item => item.param === 'password');
    const password2Alert = alert.filter(item => item.param === 'password2');

    useEffect(() => {
        return () => {
            clearAlerts();
            window.scrollTo(0, 0);
        }
    }, [clearAlerts]);

    return (
        <>
            <h1>Rejestracja</h1>
            <h2>Masz już konto? <Link to="/auth/login">Zaloguj się</Link></h2>
            <form onSubmit={e => onSumbit(e)}>
                <label>
                    <p>Login</p>
                    <input type="text" name="login" className={loginAlert.length > 0 ? 'failed' : ''} onChange={e => onChange(e)} />
                    {loginAlert.length > 0 && <p className="input-warning">{loginAlert[0].msg}</p>}
                </label>
                <label>
                    <p>Adres e-mail</p>
                    <input type="email" name="email" className={emailAlert.length > 0 ? 'failed' : ''} onChange={e => onChange(e)} />
                    {emailAlert.length > 0 && <p className="input-warning">{emailAlert[0].msg}</p>}
                </label>
                <label>
                    <p>Hasło</p>
                    <input type="password" name="password" className={passwordAlert.length > 0 ? 'failed' : ''} onChange={e => onChange(e)} />
                    {passwordAlert.length > 0 && <p className="input-warning">{passwordAlert[0].msg}</p>}
                </label>
                <label>
                    <p>Powtórz hasło</p>
                    <input type="password" name="password2" className={password2Alert.length > 0 ? 'failed' : ''} onChange={e => onChange(e)} />
                    {password2Alert.length > 0 && <p className="input-warning">{password2Alert[0].msg}</p>}
                </label>
                <button>Rejestracja</button>
            </form>
        </>
    );
}

Register.propTypes = {
    registerUser: PropTypes.func.isRequired,
    setAlert: PropTypes.func.isRequired,
    clearAlerts: PropTypes.func.isRequired,
    deleteAlert: PropTypes.func.isRequired,
    alert: PropTypes.array.isRequired,
}

const mapStateToProps = state => ({
    alert: state.alert
})

export default connect(mapStateToProps, { registerUser, setAlert, clearAlerts, deleteAlert })(Register);