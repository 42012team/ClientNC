package classes.transport;

import classes.processors.ResponseProcessor;
import java.util.Map;

public interface Configuration {

    public Map<String, ResponseProcessor> getMap();

}
