import {withRouter} from "react-router-dom";
import * as React from "react";
import "./homepage.scss"
import StoreService from "../../servicios/StoreService";
import LoadingSpinner from "../../components/loading-spinner/LoadingSpinner";
import Store from "./store/Store";
import {LanguageContext} from "../../constants/LanguageMaps";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faStoreSlash} from "@fortawesome/free-solid-svg-icons/faStoreSlash";

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
                if(result.data.length === 0){
                    this.setState({stores: result.data, loadingEntitiesState: false, showingShoppingCart: false, dataToShow: false})
                }else {
                    this.setState({
                        stores: result.data,
                        loadingEntitiesState: false,
                        showingShoppingCart: false,
                        dataToShow: true
                    })
                }
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
                    {!this.state.loadingEntitiesState && !this.state.showingShoppingCart && this.state.dataToShow &&
                        <div className="entities">
                            {this.state.stores.map(store => this.renderStore(store))}
                        </div>
                    }
                    {!this.state.dataToShow && !this.state.loadingEntitiesState &&
                    <div className="no-products">
                        <FontAwesomeIcon icon={faStoreSlash}/>
                        <span>{this.context.noStores}</span>
                    </div>
                    }
                </div>
            </div>
        )
    }
}
Stores.contextType = LanguageContext;

export default withRouter(Stores);