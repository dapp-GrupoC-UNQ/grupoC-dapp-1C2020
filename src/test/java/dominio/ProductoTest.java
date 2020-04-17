package dominio;
import builders.ProductoBuilder;
import model.Producto;
import model.excepciones.ProductoConPrecioNegativoException;
import model.excepciones.ProductoConStockNegativoException;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoTest {

    @Test
    public void unProductoTieneUnNombre() {
        Producto jugo = ProductoBuilder.unProducto().conNombre("Jugo de naranja").build();
        assertEquals(jugo.nombre(), "Jugo de naranja");
    }

    @Test
    public void unProductoTieneUnaMarca() {
        Producto jugo = ProductoBuilder.unProducto().conMarca("Tang").build();
        assertEquals(jugo.marca(), "Tang");
    }

    @Test
    public void unProductoTieneUnStock() {
        Producto producto = ProductoBuilder.unProducto().conStock(12).build();
        assertEquals(producto.stock(), 12);
    }

    @Test
    public void unProductoNoPuedeTenerUnStockMenorACero() {
        assertThrows(ProductoConStockNegativoException.class, () -> ProductoBuilder.unProducto().conStock(-6).build());
    }

    @Test
    public void unProductoTieneUnPrecio() {
        Producto producto = ProductoBuilder.unProducto().conPrecio(5.0).build();
        assertEquals(producto.precio(), 5.0);
    }

    @Test
    public void unProductoNoPuedeTenerUnPrecioMenorACero() {
        assertThrows(ProductoConPrecioNegativoException.class, () -> ProductoBuilder.unProducto().conPrecio(-6.0).build());
    }
}
