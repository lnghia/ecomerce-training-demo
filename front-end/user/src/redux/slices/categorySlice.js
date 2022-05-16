import { createSlice } from "@reduxjs/toolkit";

export const categorySlice = createSlice({
    name: 'category',
    initialState: {
        categoryList: [],
        sportList: [],
        technologyList: [],
        genderList: [],
        genderFilterValues: [],
        sportFilterValues: [],
        categoryFilterValues: [],
        technologyFilterValues: []
    },
    reducers: {
        updateCategoryListAction: (state, action) => {
            state.categoryList = action.payload;
        },
        updateSportListAction: (state, action) => {
            state.sportList = action.payload;
        },
        updateTechnologyListAction: (state, action) => {
            state.technologyList = action.payload;
        },
        updateGenderListAction: (state, action) => {
            state.genderList = action.payload;
        },
        updateGenderFilterValueAction: (state, action) => {
            let index = state.genderFilterValues.indexOf(action.payload);

            if (index !== -1) {
                state.genderFilterValues.splice(index, 1);
            } else {
                state.genderFilterValues.push(action.payload);
            }
        },
        updateSportFilterValueAction: (state, action) => {
            let index = state.sportFilterValues.indexOf(action.payload);
            
            if (index !== -1) {
                state.sportFilterValues.splice(index, 1);
            } else {
                state.sportFilterValues.push(action.payload);
            }
        },
        updateCategoryFilterValueAction: (state, action) => {
            let index = state.categoryFilterValues.indexOf(action.payload);
            
            if (index !== -1) {
                state.categoryFilterValues.splice(index, 1);
            } else {
                state.categoryFilterValues.push(action.payload);
            }
        },
        updateTechnologyFilterValueAction: (state, action) => {
            let index = state.technologyFilterValues.indexOf(action.payload);
            
            if (index !== -1) {
                state.technologyFilterValues.splice(index, 1);
            } else {
                state.technologyFilterValues.push(action.payload);
            }
        }
    }
})

export const {
    updateCategoryListAction,
    updateSportListAction,
    updateTechnologyListAction,
    updateGenderListAction,
    updateGenderFilterValueAction,
    updateSportFilterValueAction,
    updateCategoryFilterValueAction,
    updateTechnologyFilterValueAction
} = categorySlice.actions;

export default categorySlice.reducer;