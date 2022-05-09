import { configureStore } from '@reduxjs/toolkit';
import storage from "redux-persist/lib/storage";
import { combineReducers } from "redux";
import { persistReducer, persistStore } from "redux-persist";

import authenticationReducer from './slices/authenticationSlice';
import productReducer from './slices/productSlice';
import categoryReducer from './slices/categorySlice';

const persistConfig = {
    key: 'root',
    storage
}

const rootReducer = combineReducers({
    authentication: authenticationReducer,
    product: productReducer,
    category: categoryReducer
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
    reducer: persistedReducer
})

export const persistor = persistStore(store);

export default store;