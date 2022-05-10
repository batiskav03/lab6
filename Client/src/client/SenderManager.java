package client;

import client.commands.AbstractCommand;
import client.commands.FilteredByColor;
import client.commands.PrintAscending;
import client.commands.RemoveKey;
import data.Color;
import data.Dragon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Scanner;

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


    @FXML
    private void exitButton() {
        System.exit(228);
    }

    @FXML
    private void printAscendingButton() {
        PrintAscending printAscending = new PrintAscending();
        printer(printAscending);

    }


    @FXML
    private void removeByKeyButton() {
        simpleSwitch("FXML/InteractiveModWithoutButtonsRemoveByKey.fxml");
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
    private void removeByLowerKeyButton() {

    }

    @FXML
    private void removeByGreaterKeyButton() {
    }

    @FXML
    private void updateByIdButton() {

    }

    @FXML
    private void InsertButton() {
    }

    @FXML
    private void InsertMaxButton() {
    }

    @FXML
    private void clearButton() {
    }

    @FXML
    private void helpButton() {
    }

    @FXML
    private void infoButton() {
    }

    @FXML
    private void showButton() {
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
        simpleSwitch("FXML/InteractiveMod.fxml");
        printer(remove);

    }
    @FXML
    private void enterID() {

        simpleSwitch("FXML/UpdInsert.fxml");
    }
    @FXML
    private void createObjField(){
//        UpdateID updateid = new UpdateID();
//        updateid.setId(Integer.parseInt(idkey.getText()));
//        updateid.setDragon(new Dragon(ClientLogin(), nameField.getText(),
//                new Coordinates(Long.parseLong(coordinateXField.getText()),Float.parseFloat(coordinateYField.getText())),
//                new Date(), Integer.valueOf(ageField.getText()), Color.valueOf(ColorMenuButton.getText()),
//                asker.askDragonType(), asker.askDragonCharacter(), new DragonHead(Long.parseLong(teethField.getText()),Float.parseFloat(eyesField.getText()))));
//        sender.Sender(updateid);
//        simpleSwitch("FXML/InteractiveMod.fxml");
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
        } else if (filterByColorButton.equals(source)) {
        } else if (helpButton.equals(source)) {
        } else if (infoButton.equals(source)) {
        } else if (updateByIdButton.equals(source)) {
        } else if (InsertButton.equals(source)) {
        } else if (InsertMaxButton.equals(source)) {
        } else if (clearButton.equals(source)) {
        } else if (removeByKeyButton.equals(source)) {
            simpleSwitch("FXML/InteractiveModWithoutButtonsRemoveByKey.fxml");

        } else if (removeByLowerKeyButton.equals(source)) {
        } else if (removeByGreaterKeyButton.equals(source)) {
        } else {
            throw new IllegalStateException("Unexpected value: " + actionEvent.getSource());
        }


    }
}
