package classes.response.impl;

import classes.response.ResponseDTO;
import classes.model.ActiveService;
import java.io.Serializable;
import java.util.List;

public class ActiveServiceResponse implements ResponseDTO, Serializable {

    private String requestType;
    List<ActiveService> activeServices;

    private ActiveServiceResponse() {
    }

    public static ActiveServiceResponse create() {

        return new ActiveServiceResponse();
    }

    public ActiveServiceResponse withResponseType(String type) {
        this.requestType = type;
        return this;
    }

    public ActiveServiceResponse withActiveServices(List<ActiveService> activeServices) {
        this.activeServices = activeServices;
        return this;
    }

    public List<ActiveService> getAllActiveServices() {
        return activeServices;
    }

    @Override
    public String getResponseType() {
        return requestType;
    }

}
