import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import LoginPage from "./pages/loginpage/LoginPage";
import HomePage from "./pages/homepage/HomePage";
import ProtectedRoute from "./components/authentication/PrivateRoute";
import {LanguageContext, LanguageMaps} from "./constants/LanguageMaps";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedUser: JSON.parse(localStorage.getItem('loggedUser')) || false,
            language: LanguageMaps.spanish
        }
    }

    logInUser = () => {
        this.setState({loggedUser: true})
        localStorage.setItem('loggedUser', true)
    }
    logOut = () => {
        this.setState({loggedUser: false})
        localStorage.setItem('loggedUser', false)
    }

    render() {
        return (
                <BrowserRouter>
                    <Switch>
                        <Route
                            exact
                            path="/"
                            render={() => <LoginPage onLogin={this.logInUser}/>}
                        />
                    </Switch>
                    <Switch>
                        <LanguageContext.Provider value={this.state.language}>
                            <ProtectedRoute path='/homepage' loggedIn={this.state.loggedUser} logOut={this.logOut} component={HomePage} />
                        </LanguageContext.Provider>
                    </Switch>
                </BrowserRouter>
        );
    }
}

export default App;
