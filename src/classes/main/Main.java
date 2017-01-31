package classes.main;

import classes.transport.ConfigurationXML;
import classes.transport.Configuration;
import classes.view.View;
import classes.controllers.ClientController;
import classes.processors.ResponseProcessor;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Configuration config = new ConfigurationXML();
        Map<String, ResponseProcessor> mapOfProcessors = config.getMap();
        ClientController controller = new ClientController(mapOfProcessors);
        View view = new View(controller);
        view.startWindow();
    }

}
