package model.excepciones;

public class NotFoundUserException  extends RuntimeException {
    public NotFoundUserException(){
        super("There is not a registered user with that username");
    }
}

