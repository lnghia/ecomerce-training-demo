import { createSlice } from "@reduxjs/toolkit";

export const authenticationSlice = createSlice({
    name: 'loggedInIdentity',
    initialState: {
        loginSuccess: false,
        accessToken: "",
        refreshToken: "",
    },
    reducers: {
        loginAction: state => {
            state.loginSuccess = true;
            state.accessToken = window.localStorage.getItem("accessToken");
            state.refreshToken = window.localStorage.getItem("refreshToken");
        },
        logoutAction: state => {
            state.loginSuccess = false;
            state.accessToken = "";
            state.refreshToken = "";
        }
    }
})

export const {loginAction, logoutAction} = authenticationSlice.actions;

export default authenticationSlice.reducer;