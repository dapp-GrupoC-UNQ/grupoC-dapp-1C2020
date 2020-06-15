import {withRouter} from "react-router-dom";
import * as React from "react";
import "./homepage.scss"
import {categories, discounts} from "../../constants";
import StoreService from "../../servicios/StoreService";
import LoadingSpinner from "../../components/loading-spinner/LoadingSpinner";
import Product from "./product/Product";
import Category from "./category/Category";
import Store from "./store/Store";
import {LanguageContext} from "../../constants/LanguageMaps";

class Stores extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            stores: [],
            loadingEntitiesState: false,
            entityRenderFunction: this.renderStore,
            showingShoppingCart: false,
            dataToShow: true,
            productsInCart: [],
            storeCategory: this.props.location.search
        }
    }

    componentDidMount() {
        const params = new URLSearchParams(this.state.storeCategory);
        const category = params.get('category');
        this.setState({ loadingEntitiesState: true });
        this.showStores(category);
    }

    renderStore = (store) => <Store store={store}/>


    showStores = (category) =>{
        StoreService().getAllStores(category)
            .then(result => {
                this.setState({stores: result.data, loadingEntitiesState: false, showingShoppingCart: false, dataToShow: true})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios")
            });
    }


    render() {
        return(
            <div className="homepage">
                <div className="entities-panel">
                    {this.state.loadingEntitiesState && <LoadingSpinner isLoading={this.state.loadingEntitiesState}/>}
                    {!this.state.isLoading && !this.state.showingShoppingCart && this.state.dataToShow &&
                        <div className="entities">
                            {this.state.stores.map(store => this.renderStore(store))}
                        </div>
                    }
                </div>
            </div>
        )
    }
}
Stores.contextType = LanguageContext;

export default withRouter(Stores);