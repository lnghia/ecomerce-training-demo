import AxiosClient from "../axios/AxiosClient";

import { raiseErrorMessages } from "../utils/errorUtil";

const fetchFeaturedProducts = async () => {
    try {
        let response = await AxiosClient.post("/product/search", {
            searchType: "desc",
            searchBy: "average_rating",
            size: 8
        });
        let data = response.data.data;

        return data;
    }
    catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}

// const fetchProductList = async () => {
//     var loginSuccess = false;

//     await AxiosClient.post("/auth/login", {
//         username: email,
//         password: inputPassword
//     })
//         .then((response) => {
//             let accessToken = response.data.accessToken;
//             let refreshToken = response.data.refreshToken;
//             window.localStorage.setItem('accessToken', accessToken);
//             window.localStorage.setItem('refreshToken', refreshToken);
//             loginSuccess = true;
//         })
//         .catch((error) => {
//             raiseErrorMessages(error.response.data.errors);
//             loginSuccess = false;
//         });

//     return loginSuccess;
// }

export { fetchFeaturedProducts };
