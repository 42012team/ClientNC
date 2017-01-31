package classes.response.impl;

import classes.response.ResponseDTO;
import classes.model.Service;
import java.io.Serializable;
import java.util.List;

public class ServiceResponse implements ResponseDTO, Serializable {

    private List<Service> services;
    private String requestType;

    private ServiceResponse() {
    }

    public static ServiceResponse create() {
        return new ServiceResponse();
    }

    public ServiceResponse withResponseType(String type) {
        this.requestType = type;
        return this;
    }

    public ServiceResponse withServices(List<Service> services) {
        this.services = services;
        return this;
    }

    public List<Service> getServices() {
        return services;
    }

    @Override
    public String getResponseType() {
        return requestType;
    }

}
