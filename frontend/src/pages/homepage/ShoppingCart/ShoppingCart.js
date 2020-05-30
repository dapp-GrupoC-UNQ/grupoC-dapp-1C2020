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
                {this.cartContent()}
            </div>
        )
    }

    cartContent() {
        return <>
            {this.cartIsEmpty() &&
            <div>
                Su carrito está vacio. Ya compra algo maldita sea
            </div>
            }
            {!this.cartIsEmpty() &&
            <div className="shopping-cart-content">
                {this.renderProductsInCart()}
            </div>
            }
        </>;
    }

    cartIsEmpty() {
        return this.props.products.length === 0;
    }
}

export default ShoppingCart;