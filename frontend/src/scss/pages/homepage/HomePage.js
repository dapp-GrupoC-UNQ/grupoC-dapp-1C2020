import {withRouter} from "react-router-dom";
import * as React from "react";

class LoginPage extends React.Component {
    render() {
        return (
            <div className="login-page">
                <div className="login-panel">
                    <div className="images-subpanel">
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
                        <div className="login-button-field">
                            <button className="login-button">¡Vamos!</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
export default withRouter(LoginPage);