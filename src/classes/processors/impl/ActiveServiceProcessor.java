package classes.processors.impl;

import classes.response.impl.ActiveServiceResponse;
import classes.response.ResponseDTO;
import classes.model.ActiveService;
import classes.processors.ResponseProcessor;
import java.util.List;

public class ActiveServiceProcessor implements ResponseProcessor {

    @Override
    public List<ActiveService> process(ResponseDTO response) {
        ActiveServiceResponse activeServiceResponse = (ActiveServiceResponse) response;
        return activeServiceResponse.getAllActiveServices();
    }

}
