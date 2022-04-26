package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static Server.StaticServerSocket.getServerSocket;

public class ServerMainThread extends Thread{

    private ServerAuthorization authorization = new ServerAuthorization();
    private ServerCollection collection = new ServerCollection();
    private String owner;

    public ServerMainThread() throws IOException {
    }
    @Override
    public void run() {
        try {
            while (true) {
                Socket client = getServerSocket().accept();
                System.out.println("Подключился новый пользователь ");
                authorization.waiting(client);
                owner = authorization.getOwner();
                DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                System.out.println(owner);
                new WorkThread(client, collection, outputStream, objectInputStream, getOwner()).start();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getOwner() {
        return owner;
    }
}

