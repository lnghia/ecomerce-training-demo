import axios from "axios";
import React from "react";
// import { fetch } from 

export const AxiosAdminClient = axios.create({
    baseURL: "http://localhost:8080/api/admin"
});

export const AxiosUserClient = axios.create({
    baseURL: "http://localhost:8080/api"
});

AxiosAdminClient.interceptors.request.use(
    config => {
        const accessToken = window.localStorage.getItem("accessToken");
        const refreshToken = window.localStorage.getItem("refreshToken");

        if (accessToken) {
            config.headers['Authorization'] = 'Bearer ' + accessToken;
        }
        if (refreshToken) {
            config.headers['Refresh-Token'] = refreshToken;
        }
        config.headers['Content-Type'] = 'application/json';

        return config;
    },
    error => {
        Promise.reject(error);
    }
)

AxiosUserClient.interceptors.request.use(
    config => {
        const accessToken = window.localStorage.getItem("accessToken");
        const refreshToken = window.localStorage.getItem("refreshToken");

        if (accessToken) {
            config.headers['Authorization'] = 'Bearer ' + accessToken;
        }
        if (refreshToken) {
            config.headers['Refresh-Token'] = refreshToken;
        }
        config.headers['Content-Type'] = 'application/json';

        return config;
    },
    error => {
        Promise.reject(error);
    }
)
