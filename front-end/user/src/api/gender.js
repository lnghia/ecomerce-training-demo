import AxiosClient from "../axios/AxiosClient";

import { raiseErrorMessages } from "../utils/errorUtil";

export const fetchGenders = async () => {
    try {
        let response = await AxiosClient.get("/gender");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}