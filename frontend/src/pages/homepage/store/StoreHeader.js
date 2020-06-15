import * as React from "react";
import './store-header.scss';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMapMarkerAlt} from "@fortawesome/free-solid-svg-icons";
import {LanguageContext} from "../../../constants/LanguageMaps";
import {categories} from "../../../constants";

class StoreHeader extends React.Component {
    constructor(props) {
        super(props);
    }

    parseStoreCategories = () => {

        return this.props.store.storeCategories.map(category => this.context.categories[category])
    }

    render() {
        return(
            <div className="store-header">
                <div className="store-image">
                    <img src={this.props.store.storeImage}/>
                </div>
                <div className="store-data">
                    <div className="store-name">
                        <span>{this.props.store.storeName}</span>
                    </div>
                    <div className="store-address">
                        <FontAwesomeIcon icon={faMapMarkerAlt}/>
                        <span>{this.props.store.storeAddress}</span>
                    </div>
                    <div className="store-categories">
                        {this.props.store.storeCategories.map(category => category)}
                    </div>
                    <div className="store-delivery">
                        <span>{this.context.maximumDeliveryDistance + this.props.store.deliveryDistanceInKm + 'km'}</span>
                    </div>
                    <div className="store-schedule">

                    </div>
                </div>
            </div>
        )
    }
}
StoreHeader.contextType = LanguageContext;
export default StoreHeader;