package dominio;
import builders.ProductoBuilder;
import model.Producto;
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
}
