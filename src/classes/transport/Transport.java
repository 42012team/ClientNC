package classes.transport;

import classes.exceptions.TransmittedException;
import classes.request.RequestDTO;
import classes.response.ResponseDTO;
import classes.processors.MainProcessor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class Transport {

    private Socket clientSocket = null;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private MainProcessor processor;

    public Transport(MainProcessor processor) {
        this.processor = processor;

    }

    private void createConfiguration() throws Exception {
        clientSocket = new Socket();
        clientSocket.connect(new InetSocketAddress("localhost", 4141), 3000);
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    public List transportObject(RequestDTO requestObject) throws TransmittedException {
        try {
            createConfiguration();
            clientSocket.setSoTimeout(7000);
            ResponseDTO obj = null;
            outputStream.writeObject(requestObject);
            outputStream.flush();
            obj = (ResponseDTO) inputStream.readObject();
            return processor.processResponse(obj);
        } catch (SocketTimeoutException ex) {
            throw TransmittedException.create("ВЫШЛО ВРЕМЯ ОЖИДАНИЯ ОТВЕТА СЕРВЕРА!");
        } catch (TransmittedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw TransmittedException.create("ПРОИЗОШЛА ОШИБКА ПРИ СОЕДИНЕНИИ С СЕРВЕРОМ!");
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (Exception ex) {
            }
        }

    }

}
