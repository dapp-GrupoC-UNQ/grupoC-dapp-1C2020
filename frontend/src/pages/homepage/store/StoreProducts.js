import * as React from "react";
import '../homepage.scss'
import {withRouter} from "react-router-dom";
import StoreService from "../../../servicios/StoreService";
import LoadingSpinner from "../../../components/loading-spinner/LoadingSpinner";
import Product from "../product/Product";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faShoppingBasket} from "@fortawesome/free-solid-svg-icons";
import {LanguageContext} from "../../../constants/LanguageMaps";




class StoreProducts extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            storeId: this.props.match.params.id,
            loadingEntitiesState: true
        }
    }

    componentDidMount() {
        this.showStoreProducts(this.state.storeId);
    }

    showStoreProducts = (storeId) => {
        this.setState({loadingEntitiesState: true})
        StoreService().getStoreProducts(storeId)
            .then(result => {
                if(result.data.merchandises.length === 0) {
                    this.setState({products: this.addStoresToProducts(result.data.merchandises, result.data.store.id, result.data.store.storeName), loadingEntitiesState: false, dataToShow: false, store: result.data.store })
                } else {
                    this.setState({products: this.addStoresToProducts(result.data.merchandises, result.data.store.id, result.data.store.storeName), loadingEntitiesState: false, dataToShow: true, store: result.data.store})
                }
            })
    }

    addStoresToProducts = (listOfProducts, storeId, storeName) =>{
        return listOfProducts.map(product => {
            product.storeName = storeName;
            product.storeId = storeId;
            return product
        })
    }

    renderProducts = (product) => <Product product={product} productIsInCart={this.props.productIsInCart} onAddToCart={this.props.addProductToCart} onRemoveFromCart={this.removeFromCart}/>

    render() {
        return(
            <div className="homepage">
                <div className="entities-panel">
                    {this.state.loadingEntitiesState && <LoadingSpinner isLoading={this.state.loadingEntitiesState}/>}
                    {!this.state.isLoading && this.state.dataToShow &&
                    <div className="entities products-entities">
                        {this.state.products.map(product => this.renderProducts(product))}
                    </div>
                    }
                    {!this.state.dataToShow && !this.state.loadingEntitiesState &&
                        <div className="no-products">
                            <FontAwesomeIcon icon={faShoppingBasket}/>
                            <span>{this.context.noProducts}</span>
                        </div>
                    }
                </div>
            </div>
        )
    }
}
StoreProducts.contextType = LanguageContext;
export default withRouter(StoreProducts);