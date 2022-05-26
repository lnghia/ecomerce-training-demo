import { AxiosUserClient } from '../AxiosClient';
import toastr from 'toastr';


const login = async (email, inputPassword) => {
    let loginSuccess = false;

    try {
        let response = await AxiosUserClient.post("/auth/login_admin", {
            username: email,
            password: inputPassword
        });

        let accessToken = response.data.data.accessToken;
        let refreshToken = response.data.data.refreshToken;
        window.localStorage.setItem('accessToken', accessToken);
        window.localStorage.setItem('refreshToken', refreshToken);
        window.localStorage.setItem('isLogin', true);

        return true;
    } catch (error) {
        let errors = error.response.data.errors;

        window.localStorage.setItem('isLogin', false);

        Object.keys(errors).forEach(key => {
            toastr.error(errors[key], key);
        });

        return false;
    }
}

export { login };

