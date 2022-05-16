import React from 'react';
import { Redirect } from 'react-router-dom';
import { Route } from "react-router-dom";

const ProtectedRoute = ({ component: Component, path, ...render }) => {
    return (
        <Route
            path={path}
            {...render}
            render={() =>
                localStorage.getItem('isLogin') === 'true' ? <Component /> : <Redirect to="/login" />
            }
        />
    );
};

export default ProtectedRoute;