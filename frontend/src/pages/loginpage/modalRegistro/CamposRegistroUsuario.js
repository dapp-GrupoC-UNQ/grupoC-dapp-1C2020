import * as React from "react";

class CamposRegistroUsuario extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            agregarHorario: true
        }
    }
    rubros = () => ['Limpieza', 'Carniceria', 'Verduleria', 'Perfumeria', 'Almacen']

    generarRubro = (rubro) => {
        return (
            <div className="rubro-checbox">
                <input type="checkbox" value={rubro} onClick={(event) => this.props.onAgregarRubro(event.target.value)}/>
                <label className="checkbox">
                    {rubro}
                </label>
            </div>
        )
    }
    textoHorarios = () => this.state.agregarHorario ? 'agregar' : 'quitar';

    actualizarTextoHorarios = () => this.setState({agregarHorario: !this.state.agregarHorario})

    render() {
        return (<div className="modal-card-body">
                    <div className="seccion-de-campos">
                        <div className="campo-a-rellenar">
                            <label>
                                Nombre y Apellido
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
                    </div>
                {this.props.esComercio &&
                <div className="seccion-de-campos">
                    <div className="campo-a-rellenar">
                        <label>
                            Horarios de apertura
                        </label>
                        <div className="horarios">
                            <div className="seleccion-horario">
                                <input type="time" name="horariocomienzo" onChange={(event) => this.props.onUpdate('primerHorarioApertura', event.target.value)}/> a
                                <input type="time" name="horariofin" onChange={(event) => this.props.onUpdate('primerHorarioCierre', event.target.value)}/>
                            </div>

                            {!this.state.agregarHorario &&
                            <div>
                                <input type="time" name="horariocomienzoalt" onChange={(event) => this.props.onUpdate('segundoHorarioApertura', event.target.value)}/> a
                                <input type="time" name="horariofinalt" onChange={(event) => this.props.onUpdate('segundoHorarioCierre', event.target.value)}/>
                            </div>
                            }

                            <div className='botonera-horarios'>
                                <a onClick={this.actualizarTextoHorarios}>{this.textoHorarios()} horario</a>
                            </div>
                        </div>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Rubros
                        </label>
                        {this.rubros().map(rubro => this.generarRubro(rubro))}
                    </div>
                </div>
                }
            </div>
        )
    }
}

export default CamposRegistroUsuario;