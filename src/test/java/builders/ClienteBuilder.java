package builders;

import model.Cliente;

public class ClienteBuilder {

    private String nombreUsuario = "Pepe";

    public static ClienteBuilder unCliente() {
        return new ClienteBuilder();
    }

    public Cliente build() {
        return new Cliente(nombreUsuario);
    }

    public ClienteBuilder conUsuario(String unUsuario) {
        nombreUsuario = unUsuario;
        return this;
    }
}
