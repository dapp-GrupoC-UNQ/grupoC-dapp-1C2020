import * as React from "react";

class CamposRegistroComercio extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            agregarHorario: true
        }
    }

    textoHorarios = () => this.state.agregarHorario ? 'agregar' : 'quitar';

    actualizarTextoHorarios = () => this.setState({agregarHorario: !this.state.agregarHorario})
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
                                Direccion
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
                    <div className="campo-a-rellenar">
                        <label>
                            Horarios de apertura
                        </label>
                        <div className="horarios">
                            <div className="seleccion-horario">
                                <input type="time" name="nombreYApellido"/> a
                                <input type="time" name="nombreYApellido"/>
                            </div>

                            {!this.state.agregarHorario &&
                            <div>
                                <input type="time" name="nombreYApellido"/> a
                                <input type="time" name="nombreYApellido"/>
                            </div>
                            }

                            <div className='botonera-horarios'>
                                <a onClick={this.actualizarTextoHorarios}>{this.textoHorarios()} horario</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default CamposRegistroComercio;