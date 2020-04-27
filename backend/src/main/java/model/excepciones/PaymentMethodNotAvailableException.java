package model.excepciones;

public class PaymentMethodNotAvailableException extends RuntimeException {
    public PaymentMethodNotAvailableException(){super ("the payment method is not available for this commerce");}
}
