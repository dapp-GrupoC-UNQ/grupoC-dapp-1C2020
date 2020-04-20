package dominio;

import builders.CompraBuilder;
import builders.MercaderiaBuilder;
import model.Compra;
import model.Mercaderia;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {

    @Test
    public void unaCompraTieneUnMedioDePago(){
        Compra compra = CompraBuilder.unaCompra().conMedioDePago("Efectivo").build();
        assertEquals("Efectivo", compra.medioDePago());
    }

    @Test
    public void unaCompraTieneUnMontoTotal(){
        Mercaderia fideos = MercaderiaBuilder.unaMercaderia().conProducto("Fideos", "Knorr").conPrecio(40.25).build();
        Mercaderia jugo = MercaderiaBuilder.unaMercaderia().conProducto("Jugo", "Tang").conPrecio(8.0).build();
        Compra compra = CompraBuilder.unaCompra().build();
        compra.agregarMercaderia(fideos);
        compra.agregarMercaderia(jugo);
        assertEquals(48.25, compra.montoTotal());
    }

    @Test
    public void unaCompraTieneUnTipoDeEnvio(){
        Compra compra = CompraBuilder.unaCompra().conTipoDeEnvio("Envio a domicilio").build();
        assertEquals("Envio a domicilio", compra.tipoEnvio());
    }



}