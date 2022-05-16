import { createSlice } from "@reduxjs/toolkit";

import { fetchFeaturedProducts } from "../../api/product";

export const productSlice = createSlice({
    name: 'products',
    initialState: {
        productList: [],
        selectedProduct: null,
        refreshReviews: false
    },
    reducers: {
        fetchProductList: state => {
            state.productList = fetchFeaturedProducts();
        },
        // fetchFeaturedProductList: state => {

        // }
        selectProductAction: (state, action) => {
            state.selectedProduct = action.payload;
        },
        onRefreshReviewsAction: (state, action) => {
            state.refreshReviews = action.payload;
        }
    }
})

export const { fetchProductList, selectProductAction, onRefreshReviewsAction } = productSlice.actions;

export default productSlice.reducer;