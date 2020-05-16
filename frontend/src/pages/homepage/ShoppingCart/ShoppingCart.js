import * as React from "react";
import "./shoppingCart.scss"

class ShoppingCart extends React.Component{

    renderProductInCart = (product) => {
        return (
                <div className="product-in-cart">
                    {product.name}
                </div>
            )
    }

    render() {
        return(
            <div className="shopping-cart-container">
                <div className="shopping-cart-title">
                    Mi carrito
                </div>
                <div className="shopping-cart-content">
                    {this.props.products.map((product) => this.renderProductInCart(product))}
                </div>
            </div>
        )
    }
}

export default ShoppingCart;