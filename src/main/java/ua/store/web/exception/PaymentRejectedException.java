package ua.store.web.exception;

public class PaymentRejectedException extends RuntimeException{

    public PaymentRejectedException() {
    }

    public PaymentRejectedException(String message) {
        super(message);
    }

    public PaymentRejectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentRejectedException(Throwable cause) {
        super(cause);
    }

    public PaymentRejectedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
