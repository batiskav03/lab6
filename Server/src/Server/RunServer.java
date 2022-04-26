package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerAuthorization authorization = new ServerAuthorization();
        ServerCollection collection = new ServerCollection();
        ServerSocket server = new ServerSocket(1337);
        while(true) {
            Socket client = server.accept();
            System.out.println("Подключился новый пользователь");
            authorization.waiting(client);
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            new WorkThread(client,collection,outputStream,objectInputStream).start();
        }

    }
}
