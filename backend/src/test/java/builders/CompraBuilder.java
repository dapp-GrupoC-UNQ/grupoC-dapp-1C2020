package builders;

import model.Compra;

public class CompraBuilder {
    public static CompraBuilder unaCompra() {
        return new CompraBuilder();
    }

    private String medioDePago;
    private String tipoDeEnvio;

    public Compra build(){
        return new Compra(medioDePago, tipoDeEnvio);
    }

    public CompraBuilder conMedioDePago(String tipoPago) {
        medioDePago = tipoPago;
        return this;
    }

    public CompraBuilder conTipoDeEnvio(String envio) {
        tipoDeEnvio = envio;
        return this;
    }
}