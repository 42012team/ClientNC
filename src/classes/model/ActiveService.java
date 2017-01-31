package classes.model;

import java.io.Serializable;
import java.util.Date;

public class ActiveService implements Comparable<ActiveService>, Serializable {

    private int serviceId;
    private int userId;
    private int id;
    private ActiveServiceStatus currentStatus;
    private ActiveServiceStatus newStatus;
    private Date date;
    private int version;

    public ActiveService(int id, int serviceId, int userId, ActiveServiceStatus currentStatus, ActiveServiceStatus newStatus, Date date) {
        this.id = id;
        this.serviceId = serviceId;
        this.userId = userId;
        this.date = date;
        this.currentStatus = currentStatus;
        this.newStatus = newStatus;
    }

    public ActiveService() {
        id = 0;

    }

    public void setCurrentStatus(ActiveServiceStatus status) {
        currentStatus = status;
    }

    public ActiveServiceStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setNewStatus(ActiveServiceStatus status) {
        newStatus = status;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ActiveServiceStatus getNewStatus() {
        return newStatus;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(ActiveService o) {
        return date.compareTo(o.getDate());

    }

}
