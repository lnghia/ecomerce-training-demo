import AxiosClient from "../axios/AxiosClient";

import { raiseErrorMessages } from "../utils/errorUtil";

export const fetchTechnologies = async () => {
    try {
        let response = await AxiosClient.get("/technology");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}