package classes.exceptions;

import classes.response.ResponseDTO;
import java.io.Serializable;

public class TransmittedException extends Exception implements ResponseDTO, Serializable {

    private String message;
    private String exceptionType;

    public static TransmittedException create(String message) {
        return new TransmittedException(message);
    }

    private TransmittedException(String message) {
        this.message = message;
        exceptionType = "exception";
    }

    public TransmittedException() {

    }

    @Override
    public String getResponseType() {
        return exceptionType;
    }

    public TransmittedException withExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
