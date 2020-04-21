import * as React from "react";
import "./modalregistrousuario.scss"

class ModalRegistroUsuario extends React.Component {
    render() {
        return(
            <div className="modal">
                <div className="modal-background"></div>
                <div className="modal-card">
                    <header className="modal-card-head">
                        <p className="modal-card-title">Registrate como Usuario</p>
                        <button className="delete" aria-label="close"></button>
                    </header>
                    <section className="modal-card-body">

                    </section>
                    <footer className="modal-card-foot">
                    </footer>
                </div>
            </div>
        )
    }
}

export default ModalRegistroUsuario;