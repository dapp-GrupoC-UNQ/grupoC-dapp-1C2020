import {withRouter} from "react-router-dom";
import * as React from "react";
import { faMapMarkerAlt, faStore } from '@fortawesome/free-solid-svg-icons'
import "./homepage.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

class HomePage extends React.Component {
    comercios = [{
        nombreComercio: "No hay por qué",
        distanciaComercio: '2.5km',
        rubrosComercio: 'carniceria, limpieza, verduleria'
    },
        {
            nombreComercio: "Lo de tito",
            distanciaComercio: '0.9km',
            rubrosComercio: 'carniceria, verduleria'
        }
    ]

    commerceCard = (commerce) => {
        return (
            <div className="carta-comercio">
                <div className='imagen-comercio'>
                    <img src={'https://www.memesargentinos.com.ar/wp-content/uploads/2019/02/China-Supermercado.jpg'}/>
                </div>
                <div className='nombre-comercio'>
                    {commerce.nombreComercio}
                </div>
                <div className='distancia-comercio'>
                    <FontAwesomeIcon icon={faMapMarkerAlt}/>
                    <p className="distancia">{commerce.distanciaComercio}</p>
                </div>
                <div className="rubros-comercio">
                    <FontAwesomeIcon icon={faStore}/>
                    <p className="rubros">{commerce.rubrosComercio}</p>
                </div>
            </div>
        )
    }

    render() {
        return(
            <div className="homepage">
                <div className="side-bar">
                    Busca tu producto
                </div>
                <div className="comercios">
                    {this.comercios.map(commerce => {
                        return this.commerceCard(commerce);
                    })}
                </div>
            </div>
        )
    }
}

export default withRouter(HomePage);