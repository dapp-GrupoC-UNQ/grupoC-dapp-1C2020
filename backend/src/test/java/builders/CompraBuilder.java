package builders;

import model.Compra;
import model.EnvioADomicilio;
import model.TipoDeEnvio;

public class CompraBuilder {
    public static CompraBuilder unaCompra() {
        return new CompraBuilder();
    }

    private String medioDePago;
    private TipoDeEnvio tipoDeEnvio;
    private String direccionDeEnvio;

    public Compra build(){
        return new Compra(medioDePago, tipoDeEnvio);
    }

    public CompraBuilder conMedioDePago(String tipoPago) {
        medioDePago = tipoPago;
        return this;
    }

    public CompraBuilder conTipoDeEnvio(TipoDeEnvio envio) {
        tipoDeEnvio = envio;
        return this;
    }

    public CompraBuilder conDireccionDeEnvio(String direccion) {
        direccionDeEnvio = direccion;
        return this;
    }
}