package Server;

import client.commands.AbstractCommand;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class WorkThread extends Thread{
    private Socket client;
    private DataOutputStream output;
    private ObjectInputStream input;
    private ServerCollection collection;
    private String owner;

    public WorkThread(Socket socket,ServerCollection collection,DataOutputStream output,ObjectInputStream input,String owner) {
        this.collection = collection;
        this.client = socket;
        this.output = output;
        this.input = input;
        this.owner = owner;
    }
    @Override
    public void run() {
        while (!client.isClosed()) {
            try {
                AbstractCommand command = null;
                command = (AbstractCommand) input.readObject();
                collection.whenExit();
                if (command.getClass().getName().equals("client.commands.Exit")) {
                    output.writeUTF("Disconnected");
                    collection.whenExit();
                    break;
            }
            output.writeUTF(collection.executeCurrentCommand(command));
            output.flush();
            System.out.println(owner);
            System.out.println("Успешный запрос");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
