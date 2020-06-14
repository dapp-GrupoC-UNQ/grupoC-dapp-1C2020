import {withRouter} from "react-router-dom";
import * as React from "react";
import "./homepage.scss"
import {categories, discounts} from "../../constants";
import SideBar from "./side-bar/SideBar";
import StoreService from "../../servicios/StoreService";
import LoadingSpinner from "../../components/loading-spinner/LoadingSpinner";
import Product from "./product/Product";
import Category from "./category/Category";
import Store from "./store/Store";
import Discount from "./discount/Discount";
import ShoppingCart from "./ShoppingCart/ShoppingCart";
import {faShoppingBasket} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {LanguageContext} from "../../constants/LanguageMaps";

class HomePage extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            entities: [], //Tengo que inicializar mi estado entonces uso una lista vacia hasta que consiga los comercios
            loadingEntitiesState: false,
            entityRenderFunction: this.renderStore,
            showingShoppingCart: false,
            productsInCart: []
            //Es importante tener toda la estructura del state planteada antes de ir a buscar cosas al backend para evitar undefines.
        }
    }

    componentDidMount() {
        //Esto se va a ejecutar automaticamente despues de que este componente se haya renderizado la primera vez
        this.setState({ loadingEntitiesState: true });
        this.showStores();
    }

    renderStore = (store) => <Store store={store} onShowStoreProducts={this.showStoreProducts}/>

    renderProducts = (product) => <Product product={product} productIsInCart={this.productIsInCart} onAddToCart={this.addToCart} onRemoveFromCart={this.removeFromCart}/>

    renderCategory = (category) => <Category category={category} onSelectCategory={this.showStoresWithACategory}/>

    renderDiscount = (discount) => <Discount discount={discount}/>

    showStores = () =>{
        StoreService().getAllStores()
            .then(result => {
                this.setState({entities: result.data, entityRenderFunction: this.renderStore, loadingEntitiesState: false, showingShoppingCart: false, dataToShow: true})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios")
            });
    }

    showCategories = () => {
        this.setState({entities: categories, entityRenderFunction: this.renderCategory, showingShoppingCart: false});
    }

    showDiscounts = () => {
        this.setState({entities: discounts, entityRenderFunction: this.renderDiscount, showingShoppingCart: false});
    }

    showStoresWithACategory = (category) => {
        StoreService().getAllStoresWithACategory(category.categoryName)
            .then(result => {
                this.setState({entities: result.data, entityRenderFunction: this.renderStore, loadingEntitiesState: false})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios de esa categoria")
            });
    }

    addStoresToProducts = (listOfProducts, storeId) =>{
        return listOfProducts.map(product => {
            product.storeId = storeId;
            return product
        })
    }

    showStoreProducts = (store) => {
        this.setState({loadingEntitiesState: true})
        StoreService().getStoreProducts(store)
            .then(result => {
                if(result.data.merchandises.length === 0) {
                    this.setState({loadingEntitiesState: false, entities: result.data.merchandises, dataToShow: false})
                } else {
                    this.setState({entities: this.addStoresToProducts(result.data.merchandises, result.data.storeId), entityRenderFunction: this.renderProducts, dataToShow: true, loadingEntitiesState: false})
                }
            })
    }

    showShoppingCart = () => this.setState({showingShoppingCart: true})
    addToCart = (product) => {
        if(!this.productIsInCart(product)){
            this.setState({productsInCart: [...this.state.productsInCart, product]});
        }
    }

    sameProduct = (productA, productB) => {
        //UNA MIERDAAAA. HAY QUE CAMBIARLO CUANDO TENGAMOS LOS IDS
        return productA.name === productB.name && productA.brand === productB.brand && productA.storeName === productB.storeName;
    }

    productIsInCart = (product) => this.state.productsInCart.some(productInCart => this.sameProduct(productInCart, product))
    removeFromCart = (product) => {
        const newProductsList = this.state.productsInCart.filter(productInCart => productInCart !== product)
        this.setState({productsInCart: newProductsList})
    }

    render() {
        return(
            <div className="homepage">
                  <SideBar showStores={this.showStores}
                           showCategories={this.showCategories}
                           showDiscounts={this.showDiscounts}
                           cart={this.showShoppingCart}
                           logOut={this.props.logOut}
                           changeLanguage={this.props.changeLanguage}
                  />
                <div className="entities-panel">
                    {this.state.isLoading && <LoadingSpinner isLoading={this.state.loadingEntitiesState}/>}
                    {!this.state.isLoading && !this.state.showingShoppingCart && this.state.dataToShow &&
                        <div className="entities">
                            {this.state.entities.map(entity => this.state.entityRenderFunction(entity))}
                        </div>
                    }
                    {!this.state.dataToShow &&
                        <div className="no-products">
                            <FontAwesomeIcon icon={faShoppingBasket}/>
                            <span>{this.context.noProducts}</span>
                        </div>
                    }
                    {this.state.showingShoppingCart && <ShoppingCart showCart={this.state.showingShoppingCart} products={this.state.productsInCart} removeFromCart={this.removeFromCart}/>}
                </div>
            </div>
        )
    }
}
HomePage.contextType = LanguageContext;

export default withRouter(HomePage);