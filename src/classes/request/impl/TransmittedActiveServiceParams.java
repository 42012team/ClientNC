package classes.request.impl;

import classes.request.RequestDTO;
import classes.model.ActiveServiceStatus;
import java.io.Serializable;
import java.util.Date;

public class TransmittedActiveServiceParams implements RequestDTO, Serializable {

    private int serviceId;
    private int userId;
    private int activeServiceId;
    private ActiveServiceStatus currentStatus;
    private ActiveServiceStatus newStatus;
    private Date date;
    private String requestType;
    private int version;
    private long unlockingTime;

    private TransmittedActiveServiceParams() {
    }

    public static TransmittedActiveServiceParams create() {
        return new TransmittedActiveServiceParams();
    }

    public TransmittedActiveServiceParams withActiveServiceId(int activeServiceId) {
        this.activeServiceId = activeServiceId;
        return this;
    }

    public TransmittedActiveServiceParams withServiceId(int serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public TransmittedActiveServiceParams withUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public TransmittedActiveServiceParams withCurrentStatus(ActiveServiceStatus currentStatus) {
        this.currentStatus = currentStatus;
        return this;
    }

    public TransmittedActiveServiceParams withNewStatus(ActiveServiceStatus newStatus) {
        this.newStatus = newStatus;
        return this;
    }

    public TransmittedActiveServiceParams withDate(Date date) {
        this.date = date;
        return this;
    }

    public TransmittedActiveServiceParams withRequestType(String type) {
        this.requestType = type;
        return this;
    }

    public TransmittedActiveServiceParams withVersion(int version) {
        this.version = version;
        return this;
    }

    public TransmittedActiveServiceParams withUnlockingTime(long unlockingTime) {
        this.unlockingTime = unlockingTime;
        return this;
    }

    public ActiveServiceStatus getCurrentStatus() {
        return currentStatus;
    }

    public ActiveServiceStatus getNewStatus() {
        return newStatus;
    }

    public int getVersion() {
        return version;
    }

    public int getServiceId() {
        return serviceId;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return activeServiceId;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String getRequestType() {
        return requestType;
    }

    public long getUnlockingTime(){ return unlockingTime; };

}
