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
            stores: [], //Tengo que inicializar mi estado entonces uso una lista vacia hasta que consiga los comercios
            loadingEntitiesState: false,
            entityRenderFunction: this.renderStore,
            showingShoppingCart: false,
            dataToShow: true,
            productsInCart: []
        }
    }

    componentDidMount() {
        //Esto se va a ejecutar automaticamente despues de que este componente se haya renderizado la primera vez
        this.setState({ loadingEntitiesState: true });
        this.showStores();
    }

    renderStore = (store) => <Store store={store}/>

    /*renderCategory = (category) => <Category category={category} onSelectCategory={this.showStoresWithACategory}/>

    renderDiscount = (discount) => <Discount discount={discount}/>*/

    showStores = () =>{
        StoreService().getAllStores()
            .then(result => {
                this.setState({stores: result.data, loadingEntitiesState: false, showingShoppingCart: false, dataToShow: true})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios")
            });
    }

   /* showCategories = () => {
        this.setState({entities: categories, entityRenderFunction: this.renderCategory, showingShoppingCart: false});
    }

    showDiscounts = () => {
        this.setState({entities: discounts, entityRenderFunction: this.renderDiscount, showingShoppingCart: false});
    }*/

  /*  showStoresWithACategory = (category) => {
        StoreService().getAllStoresWithACategory(category.categoryName)
            .then(result => {
                this.setState({entities: result.data, entityRenderFunction: this.renderStore, loadingEntitiesState: false})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios de esa categoria")
            });
    }*/

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