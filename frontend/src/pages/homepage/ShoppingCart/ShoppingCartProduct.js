import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMinusCircle, faPlusCircle, faStore, faTimes} from "@fortawesome/free-solid-svg-icons";
import RemoveProductConfirmation from "./removeProduct/RemoveProductConfirmation";

class ShoppingCartProduct extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            quantity: 1,
            removeProductModalOpen: false
        }
    }

    increaseProductQuantity = () => this.setState({quantity: this.state.quantity + 1})
    decreaseProductQuantity = () => this.setState({quantity: Math.max(1, this.state.quantity - 1)})
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
                        {this.props.product.name}
                    </div>
                    <div className="product-brand">
                        {this.props.product.brand}
                    </div>
                    <div className="product-store">
                        <FontAwesomeIcon icon={faStore}/>
                        {this.props.product.storeName}
                    </div>
                </div>
                <div className="divider"/>
                <div className="product-quantity-and-price">
                    <div className="price-per-unit">
                        Precio por unidad: ${this.props.product.price}
                        <FontAwesomeIcon icon={faTimes} onClick={this.openRemoveProductModal}/>
                    </div>
                    <div className="quantity">
                        Llevas {this.state.quantity}
                    </div>
                    <div className="add-or-quit-and-total-panel">
                        <div className="increase-decrease-buttons">
                            <FontAwesomeIcon icon={faPlusCircle} onClick={this.increaseProductQuantity}/>
                            <FontAwesomeIcon icon={faMinusCircle} onClick={this.decreaseProductQuantity}/>
                        </div>
                        <div className="total-product-price">
                            Total: ${this.props.product.price * this.state.quantity}
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

export default ShoppingCartProduct;