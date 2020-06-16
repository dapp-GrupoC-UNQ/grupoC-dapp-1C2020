import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMinusCircle, faPlusCircle, faStore, faTimes} from "@fortawesome/free-solid-svg-icons";
import RemoveProductConfirmation from "./removeProduct/RemoveProductConfirmation";
import {LanguageContext} from "../../../constants/LanguageMaps";

class ShoppingCartProduct extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            removeProductModalOpen: false
        }
    }

    increaseProductQuantity = () => this.props.increaseProductQuantity(this.props.product);
    decreaseProductQuantity = () => this.props.decreaseProductQuantity(this.props.product);
    openRemoveProductModal = () => this.setState({removeProductModalOpen: true})
    closeRemoveProductModal = () => this.setState({removeProductModalOpen: false})
    removeProductFromCart = () => {
        this.props.removeProductFromCart(this.props.product);
        this.closeRemoveProductModal();
    }

    render() {
        return (
            <div className="product-in-cart">
                <div className="product-image">
                    <img src={this.props.product.productImage}/>
                </div>
                <div className="product-data">
                    <div className="product-name">
                        <span>{this.props.product.name}</span>
                    </div>
                    <div className="product-brand">
                        <span>{this.props.product.brand}</span>
                    </div>
                    <div className="product-store">
                        <FontAwesomeIcon icon={faStore}/>
                        <span>{this.props.product.storeName}</span>
                    </div>
                </div>
                <div className="divider"/>
                <div className="product-quantity-and-price">
                    <div className="price-per-unit">
                        <span>{this.context.pricePerUnit} ${this.props.product.price}</span>
                        <FontAwesomeIcon icon={faTimes} onClick={this.openRemoveProductModal}/>
                    </div>
                    <div className="quantity">
                        <span>{this.context.amountInCart} {this.props.product.quantity}</span>
                    </div>
                    <div className="add-or-quit-and-total-panel">
                        <div className="increase-decrease-buttons">
                            <FontAwesomeIcon icon={faPlusCircle} onClick={this.increaseProductQuantity}/>
                            <FontAwesomeIcon icon={faMinusCircle} onClick={this.decreaseProductQuantity}/>
                        </div>
                        <div className="total-product-price">
                            <span>Total: ${this.props.product.price * this.props.product.quantity}</span>
                        </div>
                    </div>
                </div>
                {this.state.removeProductModalOpen && <RemoveProductConfirmation
                                                        product={this.props.product}
                                                        onClose={this.closeRemoveProductModal}
                                                        onAccept={this.removeProductFromCart}/>
                }
            </div>
        )
    }
}
ShoppingCartProduct.contextType = LanguageContext;
export default ShoppingCartProduct;