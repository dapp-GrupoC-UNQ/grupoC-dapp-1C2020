import React from 'react';
import './pages/loginpage/loginpage.scss';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import './encuarentenados-app.scss'
import LoginPage from "./pages/loginpage/LoginPage";
import HomePage from "./pages/homepage/Stores";
import ProtectedRoute from "./components/authentication/PrivateRoute";
import {LanguageContext, LanguageMaps} from "./constants/LanguageMaps";
import SideBar from "./pages/homepage/side-bar/SideBar";
import StoreProducts from "./pages/homepage/store/StoreProducts";
import ShoppingCart from "./pages/homepage/ShoppingCart/ShoppingCart";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            loggedUser: JSON.parse(localStorage.getItem('loggedUser')) || false,
            language: LanguageMaps.spanish,
            productsInCart: []
        }
    }

    addProductToCart = (product) => {
        product.quantity = 1
        this.setState({productsInCart: [...this.state.productsInCart, product]})
    }

    removeProductFromCart = (product) => {
        let updatedProducts = this.state.productsInCart;
        updatedProducts = updatedProducts.filter(p => !this.sameProduct(p, product))
        this.setState({productsInCart: updatedProducts})
    }

    increaseProductQuantity = (product) => {
        product.quantity += product.quantity;
    }

    decreaseProductQuantity = (product) => product.quantity -= product.quantity;

    productIsInCart = (product) => this.state.productsInCart.some(productInCart => this.sameProduct(productInCart, product))
    sameProduct = (productA, productB) => productA.name === productB.name && productA.brand === productB.brand && productA.storeName === productB.storeName;

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
                        <LanguageContext.Provider value={this.state.language}>
                            <div className='encuarentena2'>
                                <SideBar changeLanguage={this.changeLanguage}/>
                                <ProtectedRoute
                                                exact
                                                path='/stores'
                                                loggedIn={this.state.loggedUser}
                                                changeLanguage={this.changeLanguage}
                                                logOut={this.logOut}
                                                component={HomePage}
                                />
                                <Route
                                    exact
                                    path="/stores/:id/products"
                                    render={props => <StoreProducts {...props}
                                                                    addProductToCart={this.addProductToCart}
                                                                    productIsInCart={this.productIsInCart}
                                    />}
                                />
                                <Route
                                    exact
                                    path="/cart"
                                    render={props => <ShoppingCart {...props} productsInCart={this.state.productsInCart}
                                                                              removeFromCart={this.removeProductFromCart}
                                                                              decreaseProductQuantity={this.decreaseProductQuantity}
                                                                              increaseProductQuantity={this.increaseProductQuantity}
                                    />}
                                />
                            </div>
                        </LanguageContext.Provider>
                </Switch>
            </BrowserRouter>
        );
    }
}

export default App;
