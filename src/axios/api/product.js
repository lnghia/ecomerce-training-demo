import { AxiosUserClient, AxiosAdminClient } from '../AxiosClient';
import toastr from 'toastr';

export const fetchProductAllApi = async () => {
    try {
        let response = await AxiosUserClient.post('/product/search', {
            name: "",
            genderId: [],
            sportId: [],
            categoryIds: [],
            technologyIds: [],
            size: 1000
        });
        let data = response.data.data;

        return data;
    } catch (error) {
        toastr.error(error.response.data.errors);

        return null;
    }
}

export const fetchCategories = async () => {
    try {
        let response = await AxiosUserClient.get("/category");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        toastr.error(error.response.data.errors);

        return [];
    }
}

export const fetchGenders = async () => {
    try {
        let response = await AxiosUserClient.get("/gender");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        toastr.error(error.response.data.errors);

        return [];
    }
}

export const fetchSports = async () => {
    try {
        let response = await AxiosUserClient.get("/sport");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        toastr.error(error.response.data.errors);

        return [];
    }
}

export const fetchTechnologies = async () => {
    try {
        let response = await AxiosUserClient.get("/technology");
        let data = response.data.data;

        return data;
    }
    catch (error) {
        toastr.error(error.response.data.errors);

        return [];
    }
}

export const deleteProductApi = async (id) => {
    console.log(typeof id, id);

    try {
        const data = {
            productId: id
        }

        let response = await AxiosAdminClient.delete('/products/delete', { data });

        return true;
    } catch (error) {
        // toastr.error(error.response.data.errors);

        return false;
    }
}

export const fetchSizesApi = async () => {
    try {
        let response = await AxiosUserClient.get('/size');

        return response.data.data;
    } catch (error) {
        if (error.response !== null) {
            toastr.error(error.response.data.errors);
        } else {
            toastr.error(error.message);
        }
    }
}

export const createProductApi = async (product) => {
    try {
        const data = {
            genderId: product.gender,
            name: product.name,
            description: product.description,
            price: product.price,
            year: product.year,
            sportId: product.sport,
            technologyIds: product.technologyIds,
            categoryIds: product.categories,
            productSizeDtoList: product.productSizeDtoList
        }

        console.log(data);

        let response = await AxiosAdminClient.post('/products/create', {
            genderId: product.gender,
            name: product.name,
            description: product.description,
            price: product.price,
            year: product.year,
            sportId: product.sport,
            technologyIds: product.technologies,
            categoryIds: product.categories,
            productSizeDtoList: product.productSizeDtoList,
            thumbnail: product.thumbnail
        });

        return response.data.data;
    } catch (error) {
        if (error.response !== null) {
            toastr.error(error.response.data.errors);
        } else {
            toastr.error(error.message);
        }

        return null;
    }
}

export const updateProductApi = async (product) => {
    try {
        console.log(product);

        const data = {
            genderId: product.gender.id !== undefined ? product.gender.id : product.gender,
            name: product.name,
            description: product.description,
            price: product.price,
            year: product.year,
            sportId: product.sport.id,
            technologyIds: product.technologies.map(item => {
                if (item.id !== undefined) {
                    return item.id;
                }

                return item;
            }),
            categoryIds: product.categories.map(item => {
                if (item.id !== undefined) {
                    return item.id;
                }

                return item;
            }),
            productSizeDtoList: product.sizes.map(item => {
                return {
                    sizeId: item.size.id,
                    number: item.inStock
                }
            }),
            productId: product.id,
            thumbnail: product.thumbnail
        }

        console.log(data);

        let response = await AxiosAdminClient.put('/products/update', data);

        return response.data.data;
    } catch (error) {
        console.log(error.data);

        if (error.response !== null) {
            toastr.error(error.response.data.errors);
        } else {
            toastr.error(error.message);
        }

        return null;
    }
}