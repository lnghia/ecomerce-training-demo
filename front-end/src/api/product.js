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
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}

export const fetchProductImages = async (id) => {
    try {
        let response = await AxiosClient.get("/product/images?id=" + toString(id));
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}

export const fetchProductDetail = async (id) => {
    try {
        let response = await AxiosClient.get("/product/" + id);
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}

export const fetchProductTechnologies = async (id) => {
    try {
        let response = await AxiosClient.get("/product/technology?id=" + id);
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return [];
    }
}

export const rateProduct = async (ratedProductId, review, stars) => {
    try {
        let response = await AxiosClient.post("/product/rate_product?productId=" + ratedProductId, {
            comment: review,
            rating: stars
        });
        let data = response.data.data;

        return true;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return false;
    }
}

export const findUserReviewOnProduct = async (productId) => {
    try {
        let response = await AxiosClient.get("/product/user_review_on_product?productId=" + productId);
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return null;
    }
}

export const fetchReviewsOnProduct = async (productId, page, size) => {
    try {
        let response = await AxiosClient.get(`/product/ratings?productId=${productId}&page=${page}&size=${size}`);
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return null;
    }
}

export const fetchProductList = async (genderFilters, sportFilters, technologyFilters, typeFilters, keyword) => {
    try {
        let response = await AxiosClient.post('/product/search', {
            name: keyword,
            genderId: genderFilters,
            sportId: sportFilters,
            categoryIds: typeFilters,
            technologyIds: technologyFilters
        });
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return null;
    }
}

export const fetchProductAll = async () => {
    try {
        let response = await AxiosClient.get('/product/all');
        let data = response.data.data;

        return data;
    } catch (error) {
        raiseErrorMessages(error.response.data.errors);

        return null;
    }
}

export { fetchFeaturedProducts };

