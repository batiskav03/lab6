package client;

import client.commands.AbstractCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import static client.ClientAuthorization.dataInput;
import static client.StaticObjOutput.getObjectOutputStream;

public class SendCmd {


    private BufferedReader keyboard;
    private ObjectOutputStream objectOutputStream;



    public SendCmd() {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        objectOutputStream = getObjectOutputStream();
    }
    public String Sender(AbstractCommand command){

        try {
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            return dataInput().readUTF();
        }
        catch (SocketException ex){
            System.out.println("Сервер отключен");
        }
        catch (IOException ex){
            System.out.println("Невозможна отправка");
        }
        return "penis";
    }
}
