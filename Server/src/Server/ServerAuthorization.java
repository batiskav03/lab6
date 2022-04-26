package Server;

import client.User;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ServerAuthorization {


    private HashMap<String, String> data;
    private LinkedList<User> userdata;
    private String owner;


    public ServerAuthorization() throws IOException {

        this.data = new HashMap<String,String>();
        this.userdata = new LinkedList<>();

    }
    public void waiting(Socket socket) throws IOException, ClassNotFoundException {

        while (true) {
           DataOutputStream output = new DataOutputStream(socket.getOutputStream());
           DataInputStream input = new DataInputStream(socket.getInputStream());
            System.out.println("Ожидание приказа");
            String order = input.readUTF();
            System.out.println(order);
            if(order.equals("signup")) {
                output.writeUTF(registration(socket));
                output.flush();
            } else if (order.equals("login")) {
                String check = authorize(socket);
                output.writeUTF(check);
                output.flush();
                if (check.equals("goodlogin")) {
                    break;
                }

            }
        }
    }


    public String registration(Socket socket) throws IOException {

        DataInputStream input = new DataInputStream(socket.getInputStream());
        String login = input.readUTF();
        String password = input.readUTF();
        for(Map.Entry<String,String> entry : data.entrySet()) {
            if (entry.getKey().equals(login)) {

                return "badsignup";
            }

        }
        data.put(login,password);
        userdata.add(new User(login,password));
        System.out.println(userdata);
        return "goodsignup";
    }
    public String authorize(Socket socket) throws IOException, ClassNotFoundException {

        DataInputStream input = new DataInputStream(socket.getInputStream());
        String login = input.readUTF();
        String password = input.readUTF();
        for(Map.Entry<String,String> entry : data.entrySet()) {
            if (entry.getKey().equals(login) || entry.getValue().equals(password)) {
                System.out.println("Пользователь подключился");
                setOwner(login);
                return "goodlogin";
            }

        }
        System.out.println("Пользователь безуспешно пытался подключится");
        return "badlogin";
    }
    public void setOwner(String login) {
        this.owner = login;
    }
    public String getOwner() {
        return owner;
    }
}
