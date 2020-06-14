import * as React from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowCircleRight, faShoppingCart, faDoorOpen, faUser} from "@fortawesome/free-solid-svg-icons";
import "./side-bar.scss"
import UserUpdateModal from "../user-data/UserUpdateModal";

class SideBar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userProfileModalOpen: false
        }
    }

    openUserProfileModal = () => this.setState({userProfileModalOpen: true})
    closeUserProfileModal = () => this.setState({userProfileModalOpen: false})
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
                        <a className="link-search" onClick={this.props.cart}>Ver mi carrito</a>
                        <FontAwesomeIcon icon={faShoppingCart}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.openUserProfileModal}>Mi cuenta</a>
                        <FontAwesomeIcon onClick={this.openUserProfileModal} icon={faUser}/>
                    </div>
                    <div className="link">
                        <a className="link-search" onClick={this.props.logOut}>Salir</a>
                        <FontAwesomeIcon onClick={this.props.logOut} icon={faDoorOpen}/>
                    </div>
                </div>
                {this.state.userProfileModalOpen && <UserUpdateModal user={this.props.loggedUser} onClose={this.closeUserProfileModal}/>}
            </div>
        );
    }
}

export default  SideBar