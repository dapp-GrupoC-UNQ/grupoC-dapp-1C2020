import * as React from "react";
import "./modalregistrousuario.scss"
import CamposRegistroUsuario from "./CamposRegistroUsuario";
import LoginService from "../../../servicios/LoginService";
import RegistrationSucceed from "./succeed/RegistrationSucceed";
import RegistrationButtons from "./registrationButtons/RegistrationButtons";

class ModalRegistroUsuario extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            registeringUser: true,
            registrationSucceed: false,
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

    addDay = (day) => {
        let openingDaysList = (this.state.openingDays || [])
        let upDateDays = openingDaysList.includes(day) ? openingDaysList.filter(openingDay => openingDay !== day) : openingDaysList.concat(day)
        this.setState({openingDays: upDateDays})
    }
    
    updateForm = (key, value) => {
        this.setState({[key]: value})
    }

    validateUser = () => {
        this.setState({isValidUser: (!!this.state.nombreYApellido && !!this.state.direccion &&
                !!this.state.email && !!this.state.password)})
        return this.state.isValidUser;
    }

    registerUser = () => {
        if(this.validateUser()){
            LoginService().registerUser({username: this.state.nombreYApellido, password: this.state.password})
                .then(() =>{
                    this.setState({registrationSucceed: true})
                })
                .catch(error => console.log(error))
        };
    }

    render() {
        return(
            <div className="modal">
                <div className="modal-background"></div>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">Registrate como {this.entityToRegister()}</p>
                        <button className="delete" aria-label="close" onClick={this.props.onClose}></button>
                    </header>
                    {!this.state.registrationSucceed  &&
                    <CamposRegistroUsuario onUpdate={this.updateForm}
                                               onAddingCategory={this.addCategory}
                                               isStore={!this.state.registeringUser}
                                               isValidUser={this.state.isValidUser}
                                               onAddingDay={this.addDay}
                    />}
                    {this.state.registrationSucceed && <RegistrationSucceed/>}
                     <footer className="modal-card-foot">
                         <RegistrationButtons
                             registrationSucceed={this.state.registrationSucceed}
                             closeModal={this.props.onClose}
                             registerUser={this.registerUser}
                             registerButtonText={this.registerButtonText()}
                             changeEntityType={this.changeEntityType}/>
                    </footer>
                </div>
            </div>
        )
    }
}

export default ModalRegistroUsuario;