package by.gourianova.apptrainer.exception;

/**
 * Project RentBike
 * Created on 14.07.2017.
 * author Shyrei Uladzimir
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
