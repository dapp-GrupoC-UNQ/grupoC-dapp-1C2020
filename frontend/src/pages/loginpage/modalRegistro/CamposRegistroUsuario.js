import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faExclamationTriangle} from "@fortawesome/free-solid-svg-icons";

class CamposRegistroUsuario extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            addSchedule: true
        }
    }
    rubros = () => ['Limpieza', 'Carniceria', 'Verduleria', 'Perfumeria', 'Almacen', 'Panaderia']

    generateCategory = (rubro) => {
        return (
            <div className="rubro-checkbox">
                <input type="checkbox" value={rubro} onClick={(event) => this.props.onAddingCategory(event.target.value)}/>
                <label className="checkbox">
                    {rubro}
                </label>
            </div>
        )
    }

    render() {
        return (<div className="modal-card-body">
                    <div className="seccion-de-campos">
                        <div className="campo-a-rellenar">
                            <label>
                                {!this.props.isStore ? "Nombre y Apellido" : "Comercio"}
                            </label>
                            <input type="text" id="nombreYApellido" name="nombreYApellido" onChange={(event) =>this.props.onUpdate('nombreYApellido', event.target.value)}/>
                        </div>
                        <div className="campo-a-rellenar">
                            <label>
                                Direccion
                            </label>
                            <input type="text" id="direccion" name="direccion" onChange={(event) =>this.props.onUpdate('direccion', event.target.value)}/>
                        </div>
                        <div className="campo-a-rellenar">
                            <label>
                                Email
                            </label>
                            <input type="email" id="email" name="email" onChange={(event) =>this.props.onUpdate('email', event.target.value)}/>
                        </div>
                        <div className="campo-a-rellenar">
                            <label>
                                Contraseña
                            </label>
                            <input type="password" id="password" name="password" onChange={(event) =>this.props.onUpdate('password', event.target.value)}/>
                        </div>

                        {!this.props.isValidUser &&
                            <div className="user-error">
                                <FontAwesomeIcon icon={faExclamationTriangle}/>
                                Hay campos sin completar.
                            </div>}
                </div>
                {this.props.isStore &&
                <div className="seccion-de-campos">
                    <div className="campo-a-rellenar">
                        <label>
                            Horario de atención
                        </label>
                        <div className="horarios">
                            <div className="seleccion-horario">
                                <input type="time" name="horariocomienzo" onChange={(event) => this.props.onUpdate('primerHorarioApertura', event.target.value)}/> a
                                <input type="time" name="horariofin" onChange={(event) => this.props.onUpdate('primerHorarioCierre', event.target.value)}/>
                            </div>
                        </div>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Rubros
                        </label>
                        <div className="categories-grid">
                            {this.rubros().map(rubro => this.generateCategory(rubro))}
                        </div>
                    </div>
                </div>
                }
            </div>
        )
    }
}

export default CamposRegistroUsuario;