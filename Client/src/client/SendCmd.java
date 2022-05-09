package client;

import client.commands.AbstractCommand;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

import static client.ClientAuthorization.dataInput;
import static client.StaticClientSocket.getClientSocket;

public class SendCmd {
    private Socket socket;

    private BufferedReader keyboard;
    private ObjectOutputStream objectOutputStream;



    public SendCmd() {
        try {
            socket = getClientSocket();
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e ) {
            e.getMessage();
        }


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
