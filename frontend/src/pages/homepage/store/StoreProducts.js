import * as React from "react";
import '../homepage.scss'
import {withRouter} from "react-router-dom";
import StoreService from "../../../servicios/StoreService";
import LoadingSpinner from "../../../components/loading-spinner/LoadingSpinner";
import Product from "../product/Product";


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
                    this.setState({loadingEntitiesState: false, dataToShow: false})
                } else {
                    this.setState({products: this.addStoresToProducts(result.data.merchandises, result.data.storeId), dataToShow: true, loadingEntitiesState: false})
                }
            })
    }

    addStoresToProducts = (listOfProducts, storeId) =>{
        return listOfProducts.map(product => {
            product.storeId = storeId;
            return product
        })
    }

    renderProducts = (product) => <Product product={product} productIsInCart={this.productIsInCart} onAddToCart={this.addToCart} onRemoveFromCart={this.removeFromCart}/>

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
                </div>
            </div>
        )
    }
}
export default withRouter(StoreProducts);