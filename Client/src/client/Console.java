package client;

import java.util.Scanner;

import static client.ClientAuthorization.ClientLogin;

public class Console {
    private Scanner keyboard;
    private String userCommand = "";
    private String[] finalCommand;
    private SenderManager sender = new SenderManager();

    public Console() {

    }

    public void interactiveMod() {
        keyboard = new Scanner(System.in);

        while (true) {
            System.out.println("Ваш никнейм: " + ClientLogin());
            System.out.println("-----------------------------");
            System.out.print("Enter the command:");
            userCommand = keyboard.nextLine();
            finalCommand = userCommand.trim().split(" ", 2);
            sender.createAndSend(finalCommand);
            if (userCommand.equals("exit")) {
                break;
            }
        }
    }

}
