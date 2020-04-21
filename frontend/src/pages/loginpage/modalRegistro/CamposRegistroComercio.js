import * as React from "react";
import HorarioApertura from "./HorarioApertura";

class CamposRegistroComercio extends React.Component {
    render() {
        return (<div className="modal-card-body is-flex">
                    <div className="seccion-de-campos">
                        <div className="campo-a-rellenar">
                            <label>
                                Nombre de tu comercio
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

                <div className="seccion-de-campos">
                    <div className="campo-a-rellenar horarios">
                        <label>
                            Horarios de apertura
                        </label>
                        <input type="text" name="nombreYApellido"/>
                    </div>
                </div>
            </div>
        )
    }
}

export default CamposRegistroComercio;