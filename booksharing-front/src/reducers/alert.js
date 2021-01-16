import { SET_ALERT, CLEAR_ALERTS, DELETE_ALERT } from "../actions/types";

const initialState = [];

export default function (state = initialState, action) {
    const { type, payload } = action;

    switch (type) {
        case SET_ALERT:
            return [...state, payload];
        case DELETE_ALERT:
            state.splice(payload, 1);
            return state;
        case CLEAR_ALERTS:
            return [];
        default:
            return state;
    }
}