package dominio;

import model.Cliente;
import builders.ClienteBuilder;
import model.excepciones.NombreDeUsuarioInvalidoException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClienteTest {

    @Test
    public void clienteTieneUnUsuario(){
        Cliente pepe = ClienteBuilder.unCliente().conUsuario("pepe1234").build();
        assertEquals(pepe.usuario(), "pepe1234");
    }

    @Test
    public void clienteNoPuedeTenerUnUsuarioSinNombre(){
        assertThrows(NombreDeUsuarioInvalidoException.class , ()->  ClienteBuilder.unCliente().conUsuario("").build() );
    }
}
