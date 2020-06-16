import * as React from "react";
import "./product.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faShoppingCart, faCheck, faTimes} from "@fortawesome/free-solid-svg-icons";

class Product extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            wasAddedToCart: false
        }
    }

    addProductToCart = () => {
        this.setState({wasAddedToCart: true})
        this.props.onAddToCart(this.props.product)
    }

    removeFromCart = () => {
        this.setState({wasAddedToCart: false})
        this.props.onRemoveFromCart(this.props.product)
    }

    render() {
        return (<div className="entity-card product-card">
            <div className="product-button">
                {!this.props.productIsInCart(this.props.product) &&
                <div className="add-to-cart-button">
                    <button onClick={this.addProductToCart}>
                        <FontAwesomeIcon icon={faShoppingCart}/>
                    </button>
                </div>
                }
                {this.props.productIsInCart(this.props.product) &&
                <div className="add-or-remove-buttons">
                    <div className="added-to-cart-button">
                        <FontAwesomeIcon icon={faCheck}/>
                    </div>
                </div>
                }
            </div>
            <div className='imagen-comercio'>
                <img src={this.props.product.productImage}/>
            </div>
            <div className='product-name'>
                <span>{this.props.product.name}</span>
            </div>
            <div className="product-footer">
                <div className='product-brand'>
                    <span>{this.props.product.brand}</span>
                </div>
                <div className='product-price'>
                    <p className="price">${this.props.product.price}</p>
                </div>
            </div>
        </div>
        )
    }
}

export default Product