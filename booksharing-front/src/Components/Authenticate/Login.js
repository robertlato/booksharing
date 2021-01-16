import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { loginUser } from '../../actions/auth';
import { setAlert, clearAlerts, deleteAlert } from '../../actions/alert';
import { Link } from 'react-router-dom';

const Login = ({ alert, loginUser, setAlert, clearAlerts, deleteAlert }) => {
    const [formData, setFormData] = useState({
        login: '',
        password: ''
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
        const { login, password } = formData;
        if (!login || !password) {
            if (!login) setAlert({ msg: 'Wprowadź login!', param: 'login' })
            if (!password) setAlert({ msg: 'Wprowadź hasło!', param: 'password' })
            return;
        }
        loginUser({ login, password });
    }

    const loginAlert = alert.filter(item => item.param === 'login');
    const passwordAlert = alert.filter(item => item.param === 'password');
    const dataAlert = alert.filter(item => item.param === 'data');

    useEffect(() => {
        return () => {
            clearAlerts();
            window.scrollTo(0, 0);
        }
    }, [clearAlerts]);

    return (
        <>
            <h1>Logowanie</h1>
            <h2>Nie masz konta? <Link to="/auth/register">Zarejestruj się</Link></h2>
            <form onSubmit={e => onSumbit(e)}>
                <label>
                    <p>Login</p>
                    <input type="text" name="login" className={loginAlert.length > 0 ? 'failed' : ''} onChange={e => onChange(e)} />
                    {loginAlert.length > 0 && <p className="input-warning">{loginAlert[0].msg}</p>}
                </label>
                <label>
                    <p>Hasło</p>
                    <input type="password" name="password" className={passwordAlert.length > 0 ? 'failed' : ''} onChange={e => onChange(e)} />
                    {passwordAlert.length > 0 && <p className="input-warning">{passwordAlert[0].msg}</p>}
                </label>
                <button>Zaloguj się</button>
                {dataAlert.length > 0 && <div className="bad-login">{dataAlert[0].msg}</div>}
            </form>
        </>
    );
}

Login.propTypes = {
    loginUser: PropTypes.func.isRequired,
    setAlert: PropTypes.func.isRequired,
    clearAlerts: PropTypes.func.isRequired,
    deleteAlert: PropTypes.func.isRequired,
    alert: PropTypes.array.isRequired,
}

const mapStateToProps = state => ({
    alert: state.alert
});

export default connect(mapStateToProps, { loginUser, setAlert, clearAlerts, deleteAlert })(Login);
