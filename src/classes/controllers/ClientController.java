package classes.controllers;

import classes.exceptions.TransmittedException;
import classes.request.impl.TransmittedActiveServiceParams;
import classes.request.impl.TransmittedServiceParams;
import classes.request.impl.TransmittedUserParams;
import classes.response.impl.ActionResponse;
import classes.transport.Transport;
import classes.processors.MainProcessor;
import classes.model.User;
import classes.model.Account;
import classes.model.ActiveService;
import classes.model.ActiveServiceStatus;
import classes.model.Service;
import classes.model.ServiceStatus;
import classes.processors.ResponseProcessor;
import classes.request.impl.TransmittedActionType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClientController {

    private User user = null;
    private Account account;
    private Transport transport;
    private MainProcessor processor;

    public ClientController(Map<String, ResponseProcessor> map) {
        processor = new MainProcessor(map);
        transport = new Transport(processor);
    }

    public User getUser() throws TransmittedException {
        TransmittedUserParams userParams = TransmittedUserParams.create().withId(user.getId())
                .withRequestType("userById");
        user = (User) transport.transportObject(userParams).get(0);
        return user;
    }

    public void deleteUser() {
        user = null;
    }

    public boolean signIn(String name, String surname, String email, String phone, String address, String login,
            String password) throws TransmittedException {
        TransmittedUserParams userParams = TransmittedUserParams.create()
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withPhone(phone)
                .withAdress(address)
                .withLogin(login)
                .withPassword(password)
                .withRequestType("signInUser")
                .withVersion(0)
                .withPrivilege("user");
        user = (User) transport.transportObject(userParams).get(0);
        System.out.println(user.getId());
        if (account == null) {
            account = new Account(user);
        }
        return user != null;
    }

    public boolean logIn(String login, String password) throws TransmittedException {
        TransmittedUserParams userRequestParams = TransmittedUserParams.create()
                .withRequestType("logIn")
                .withLogin(login)
                .withPassword(password);
        List responseList = transport.transportObject(userRequestParams);
        user = (User) responseList.get(0);
        if (account == null) {
            account = new Account(user);
        }
        return user.getPrivilege().equals("admin");
    }

    public void changeUser(String name, String surname, String email, String phone, String address,
            String password,long unlockingTime) throws TransmittedException {
        TransmittedUserParams userParams = TransmittedUserParams.create()
                .withRequestType("changeUser")
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withPhone(phone)
                .withAdress(address)
                .withLogin(user.getLogin())
                .withPassword(password)
                .withId(user.getId())
                .withVersion(user.getVersion())
                .withPrivilege(user.getPrivilege())
                .withUnlockingTime(unlockingTime);
        user = (User) transport.transportObject(userParams).get(0);
    }

    public List<Service> getAllServices() throws TransmittedException {
        return transport.transportObject(TransmittedServiceParams.create().withRequestType("allServices"));
    }

    public List<ActiveService> getActiveServices() throws TransmittedException {
        List<ActiveService> list = null;
        if (user != null) {
            TransmittedActiveServiceParams.create().withUserId(user.getId());
            list = transport.transportObject(TransmittedActiveServiceParams.create().
                    withUserId(user.getId()).withRequestType("allActiveServices"));
        }
        return list;
    }

    public List<Service> getActiveServicesDecriptions() throws TransmittedException {
        return transport.transportObject(TransmittedServiceParams.create().
                withUserId(user.getId()).withRequestType("activeServicesDescriptions"));
    }

    public void addActiveService(int serviceId, Date date) throws TransmittedException {
        TransmittedActiveServiceParams activeServiceParams = TransmittedActiveServiceParams.create()
                .withServiceId(serviceId)
                .withUserId(user.getId())
                .withDate(date)
                .withCurrentStatus(ActiveServiceStatus.PLANNED)
                .withNewStatus(ActiveServiceStatus.ACTIVE);
        account.setActiveServices(transport
                .transportObject(activeServiceParams
                        .withRequestType("createActiveService")));
    }

    public void deleteActiveService(int activeServiceId,long unlockingTime) throws TransmittedException {
        TransmittedActiveServiceParams activeServiceParams = TransmittedActiveServiceParams.create()
                .withActiveServiceId(activeServiceId)
                .withUserId(user.getId())
                .withUnlockingTime(unlockingTime);
        List<ActiveService> activeServices = transport
                .transportObject(activeServiceParams
                        .withRequestType("deleteActiveService"));
        account.setActiveServices(activeServices);
    }

    public void changeActiveService(int activeServiceId, ActiveServiceStatus currentStatus,
            ActiveServiceStatus newStatus, Date date, int version,long unlockingTime) throws TransmittedException {
        TransmittedActiveServiceParams activeServiceParams = TransmittedActiveServiceParams.create()
                .withActiveServiceId(activeServiceId)
                .withUserId(user.getId())
                .withDate(date)
                .withCurrentStatus(currentStatus)
                .withNewStatus(newStatus)
                .withVersion(version)
                .withUnlockingTime(unlockingTime);
        account.setActiveServices(transport
                .transportObject(activeServiceParams
                        .withRequestType("changeActiveService")));
    }

    public List<Service> getAllowedToConnectServices() throws TransmittedException {
        return transport.transportObject(TransmittedServiceParams.create().
                withUserId(user.getId()).withRequestType("allowedToConnect"));
    }

    //для пассивной блокировки
    public ActionResponse checkAction(int id, boolean isCanceled) throws TransmittedException {
        return (ActionResponse) (transport.transportObject(TransmittedActionType.create()
                .withRequestType("actionType")
                .withId(id)
                .withIsCanceled(isCanceled))).get(0);
    }

    public void changeService(int id, String name, String description, ServiceStatus serviceStatus, int version,long unlockingTime) throws TransmittedException {
        TransmittedServiceParams serviceParams = TransmittedServiceParams.create()
                .withServiceId(id)
                .withName(name)
                .withDescription(description)
                .withStatus(serviceStatus)
                .withVersion(version)
                .withUnlockingTime(unlockingTime);
        transport.transportObject(serviceParams.withRequestType("changeService"));
    }

    public void deleteService(int serviceId,long unlockingTime) throws TransmittedException {
        TransmittedServiceParams serviceParams = TransmittedServiceParams.create()
                .withServiceId(serviceId)
                .withUnlockingTime(unlockingTime);
        transport.transportObject(serviceParams.withRequestType("deleteService"));
    }

    public void addService(String name, String description, String type) throws TransmittedException {
        TransmittedServiceParams serviceParams = TransmittedServiceParams.create()
                .withName(name)
                .withDescription(description)
                .withType(type)
                .withStatus(ServiceStatus.ALLOWED);
        transport.transportObject(serviceParams.withRequestType("createService"));
    }

    public void signInAdmin(String login, String password) throws TransmittedException {
        TransmittedUserParams userParams = TransmittedUserParams.create()
                .withLogin(login)
                .withPassword(password)
                .withRequestType("signInAdmin")
                .withVersion(0)
                .withPrivilege("admin");
        transport.transportObject(userParams);
    }

}
