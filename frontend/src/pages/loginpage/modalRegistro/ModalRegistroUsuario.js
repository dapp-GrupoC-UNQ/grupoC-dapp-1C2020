import * as React from "react";
import "./modalregistrousuario.scss"
import CamposRegistroUsuario from "./CamposRegistroUsuario";

class ModalRegistroUsuario extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            registeringUser: true,
            isValidUser: true
        }
    }

    entityToRegister = () => this.state.registeringUser ? 'usuario' : 'comercio';
    registerButtonText = () => this.state.registeringUser ? 'comercio' : 'usuario';

    changeEntityType = () => this.setState({registeringUser: !this.state.registeringUser})

    addCategory = (rubro) => {
        let listaActualRubros = (this.state.rubros || [])
        //agregar a la lista  si se selecciona. Si se deselecciona hay que sacarlo
        let nuevosRubros = listaActualRubros.includes(rubro) ? listaActualRubros.filter(rubroEnLista => rubroEnLista !== rubro) : listaActualRubros.concat(rubro)
        this.setState({rubros: nuevosRubros})
    }

    updateForm = (key, value) => {
        this.setState({[key]: value})
    }

    validateUser = () => {
        this.setState({isValidUser: (!!this.state.nameAndSurname && !!this.state.address &&
                !!this.state.email && !!this.state.password)})}

    render() {
        return(
            <div className="modal">
                <div className="modal-background"></div>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">Registrate como {this.entityToRegister()}</p>
                        <button className="delete" aria-label="close" onClick={this.props.onClose}></button>
                    </header>
                        <CamposRegistroUsuario onUpdate={this.updateForm}
                                               onAddingCategory={this.addCategory}
                                               isStore={!this.state.registeringUser}
                                               isValidUser={this.state.isValidUser}/>

                    <footer className="modal-card-foot">
                        <button className="boton-modal" onClick={this.validateUser}>Registrarme</button>
                        <button className="boton-modal" onClick={this.changeEntityType}>Registrarme como {this.registerButtonText()}</button>
                    </footer>
                </div>
            </div>
        )
    }
}

export default ModalRegistroUsuario;