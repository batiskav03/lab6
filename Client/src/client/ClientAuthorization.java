package client;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import static client.StaticClientSocket.getClientSocket;

public class ClientAuthorization {
    @FXML
    private Button themeButton;

    @FXML
    private StackPane parentContainer;

    @FXML
    private Button registerButton;

    @FXML
    private TextField signupLogin;

    @FXML
    private PasswordField signupPassword;

    @FXML
    private Button backButton;


    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private Button enterToSystem;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;


    private static String owner;
    private Scanner scan;
    private Socket socket;
    private static DataOutputStream output;
    private static DataInputStream input;


    public ClientAuthorization() throws IOException {
        this.socket = getClientSocket();
        this.scan = new Scanner(System.in);
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }


/*    public void action() throws IOException {
        while (true) {
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
    }*/

    public void signUp(String login, String password) {
        try {
            output.writeUTF("signup");

            System.out.println("Введите желаемый логин:");

            output.writeUTF(login);
            output.flush();

            System.out.println("Введите желаемый пароль:");

            output.writeUTF(password);
            output.flush();

            System.out.println("Обработка запроса");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void logIn(String login, String password) {
        try {
            output.writeUTF("login");
            System.out.println("Введите логин:");

            setOwner(login);
            output.writeUTF(login);
            output.flush();

            System.out.println("Введите пароль:");

            output.writeUTF(password);
            output.flush();

            System.out.println("Обработка запроса");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

/*    public void switchCase() throws IOException {
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
    }*/

    public static String ClientLogin() {
        return owner;
    }

    public void setOwner(String login) {
        owner = login;
    }

    public static DataOutputStream dataOutput() {
        return output;
    }

    public static DataInputStream dataInput() {
        return input;
    }


    @FXML
    private void enterToSystem() {

        String login = loginField.getText();
        String password = passwordField.getText();
        if (!login.equals("") && !password.equals("")) {
            logIn(login, password);
        }
        try {
            String ans = input.readUTF();
            switch (ans) {
                case "goodlogin":
                    System.out.println("Авторизация прошла успешно");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXML/InteractiveMod.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Scene scene = enterToSystem.getScene();
                    root.translateYProperty().set(scene.getHeight());

                    parentContainer.getChildren().add(root);

                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    timeline.getKeyFrames().add(kf);
                    timeline.setOnFinished(t -> {
                        parentContainer.getChildren().remove(anchorRoot);
                    });
                    timeline.play();
                    return;
                case "badlogin":
                    System.out.println("Логин или пароль неверны." +
                            "\nПопробуйте еще раз.");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Логин или пароль неверны." +
                            "\nПопробуйте еще раз.", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.hide();
                    }
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void enterToSystemDark() {

        String login = loginField.getText();
        String password = passwordField.getText();
        if (!login.equals("") && !password.equals("")) {
            logIn(login, password);
        }
        try {
            String ans = input.readUTF();
            switch (ans) {
                case "goodlogin":
                    System.out.println("Авторизация прошла успешно");
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXML_Dark/InteractiveMod_dark.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Scene scene = enterToSystem.getScene();
                    root.translateYProperty().set(scene.getHeight());

                    parentContainer.getChildren().add(root);

                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    timeline.getKeyFrames().add(kf);
                    timeline.setOnFinished(t -> {
                        parentContainer.getChildren().remove(anchorRoot);
                    });
                    timeline.play();
                    return;
                case "badlogin":
                    System.out.println("Логин или пароль неверны." +
                            "\nПопробуйте еще раз.");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Логин или пароль неверны." +
                            "\nПопробуйте еще раз.", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.hide();
                    }
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void loadLogin() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/LogInScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = loginButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();

    }

    @FXML
    private void loadLoginDark() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML_Dark/LogInScene_dark.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = loginButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();

    }

    @FXML
    private void back() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/AppFX.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = backButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void backDark() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML_Dark/AppFX_dark.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = backButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void tryToSignup() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML/SignUpScene.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = signupButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void tryToSignupDark() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML_Dark/SignUpScene_dark.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = signupButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    @FXML
    private void register() {

        String login = signupLogin.getText();
        String password = signupPassword.getText();
        if (!login.equals("") && !password.equals("")) {
            signUp(login, password);
        }
        try {
            String ans = input.readUTF();
            switch (ans) {
                case "badsignup":
                    System.out.println("Пользователь с данным логином уже существует, попробуйте еще раз");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Пользователь с данным логином уже существует, попробуйте еще раз", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.hide();
                    }
                    break;
                case "goodsignup":
                    System.out.println("Регистрация прошла успешно");

                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void registerDark() {

        String login = signupLogin.getText();
        String password = signupPassword.getText();
        if (!login.equals("") && !password.equals("")) {
            signUp(login, password);
        }
        try {
            String ans = input.readUTF();
            switch (ans) {
                case "badsignup":
                    System.out.println("Пользователь с данным логином уже существует, попробуйте еще раз");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Пользователь с данным логином уже существует, попробуйте еще раз", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.hide();
                    }
                    break;
                case "goodsignup":
                    System.out.println("Регистрация прошла успешно");

                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void switchToAppDark() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXML_Dark/AppFX_dark.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = themeButton.getScene();
        root.translateYProperty().set(scene.getHeight());

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }
}