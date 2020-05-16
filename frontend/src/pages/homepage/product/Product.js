import * as React from "react";
import "./product.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faShoppingCart} from "@fortawesome/free-solid-svg-icons";

class Product extends React.Component {
    render() {
        return (<div className="entity-card product-card">
            <div className="product-button">
                <button className="add-to-cart-button">
                    <FontAwesomeIcon icon={faShoppingCart}/>
                </button>
            </div>
            <div className='imagen-comercio'>
                <img src={this.props.product.productImage}/>
            </div>
            <div className='product-name'>
                {this.props.product.name}
            </div>
            <div className="product-footer">
                <div className='product-brand'>
                    {this.props.product.brand}
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