import { createSlice } from "@reduxjs/toolkit";

export const authenticationSlice = createSlice({
    name: 'loggedInIdentity',
    initialState: {
        loginSuccess: false
    },
    reducers: {
        loginAction: state => {
            state.loginSuccess = true;
        },
        logoutAction: state => {
            state.loginSuccess = false;
        }
    }
})

export const {loginAction, logoutAction} = authenticationSlice.actions;

export default authenticationSlice.reducer;