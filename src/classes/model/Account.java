package classes.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Account {

    private User user = null;
    private List<ActiveService> activeServices;

    public Account(User user) {
        this.user = user;
        activeServices = new ArrayList<ActiveService>();
    }

    public List<ActiveService> getActiveServices() {
        return activeServices;
    }

    public boolean addActiveService(ActiveService activeService) {
        if (activeService == null) {
            return false;
        }
        activeServices.add(activeService);
        return true;
    }

    public void setActiveServices(List<ActiveService> activeServices) {
        this.activeServices = activeServices;
    }

    public void deleteActiveService(int serviceId) {
        Iterator itr = activeServices.iterator();
        while (itr.hasNext()) {
            ActiveService active = (ActiveService) itr.next();
            if (active.getId() == serviceId) {
                activeServices.remove(active);
                break;
            }
        }
    }

    public ActiveService getActiveServiceById(int activeServiceId) {
        for (int i = 0; i < activeServices.size(); i++) {
            if (activeServices.get(i).getId() == activeServiceId) {
                return activeServices.get(i);
            }
        }
        return null;
    }
}
