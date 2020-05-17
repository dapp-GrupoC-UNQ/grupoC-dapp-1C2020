import * as React from "react";
import "./shoppingCart.scss"
import ShoppingCartProduct from "./ShoppingCartProduct";

class ShoppingCart extends React.Component{

    renderProductsInCart = () => this.props.products.map((product) => <ShoppingCartProduct product={product}/>)

    render() {
        return(
            <div className="shopping-cart-container">
                <div className="shopping-cart-title">
                    Mi carrito
                </div>
                <div className="shopping-cart-content">
                    {this.renderProductsInCart()}
                </div>
            </div>
        )
    }
}

export default ShoppingCart;