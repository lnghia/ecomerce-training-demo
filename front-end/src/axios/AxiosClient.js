import axios from "axios";
import React from "react";

const AxiosClient = axios.create({
    baseURL: "http://localhost:8080/api"
});

AxiosClient.interceptors.request.use(
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

export default AxiosClient;