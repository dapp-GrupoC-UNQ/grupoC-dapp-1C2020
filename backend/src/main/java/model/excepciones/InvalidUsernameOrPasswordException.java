package model.excepciones;

public class InvalidUsernameOrPasswordException extends RuntimeException{

    public InvalidUsernameOrPasswordException() {super("The username cannot be empty");}
}
