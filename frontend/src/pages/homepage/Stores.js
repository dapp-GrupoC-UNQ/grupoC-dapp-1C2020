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
            //Es importante tener toda la estructura del state planteada antes de ir a buscar cosas al backend para evitar undefines.
        }
    }

    componentDidMount() {
        //Esto se va a ejecutar automaticamente despues de que este componente se haya renderizado la primera vez
        this.setState({ loadingEntitiesState: true });
        this.showStores();
    }

    renderStore = (store) => <Store store={store}/>

   /* renderProducts = (product) => <Product product={product} productIsInCart={this.productIsInCart} onAddToCart={this.addToCart} onRemoveFromCart={this.removeFromCart}/>

    renderCategory = (category) => <Category category={category} onSelectCategory={this.showStoresWithACategory}/>

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

    /*addStoresToProducts = (listOfProducts, storeId) =>{
        return listOfProducts.map(product => {
            product.storeId = storeId;
            return product
        })
    }*/

    showShoppingCart = () => this.setState({showingShoppingCart: true, dataToShow: true})
   /* addToCart = (product) => {
        if(!this.productIsInCart(product)){
            this.setState({productsInCart: [...this.state.productsInCart, product]});
        }
    }*/

    sameProduct = (productA, productB) => {
        //UNA MIERDAAAA. HAY QUE CAMBIARLO CUANDO TENGAMOS LOS IDS
        return productA.name === productB.name && productA.brand === productB.brand && productA.storeName === productB.storeName;
    }

/*    productIsInCart = (product) => this.state.productsInCart.some(productInCart => this.sameProduct(productInCart, product))
    removeFromCart = (product) => {
        const newProductsList = this.state.productsInCart.filter(productInCart => productInCart !== product)
        this.setState({productsInCart: newProductsList})
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
                    {/*{!this.state.dataToShow && !this.state.loadingEntitiesState &&
                        <div className="no-products">
                            <FontAwesomeIcon icon={faShoppingBasket}/>
                            <span>{this.context.noProducts}</span>
                        </div>
                    }*/}
{/*
                    {this.state.showingShoppingCart && <ShoppingCart showCart={this.state.showingShoppingCart} products={this.state.productsInCart} removeFromCart={this.removeFromCart}/>}
*/}
                </div>
            </div>
        )
    }
}
Stores.contextType = LanguageContext;

export default withRouter(Stores);