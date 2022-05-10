package client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import static client.StaticClientSocket.getClientSocket;

public class StaticObjOutput {
    private static ObjectOutputStream objectOutputStream;

    static {
        try {
            objectOutputStream = new ObjectOutputStream(getClientSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ;


    public static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
