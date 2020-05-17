import * as React from "react";
import "./shoppingCart.scss"
import ShoppingCartProduct from "./ShoppingCartProduct";

class ShoppingCart extends React.Component{
    constructor(props) {
        super(props);
    }


    renderProductsInCart = () => this.props.products.map((product) => <ShoppingCartProduct product={product}
                                                                                           removeProductFromCart={this.removeProductFromCart}/>)

    removeProductFromCart = (product) => {
        this.props.removeFromCart(product)
    }

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