import * as React from "react";
import "./imagenesProductos.scss"
import mandarina from "./mandarina.png"
import pan from "./pan.png"
import jabon from "./soap.png"
import cookie from "./cookie.png"
import bolsas from "./bolsas.png"
import vaso from "./vaso.png"
import libros from "./libros.png"
import hamburguesa from "./hamburguesa.png"
import manzana from "./manzana.png"
import pizza from "./pizza.png"
import rugby from "./rugby.png"

class ImagenesProductos extends React.Component {
    render() {
        return (
            <div className="productos">
                <div className="columna-productos">
                    <div className="mandarina">
                        <img src={mandarina}/>
                    </div>
                    <div className="jabon">
                        <img src={jabon}/>
                    </div>
                    <div className="cookie">
                        <img src={cookie}/>
                    </div>
                    <div className="hamburguesa">
                        <img src={hamburguesa}/>
                    </div>
                    <div className="manzana">
                        <img src={manzana}/>
                    </div>
                    <div className="pizza">
                        <img src={pizza}/>
                    </div>
                </div>
                <div className="columna-productos">
                    <div className="pan">
                        <img src={pan}/>
                    </div>
                    <div className="bolsas">
                        <img src={bolsas}/>
                    </div>
                    <div className="vaso">
                        <img src={vaso}/>
                    </div>
                    <div className="libros">
                        <img src={libros}/>
                    </div>
                    <div className="rugby">
                        <img src={rugby}/>
                    </div>
                </div>
            </div>
            )
    }
}

export default ImagenesProductos;