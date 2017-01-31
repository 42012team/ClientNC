package classes.request.impl;

import classes.request.RequestDTO;
import classes.model.ServiceStatus;
import java.io.Serializable;

public class TransmittedServiceParams implements RequestDTO, Serializable {

    private String requestType;
    private Integer serviceId;
    private String name;
    private String description;
    private ServiceStatus serviceStatus;
    private String serviceType;
    private int userId;
    private int version;
    private long unlockingTime;

    TransmittedServiceParams() {
    }

    public static TransmittedServiceParams create() {
        return new TransmittedServiceParams();
    }

    public TransmittedServiceParams withRequestType(String type) {
        this.requestType = type;
        return this;
    }

    public TransmittedServiceParams withServiceId(Integer serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public TransmittedServiceParams withName(String name) {
        this.name = name;
        return this;
    }

    public TransmittedServiceParams withDescription(String description) {
        this.description = description;
        return this;
    }

    public TransmittedServiceParams withType(String type) {
        this.serviceType = type;
        return this;
    }

    public TransmittedServiceParams withStatus(ServiceStatus status) {
        this.serviceStatus = status;
        return this;
    }

    public TransmittedServiceParams withUserId(int id) {
        this.userId = id;
        return this;
    }

    public TransmittedServiceParams withVersion(int version) {
        this.version = version;
        return this;
    }

    public TransmittedServiceParams withUnlockingTime(long unlockingTime) {
        this.unlockingTime = unlockingTime;
        return this;
    }

    @Override
    public String getRequestType() {
        return requestType;
    }

    public int getServiceId() {
        return serviceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public int getUserId() {
        return userId;
    }

    public int getVersion() {
        return version;
    }

    public long getUnlockingTime(){ return unlockingTime; };
}
