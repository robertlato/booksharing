  import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import './Auth.css';
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import Login from './Login';
import Register from './Register';


const Auth = ({ isAuthenticated }) => {
    useEffect(() => {
        return () => {
            window.scrollTo(0, 0);
        }
    }, [])

    if (isAuthenticated) {
        return <Redirect to="/profile" />
    }

    return (
        <div className="min-container">
            <div className="auth-container">
                <div className="auth-background">
                </div>
                <div className="auth-form">
                    <Route path="/auth/login" exact component={Login} />
                    <Route path="/auth/register" exact component={Register} />
                </div>
            </div>
        </div>
    );
}

Auth.propTypes = {
    isAuthenticated: PropTypes.bool,
}

const mapStateToProps = state => ({
    isAuthenticated: state.auth.isAuthenticated,
})

export default connect(mapStateToProps)(Auth);