import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheckCircle} from "@fortawesome/free-solid-svg-icons";

class RegistrationSucceed extends React.Component {
    render() {
        return(
            <div className="modal-card-body success">
                <div className='success-icon'>
                    <FontAwesomeIcon icon={faCheckCircle}/>
                </div>
                <div className="success-title">
                    ¡Te registraste con éxito!
                </div>
                <div className="success-subtitle">
                    Ya podes empezar a comprar
                </div>
            </div>
        )
    }

}

export default RegistrationSucceed;