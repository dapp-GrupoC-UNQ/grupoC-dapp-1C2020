import {withRouter} from "react-router-dom";
import * as React from "react";
import { faMapMarkerAlt, faStore } from '@fortawesome/free-solid-svg-icons'
import "./homepage.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {categories, discounts} from "../../constants";
import SideBar from "./side-bar/SideBar";
import StoreService from "../../servicios/StoreService";
import LoadingSpinner from "../../components/loading-spinner/LoadingSpinner";

class HomePage extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            entities: [], //Tengo que inicializar mi estado entonces uso una lista vacia hasta que consiga los comercios
            loadingEntitiesState: false,
            entityRenderFunction: this.renderStore
            //Es importante tener toda la estructura del state planteada antes de ir a buscar cosas al backend para evitar undefines.
        }
    }

    componentDidMount() {
        //Esto se va a ejecutar automaticamente despues de que este componente se haya renderizado la primera vez
        this.setState({ loadingEntitiesState: true });
        this.showStores();
    }

    renderStore = (store) => {
        return (
            <div className="entity-card" onClick={() => this.showStoreProducts(store)}>
                <div className='imagen-comercio'>
                    <img src={'https://www.memesargentinos.com.ar/wp-content/uploads/2019/02/China-Supermercado.jpg'}/>
                </div>
                <div className='nombre-comercio'>
                    {store.storeName}
                </div>
                <div className='distancia-comercio'>
                    <FontAwesomeIcon icon={faMapMarkerAlt}/>
                    <p className="distancia">{store.storeAdress}</p>
                </div>
                <div className='rubros-comercio'>
                    <FontAwesomeIcon icon={faStore}/>
                    <p className="rubros">{store.storeCategory}</p>
                </div>
            </div>
        )
    }

    renderProducts = (product) => {
        return (<div className="entity-card product-card">
                    <div className='imagen-comercio'>
                        <img src={'https://digitalcontent.api.tesco.com/v2/media/ghs/744eaf3a-ccd5-4188-a60e-001845bd74b6/snapshotimagehandler_689352616.jpeg?h=540&w=540'}/>
                    </div>
                    <div className='product-name'>
                        {product.name}
                    </div>
                    <div className="product-footer">
                        <div className='product-brand'>
                            {product.brand}
                        </div>
                        <div className='product-price'>
                            <p className="price">${product.price}</p>
                        </div>
                    </div>
                </div>)
    }

    renderCategory = (category) => {
        return(
            <div className="entity-card category-card" onClick={() => this.showStoresWithACategory(category.categoryName)}>
                <div className='imagen-comercio'>
                    <img src={category.categoryImageURL}/>
                </div>
                <div className='nombre-comercio'>
                    {category.categoryName}
                </div>
            </div>
        )

    }

    renderDiscount = (discount) => {
        return (
            <div className="entity-card">
                <div className='imagen-comercio'>
                    <img src={discount.discountImageURL}/>
                </div>
                <div className='nombre-comercio'>
                    {discount.discountText}
                </div>
                <div className="discount-store">
                    <FontAwesomeIcon icon={faStore}/>
                    {discount.storeName}
                </div>
            </div>
        )
    }

    showStores = () =>{
        StoreService().getAllStores()
            .then(result => {
                this.setState({entities: result.data, entityRenderFunction: this.renderStore, loadingEntitiesState: false})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios")
            });
    }

    showCategories = () => {
        this.setState({entities: categories, entityRenderFunction: this.renderCategory});
    }

    showDiscounts = () => {
        this.setState({entities: discounts, entityRenderFunction: this.renderDiscount});
    }

    showStoresWithACategory = (category) => {
        StoreService().getAllStoresWithACategory(category)
            .then(result => {
                this.setState({entities: result.data, entityRenderFunction: this.renderStore, loadingEntitiesState: false})
            })
            .catch(error => {
                alert("Uy, no pudimos cargar los comercios de esa categoria")
            });
    }

    showStoreProducts = (store) => {
        StoreService().getStoreProducts(store)
            .then(result => {
                this.setState({entities: result.data, entityRenderFunction: this.renderProducts, loadingEntitiesState: false})
            })
    }

    render() {
        return(
            <div className="homepage">
                {/*Extraje la SideBar a otro componente para que HomePage no crezca mucho*/}
                {/*Voy a pasarle a la SideBar las props que necesite usar para actualizar mi lista de entidades cuando el usuario haga click*/}
                  <SideBar showStores={this.showStores}
                           showCategories={this.showCategories}
                           showDiscounts={this.showDiscounts}
                  />
                  {/*El spinner solo se renderiza cuando el estado de la homepage tenga loadingEntitiesState en true*/}
                  {this.state.isLoading && <LoadingSpinner isLoading={this.state.loadingEntitiesState}/>}
                  {!this.state.isLoading && <div className="entities">
                      {this.state.entities.map(entity => this.state.entityRenderFunction(entity))}
                  </div>}
            </div>
        )
    }

}

export default withRouter(HomePage);