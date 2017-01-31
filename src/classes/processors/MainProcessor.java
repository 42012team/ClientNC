package classes.processors;

import classes.exceptions.TransmittedException;
import classes.response.ResponseDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainProcessor {

    private Map<String, ResponseProcessor> processorsByTypeMap = new HashMap< String, ResponseProcessor>();

    public MainProcessor(Map<String, ResponseProcessor> processorsByTypeMap) {
        this.processorsByTypeMap = processorsByTypeMap;
    }

    public List processResponse(ResponseDTO response) throws TransmittedException {
        if (response != null) {
            String type = response.getResponseType();
            List responseList = processorsByTypeMap.get(type).process(response);
            return responseList;
        } else {
            return null;
        }
    }

}
