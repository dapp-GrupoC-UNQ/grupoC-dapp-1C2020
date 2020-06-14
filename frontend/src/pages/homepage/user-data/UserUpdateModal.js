import * as React from "react";
import './user-update-modal.scss'
class UserUpdateModal extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: this.props.user.username,
            address: this.props.user.address,
            password: this.props.user.password
        }
    }

    render() {
        return(
            <div className='modal'>
                <div className="modal-background"/>
                <div className='modal-card'>
                    <header className="modal-card-head">
                        <p className="modal-card-title">Tu Perfil</p>
                        <button className="delete" aria-label="close" onClick={this.props.onClose}/>
                    </header>
                    <div className="modal-card-body">
                        <div className='seccion-de-campos'>
                            <div className="campo-a-rellenar">
                                <label>
                                    Email
                                </label>
                                <span>{this.state.username}</span>
                            </div>
                            <div className="campo-a-rellenar">
                                <label>
                                    Direccion
                                </label>
                                <input type="text" id="user-address" name="user-address" value={this.state.address}
                                       onChange={(event) => this.setState({address: event.target.value})}/>
                            </div>
                            <div className="campo-a-rellenar">
                                <label>
                                    Nueva contraseña
                                </label>
                                <input type="password" id="user-password" name="user-password" value={this.state.password}
                                       onChange={(event) => this.setState({password: event.target.value})}/>
                            </div>
                        </div>
                    </div>
                    <footer className="modal-card-foot">
                        <button className="boton-modal" onClick={()=>{}}>Actualizar</button>
                    </footer>
                </div>
            </div>
        )
    }
}

export default UserUpdateModal;