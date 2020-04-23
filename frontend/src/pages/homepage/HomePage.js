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
    render() {
        return(
            <div className="homepage">
                <div className="comercios">
                    {this.comercios.map(comercio => {
                        return (
                            <div className="carta-comercio">
                                <div className='imagen-comercio'>
                                    <img src={'https://www.memesargentinos.com.ar/wp-content/uploads/2019/02/China-Supermercado.jpg'}/>
                                </div>
                                <div className='nombre-comercio'>
                                    {comercio.nombreComercio}
                                </div>
                                <div className='distancia-comercio'>
                                    <FontAwesomeIcon icon={faMapMarkerAlt}/>
                                    <p className="distancia">{comercio.distanciaComercio}</p>
                                </div>
                                <div className="rubros-comercio">
                                    <FontAwesomeIcon icon={faStore}/>
                                    <p className="rubros">{comercio.rubrosComercio}</p>
                                </div>
                            </div>
                        )
                    })}
                </div>
            </div>
        )
    }
}

export default withRouter(HomePage);