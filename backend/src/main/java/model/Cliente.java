package model;

import model.excepciones.NombreDeUsuarioInvalidoException;

public class Cliente {

    private String nombreUsuario;

    public Cliente(String usuario){
        if(usuario.isEmpty()){
            throw new NombreDeUsuarioInvalidoException();
        }
        nombreUsuario = usuario;
    }

    public String usuario() { return this.nombreUsuario;   }
}
