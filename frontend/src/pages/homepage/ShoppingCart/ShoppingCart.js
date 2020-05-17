import * as React from "react";
import "./shoppingCart.scss"
import {faStore} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

class ShoppingCart extends React.Component{

    renderProductInCart = (product) => {
        return (
                <div className="product-in-cart">
                    <div className="product-image">
                        <img src={product.productImage}/>
                    </div>
                    <div className="product-data">
                        <div className="product-name">
                            {product.name}
                        </div>
                        <div className="product-brand">
                            {product.brand}
                        </div>
                        <div className="product-store">
                            <FontAwesomeIcon icon={faStore}/>
                            {product.storeName}
                        </div>
                    </div>
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