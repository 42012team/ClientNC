package classes.transport;

import classes.processors.ResponseProcessor;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationXML implements Configuration {

    private final String PATH = "D:\\nc\\configC.xml";
    private Document doc;
    Map<String, ResponseProcessor> processorByType = new HashMap< String, ResponseProcessor>();

    public ConfigurationXML() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(new File(PATH));
        } catch (ParserConfigurationException | SAXException | IOException ex) {

        }
        doc.getDocumentElement().normalize();
    }

    @Override
    public Map<String, ResponseProcessor> getMap() {
        NodeList processorNode = doc.getElementsByTagName("processor");
        try {
            for (int temp = 0; temp < processorNode.getLength(); temp++) {
                Element processorElement = (Element) processorNode.item(temp);
                String type = processorElement.getAttribute("type");
                Class clazz = Class.forName(processorElement.getAttribute("className"));
                Object object = clazz.newInstance();
                processorByType.put(type, (ResponseProcessor) object);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SecurityException | IllegalArgumentException ex) {
            System.out.println("ВНУТРЕНЯЯ ОШИБКА! ПОЖАЛУЙСТА, ПЕРЕУСТАНОВИТЕ ПРИЛОЖЕНИЕ ИЛИ СВЯЖИТЕСЬ СО СЛУЖБОЙ ПОДДЕРЖКИ! ");
        }
        return processorByType;
    }

    private void write() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(PATH));
            transformer.transform(source, result);
        } catch (TransformerException ex) {
        }
    }

}
