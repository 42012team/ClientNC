package classes.processors.impl;

import classes.processors.ResponseProcessor;
import classes.response.ResponseDTO;
import classes.response.impl.ActionResponse;
import java.util.Collections;
import java.util.List;

public class ActionTypeProcessor implements ResponseProcessor {

    @Override
    public List<ActionResponse> process(ResponseDTO response) {
        ActionResponse actionResponse = (ActionResponse) response;
        return Collections.singletonList(actionResponse);
    }

}
