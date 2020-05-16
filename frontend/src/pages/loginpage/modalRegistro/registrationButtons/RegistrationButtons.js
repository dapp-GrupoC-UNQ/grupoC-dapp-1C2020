import * as React from "react";

class RegistrationButtons extends React.Component {
    render() {
        return(
            <div className="registration-buttons">
                {!this.props.registrationSucceed &&
                <div className="registering-buttons">
                    <button className="boton-modal" onClick={this.props.registerUser}>Registrarme</button>
                    <button className="boton-modal" onClick={this.props.changeEntityType}>Registrarme como {this.props.registerButtonText}</button>
                </div>
                }
                {this.props.registrationSucceed &&
                <div className="success-button">
                    <button className="boton-modal" onClick={this.props.closeModal}>¡Entendido!</button>
                </div>
                }

            </div>
        )
    }
}

export default RegistrationButtons