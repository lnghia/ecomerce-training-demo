import AxiosClient from "../axios/AxiosClient";

import { raiseErrorMessages } from "../utils/errorUtil";

export const fetchCategories = async () => {
    try {
        let response = await AxiosClient.get("/category");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}