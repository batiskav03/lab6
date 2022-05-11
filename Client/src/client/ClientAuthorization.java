package client;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static client.GUI.AppFX.languageResource;
import static client.StaticClientSocket.getClientSocket;

public class ClientAuthorization {

    @FXML
    public Button themeButton;

    @FXML
    private TextField keyFiled;

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
    public void simpleSwitch(String URL){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        loader.setResources(languageResource);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        parentContainer.getChildren().add(root);
        parentContainer.getChildren().remove(anchorRoot);
    }

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

    public void slide(Button button,String URL) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
        loader.setResources(languageResource);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = button.getScene();
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
                    slide(enterToSystem,"FXML/InteractiveMod.fxml");
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
                    slide(enterToSystem,"FXML_Dark/InteractiveMod_dark.fxml");
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
        slide(loginButton,"FXML/LogInScene.fxml");
    }

    @FXML
    private void loadLoginDark() {
        slide(loginButton,"FXML_Dark/LogInScene_dark.fxml");
    }

    @FXML
    private void back() {
        slide(backButton,"FXML/AppFX.fxml");
    }

    @FXML
    private void backDark() {
        slide(backButton,"FXML_Dark/AppFX_dark.fxml");
    }

    @FXML
    private void tryToSignup() {
        slide(signupButton,"FXML/SignUpScene.fxml");
    }

    @FXML
    private void tryToSignupDark() {
        slide(signupButton,"FXML_Dark/SignUpScene_dark.fxml");
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
                    Alert wrong = new Alert(Alert.AlertType.WARNING, "Пользователь с данным логином уже существует, попробуйте еще раз", ButtonType.OK);
                    wrong.showAndWait();
                    if (wrong.getResult() == ButtonType.OK) {
                        wrong.hide();
                    }
                    break;
                case "goodsignup":
                    System.out.println("Регистрация прошла успешно");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Регестрация успешно произведена!", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.hide();
                        slide(registerButton,"FXML/LogInScene.fxml");
                    }
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
                    Alert wrong = new Alert(Alert.AlertType.WARNING, "Пользователь с данным логином уже существует, попробуйте еще раз", ButtonType.OK);
                    wrong.showAndWait();
                    if (wrong.getResult() == ButtonType.OK) {
                        wrong.hide();
                    }
                    break;
                case "goodsignup":
                    System.out.println("Регистрация прошла успешно");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Регестрация успешно произведена!", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.hide();
                        slide(registerButton,"FXML_Dark/LogInScene_dark.fxml");
                    }
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void switchToAppDark() {
        slide(themeButton,"FXML_Dark/AppFX_dark.fxml");
    }

    @FXML
    private void switchToAppLight() {
        slide(themeButton,"FXML/AppFX.fxml");
    }

    @FXML
    private void switchToLogInLight() {
        slide(themeButton,"FXML/LogInScene.fxml");
    }

    @FXML
    private void switchToLogInLDark() {
        slide(themeButton,"FXML_Dark/LogInScene_dark.fxml");
    }

    @FXML
    private void switchToSignUpDark() {
        slide(themeButton,"FXML_Dark/SignUpScene_dark.fxml");
    }

    @FXML
    private void switchToSignLight() {
        slide(themeButton,"FXML/SignUpScene.fxml");
    }
    public void en() throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML/AppFX.fxml");

    }

    public void fr() {
        Locale.setDefault(Locale.FRENCH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML/AppFX.fxml");
    }
    public void cs(){
        Locale.setDefault(new Locale("cs"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML/AppFX.fxml");
    }
    public void ru(){
        Locale.setDefault(new Locale("ru"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML/AppFX.fxml");

    }
    public void endark() throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML_Dark/AppFX_dark.fxml");

    }

    public void frdark() {
        Locale.setDefault(Locale.FRENCH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML_Dark/AppFX_dark.fxml");
    }
    public void csdark(){
        Locale.setDefault(new Locale("cs"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML_Dark/AppFX_dark.fxml");
    }
    public void rudark(){
        Locale.setDefault(new Locale("ru"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(signupButton,"FXML_Dark/AppFX_dark.fxml");

    }



}