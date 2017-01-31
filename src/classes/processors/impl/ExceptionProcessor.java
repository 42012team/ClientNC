package classes.processors.impl;

import classes.exceptions.TransmittedException;
import classes.processors.ResponseProcessor;
import classes.response.ResponseDTO;
import java.util.List;

public class ExceptionProcessor implements ResponseProcessor {

    @Override
    public List process(ResponseDTO response) throws TransmittedException {
        TransmittedException ex = (TransmittedException) response;
        throw ex;
    }

}
