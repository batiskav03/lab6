package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static client.StaticClientSocket.getClientSocket;

public class ClientAuthorization {
    private static String owner;
    private Scanner scan;
    private Socket socket;
    private static DataOutputStream output;
    private static DataInputStream input;


    public ClientAuthorization() throws IOException {
        this.socket = getClientSocket();
        this.scan = new Scanner(System.in);
        output  = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }



    public void action() throws IOException {
        System.out.println("Для того что бы авторизоваться введите: log in" +
                "\nДля того чтобы зарегистрироваться введите: sign up");
        switchCase();
        while(true){
            String ans = input.readUTF();
            switch (ans) {
                case "badsignup":
                    System.out.println("Пользователь с данным логином уже существует, попробуйте еще раз");
                    switchCase();
                    break;
                case "goodsignup":
                    System.out.println("Регистрация прошла успешно");
                    switchCase();
                    break;
                case "goodlogin":
                    System.out.println("Авторизация прошла успешно");

                    return;
                case "badlogin":
                    System.out.println("Логин или пароль неверны." +
                            "\nПопробуйте еще раз.");
                    switchCase();
                    break;
            }
        }
    }

    public void signUp() throws IOException {
        output.writeUTF("signup");

        System.out.println("Введите желаемый логин:");
        String login = scan.nextLine();
        output.writeUTF(login);
        output.flush();

        System.out.println("Введите желаемый пароль:");
        String password = scan.nextLine();
        output.writeUTF(password);
        output.flush();

        System.out.println("Обработка запроса");

    }

    public void logIn() throws IOException {
        output.writeUTF("login");

        System.out.println("Введите логин:");
        String login = scan.nextLine();
        setOwner(login);
        output.writeUTF(login);
        output.flush();

        System.out.println("Введите пароль:");
        String password = scan.nextLine();

        output.writeUTF(password);
        output.flush();

        System.out.println("Обработка запроса");

    }
    public void switchCase() throws IOException {
        System.out.println("Введите то, что хотите сделать:");
        String arg = scan.nextLine();
        switch (arg) {
            case "log in":
                logIn();
                break;
            case "sign up":
                signUp();
                break;
            default:
                System.out.println("Wrong!");
                switchCase();
        }
    }

    public static String ClientLogin() {
        return owner;
    }
    public void setOwner(String login){
        owner = login;
    }
    public static DataOutputStream dataOutput() {
        return output;
    }
    public static DataInputStream dataInput() {
        return input;
    }
}
