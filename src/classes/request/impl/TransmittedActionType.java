package classes.request.impl;

import classes.request.RequestDTO;
import java.io.Serializable;

//весь класс - для пассивной блокировки
public class TransmittedActionType implements RequestDTO, Serializable {

    private String requestType;
    private int id;
    private boolean isCanceled;

    private TransmittedActionType() {

    }

    public static TransmittedActionType create() {
        return new TransmittedActionType();
    }

    public TransmittedActionType withRequestType(String type) {
        this.requestType = type;
        return this;
    }

    public TransmittedActionType withId(int id) {
        this.id = id;
        return this;
    }

    public TransmittedActionType withIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
        return this;
    }


    @Override
    public String getRequestType() {
        return requestType;
    }

    public int getId() {
        return id;
    }

    public boolean isCanceled() {
        return isCanceled;
    }


}
