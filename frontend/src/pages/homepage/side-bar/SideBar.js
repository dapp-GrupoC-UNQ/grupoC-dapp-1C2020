import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowCircleRight, faShoppingCart, faDoorOpen} from "@fortawesome/free-solid-svg-icons";
import "./side-bar.scss"
import {LanguageContext, LanguageMaps} from "../../../constants/LanguageMaps";
import english from '../../loginpage/imagenes-home-page/english.png';
import spanish from '../../loginpage/imagenes-home-page/spanish.png';

class SideBar extends React.Component {
    render() {
        return (
            <div className="side-bar">
                <div className="bar-title">
                    {this.context.sideBarTitle}
                </div>
                <div className="links-container">
                    <div className="link">
                        {/*Voy a usar las props para actualizar el estado de mi padre. Es decir, la lista de entidades que muestra mi padre*/}
                        <a className="link-search" onClick={this.props.showStores}>{this.context.sideBarStore}</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.showCategories}>{this.context.categories}</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.showDiscounts}>{this.context.discounts}</a>
                        <FontAwesomeIcon icon={faArrowCircleRight}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.cart}>{this.context.seeMyCart}</a>
                        <FontAwesomeIcon icon={faShoppingCart}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.logOut}>{this.context.logOut}</a>
                        <FontAwesomeIcon onClick={this.props.logOut} icon={faDoorOpen}/>
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
export default  SideBar