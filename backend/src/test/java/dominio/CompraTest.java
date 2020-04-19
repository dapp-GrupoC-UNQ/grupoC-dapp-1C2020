package dominio;

import builders.CompraBuilder;
import model.Compra;
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
        Compra compra = CompraBuilder.unaCompra().conMontoTotal(1525.30).build();
        assertEquals(1525.30, compra.montoTotal());
    }

    @Test
    public void unaCompraTieneUnTipoDeEnvio(){
        Compra compra = CompraBuilder.unaCompra().conTipoDeEnvio("Envio a domicilio").build();
        assertEquals("Envio a domicilio", compra.tipoEnvio());
    }

}