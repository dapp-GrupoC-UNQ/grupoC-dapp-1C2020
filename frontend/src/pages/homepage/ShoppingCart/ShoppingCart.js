import * as React from "react";

class ShoppingCart extends React.Component{
    render() {
        return(
            <div>
                {
                    this.props.showCart &&
                    <div>

                    </div>
                }
            </div>
        )
    }
}

export default ShoppingCart;