import { createSlice } from "@reduxjs/toolkit";

import { fetchFeaturedProducts } from "../../api/product";

export const productSlice = createSlice({
    name: 'products',
    initialState: {
        productList: []
    },
    reducers: {
        fetchProductList: state => {
            state.productList = fetchFeaturedProducts();
        },
        // fetchFeaturedProductList: state => {

        // }
    }
})

export const { fetchProductList } = productSlice.actions;

export default productSlice.reducer;