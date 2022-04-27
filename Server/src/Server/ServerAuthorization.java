package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static Server.DatabaseManager.getFromDatabase;

public class ServerAuthorization {


    private HashMap<String, String> data;

    private String owner;


    public ServerAuthorization() throws IOException {

        this.data = getFromDatabase();


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
        data.put(login,hashPassword(password));
        DatabaseManager.savePasswords(login,hashPassword(password));
        System.out.println(data);
        return "goodsignup";
    }
    public String authorize(Socket socket) throws IOException, ClassNotFoundException {

        DataInputStream input = new DataInputStream(socket.getInputStream());
        String login = input.readUTF();
        String password = input.readUTF();
        for(Map.Entry<String,String> entry : data.entrySet()) {
            if (entry.getKey().equals(login) || entry.getValue().equals(hashPassword(password))) {
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
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger hash = new BigInteger(1,digest);
            String hashPass = hash.toString(16);
            while (hashPass.length() < 32) {
                hashPass = "0" + hashPass;
            }
            return hashPass;

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Неверный алгоритм хеширования");;
        }
        return null;
    }
}
