package client;

import client.commands.*;
import data.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import static client.ClientAuthorization.ClientLogin;

public class SenderManager {

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


    public void simpleSwitch(String URL){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(URL));
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
    private void enterID() {

        simpleSwitch("FXML/UpdInsert.fxml");
    }
    @FXML
    private void createObjField(){
        UpdateID updateid = new UpdateID();
        updateid.setId(Integer.parseInt(idkey.getText()));
        updateid.setDragon(new Dragon(ClientLogin(), nameField.getText(),
                new Coordinates(Long.parseLong(coordinateXField.getText()),Float.parseFloat(coordinateYField.getText())),
                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
                DragonType.valueOf(typeMenuButton.getText()), DragonCharacter.valueOf(characterMenuButton.getText()), new DragonHead(Long.parseLong(teethField.getText()),Float.parseFloat(eyesField.getText()))));
        String ans = sender.Sender(updateid);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.show();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }
    @FXML
    private void ColorMenuButton(){

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

    public void typeMenuButton() {
    }

    public void characterMenuButton(ActionEvent actionEvent) {
    }

    public void createNewObjField(ActionEvent actionEvent) {
        InsertNull insert = new InsertNull();
        insert.setKey(Integer.parseInt(idkey.getText()));
        insert.setDragon(new Dragon(ClientLogin(), nameField.getText(),
                new Coordinates(Long.parseLong(coordinateXField.getText()),Float.parseFloat(coordinateYField.getText())),
                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
                DragonType.valueOf(typeMenuButton.getText()), DragonCharacter.valueOf(characterMenuButton.getText()), new DragonHead(Long.parseLong(teethField.getText()),Float.parseFloat(eyesField.getText()))));
        String ans = sender.Sender(insert);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.show();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    public void enterKeyRemoveByGRKey() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveGreaterKey remove = new RemoveGreaterKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }

    public void enterKeyRemoveByLWKey() {
        Integer key = Integer.valueOf(keyFiled.getText());
        RemoveLowerKey remove = new RemoveLowerKey();
        remove.setKey(key);
        String ans = sender.Sender(remove);
        Alert info = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
        info.showAndWait();
        simpleSwitch("FXML/InteractiveMod.fxml");
    }
}