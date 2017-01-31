package classes.response.impl;

import classes.response.ResponseDTO;
import java.io.Serializable;

public class UserResponse implements ResponseDTO, Serializable {

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

    private UserResponse() {
    }

    public static UserResponse create() {
        return new UserResponse();
    }

    public UserResponse withId(Integer id) {
        this.userId = id;
        return this;
    }

    public UserResponse withName(String name) {
        this.name = name;
        return this;
    }

    public UserResponse withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserResponse withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserResponse withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserResponse withAdress(String address) {
        this.address = address;
        return this;
    }

    public UserResponse withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserResponse withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserResponse withResponseType(String type) {
        this.type = type;
        return this;
    }

    public UserResponse withVersion(int version) {
        this.version = version;
        return this;
    }

    public UserResponse withPrivilege(String privilege) {
        this.privilege = privilege;
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
    public String getResponseType() {
        return type;
    }

    public int getVersion() {
        return version;
    }

    public String getPrivilege() {
        return privilege;
    }

}
