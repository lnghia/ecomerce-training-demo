import AxiosClient from '../AxiosClient';
import toastr from 'toastr';

export const fetchProductAllApi = async () => {
    try {
        let response = await AxiosClient.post('/product/search', {
            name: "",
            genderId: [],
            sportId: [],
            categoryIds: [],
            technologyIds: []
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
        let response = await AxiosClient.get("/category");
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
        let response = await AxiosClient.get("/gender");
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
        let response = await AxiosClient.get("/sport");
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
        let response = await AxiosClient.get("/technology");
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

        let response = await AxiosClient.delete('/product/delete', { data });

        return true;
    } catch (error) {
        // toastr.error(error.response.data.errors);

        return false;
    }
}

export const fetchSizesApi = async () => {
    try {
        let response = await AxiosClient.get('/size');

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

        let response = await AxiosClient.post('/product/create', {
            genderId: product.gender,
            name: product.name,
            description: product.description,
            price: product.price,
            year: product.year,
            sportId: product.sport,
            technologyIds: product.technologies,
            categoryIds: product.categories,
            productSizeDtoList: product.productSizeDtoList
        });

        return response.data.data.id;
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
            genderId: product.gender,
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
            productId: product.id
        }

        console.log(data);

        let response = await AxiosClient.put('/product/update', data);

        return true;
    } catch (error) {
        console.log(error.data);

        if (error.response !== null) {
            toastr.error(error.response.data.errors);
        } else {
            toastr.error(error.message);
        }

        return false;
    }
}