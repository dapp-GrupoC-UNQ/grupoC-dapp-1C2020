import * as React from "react";
import "./removeProductConfirmation.scss";

class RemoveProductConfirmation extends React.Component {
    render() {
        return(
                <div className="modal">
                    <div className="modal-background"/>
                    <div className="modal-content">
                        <div className="remove-product-confirmation-card">
                            <div className="remove-confirmation-title">
                                ¿Estás segure que queres quitar el producto {this.props.product.name} {this.props.product.brand} del carrito?
                            </div>
                            <div className="remove-confirmation-buttons">
                                <button className="remove-confirmation-button" onClick={this.props.onAccept}>Aceptar</button>
                                <button className="remove-confirmation-button" onClick={this.props.onClose}>Cancelar</button>
                            </div>
                        </div>
                    </div>
                </div>
        )
    }
}

export default RemoveProductConfirmation