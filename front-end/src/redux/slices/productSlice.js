import { createSlice } from "@reduxjs/toolkit";

import { fetchFeaturedProducts } from "../../api/product";

export const productSlice = createSlice({
    name: 'products',
    initialState: {
        productList: [],
        selectedProduct: null
    },
    reducers: {
        fetchProductList: state => {
            state.productList = fetchFeaturedProducts();
        },
        // fetchFeaturedProductList: state => {

        // }
        selectProductAction: (state, action) => {
            state.selectedProduct = action.payload; 
        }
    }
})

export const { fetchProductList, selectProductAction } = productSlice.actions;

export default productSlice.reducer;