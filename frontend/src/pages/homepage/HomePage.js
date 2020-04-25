import {withRouter} from "react-router-dom";
import * as React from "react";
import { faMapMarkerAlt, faStore, faArrowCircleRight } from '@fortawesome/free-solid-svg-icons'
import "./homepage.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {categories, comercios, discounts} from "../../constants";

class HomePage extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            entities: comercios,
            entityRenderFunction: this.renderStore
        }
    }

    renderStore = (store) => {
        return (
            <div className="entity-card">
                <div className='imagen-comercio'>
                    <img src={'https://www.memesargentinos.com.ar/wp-content/uploads/2019/02/China-Supermercado.jpg'}/>
                </div>
                <div className='nombre-comercio'>
                    {store.nombreComercio}
                </div>
                <div className='distancia-comercio'>
                    <FontAwesomeIcon icon={faMapMarkerAlt}/>
                    <p className="distancia">{store.distanciaComercio}</p>
                </div>
                <div className="rubros-comercio">
                    <FontAwesomeIcon icon={faStore}/>
                    <p className="rubros">{store.rubrosComercio}</p>
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
            <div className="discounts">
                {discount.discountText}
            </div>
        )
    }

    showStores = () =>{
        this.setState({entities: comercios, entityRenderFunction: this.renderStore});
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