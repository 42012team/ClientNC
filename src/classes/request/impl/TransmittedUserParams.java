package classes.request.impl;

import classes.request.RequestDTO;
import java.io.Serializable;

public class TransmittedUserParams implements RequestDTO, Serializable {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String login;
    private String password;
    private String type;
    private Integer userId;
    private int version;
    private String privilege;
    private long unlockingTime;


    private TransmittedUserParams() {

    }

    public static TransmittedUserParams create() {
        return new TransmittedUserParams();
    }

    public TransmittedUserParams withId(Integer id) {
        this.userId = id;
        return this;
    }

    public TransmittedUserParams withName(String name) {
        this.name = name;
        return this;
    }

    public TransmittedUserParams withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public TransmittedUserParams withEmail(String email) {
        this.email = email;
        return this;
    }

    public TransmittedUserParams withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public TransmittedUserParams withAdress(String address) {
        this.address = address;
        return this;
    }

    public TransmittedUserParams withLogin(String login) {
        this.login = login;
        return this;
    }

    public TransmittedUserParams withPassword(String password) {
        this.password = password;
        return this;
    }

    public TransmittedUserParams withRequestType(String type) {
        this.type = type;
        return this;
    }

    public TransmittedUserParams withVersion(int version) {
        this.version = version;
        return this;
    }

    public TransmittedUserParams withPrivilege(String privilege) {
        this.privilege = privilege;
        return this;
    }

    public TransmittedUserParams withUnlockingTime(long unlockingTime) {
        this.unlockingTime = unlockingTime;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getRequestType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public String getPrivilege() {
        return privilege;
    }

    public long getUnlockingTime(){ return unlockingTime; };
}
