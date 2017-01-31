package classes.processors;

import classes.exceptions.TransmittedException;
import classes.response.ResponseDTO;
import java.util.List;

public interface ResponseProcessor {

    public List process(ResponseDTO response) throws TransmittedException;

}
