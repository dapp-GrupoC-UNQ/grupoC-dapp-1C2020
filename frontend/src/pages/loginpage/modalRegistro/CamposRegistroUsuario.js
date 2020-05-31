import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faExclamationTriangle} from "@fortawesome/free-solid-svg-icons";

class CamposRegistroUsuario extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            deliveryDistance: 1
        }
    }
    rubros = () => [{value:'CLEANING_SUPPLIES', label:'Limpieza'}, {value:'BUTCHER', label:'Carniceria'}, {value:'GREENGROCES', label:'Verduleria'},
                    {value:'HYGIENE_PRODUCTS', label:'Perfumeria'}, {value:'GROCERY', label:'Almacen'}, {value:'BAKERY', label:'Panaderia'}]

    openingDays = () => [{value:'MONDAY', label:'Lunes'}, {value:'TUESDAY', label:'Martes'}, {value:'WEDNESDAY', label:'Miercoles'},
                        {value:'THURSDAY',label:'Jueves'}, {value:'FRIDAY', label:'Viernes'}, {value:'SATURDAY', label:'Sabado'}, {value:'SUNDAY', label:'Domingo'}]

    paymentMethod = () => ['Efectivo', 'Tarjeta de Debito', 'Tarjeta de Credito']

    generateCategory = (rubro) => {
        return (
            <div className="rubro-checkbox">
                <input type="checkbox" value={rubro.value} onClick={(event) => this.props.onAddingCategory(event.target.value)}/>
                <label className="checkbox">
                    {rubro.label}
                </label>
            </div>
        )
    }

    generateDay = (day) => {
        return(
            <div className="rubro-checkbox">
                <input type="checkbox" value={day.value}
                       onClick={(event) => this.props.onAddingDay(event.target.value)}/>
                <label className="checkbox">
                    {day.label}
                </label>
            </div>)
    }

    generate1paymentMethod = (method) => {
        return(
            <div className="rubro-checkbox">
                <input type="checkbox" value={method}
                       onClick={(event) => this.props.onAddingPaymentMethod(event.target.value)}/>
                <label className="checkbox">
                    {method}
                </label>
            </div>)
    }

    render() {
        return (<div className="modal-card-body">
                <div className="seccion-de-campos">
                    {this.props.isStore &&
                    <div className="campo-a-rellenar">
                        <label>
                            Nombre de Comercio
                        </label>
                        <input type="text" id="nombreYApellido" name="nombreYApellido"
                               onChange={(event) => this.props.onUpdate('storeName', event.target.value)}/>
                    </div>}
                    <div className="campo-a-rellenar">
                        <label>
                            Email
                        </label>
                        <input type="email" id="email" name="email"
                               onChange={(event) => this.props.onUpdate('email', event.target.value)}/>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Direccion
                        </label>
                        <input type="text" id="direccion" name="direccion"
                               onChange={(event) => this.props.onUpdate('direccion', event.target.value)}/>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Contraseña
                        </label>
                        <input type="password" id="password" name="password"
                               onChange={(event) => this.props.onUpdate('password', event.target.value)}/>
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
                                <input type="time" name="horariocomienzo"
                                       onChange={(event) => this.props.onUpdate('openingTime', event.target.value)}/> a
                                <input type="time" name="horariofin"
                                       onChange={(event) => this.props.onUpdate('closingTime', event.target.value)}/>
                            </div>
                        </div>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Días de atención
                        </label>
                        <div className="categories-grid">
                        {this.openingDays().map(day => this.generateDay(day))}
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
                {this.props.isStore &&
                <div className="seccion-de-campos">
                    <div className="campo-a-rellenar">
                        <label>
                            Medios de Pago
                        </label>
                        <div className="categories-grid">
                            {this.paymentMethod().map(method => this.generate1paymentMethod(method))}
                        </div>
                    </div>
                    <div className="campo-a-rellenar">
                        <label>
                            Distancia maxima de delivery (Km)
                        </label>
                        <input type="number" value={this.state.deliveryDistance} id="delivery" name="delivery"
                               onChange={(event) => this.updateDeliveryDistance(parseInt(event.target.value))}/>
                    </div>
                </div>
                }
            </div>

        )
    }

    updateDeliveryDistance(number) {
        this.setState({deliveryDistance: Math.max(1, number)});
        this.props.onUpdate('deliveryDistance', Math.max(1, number));
    }
}

export default CamposRegistroUsuario;