import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import LoginPage from "./pages/loginpage/LoginPage";
import HomePage from "./pages/homepage/HomePage";
import ProtectedRoute from "./components/authentication/PrivateRoute";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedUser: false
        }
    }

    logInUser = () => this.setState({loggedUser: true})

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
                    <ProtectedRoute path='/homepage' loggedIn={this.state.loggedUser} component={HomePage} />
                </Switch>
            </BrowserRouter>
        );
    }
}

export default App;
