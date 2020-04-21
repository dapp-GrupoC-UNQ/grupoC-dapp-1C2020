import * as React from "react";

class CamposRegistroUsuario extends React.Component {
    render() {
        return (<div className="modal-card-body">
                    <div className="campo-a-rellenar">
                        <label>
                            Nombre y Apellido
                        </label>
                        <input type="text" id="nombreYApellido" name="nombreYApellido"/>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Email
                        </label>
                        <input type="email" id="nombreYApellido" name="nombreYApellido"/>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Contraseña
                        </label>
                        <input type="password" id="nombreYApellido" name="nombreYApellido"/>
                    </div>
                </div>
        )
    }
}

export default CamposRegistroUsuario;