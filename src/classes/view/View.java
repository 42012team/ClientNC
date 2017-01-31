package classes.view;

import classes.controllers.ClientController;
import classes.exceptions.TransmittedException;
import classes.model.ActiveService;
import classes.model.ActiveServiceStatus;
import classes.model.Service;
import classes.model.ServiceStatus;
import classes.model.User;
import classes.response.impl.ActionResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class View {

    private ClientController controller;

    public View(ClientController controller) {
        this.controller = controller;
    }

    public void startWindow() {
        int i = 0;
        do {
            System.out.println("ВЫБЕРИТЕ ДЕЙСТВИЕ: ");
            System.out.println("1 - Войти");
            System.out.println("2 - Зарегистрироваться");
            System.out.println("3 - Посмотреть все услуги компании");
            System.out.println("4 - Выйти");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                switch (i) {
                    case 1:
                        showLogInWindow();
                        break;
                    case 2:
                        showSignInWindow();
                        break;
                    case 3:
                        showAllServices();
                        break;
                }
            } else {
                System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
            }
        } while (i != 4);
        System.exit(0);
    }

    public void showLogInWindow() {
        try {
            System.out.println("Введите логин: ");
            Scanner sc = new Scanner(System.in);
            String login = sc.nextLine();
            System.out.println("Введите пароль: ");
            String password = sc.nextLine();
            if (controller.logIn(login, password)) {
                showAdminMenuWindow();
            } else {
                showProfileDetailsWindow();
            }
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            startWindow();
        }
    }

    public void showSignInWindow() {
        String login = input("Введите логин ");
        String password = input("Введите пароль ");
        String name = input("Введите имя: ");
        String surname = input("Введите фамилию ");
        String address = input("Введите адресс ");
        String email = input("Введите email ");
        String phone = input("Введите телефон ");
        try {
            controller.signIn(name, surname, email, phone, address, login, password);
            showProfileDetailsWindow();
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            startWindow();
        }
    }

    public void showProfileDetailsWindow() {
        try {
            int i = 0;
            do {
                User user = controller.getUser();
                System.out.println("_______________________________________________________");
                System.out.println("Имя: " + user.getName());
                System.out.println("Фамилия: " + user.getSurname());
                System.out.println("Логин: " + user.getLogin());
                System.out.println("Пароль: " + user.getPassword());
                System.out.println("Адресс: " + user.getAddress());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Телефон: " + user.getPhone());
                System.out.println("_______________________________________________________");
                System.out.println("ВЫБЕРИТЕ ДЕЙСТВИЕ: ");
                System.out.println("1 - Посмотреть подключенные услуги");
                System.out.println("2 - Изменить профиль");
                System.out.println("3 - Посмотреть все услуги компании");
                System.out.println("4 - Выйти");
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    i = sc.nextInt();
                    switch (i) {
                        case 1:
                            showActiveServicesWindow();
                            break;
                        case 2:
                            ActionResponse actionResponse=controller.checkAction(user.getId(), false);
                            if (actionResponse.isAllowed()) {
                                showChangeUserWindow(actionResponse.getUnlockingTIme());
                            } else {
                                System.out.println("ВРЕМЕННО НЕВОЗМОЖНО ВЫПОЛНИТЬ ИЗМЕНЕНИЕ!");
                            }
                            break;
                        case 3:
                            showAllServices();
                            break;
                    }

                } else {
                    System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
                }
            } while (i != 4);
            exit();
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage() + " НЕВОЗМОЖНО ЗАГРУЗИТЬ ДАННЫЕ ПРОФИЛЯ!");
            startWindow();
        }
    }

    public void showChangeUserWindow(long unlockingTime) {
        try {
            User user = controller.getUser();
            System.out.println("Введите новые значения, если значение не меняется, оставьте поле пустым");
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите пароль: ");
            String password = sc.nextLine();
            if (password.trim().equals("")) {
                password = user.getPassword();
            }
            System.out.println("Введите имя: ");
            String name = sc.nextLine();
            if (name.trim().equals("")) {
                name = user.getName();
            }
            System.out.println("Введите фамилию : ");
            String surname = sc.nextLine();
            if (surname.trim().equals("")) {
                surname = user.getSurname();
            }
            System.out.println("Введите email: ");
            String email = sc.nextLine();
            if (email.trim().equals("")) {
                email = user.getEmail();
            }
            System.out.println("Введите адресс: ");
            String address = sc.nextLine();
            if (address.trim().equals("")) {
                address = user.getAddress();
            }
            System.out.println("Введите телефон: ");
            String phone = sc.nextLine();
            if (phone.trim().equals("")) {
                phone = user.getPhone();
            }
            controller.changeUser(name, surname, email, phone, address, password,unlockingTime);
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            showProfileDetailsWindow();
        }

    }

    public void showAllServices() {
        try {
            List<Service> services = controller.getAllServices();
            System.out.println("Компания предоставляет " + services.size() + " услуг: ");
            int j = 1;
            for (Service s : services) {
                System.out.println("_______________________________________________________");
                System.out.println("Услуга номер " + j);
                System.out.println("Название услуги: " + s.getName());
                System.out.println("Описание услуги: " + s.getDescription());
                System.out.println("Тип услуги: " + s.getType());
                System.out.println("Статус услуги: " + s.getStatus());
                System.out.println("_______________________________________________________");
                System.out.println("\n");
                j++;
            }
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            startWindow();
        }
    }

    public void exit() {
        controller.deleteUser();
        startWindow();
    }

    public void showActiveServicesWindow() {
        int i = 0;
        do {
            try {
                List<ActiveService> activeServices = controller.getActiveServices();
                List<Service> services = controller.getActiveServicesDecriptions();
                int j;
                for (int k = 0; k < services.size(); k++) {
                    Service s = services.get(k);
                    System.out.println("_______________________________________________________");
                    System.out.println("Услуга номер " + (k + 1));
                    System.out.println("Название услуги: " + s.getName());
                    System.out.println("Описание услуги: " + s.getDescription());
                    System.out.println("Тип услуги: " + s.getType());
                    System.out.println("Статус услуги: " + activeServices.get(k).getCurrentStatus().toString());
                    if (activeServices.get(k).getCurrentStatus().equals(ServiceStatus.DEPRECATED)) {
                        System.out.println("Ваша услуга устарела!");
                    }
                    if (activeServices.get(k).getNewStatus() != null) {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                        String strDate = sdfDate.format(activeServices.get(k).getDate());
                        System.out.println("Запланировано изменение статуса услуги на " + activeServices.get(k).getNewStatus().toString()
                                + " c " + strDate);
                    }
                    System.out.println("_______________________________________________________");
                    System.out.println("\n");
                }
                System.out.println("ВЫБЕРИТЕ ДЕЙСТВИЕ: ");
                System.out.println("1 - Подключить новую услугу");
                System.out.println("2 - Отказаться от услуги");
                System.out.println("3 - Изменить услугу");
                System.out.println("4 - Вернуться в профиль");
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    i = sc.nextInt();
                    switch (i) {
                        case 1:
                            showAddActiveServiceWindow();
                            break;
                        case 2:
                            System.out.println("Введите номер удаляемой услуги: ");
                            if (sc.hasNextInt()) {
                                j = sc.nextInt();
                                if ((activeServices.size() >= j) && (j > 0)) {
                                    ActionResponse actionResponse=controller.checkAction(activeServices.get(j - 1).getId(), false);
                                    if (actionResponse.isAllowed()) {
                                        showDeleteActiveServiceWindow(activeServices.get(j - 1).getId(),actionResponse.getUnlockingTIme());
                                    } else {
                                        System.out.println("УДАЛЕНИЕ ВРЕМЕННО НЕВОЗМОЖНО!");
                                    }
                                }
                            } else {
                                System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
                            }
                            break;
                        case 3:
                            System.out.println("Введите номер изменяемой услуги: ");
                            if (sc.hasNextInt()) {
                                j = sc.nextInt();
                                if ((activeServices.size() >= j) && (j > 0) && (activeServices.get(j - 1).getCurrentStatus() != ActiveServiceStatus.DISCONNECTED)) {
                                    ActionResponse actionResponse=controller.checkAction(activeServices.get(j - 1).getId(), false);
                                    if (actionResponse.isAllowed()) {
                                        showChangeActiveServiceWindow(activeServices.get(j - 1),actionResponse.getUnlockingTIme());
                                    } else {
                                        System.out.println("ИЗМЕНЕНИЕ УСЛУГИ ВРЕМЕННО НЕВОЗМОЖНО! ПОВТОРИТЕ ПОЗЖЕ!");
                                    }
                                } else {
                                    System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
                                }
                            } else {
                                System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
                            }
                            break;
                    }
                } else {
                    System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
                }
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showProfileDetailsWindow();
            }
        } while (i != 4);
    }

    public void showAddActiveServiceWindow() {
        List<Service> services = showAllowedToConnectServices();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер добавляемой услуги: ");
        String s = sc.nextLine();
        if (s.matches("[-+]?\\d+")) {
            int j = Integer.parseInt(s);
            if ((j > 0) && (j <= services.size())) {
                Date date = null;
                do {
                    System.out.println("Введите дату в формате dd.MM.yyyy HH:mm :");
                    String tempDate = sc.nextLine();
                    date = checkDateWindow(tempDate);
                } while (date == null);
                try {
                    controller.addActiveService(services.get(j - 1).getId(), date);
                } catch (TransmittedException ex) {
                    System.out.println(ex.getMessage());
                    startWindow();
                }
            } else {
                System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
            }
        } else {
            System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
        }
    }

    public List<Service> showAllowedToConnectServices() {
        try {
            List<Service> services = controller.getAllowedToConnectServices();
            int j = 1;
            for (Service s : services) {
                System.out.println("_______________________________________________________");
                System.out.println("Услуга номер " + j);
                System.out.println("Название услуги: " + s.getName());
                System.out.println("Описание услуги: " + s.getDescription());
                System.out.println("Тип услуги: " + s.getType());
                System.out.println("_______________________________________________________");
                System.out.println("\n");
                j++;
            }
            return services;
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            showProfileDetailsWindow();
        }
        return null;
    }

    public void showDeleteActiveServiceWindow(int activeServiceId,long unlockingTime) {
        if (agreeWindow("Услуга будет удалена. ")) {
            try {
                controller.deleteActiveService(activeServiceId,unlockingTime);
                System.out.println("Услуга была удалена!");
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showProfileDetailsWindow();
            }
        } else {
            try {
                controller.checkAction(activeServiceId, true);
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showProfileDetailsWindow();
            }
        }
    }

    public void showChangeActiveServiceWindow(ActiveService activeService,long unlockingTime) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        ActiveServiceStatus currentStatus = activeService.getCurrentStatus();
        ActiveServiceStatus newStatus = activeService.getNewStatus();
        if (currentStatus == ActiveServiceStatus.ACTIVE) {
            boolean isChanged = false;
            if (newStatus == ActiveServiceStatus.SUSPENDED) {
                if (agreeWindow("Запланированное изменение будет отменено. ")) {
                    newStatus = null;
                    isChanged = true;
                    date = activeService.getDate();
                }
            }
            if (!isChanged) {
                do {
                    System.out.println("Введите дату блокировки в формате dd.MM.yyyy HH:mm");
                    String tempDate = sc.nextLine();
                    date = checkDateWindow(tempDate);
                } while (date == null);
                newStatus = ActiveServiceStatus.SUSPENDED;
            }
        }
        if ((currentStatus == ActiveServiceStatus.PLANNED) || (currentStatus == ActiveServiceStatus.SUSPENDED)) {
            do {
                System.out.println("Введите новую дату подключения в формате dd.MM.yyyy HH:mm : ");
                String tempDate = sc.nextLine();
                date = checkDateWindow(tempDate);
            } while (date == null);
            newStatus = ActiveServiceStatus.ACTIVE;
        }
        if (agreeWindow("Услуга будет изменена. ")) {
            try {
                controller.changeActiveService(activeService.getId(), currentStatus, newStatus, date,
                        activeService.getVersion(),unlockingTime);
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showProfileDetailsWindow();
            }
        } else {
            try {
                controller.checkAction(activeService.getId(), true);
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showProfileDetailsWindow();
            }
        }
    }

    public boolean agreeWindow(String text) {
        int j = 0;
        do {
            System.out.println(text + "Вы уверены?");
            System.out.println("1 - Да");
            System.out.println("2 - Нет");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                j = sc.nextInt();
            } else {
                System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
            }
        } while ((j != 1) && (j != 2));
        return j == 1;
    }

    private Date checkDateWindow(String date) {
        DateValidator dateValidator = new DateValidator();
        if (dateValidator.validate(date)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                Date newDate = format.parse(date);
                Date currentDate = new Date();
                if (currentDate.compareTo(newDate) < 0) {
                    return newDate;
                }
            } catch (ParseException ex) {
            }
        }
        System.out.println("ВВЕДИТЕ ДОПУСТИМУЮ ДАТУ В НУЖНОМ ФОРМАТЕ!");
        return null;
    }

    private String input(String visibleText) {
        System.out.println(visibleText + ": ");
        Scanner sc = new Scanner(System.in);
        String str = "";
        while ((str = sc.nextLine()).trim().equals("")) {
            System.out.println("ПОВТОРИТЕ ВВОД! " + visibleText + ": ");
        }
        return str;
    }

    private void showAdminMenuWindow() {
        int i = 0;
        do {
            System.out.println("_______________________________________________________");
            System.out.println("ВЫБЕРИТЕ ДЕЙСТВИЕ: ");
            System.out.println("1 - Посмотреть все услуги компании");
            System.out.println("2 - Добавить администратора");
            System.out.println("3 - Выйти");
            Scanner sc = new Scanner(System.in);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                switch (i) {
                    case 1:
                        showAllServicesAdmin();
                        break;
                    case 2:
                        showAddAdminWindow();
                        break;
                }
            } else {
                System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
            }
        } while (i != 3);
        startWindow();
    }

    private void showAllServicesAdmin() {
        try {
            int i = 0;
            do {
                showAllServices();
                System.out.println("ВЫБЕРИТЕ ДЕЙСТВИЕ: ");
                System.out.println("1 - Добавить новую услугу");
                System.out.println("2 - Удалить услугу");
                System.out.println("3 - Изменить услугу");
                System.out.println("4 - Вернуться в профиль");
                List<Service> allServices = controller.getAllServices();
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    i = sc.nextInt();
                    int j = 0;
                    switch (i) {
                        case 1:
                            showAddServiceWindow();
                            break;
                        case 2:
                            System.out.println("Введите номер удаляемой услуги: ");
                            if (sc.hasNextInt()) {
                                j = sc.nextInt();
                                if ((allServices.size() >= j) && (j > 0)) {
                                    ActionResponse actionResponse=controller.checkAction(allServices.get(j - 1).getId(), false);
                                    if (actionResponse.isAllowed()) {
                                        showDeleteServiceWindow(allServices.get(j - 1).getId(),actionResponse.getUnlockingTIme());
                                    } else {
                                        System.out.println("УДАЛЕНИЕ ВРЕМЕННО НЕВОЗМОЖНО!");
                                    }
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Введите номер изменяемой услуги: ");
                            j = sc.nextInt();
                            if ((allServices.size() >= j) && (j > 0)) {
                                ActionResponse actionResponse=controller.checkAction(allServices.get(j - 1).getId(), false);
                                if (actionResponse.isAllowed()) {
                                    showChangeServiceWindow(allServices.get(j - 1),actionResponse.getUnlockingTIme());
                                } else {
                                    System.out.println("ИЗМЕНЕНИЕ УСЛУГИ ВРЕМЕННО НЕВОЗМОЖНО! ПОВТОРИТЕ ПОЗЖЕ!");
                                }
                            } else {
                                System.out.println("ИЗМЕНЕНИЕ УСЛУГИ ВРЕМЕННО НЕВОЗМОЖНО! ПОВТОРИТЕ ПОЗЖЕ!");
                            }
                            break;
                    }
                } else {
                    System.out.println("НЕКОРРЕКТНЫЙ ВВОД!");
                }
            } while (i != 4);
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            showAdminMenuWindow();
        }
    }

    private void showAddAdminWindow() {
        String login = input("Введите логин ");
        String password = input("Введите пароль ");
        try {
            controller.signInAdmin(login, password);
            showAdminMenuWindow();
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            showAdminMenuWindow();
        }
    }

    private void showAddServiceWindow() {
        String name = input("Введите название добавляемой услуги");
        String description = input("Введите описание добавляемой услуги");
        String type = input("Введите тип добавляемой услуги");
        try {
            controller.addService(name, description, type);
            System.out.println("Услуга была добавлена!");
        } catch (TransmittedException ex) {
            System.out.println(ex.getMessage());
            showAdminMenuWindow();
        }
    }

    private void showDeleteServiceWindow(int serviceId,long unlockingTime) {
        if (agreeWindow("Услуга будет удалена. ")) {
            try {
                controller.deleteService(serviceId,unlockingTime);
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showAdminMenuWindow();
            }
        } else {
            try {
                controller.checkAction(serviceId, true);
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showAdminMenuWindow();
            }
        }
    }

    private void showChangeServiceWindow(Service service,long unlockingTime) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите новые значения, если значение не меняется, оставьте поле пустым");
        System.out.println("Введите название услуги: ");
        String name = sc.nextLine();
        if (name.trim().equals("")) {
            name = service.getName();
        }
        System.out.println("Введите описание услуги: ");
        String description = sc.nextLine();
        if (description.trim().equals("")) {
            description = service.getDescription();
        }
        ServiceStatus serviceStatus = null;
        int i = 0;
        do {
            System.out.println("Выберите статус услуги: ");
            System.out.println("1 - ALLOWED");
            System.out.println("2 - DEPRECATED");
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                switch (i) {
                    case 1:
                        serviceStatus = ServiceStatus.ALLOWED;
                        break;
                    case 2:
                        serviceStatus = ServiceStatus.DEPRECATED;
                        break;
                }
            }
        } while ((i > 2) || (i < 1));
        if (agreeWindow("Услуга будет изменена. ")) {
            try {
                controller.changeService(service.getId(), name, description, serviceStatus, service.getVersion(),unlockingTime);
                System.out.println("Услуга была изменена!");
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showAdminMenuWindow();
            }
        } else {
            try {
                controller.checkAction(service.getId(), true);
            } catch (TransmittedException ex) {
                System.out.println(ex.getMessage());
                showAdminMenuWindow();
            }
        }
    }

}
