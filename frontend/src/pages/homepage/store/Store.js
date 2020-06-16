import * as React from "react";
import {withRouter} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMapMarkerAlt, faStore} from "@fortawesome/free-solid-svg-icons";
import {LanguageContext} from "../../../constants/LanguageMaps";

class Store extends React.Component {

    goToStoreProducts = () => this.props.history.push('/stores/' + this.props.store.id + '/products')

    parseStoreCategories = () => this.props.store.storeCategories.map(category => this.context.storeCategories[category]).join(', ')

    render() {
        return (
            <div className="entity-card" onClick={this.goToStoreProducts}>
                <div className='imagen-comercio'>
                    <img src={this.props.store.storeImage} onError={<div>holis</div>}/>
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
                    <p className="rubros">{this.parseStoreCategories()}</p>
                </div>
            </div>
        )
    }
}
Store.contextType = LanguageContext;
export default withRouter(Store);