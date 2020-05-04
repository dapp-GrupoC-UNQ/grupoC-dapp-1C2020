import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowCircleRight} from "@fortawesome/free-solid-svg-icons";
import "./side-bar.scss"

class SideBar extends React.Component {
    render() {
        return (
            <div className="side-bar">
                <div className="bar-title">
                    Busca tu producto
                </div>
                <div className="links-container">
                    <div className="link">
                        {/*Voy a usar las props para actualizar el estado de mi padre. Es decir, la lista de entidades que muestra mi padre*/}
                        <a className="link-search" onClick={this.props.showStores}>Comercios</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.showCategories}>Rubros</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.showDiscounts}>Ofertas</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.showDiscounts}>Mi Carrito</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                </div>
            </div>
        );
    }
}

export default  SideBar