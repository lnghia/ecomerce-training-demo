import AxiosClient from "../axios/AxiosClient";
import toastr from 'toastr';

const login = async (email, inputPassword) => {
    var loginSuccess = false;

    await AxiosClient.post("/auth/login", {
        username: email,
        password: inputPassword
    })
        .then((response) => {
            let accessToken = response.data.accessToken;
            let refreshToken = response.data.refreshToken;
            window.localStorage.setItem('accessToken', accessToken);
            window.localStorage.setItem('refreshToken', refreshToken);
            loginSuccess = true;
        })
        .catch((error) => {
            let errors = error.response.data.errors;

            Object.keys(errors).forEach(key => {
                toastr.error(errors[key], key);
            });
            loginSuccess = false;
        });

    return loginSuccess;
}

export { login };

