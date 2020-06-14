import * as React from "react";
import "./shoppingCart.scss"
import ShoppingCartProduct from "./ShoppingCartProduct";
import {LanguageContext} from "../../../constants/LanguageMaps";

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
                    {this.context.cartTitle}
                </div>
                {this.cartContent()}
            </div>
        )
    }

    cartContent() {
        return <>
            {this.cartIsEmpty() &&
            <div>
                {this.context.emptyCartText}
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
ShoppingCart.contextType = LanguageContext;

export default ShoppingCart;