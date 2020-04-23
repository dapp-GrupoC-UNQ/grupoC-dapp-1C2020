import {withRouter} from "react-router-dom";
import * as React from "react";
import { faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons'
import "./homepage.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

class HomePage extends React.Component {
    render() {
        return(
            <div className="homepage">
                <div className="comercios">
                    <div className="carta-comercio">
                        <div className='imagen-comercio'>
                            <img src={'https://www.memesargentinos.com.ar/wp-content/uploads/2019/02/China-Supermercado.jpg'}/>
                        </div>
                        <div className='nombre-comercio'>
                            Super "No hay por qué"
                        </div>
                        <div className='distancia-comercio'>
                            <FontAwesomeIcon icon={faMapMarkerAlt}/>
                            <p className="distancia">a 2.5 km</p>
                        </div>
                    </div>
                    <div className="carta-comercio">
                        hi
                    </div>
                    <div className="carta-comercio">
                        hi
                    </div>
                    <div className="carta-comercio">
                        hi
                    </div>
                    <div className="carta-comercio">
                        hi
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(HomePage);