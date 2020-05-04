import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import LoginPage from "./pages/loginpage/LoginPage";
import HomePage from "./pages/homepage/HomePage";

class App extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            shoppingCart: {
                products: []
            }
        }
    }

    addToShoppingCart = (product) => {
        const newShoppingCart = this.state.shoppingCart.products.concat(product);

        this.setState({shoppingCart: newShoppingCart})
    }

    render() {
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
                        render={() => <HomePage onAddToShoppingCart={(product) => this.addToShoppingCart(product)}/>}
                    />
                </Switch>
            </BrowserRouter>
        );
    }
}

export default App;
