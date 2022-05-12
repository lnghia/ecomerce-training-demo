import { AxiosAdminClient } from '../AxiosClient';
import toastr from 'toastr';

export const fetchActiveUsers = async () => {
    try {
        let response = await AxiosAdminClient.get('/users/active');
        let data = response.data.data;

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const fetchInActiveUsers = async () => {
    try {
        let response = await AxiosAdminClient.get('/users/de_active');
        let data = response.data.data;

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const fetchRoles = async () => {
    try {
        let response = await AxiosAdminClient.get('/roles');
        let data = response.data.data;

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const assignUserRole = async (_userId, _roleId) => {
    try {
        let response = await AxiosAdminClient.post('/users/assign_role', {
            userId: _userId,
            roleId: _roleId
        });

        return response.data.data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const disableUser = async (_userId) => {
    try {
        let response = await AxiosAdminClient.put(`/users/de_active?id=${_userId}`);

        return response.data.data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const activateUser = async (_userId) => {
    try {
        let response = await AxiosAdminClient.put(`/users/active?id=${_userId}`);

        return response.data.data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}