import * as React from "react";
import {withRouter} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMapMarkerAlt, faStore} from "@fortawesome/free-solid-svg-icons";

class Store extends React.Component {

    goToStoreProducts = () => this.props.history.push('/stores/' + this.props.store.id + '/products')

    render() {
        return (
            <div className="entity-card" onClick={this.goToStoreProducts}>
                <div className='imagen-comercio'>
                    <img src={this.props.store.storeImage}/>
                </div>
                <div className='nombre-comercio'>
                    <span>{this.props.store.storeName}</span>
                </div>
                <div className='distancia-comercio'>
                    <FontAwesomeIcon icon={faMapMarkerAlt}/>
                    <p className="distancia">{this.props.store.storeAddress}</p>
                </div>
                <div className='rubros-comercio'>
                    <FontAwesomeIcon icon={faStore}/>
                    <p className="rubros">{this.props.store.storeCategories}</p>
                </div>
            </div>
        )
    }
}

export default withRouter(Store);