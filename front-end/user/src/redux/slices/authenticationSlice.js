import { createSlice } from "@reduxjs/toolkit";

export const authenticationSlice = createSlice({
    name: 'loggedInIdentity',
    initialState: {
        loginSuccess: false,
        accessToken: "",
        refreshToken: "",
        openLoginModal: true
    },
    reducers: {
        loginAction: state => {
            state.loginSuccess = true;
            // state.accessToken = window.localStorage.getItem("accessToken");
            // state.refreshToken = window.localStorage.getItem("refreshToken");
        },
        logoutAction: state => {
            state.loginSuccess = false;
            window.localStorage.setItem("accessToken", "");
            window.localStorage.setItem("refreshToken", "");
        },
        showLoginModalAction: state => {
            state.openLoginModal = true;
        },
        closeLoginModalAction: state => {
            state.openLoginModal = false;
        }
    }
})

export const { loginAction, logoutAction, showLoginModalAction, closeLoginModalAction } = authenticationSlice.actions;

export default authenticationSlice.reducer;