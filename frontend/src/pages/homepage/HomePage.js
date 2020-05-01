import {withRouter} from "react-router-dom";
import * as React from "react";
import { faMapMarkerAlt, faStore, faArrowCircleRight } from '@fortawesome/free-solid-svg-icons'
import "./homepage.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {categories, stores, discounts} from "../../constants";

import axios from "axios";
const SERVICE_URL = 'http://localhost:8080/';
class HomePage extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            entities: [],
            loadingEntitiesState: false,
            entityRenderFunction: this.renderStore
        }
    }

    componentDidMount() {
        this.setState({ isLoading: true });
        axios.get(`${SERVICE_URL}stores`)
            .then(result => {
                this.setState({entities: result.data, isLoading: false})
            })
            .catch(error => {
            });
    }

    renderStore = (store) => {
        return (
            <div className="entity-card">
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
            </div>
        )
    }


    renderCategory = (category) => {
        return(
            <div className="entity-card category-card">
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
        this.setState({entities: stores, entityRenderFunction: this.renderStore});
    }

    showCategories = () => {
        this.setState({entities: categories, entityRenderFunction: this.renderCategory});
    }

    showDiscount = () => {
        this.setState({entities: discounts, entityRenderFunction: this.renderDiscount});
    }
    render() {
        return(
            <div className="homepage">
                <div className="side-bar">
                    <div className="bar-title">
                        Busca tu producto
                    </div>
                    <div className="links-container">
                        <div className="link">
                            <a className="link-search" onClick={this.showStores}>Comercios</a>
                            <FontAwesomeIcon icon={faArrowCircleRight}/>
                        </div>
                        <div className="link">
                            <a className="link-search" onClick={this.showCategories}>Rubro</a>
                            <FontAwesomeIcon icon={faArrowCircleRight}/>
                        </div>
                        <div className="link">
                            <a className="link-search" onClick={this.showDiscount}>Ofeltas</a>
                            <FontAwesomeIcon icon={faArrowCircleRight}/>
                        </div>
                    </div>
                </div>
                  <div className="entities">
                      {this.state.entities.map(entity => this.state.entityRenderFunction(entity))}
                  </div>
            </div>
        )
    }

}

export default withRouter(HomePage);