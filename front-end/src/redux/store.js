import { configureStore } from '@reduxjs/toolkit';
import storage from "redux-persist/lib/storage";
import { combineReducers } from "redux";
import { persistReducer, persistStore } from "redux-persist";

import authenticationReducer from './slices/authenticationSlice';

const persistConfig = {
    key: 'root',
    storage
}

const rootReducer = combineReducers({
    authentication: authenticationReducer
});

const persistedReducer = persistReducer(persistConfig, rootReducer); 

const store = configureStore({
    reducer: persistedReducer
})

export const persistor = persistStore(store);

export default store;