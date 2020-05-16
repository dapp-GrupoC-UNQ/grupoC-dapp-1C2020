import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMapMarkerAlt, faStore} from "@fortawesome/free-solid-svg-icons";

class Store extends React.Component {
    render() {
        return (
            <div className="entity-card" onClick={() => this.props.onShowStoreProducts(this.props.store)}>
                <div className='imagen-comercio'>
                    <img src={'https://www.memesargentinos.com.ar/wp-content/uploads/2019/02/China-Supermercado.jpg'}/>
                </div>
                <div className='nombre-comercio'>
                    {this.props.store.storeName}
                </div>
                <div className='distancia-comercio'>
                    <FontAwesomeIcon icon={faMapMarkerAlt}/>
                    <p className="distancia">{this.props.store.storeAdress}</p>
                </div>
                <div className='rubros-comercio'>
                    <FontAwesomeIcon icon={faStore}/>
                    <p className="rubros">{this.props.store.storeCategory}</p>
                </div>
            </div>
        )
    }
}

export default Store;