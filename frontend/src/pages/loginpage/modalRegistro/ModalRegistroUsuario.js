import * as React from "react";
import "./modalregistrousuario.scss"
import CamposRegistroUsuario from "./CamposRegistroUsuario";
import LoginService from "../../../servicios/LoginService";
import RegistrationSucceed from "./succeed/RegistrationSucceed";
import RegistrationButtons from "./registrationButtons/RegistrationButtons";
import EntitiesValidator from "../../../helpers/EntitiesValidator";
import EntitiesBuilder from "../../../helpers/EntitiesBuilder";

class ModalRegistroUsuario extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            registeringUser: true,
            registrationSucceed: false,
            deliveryDistance: 1,
            isValidUser: true,
            isValidMail: true
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

    addPaymentMethod = (method) => {
        let paymentMethodList = (this.state.paymentMethods || [])
        let upDatePaymentMethods = paymentMethodList.includes(method) ? paymentMethodList.filter(paymentMethod => paymentMethod !== method) : paymentMethodList.concat(method)
        this.setState({paymentMethods: upDatePaymentMethods})
    }
    
    updateForm = (key, value) => {
        this.setState({[key]: value})
    }

    validateUser = () => {
        const isValidUser = EntitiesValidator().validateClientUser(this.state);
        this.setState({isValidUser: isValidUser})
        return isValidUser;
    }

    validateMail = () => {
        const isValidMail = EntitiesValidator().validateEmail(this.state);
        this.setState({isValidMail: isValidMail})
        return isValidMail;
    }

    validateStoreUser = () => {
        const isValidUser = EntitiesValidator().validateStoreAdmin(this.state);
        this.setState({isValidUser: isValidUser})
        return isValidUser;
    }

    buildUser = () => EntitiesBuilder().buildClientUser(this.state);

    buildStoreAdmin = () => EntitiesBuilder().buildStoreAdmin(this.state);

    registerUser = () => {
        if(this.state.registeringUser && this.validateUser() && this.validateMail()){
            LoginService().registerUser(this.buildUser())
                .then(() =>{
                    this.setState({registrationSucceed: true})
                })
                .catch(error => console.log(error))
        }
        if(!this.state.registeringUser && this.validateStoreUser() && this.validateMail()){
            LoginService().registerStoreUser(this.buildStoreAdmin())
                .then(() =>{
                    this.setState({registrationSucceed: true})
                })
                .catch(error => console.log(error))
        }
    }

    render() {
        return(
            <div className="modal">
                <div className="modal-background"/>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">Registrate como {this.entityToRegister()}</p>
                        <button className="delete" aria-label="close" onClick={this.props.onClose}/>
                    </header>
                    {!this.state.registrationSucceed  &&
                    <CamposRegistroUsuario onUpdate={this.updateForm}
                                               onAddingCategory={this.addCategory}
                                               isStore={!this.state.registeringUser}
                                               isValidUser={this.state.isValidUser}
                                               onAddingDay={this.addDay}
                                               onAddingPaymentMethod={this.addPaymentMethod}
                                               isValidMail={this.state.isValidMail}
                    />}
                    {this.state.registrationSucceed && <RegistrationSucceed
                                                            registerUser={this.state.registeringUser}/>}
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