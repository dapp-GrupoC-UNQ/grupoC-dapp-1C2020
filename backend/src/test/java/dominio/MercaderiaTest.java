package dominio;

import builders.MercaderiaBuilder;
import model.Mercaderia;
import model.excepciones.MercaderiaConPrecioNegativoException;
import model.excepciones.MercaderiaConStockInsuficienteException;
import model.excepciones.MercaderiaConStockNegativoException;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class MercaderiaTest {

    @Test
    public void unaMercaderiaTieneSuProducto() {
        Mercaderia mercaderia = MercaderiaBuilder.unaMercaderia().conProducto("Fideos", "Knorr").build();
        assertEquals("Fideos", mercaderia.producto().nombre());
        assertEquals("Knorr", mercaderia.producto().marca());
    }

    @Test
    public void unaMercaderiaNoPuedeTenerStockNegativo() {
        assertThrows(MercaderiaConStockNegativoException.class,
                () -> MercaderiaBuilder.unaMercaderia().conStock(-4).build());
    }

    @Test
    public void unaMercaderiaNoPuedeTenerPrecioNegativo() {
        assertThrows(MercaderiaConPrecioNegativoException.class,
                () -> MercaderiaBuilder.unaMercaderia().conPrecio(-4.7).build());
    }

    @Test
    public void alAgregarStockDeUnProductoEsteSeIncrementa() {
        Mercaderia unaMercaderia = MercaderiaBuilder.unaMercaderia().conStock(20).build();
        unaMercaderia.agregarStock(2);
        assertEquals(22, unaMercaderia.stock());
    }

    @Test
    public void sePuedeDecrementarElStockDeUnaMercaderia() {
        Mercaderia unaMercaderia = MercaderiaBuilder.unaMercaderia().conStock(20).build();
        unaMercaderia.decrementarStock(2);
        assertEquals(18, unaMercaderia.stock());
    }

    @Test
    public void noSePuedeComprarUnaCantidadMayorAlStockDisponible() {
        Mercaderia unaMercaderia = MercaderiaBuilder.unaMercaderia().conStock(20).build();
        assertThrows(MercaderiaConStockInsuficienteException.class,
                () -> unaMercaderia.decrementarStock(30));
    }

    @Test
    public void unaMercaderiaPuedeActualizarSuPrecio() {
        Mercaderia unaMercaderia = MercaderiaBuilder.unaMercaderia().conPrecio(20.0).build();
        unaMercaderia.actualizarPrecio(22.34);
        assertEquals(22.34, unaMercaderia.precio());
    }

    @Test
    public void noSePuedeActualizarElPrecioDeUnaMercaderiaAUnValorNegativo() {
        Mercaderia unaMercaderia = MercaderiaBuilder.unaMercaderia().conPrecio(20.0).build();
        assertThrows(MercaderiaConPrecioNegativoException.class,
                () -> unaMercaderia.actualizarPrecio(-12.0));
    }
}
