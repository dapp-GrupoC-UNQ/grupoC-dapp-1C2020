import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import LoginPage from "./pages/loginpage/LoginPage";
import HomePage from "./pages/homepage/HomePage";

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
            <Switch>
                <Route
                    exact
                    path="/homepage"
                    render={() => <HomePage/>}
                />
            </Switch>
        </BrowserRouter>
  );
}

export default App;
