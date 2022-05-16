import axios from "axios";
import React from "react";
// import { fetch } from 

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

// const refreshTokenFetchResult = null;

// const fetchUpdateTokens = async () => {
//     try {
//         refreshTokenFetchResult = await AxiosClient.put("/auth/refresh_tokens");
//     } catch (error) {
//         refreshTokenFetchResult - null;
//     }
// }

// AxiosClient.interceptors.response.use(
//     config => {
//         return response;
//     },
//     error => {
//         return new Promise(resolve => {
//             const originalRequest = error.config;
//             const refreshToken = window.localStorage.getItem('refreshToken');
//             if (error.response && error.response.status === 401 && error.config && !error.config.__isRetryRequest && refreshToken) {
//                 originalRequest._retry = true

//                 const response = fetch("http://localhost:8080/api/auth/refresh_tokens", {
//                     method: 'POST',
//                     headers: {
//                         'Content-Type': 'application/json',
//                         'Refresh-Token': refreshToken
//                     }
//                 })
//                     .then((res) => res.json())
//                     .then((res) => {
//                         localStorage.set(res.access, 'token')

//                         return axios(originalRequest)
//                     })
//                 resolve(response)
//             }

//             return Promise.reject(error)
//         })
//     }
// )

export default AxiosClient;