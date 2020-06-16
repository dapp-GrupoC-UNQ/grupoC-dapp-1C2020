import * as React from "react";
import {withRouter} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowCircleRight, faShoppingCart, faDoorOpen} from "@fortawesome/free-solid-svg-icons";
import "./side-bar.scss"
import {LanguageContext, LanguageMaps} from "../../../constants/LanguageMaps";
import english from '../../loginpage/imagenes-home-page/english.png';
import spanish from '../../loginpage/imagenes-home-page/spanish.png';

class SideBar extends React.Component {

    constructor(props) {
        super(props);
    }

    goToStores = () => this.props.history.push('/stores')
    goToShoppingCart = () => this.props.history.push('/cart')
    goToCategories = () => this.props.history.push('/categories')
    logOut = () => {
        this.props.history.push('/')
        this.props.onLogout();
    }

    render() {
        return (
            <div className="side-bar">
                <div className="bar-title">
                    {this.context.sideBarTitle}
                </div>
                <div className="links-container">
                    <div className="link">
                        {/*Voy a usar las props para actualizar el estado de mi padre. Es decir, la lista de entidades que muestra mi padre*/}
                        <a className="link-search" onClick={this.goToStores}>{this.context.sideBarStore}</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.goToCategories}>{this.context.categories}</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                   {/* <div className="link">
                        <a className="link-search" onClick={this.props.showDiscounts}>{this.context.discounts}</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>*/}
                    <div className="link">
                        <a className="link-search" onClick={this.goToShoppingCart}>{this.context.seeMyCart}</a>
                        <FontAwesomeIcon icon={faShoppingCart}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.logOut}>{this.context.logOut}</a>
                        <FontAwesomeIcon onClick={this.logOut} icon={faDoorOpen}/>
                    </div>
                    <div className="languages">
                        <div className="language" onClick={() => this.props.changeLanguage(LanguageMaps.english)}>
                            <img src={english}/>
                        </div>
                        <div className="language" onClick={() => this.props.changeLanguage(LanguageMaps.spanish)}>
                            <img src={spanish}/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
SideBar.contextType = LanguageContext;
export default  withRouter(SideBar);