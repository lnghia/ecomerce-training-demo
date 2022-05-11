export const loginSuccessSelector = state => state.authentication.loginSuccess;

export const productListSelector = state => state.product.productList;

export const selectedProductIdSelector = state => state.product.selectedProduct;

export const openLoginModalSelector = state => state.authentication.openLoginModal;

export const categoryListSelector = state => state.category.categoryList;

export const sportListSelector = state => state.category.sportList;

export const technologyListSelector = state => state.category.technologyList;

export const genderListSelector = state => state.category.genderList;

export const genderFilterValuesSelector = state => state.category.genderFilterValues;

export const sportFilterValuesSelector = state => state.category.sportFilterValues;

export const categoryFilterValuesSelector = state => state.category.categoryFilterValues;

export const technologyFilterValuesSelector = state => state.category.technologyFilterValues;

export const onRefreshReviewsSelector = state => state.product.onRefreshReviewsSelector;
