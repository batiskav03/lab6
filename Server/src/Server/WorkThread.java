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
            AbstractCommand command = null;
            try {
                command = (AbstractCommand) input.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (command.getClass().getName().equals("client.commands.Exit")) {
                try {
                    output.writeUTF("Disconnected");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                collection.whenExit();
                break;
            }
            try {
                output.writeUTF(collection.executeCurrentCommand(command));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(owner);
            System.out.println("Успешный запрос");
        }
    }
}
