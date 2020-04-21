import {withRouter} from "react-router-dom";
import * as React from "react";
import ImagenesProductos from "./imagenes-home-page/ImagenesProductos";
import ModalRegistroUsuario from "./ModalRegistroUsuario";

class LoginPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modalRegistroUsuarioAbierto: false
        }
    }

    render() {
        return (
            <div className="login-page">
                <div className="login-panel">
                    <div className="images-subpanel">
                        <ImagenesProductos/>
                    </div>
                    <div className="divisor-line">
                    </div>
                    <div className="user-subpanel">
                        <div className="welcome-text">
                            ¡Bienvenidx!
                        </div>
                        <div className="lets-buy-text">
                            Logeate con tu usuario y contraseña y empezá a comprar tus productos favoritos
                        </div>
                        <div className="usuario-input">
                            <label htmlFor="fname">Usuario:</label>
                            <input type="text" id="fname" name="fname"/>
                        </div>
                        <div className="password-input">
                            <label htmlFor="fname">Contraseña:</label>
                            <input type="password" id="password" name="password"/>
                        </div>
                        <div className="button-panel">
                            <div className="login-button-field">
                                <button className="login-button"
                                        onClick={() => this.setState({modalRegistroUsuarioAbierto: true})}>¿No tenes cuenta?</button>
                            </div>
                            <div className="login-button-field">
                                <button className="login-button">¡Vamos!</button>
                            </div>
                        </div>

                    </div>
                </div>
                {this.state.modalRegistroUsuarioAbierto && <ModalRegistroUsuario/>}
            </div>
        )
    }
}
export default withRouter(LoginPage);