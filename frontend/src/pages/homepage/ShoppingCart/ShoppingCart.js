import * as React from "react";
import "./shoppingCart.scss"
import ShoppingCartProduct from "./ShoppingCartProduct";
import {LanguageContext} from "../../../constants/LanguageMaps";

class ShoppingCart extends React.Component{
    renderProductsInCart = () => this.props.productsInCart.map((product) => <ShoppingCartProduct product={product}
                                                                                                 removeProductFromCart={this.removeProductFromCart}
                                                                                                 increaseProductQuantity={this.props.increaseProductQuantity}
                                                                                                 decreaseProductQuantity={this.props.decreaseProductQuantity}/>)

    removeProductFromCart = (product) => {
        this.props.removeFromCart(product)
    }

    render() {
        return(
            <div className="homepage">
                <div className="shopping-cart-container">
                    <div className="shopping-cart-title">
                        {this.context.cartTitle}
                    </div>
                    {this.cartContent()}
                </div>
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
        return this.props.productsInCart.length === 0;
    }
}
ShoppingCart.contextType = LanguageContext;

export default ShoppingCart;