import * as React from "react";
import "./modalregistrousuario.scss"
import CamposRegistroUsuario from "./CamposRegistroUsuario";
import CamposRegistroComercio from "./CamposRegistroComercio";

class ModalRegistroUsuario extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            registrandoUsuario: true
        }
    }

    entidadARegistrar = () => this.state.registrandoUsuario ? 'usuario' : 'comercio';
    textoBotonRegistrarmeComo = () => this.state.registrandoUsuario ? 'comercio' : 'usuario';

    cambiarDeEntidad = () => this.setState({registrandoUsuario: !this.state.registrandoUsuario})
    render() {
        return(
            <div className="modal">
                <div className="modal-background"></div>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">Registrate como {this.entidadARegistrar()}</p>
                        <button className="delete" aria-label="close" onClick={this.props.onClose}></button>
                    </header>
                        {this.state.registrandoUsuario && <CamposRegistroUsuario/>}
                        {!this.state.registrandoUsuario && <CamposRegistroComercio/>}
                    <footer className="modal-card-foot">
                        <button className="boton-modal">Registrarme</button>
                        <button className="boton-modal" onClick={this.cambiarDeEntidad}>Registrarme como {this.textoBotonRegistrarmeComo()}</button>
                    </footer>
                </div>
            </div>
        )
    }
}

export default ModalRegistroUsuario;