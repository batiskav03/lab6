package Server;

import client.commands.AbstractCommand;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RunServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerCollection collection = new ServerCollection();
        ServerSocket server = new ServerSocket(1337);
        Socket client = server.accept();
        DataInputStream inputStream=new DataInputStream(client.getInputStream());
        DataOutputStream outputStream=new DataOutputStream(client.getOutputStream());
        ObjectInputStream objectInputStream=new ObjectInputStream(client.getInputStream());
        System.out.println("Подключился новый пользователь");
        while (!client.isClosed()){
            AbstractCommand command = (AbstractCommand) objectInputStream.readObject();
            if (command.getClass().getName().equals("client.commands.Exit")){
                outputStream.writeUTF("Disconnected");
                collection.whenExit();
                break;
            }
            outputStream.writeUTF(collection.executeCurrentCommand(command));
            outputStream.flush();
            System.out.println("Успешный запрос");


        }
    }
}
