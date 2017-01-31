package classes.model;

import java.io.Serializable;

public class Service implements Serializable {

    private String description;
    private int id;
    private String name;
    private String type;
    private ServiceStatus status;
    private int version;

    public Service(int id, String name, String description, String type, int version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = ServiceStatus.ALLOWED;
        this.version = version;
    }

    public Service() {
        id = 0;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
