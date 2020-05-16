import * as React from "react";
import {faStore} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

class Discount extends React.Component {
    render() {
        return (
            <div className="entity-card">
                <div className='imagen-comercio'>
                    <img src={this.props.discount.discountImageURL}/>
                </div>
                <div className='nombre-comercio'>
                    {this.props.discount.discountText}
                </div>
                <div className="discount-store">
                    <FontAwesomeIcon icon={faStore}/>
                    {this.props.discount.storeName}
                </div>
            </div>
        )
    }
}

export default Discount