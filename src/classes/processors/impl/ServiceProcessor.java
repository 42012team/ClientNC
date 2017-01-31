package classes.processors.impl;

import classes.response.impl.ServiceResponse;
import classes.response.ResponseDTO;
import classes.model.Service;
import classes.processors.ResponseProcessor;
import java.util.List;

public class ServiceProcessor implements ResponseProcessor {

    @Override
    public List<Service> process(ResponseDTO response) {
        ServiceResponse servicesResponse = (ServiceResponse) response;
        return servicesResponse.getServices();
    }

}
