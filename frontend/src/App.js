import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import LoginPage from "./pages/loginpage/LoginPage";

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
