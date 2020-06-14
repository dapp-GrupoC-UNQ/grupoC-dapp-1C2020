import {withRouter} from "react-router-dom";
import * as React from "react";
import ImagenesProductos from "./imagenes-home-page/ImagenesProductos";
import ModalRegistroUsuario from "./modalRegistro/ModalRegistroUsuario";
import LoginService from "../../servicios/LoginService";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faExclamationTriangle} from "@fortawesome/free-solid-svg-icons";

class LoginPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userRegisterModalOpen: false,
            userFieldsError: false,
            username: '',
            password: ''
        }
    }

    closeModal = () => {
        this.setState({userRegisterModalOpen: false})
    }

    loginUser = () => {
        if (this.userIsValid()) {
            LoginService().validateUser({username: this.state.username, password: this.state.password})
                .then(response => {
                    this.props.onLogin()
                    this.props.history.push("/stores")
                })
                .catch( error => {
                    alert("El nombre de usuario o la contraseña no son correctos")
                })
        }
    }

    userIsValid = () => {
        this.setState({userFieldsError: (!this.state.username || !this.state.password) })
        return (!!this.state.username && this.state.username !== "") && (!!this.state.password && this.state.password !== "")
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
                        <div className={"usuario-input" + (this.state.userFieldsError ? " error" : "")}>
                            <label htmlFor="fname">Usuario:</label>
                            <input type="text" id="fname" name="fname" onChange={(event) => this.setState({username: event.target.value})}/>
                        </div>
                        <div className={"password-input" + (this.state.userFieldsError ? " error" : "")}>
                            <label htmlFor="fname">Contraseña:</label>
                            <input type="password" id="password" name="password" onChange={(event) => this.setState({password: event.target.value})}/>
                        </div>
                        {this.state.userFieldsError &&
                        <div className="uncompleted-fields-error">
                            <FontAwesomeIcon icon={faExclamationTriangle}/>
                            Hay campos sin completar.
                        </div>
                        }
                        <div className="button-panel">
                            <div className="login-button-field">
                                <button className="login-button"
                                        onClick={() => this.setState({userRegisterModalOpen: true})}>¿No tenes cuenta?</button>
                            </div>
                            <div className="login-button-field">
                                <button className="login-button" onClick={this.loginUser}>¡Vamos!</button>
                            </div>
                        </div>

                    </div>
                </div>
                {this.state.userRegisterModalOpen && <ModalRegistroUsuario onClose={this.closeModal}/>}
            </div>
        )
    }
}
export default withRouter(LoginPage);