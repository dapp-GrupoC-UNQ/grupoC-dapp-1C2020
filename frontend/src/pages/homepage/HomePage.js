import {withRouter} from "react-router-dom";
import * as React from "react";
import { faMapMarkerAlt, faStore, faArrowCircleRight } from '@fortawesome/free-solid-svg-icons'
import "./homepage.scss"
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

class HomePage extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            entitites: this.comercios,
            entityRenderFunction: this.renderStore
        }
    }
    comercios = [{
        nombreComercio: "No hay por qué",
        distanciaComercio: '2.5km',
        rubrosComercio: 'carniceria, limpieza, verduleria'
    },
        {
            nombreComercio: "Lo de tito",
            distanciaComercio: '0.9km',
            rubrosComercio: 'carniceria, verduleria'
        }
    ]

    categories = [{
        nombreDeCategoria: "Almacen"
    },
        {
            nombreDeCategoria: "Limpieza"
        },
]

    discounts = [{
        discountText: "2x1 en cerveza Brahama de 1L. Llevando 2 pagas cada una $40",
        storeName: "Lo de tito"
    },
        {
        discountText: "25% de descuento en fideos Matarazzo. Antes $100 - Ahora $75 ",
        storeName: "No hay por que"
    }
    ]

    renderStore = (store) => {
        return (
            <div className="carta-comercio">
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
            <div className="category">
                {category.nombreDeCategoria}
                {category.storeName}
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
        this.setState({entities: this.comercios, entityRenderFunction: this.renderStore});
    }

    showCategories = () => {
        this.setState({entities: this.categories, entityRenderFunction: this.renderCategory});
    }

    showDiscount = () => {
        this.setState({entities: this.discounts, entityRenderFunction: this.renderDiscount});
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
                  <div className="comercios">
                      {this.state.entitites.map(entity => this.state.entityRenderFunction(entity))}
                  </div>
            </div>
        )
    }

}

export default withRouter(HomePage);