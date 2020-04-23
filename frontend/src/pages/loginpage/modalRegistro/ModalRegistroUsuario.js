import * as React from "react";
import "./modalregistrousuario.scss"
import CamposRegistroUsuario from "./CamposRegistroUsuario";

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

    agregarRubro = (rubro) => {
        let listaActualRubros = (this.state.rubros || [])
        //agregar a la lista  si se selecciona. Si se deselecciona hay que sacarlo
        let nuevosRubros = listaActualRubros.includes(rubro) ? listaActualRubros.filter(rubroEnLista => rubroEnLista !== rubro) : listaActualRubros.concat(rubro)
        this.setState({rubros: nuevosRubros})
    }

    actualizarFormulario = (clave, valor) => {
        this.setState({[clave]: valor})
    }

    validarUsuario = () => {
        return (!!this.state.nombreYApellido && !!this.state.direccion &&
               !!this.state.email && !!this.state.password) ?
                alert("El usuario es valido") : alert("Faltan campos por completar")}

    render() {
        return(
            <div className="modal">
                <div className="modal-background"></div>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">Registrate como {this.entidadARegistrar()}</p>
                        <button className="delete" aria-label="close" onClick={this.props.onClose}></button>
                    </header>
                        <CamposRegistroUsuario onUpdate={this.actualizarFormulario}
                                               onAgregarRubro={this.agregarRubro}
                                               esComercio={!this.state.registrandoUsuario}/>
                    <footer className="modal-card-foot">
                        <button className="boton-modal" onClick={this.validarUsuario}>Registrarme</button>
                        <button className="boton-modal" onClick={this.cambiarDeEntidad}>Registrarme como {this.textoBotonRegistrarmeComo()}</button>
                    </footer>
                </div>
            </div>
        )
    }
}

export default ModalRegistroUsuario;