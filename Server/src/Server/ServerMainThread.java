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
        while(true) {
            Socket client = null;
            try {
                client = getServerSocket().accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Подключился новый пользователь ");

            try {
                authorization.waiting(client);
                owner = authorization.getOwner();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            DataOutputStream outputStream = null;
            try {
                outputStream = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(owner);
            new WorkThread(client,collection,outputStream,objectInputStream,getOwner()).start();
        }
    }

    public String getOwner() {
        return owner;
    }
}
