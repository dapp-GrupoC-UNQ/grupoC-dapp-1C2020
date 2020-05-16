import * as React from "react";

class Product extends React.Component {
    render() {
        return (<div className="entity-card product-card">
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