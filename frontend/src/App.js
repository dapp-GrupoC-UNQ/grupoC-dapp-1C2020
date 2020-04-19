import React from 'react';
import './scss/pages/homepage/homepage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import LoginPage from "./scss/pages/homepage/HomePage";

function App() {
  return (
        <BrowserRouter>
            <Switch>
                <Route
                    exact
                    path="/"
                    render={() => <LoginPage/>}
                />
            </Switch>
        </BrowserRouter>
  );
}

export default App;
