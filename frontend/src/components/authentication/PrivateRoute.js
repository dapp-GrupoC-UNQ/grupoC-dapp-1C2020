import React from "react";
import {Redirect, Route} from "react-router-dom";
import {LanguageContext} from "../../constants/LanguageMaps";

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
ProtectedRoute.contextType = LanguageContext;

export default ProtectedRoute