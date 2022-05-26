import AxiosClient from "../axios/AxiosClient";
import toastr from 'toastr';

const login = async (email, inputPassword) => {
    var loginSuccess = false;

    await AxiosClient.post("/auth/login", {
        username: email,
        password: inputPassword
    })
        .then((response) => {
            let accessToken = response.data.data.accessToken;
            let refreshToken = response.data.data.refreshToken;
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

const registerApi = async (_email, _password, _firstName, _lastName) => {
    try {
        let response = await AxiosClient.post('/auth/register', {
            email: _email,
            password: _password,
            firstName: _firstName,
            lastName: _lastName
        });

        toastr.success('Success', 'Register successfully.');

        return response.data.data;
    } catch (error) {
        let errors = error.response.data.errors;

        Object.keys(errors).forEach(key => {
            toastr.error(errors[key], key);
        });

        return null;
    }
}

export { login, registerApi };

