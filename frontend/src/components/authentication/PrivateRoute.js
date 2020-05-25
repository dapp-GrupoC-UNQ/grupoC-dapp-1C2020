import React from "react";
import {Redirect, Route} from "react-router-dom";

const ProtectedRoute = ({ component: Comp, loggedIn, logOut, path, ...rest }) => {
    return (
        <Route
            path={path}
            {...rest}
            render={props => {
                return loggedIn ? <Comp {...props} logOut={logOut}/> : <Redirect to="/" />;
            }}
        />
    );
};

export default ProtectedRoute