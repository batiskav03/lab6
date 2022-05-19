package client;

import client.commands.*;
import data.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static Server.DatabaseManager.getFromDatabaseDragons;
import static client.ClientAuthorization.ClientLogin;
import static client.GUI.AppFX.languageResource;
import static client.commands.Show.getDragonList;

public class SenderManager {

    @FXML
    private Button themeButton;

    @FXML
    private TextField keyFiled;

    @FXML
    private Button enterKey;

    @FXML
    private MenuItem yellowFilter;

    @FXML
    private MenuItem blackFilter;

    @FXML
    private MenuItem blueFilter;

    @FXML
    private TextArea textArea;

    @FXML
    private Button InsertButton;

    @FXML
    private Button InsertMaxButton;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private Button clearButton;

    @FXML
    private Button exitButton;

    @FXML
    private MenuButton filterByColorButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button infoButton;

    @FXML
    private StackPane parentContainer;

    @FXML
    private Button printAscendingButton;

    @FXML
    private Button removeByGreaterKeyButton;

    @FXML
    private Button removeByKeyButton;

    @FXML
    private Button removeByLowerKeyButton;

    @FXML
    private Button showButton;

    @FXML
    private Button updateByIdButton;

    @FXML
    private MenuButton ColorMenuButton;

    @FXML
    private TextField ageField;

    @FXML
    private MenuButton characterMenuButton;

    @FXML
    private TextField coordinateXField;

    @FXML
    private TextField coordinateYField;

    @FXML
    private Button createObjField;

    @FXML
    private TextField eyesField;

    @FXML
    private TextField nameField;


    @FXML
    private TextField teethField;

    @FXML
    private MenuButton typeMenuButton;

    @FXML
    private MenuItem airDragon;

    @FXML
    private MenuItem blackDragon;
    @FXML
    private MenuItem blueDragon;
    @FXML
    private MenuItem evilDragon;
    @FXML
    private MenuItem fireDragon;

    @FXML
    private MenuItem goodDragon;
    @FXML
    private MenuItem undergroundDragon;

    @FXML
    private MenuItem waterDragon;

    @FXML
    private MenuItem wiseDragon;

    @FXML
    private MenuItem yellowDragon;

    @FXML
    private TextField idkey;

    private String fullText = "";
    private Asker asker = new Asker(new Scanner(System.in));
    private SendCmd sender = new SendCmd();
    private Dragon dragonToSend;


    public void printer(AbstractCommand command) {
        fullText += sender.Sender(command) +
                "\n-------------------------------------" +
                "----------------------------------------" +
                "-----------------------------------------" +
                "-------------------------------------------" +
                "--------------------------------------------" +
                "\n";
        textArea.setText(fullText);
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


    public void simpleSwitch(String URL) {
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

    @FXML
    private void blackFilter() {
        FilteredByColor filterbycolor = new FilteredByColor();
        filterbycolor.setColor(Color.valueOf("BLACK"));
        printer(filterbycolor);
    }

    @FXML
    private void yellowFilter() {
        FilteredByColor filterbycolor = new FilteredByColor();
        filterbycolor.setColor(Color.valueOf("YELLOW"));
        printer(filterbycolor);
    }

    @FXML
    private void blueFilter() {
        FilteredByColor filterbycolor = new FilteredByColor();
        filterbycolor.setColor(Color.valueOf("BLUE"));
        printer(filterbycolor);
    }


    @FXML
    private void enterKeyRemoveByKey() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveKey remove = new RemoveKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    @FXML
    private void enterKeyRemoveByKeyDark() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveKey remove = new RemoveKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML_Dark/InteractiveMod_dark.fxml");
    }

    @FXML
    private void enterID() {

        simpleSwitch("FXML/UpdInsert.fxml");
    }

    @FXML
    private void createObjField() {
        UpdateID updateid = new UpdateID();
        updateid.setId(Integer.parseInt(idkey.getText()));
        updateid.setDragon(new Dragon(ClientLogin(), nameField.getText(),
                new Coordinates(Long.parseLong(coordinateXField.getText()), Float.parseFloat(coordinateYField.getText())),
                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
                DragonType.valueOf(typeMenuButton.getText()), DragonCharacter.valueOf(characterMenuButton.getText()), new DragonHead(Long.parseLong(teethField.getText()), Float.parseFloat(eyesField.getText()))));
        String ans = sender.Sender(updateid);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.show();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    @FXML
    private void createObjFieldDark() {
        UpdateID updateid = new UpdateID();
        updateid.setId(Integer.parseInt(idkey.getText()));
        updateid.setDragon(new Dragon(ClientLogin(), nameField.getText(),
                new Coordinates(Long.parseLong(coordinateXField.getText()), Float.parseFloat(coordinateYField.getText())),
                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
                DragonType.valueOf(typeMenuButton.getText()), DragonCharacter.valueOf(characterMenuButton.getText()), new DragonHead(Long.parseLong(teethField.getText()), Float.parseFloat(eyesField.getText()))));
        String ans = sender.Sender(updateid);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.show();
        simpleSwitch("FXML_Dark/InteractiveMod_dark.fxml");
    }

    @FXML
    private void ColorMenuButton() {

    }

    @FXML
    private void waterDragon() {
        typeMenuButton.setText("WATER");
    }

    @FXML
    private void undergroundDragon() {
        typeMenuButton.setText("UNDERGROUND");
    }

    @FXML
    private void airDragon() {
        typeMenuButton.setText("AIR");
    }

    @FXML
    private void fireDragon() {
        typeMenuButton.setText("FIRE");
    }

    @FXML
    private void wiseDragon() {
        characterMenuButton.setText("WISE");
    }

    @FXML
    private void evilDragon() {
        characterMenuButton.setText("EVIL");
    }

    @FXML
    private void goodDragon() {
        characterMenuButton.setText("GOOD");
    }

    @FXML
    private void yellowDragon() {
        ColorMenuButton.setText("YELLOW");
    }

    @FXML
    private void blackDragon() {
        ColorMenuButton.setText("BLACK");
    }

    @FXML
    private void blueDragon() {
        ColorMenuButton.setText("BLUE");
    }

    public void idkey() {
    }



    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (printAscendingButton.equals(source)) {
            PrintAscending printAscending = new PrintAscending();
            printer(printAscending);
        } else if (exitButton.equals(source)) {
            System.exit(228);
        } else if (showButton.equals(source)) {
            Show show = new Show();
            printer(show);

            Stage stage = new Stage();
            stage.setWidth(1920);
            stage.setHeight(1080);
            AnchorPane anchorRoot = new AnchorPane();

            LinkedList<Dragon> dragons = getFromDatabaseDragons();

            for (Dragon dragon : dragons) {
                Button btn = new Button();
                int x = Math.round(dragon.getX());

                int y = Math.round(dragon.getY());

                StackPane stackPane = new StackPane();

                stackPane.setLayoutX(x);
                stackPane.setLayoutY(y);
                stackPane.getChildren().add(btn);

                if (dragon.getOwner().equals(ClientLogin())){
                    Image image = new Image(new File("GUI/Data/Dragon-PNG-HD.png").toURI().toString());
                    ImageView imageview = new ImageView(image);
                    imageview.setPreserveRatio(true);
                    imageview.setPickOnBounds(true);
                    imageview.setFitHeight(122);
                    imageview.setFitWidth(250);
                    imageview.setLayoutX(x);
                    imageview.setLayoutY(y);
                    stackPane.getChildren().add(imageview);
                } else {

                    Image image = new Image(new File("GUI/Data/Dragon-PNG-Photo.png").toURI().toString());
                    ImageView imageview = new ImageView(image);
                    imageview.setPreserveRatio(true);
                    imageview.setPickOnBounds(true);
                    imageview.setFitHeight(122);
                    imageview.setFitWidth(250);
                    imageview.setLayoutX(x+1);
                    imageview.setLayoutY(y+1);
                    stackPane.getChildren().add(imageview);
                }

                anchorRoot.getChildren().add(stackPane);

            }
            stage.setScene(new Scene(anchorRoot,1000,500));
            stage.show();
            System.out.println("дошла");
            showLandscape();
        } else if (helpButton.equals(source)) {
            Help help = new Help();
            printer(help);
        } else if (infoButton.equals(source)) {
            Info info = new Info();
            printer(info);
        } else if (updateByIdButton.equals(source)) {
            simpleSwitch("FXML/UpdInsert.fxml");
        } else if (InsertButton.equals(source)) {
            simpleSwitch("FXML/newDragon.fxml");
        } else if (InsertMaxButton.equals(source)) {
            MaxByCreationDate maxbydate = new MaxByCreationDate();
            printer(maxbydate);
        } else if (clearButton.equals(source)) {
            Clear clear = new Clear();
            printer(clear);
        } else if (removeByKeyButton.equals(source)) {
            simpleSwitch("FXML/InteractiveModWithoutButtonsRemoveByKey.fxml");
        } else if (removeByLowerKeyButton.equals(source)) {
            simpleSwitch("FXML/InteractiveModWithoutButtonsRemoveByLWKey.fxml");
        } else if (removeByGreaterKeyButton.equals(source)) {
            simpleSwitch("FXML/InteractiveModWithoutButtonsRemoveByGRKey.fxml");
        } else {
            throw new IllegalStateException("Unexpected value: " + actionEvent.getSource());
        }


    }
    @FXML
    public void dragon1() {

        Alert wrong = new Alert(Alert.AlertType.WARNING, "owner=1, id=1646889798, name='60chlen'," +
                " coordinates={x = -333, y = 261.0}, creationDate=Wed May 11 00:46:37 MSK 2022, age=123, color=BLACK, " +
                "type=UNDERGROUND, character=EVIL, head={eyes = 123, tooth = 123.0} ", ButtonType.OK);
        wrong.showAndWait();
        if (wrong.getResult() == ButtonType.OK) {
            wrong.hide();
        }
    }

    @FXML
    public void dragon2() {

        Alert wrong = new Alert(Alert.AlertType.WARNING, "{owner=1, id=1566194770, name='1', " +
                "coordinates={x = 123, y = 123.0}, creationDate=Wed May 11 03:28:43 MSK 2022, age=1, color=BLACK, " +
                "type=UNDERGROUND, character=WISE, head={eyes = 123, tooth = 123.0}} ", ButtonType.OK);
        wrong.showAndWait();
        if (wrong.getResult() == ButtonType.OK) {
            wrong.hide();
        }
    }

    public void dragon3() {

        Alert wrong = new Alert(Alert.AlertType.INFORMATION, "owner=alever, id=123321231, name='Larymar'," +
                " coordinates={x = 200, y = -100.0}, creationDate=Wed May 11 11:46:37 MSK 2022, age=18, color=BLACK, " +
                "type=UNDERGROUND, character=EVIL, head={eyes = 100, tooth = 100} ", ButtonType.OK);
        wrong.showAndWait();
        if (wrong.getResult() == ButtonType.OK) {
            wrong.hide();
        }
    }

    public void dragon4() {

        Alert wrong = new Alert(Alert.AlertType.WARNING, "owner=maxxxxx, id=103441123, name='name'," +
                " coordinates={x = 600, y = 111.0}, creationDate=Wed May 11 00:40:37 MSK 2022, age=69, color=BLACK, " +
                "type=UNDERGROUND, character=EVIL, head={eyes = 0, tooth = 0.0} ", ButtonType.OK);
        wrong.showAndWait();
        if (wrong.getResult() == ButtonType.OK) {
            wrong.hide();
        }
    }


    private void showLandscape() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML/landscape.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleButtonActionDark(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (printAscendingButton.equals(source)) {
            PrintAscending printAscending = new PrintAscending();
            printer(printAscending);
        } else if (exitButton.equals(source)) {
            System.exit(228);
        } else if (showButton.equals(source)) {
            Show show = new Show();
            printer(show);
            showLandscape();
        } else if (helpButton.equals(source)) {
            Help help = new Help();
            printer(help);
        } else if (infoButton.equals(source)) {
            Info info = new Info();
            printer(info);
        } else if (updateByIdButton.equals(source)) {
            simpleSwitch("FXML_Dark/UpdInsert_dark.fxml");
        } else if (InsertButton.equals(source)) {
            simpleSwitch("FXML_Dark/newDragon_dark.fxml");
        } else if (InsertMaxButton.equals(source)) {
            MaxByCreationDate maxbydate = new MaxByCreationDate();
            printer(maxbydate);

        } else if (clearButton.equals(source)) {
            Clear clear = new Clear();
            printer(clear);
        } else if (removeByKeyButton.equals(source)) {
            simpleSwitch("FXML_Dark/InteractiveModWithoutButtonsRemoveByKey_dark.fxml");
        } else if (removeByLowerKeyButton.equals(source)) {
            simpleSwitch("FXML_Dark/InteractiveModWithoutButtonsRemoveByLWKey_dark.fxml");
        } else if (removeByGreaterKeyButton.equals(source)) {
            simpleSwitch("FXML_Dark/InteractiveModWithoutButtonsRemoveByGRKey_dark.fxml");
        } else {
            throw new IllegalStateException("Unexpected value: " + actionEvent.getSource());
        }


    }

    @FXML
    public void typeMenuButton() {
    }

    @FXML
    public void characterMenuButton(ActionEvent actionEvent) {
    }

    @FXML
    public void createNewObjField(ActionEvent actionEvent) {
        InsertNull insert = new InsertNull();
        insert.setKey(Integer.parseInt(idkey.getText()));
        insert.setDragon(new Dragon(ClientLogin(), nameField.getText(),
                new Coordinates(Long.parseLong(coordinateXField.getText()), Float.parseFloat(coordinateYField.getText())),
                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
                DragonType.valueOf(typeMenuButton.getText()), DragonCharacter.valueOf(characterMenuButton.getText()), new DragonHead(Long.parseLong(teethField.getText()), Float.parseFloat(eyesField.getText()))));
        String ans = sender.Sender(insert);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.show();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }


    @FXML
    public void createNewObjFieldDark(ActionEvent actionEvent) {
        InsertNull insert = new InsertNull();
        insert.setKey(Integer.parseInt(idkey.getText()));
        insert.setDragon(new Dragon(ClientLogin(), nameField.getText(),
                new Coordinates(Long.parseLong(coordinateXField.getText()), Float.parseFloat(coordinateYField.getText())),
                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
                DragonType.valueOf(typeMenuButton.getText()), DragonCharacter.valueOf(characterMenuButton.getText()), new DragonHead(Long.parseLong(teethField.getText()), Float.parseFloat(eyesField.getText()))));
        String ans = sender.Sender(insert);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.show();
        simpleSwitch("FXML_Dark/InteractiveMod_dark.fxml");
    }

    @FXML
    private void switchToInteractiveDark() {
        simpleSwitch("FXML_Dark/InteractiveMod_dark.fxml");
    }

    @FXML
    private void switchToInteractiveLight() {
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    @FXML
    public void enterKeyRemoveByGRKey() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveGreaterKey remove = new RemoveGreaterKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    @FXML
    public void enterKeyRemoveByGRKeyDark() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveGreaterKey remove = new RemoveGreaterKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML_Dark/InteractiveMod_dark.fxml");
    }

    @FXML
    public void enterKeyRemoveByLWKey() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveLowerKey remove = new RemoveLowerKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    @FXML
    public void enterKeyRemoveByLWKeyDark() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveLowerKey remove = new RemoveLowerKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML_Dark/InteractiveMod_dark.fxml");
    }
    public void en() throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML/InteractiveMod.fxml");

    }

    public void fr() {
        Locale.setDefault(Locale.FRENCH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML/InteractiveMod.fxml");
    }
    public void cs(){
        Locale.setDefault(new Locale("cs"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML/InteractiveMod.fxml");
    }
    public void ru(){
        Locale.setDefault(new Locale("ru"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML/InteractiveMod.fxml");

    }
    public void endark() throws IOException {
        Locale.setDefault(Locale.ENGLISH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML_Dark/InteractiveMod_dark.fxml");

    }
    public void frdark() {
        Locale.setDefault(Locale.FRENCH);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML_Dark/InteractiveMod_dark.fxml");
    }
    public void csdark(){
        Locale.setDefault(new Locale("cs"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML_Dark/InteractiveMod_dark.fxml");
    }
    public void rudark(){
        Locale.setDefault(new Locale("ru"));
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());
        slide(exitButton,"FXML_Dark/InteractiveMod_dark.fxml");

    }
    public void parser(String ans){
        ArrayList<String> str = new ArrayList<String>(Arrays.asList(ans.split("\n")));
        for (String s:str){
            if (s.contains("owner=" + ClientLogin())){

            }
        }
    }
}