import React, { useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import './Homepage.css';
import MyMap from '../MyMap/MyMap'
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

const Homepage = () => {
    useEffect(() => {
        return () => {
            window.scrollTo(0, 0);
        }
    }, []);

    return (
        <div>
            <h1>Homepage</h1>
            <MyMap />
        </div>
    );
}

export default Homepage;