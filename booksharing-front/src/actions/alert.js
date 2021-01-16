import { SET_ALERT, CLEAR_ALERTS, DELETE_ALERT } from "./types"


export const setAlert = payload => dispatch => {
    dispatch({
        type: SET_ALERT,
        payload
    })
}

export const deleteAlert = id => dispatch => {
    dispatch({
        type: DELETE_ALERT,
        payload: id
    })
}

export const clearAlerts = () => dispatch => {
    dispatch({
        type: CLEAR_ALERTS
    })
}