import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import './encuarentenados-app.scss'
import LoginPage from "./pages/loginpage/LoginPage";
import HomePage from "./pages/homepage/HomePage";
import ProtectedRoute from "./components/authentication/PrivateRoute";
import {LanguageContext, LanguageMaps} from "./constants/LanguageMaps";
import SideBar from "./pages/homepage/side-bar/SideBar";

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

    changeLanguage = (language) => this.setState({language: language})

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
                    <React.Fragment>
                        <LanguageContext.Provider value={this.state.language}>
                            <div className='encuarentena2'>
                                <SideBar changeLanguage={this.changeLanguage}/>
                                <Switch>
                                        <ProtectedRoute path='/homepage'
                                                        loggedIn={this.state.loggedUser}
                                                        changeLanguage={this.changeLanguage}
                                                        logOut={this.logOut}
                                                        component={HomePage}
                                        />
                                </Switch>
                            </div>
                        </LanguageContext.Provider>
                    </React.Fragment>
                </BrowserRouter>
        );
    }
}

export default App;
