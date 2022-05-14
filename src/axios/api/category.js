import { AxiosUserClient, AxiosAdminClient } from '../AxiosClient';
import toastr from 'toastr';

export const updateCategoryApi = async (categoryType, categoryId, _name, _description) => {
    try {
        let response = await AxiosAdminClient.put(`/${categoryType}?id=${categoryId}`, {
            name: _name,
            description: _description
        });
        let data = response.data.data;

        console.log(data);

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const createCategoryApi = async (categoryType, _name, _description) => {
    try {
        let response = await AxiosAdminClient.post(`/${categoryType}`, {
            name: _name,
            description: _description
        });
        let data = response.data.data;

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const fetchCategoriesByType = async (categoryType) => {
    try {
        let response = await AxiosUserClient.get(`/${categoryType}`);
        let data = response.data.data;

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}