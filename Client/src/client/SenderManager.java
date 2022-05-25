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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

import static Server.DatabaseManager.getFromDatabaseDragons;
import static client.ClientAuthorization.ClientLogin;
import static client.GUI.AppFX.languageResource;

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
        KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
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
                int x = Math.round(dragon.getX());
                int y = Math.round(dragon.getY());
                StackPane stackPane = new StackPane();
                boolean isOwner = dragon.getOwner().equals(ClientLogin());
                if (isOwner) {
                    ImageView imageview = new ImageView(getClass().getResource("GUI/Data/Dragon-PNG-Photo.png").toExternalForm());
                    imageview.setPreserveRatio(true);
                    imageview.setPickOnBounds(true);
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(60);
                    imageview.setLayoutX(960+x);
                    imageview.setLayoutY(540+y);
                    stackPane.getChildren().add(imageview);
                } else {
                    ImageView imageview = new ImageView(getClass().getResource("GUI/Data/dragon_PNG994.png").toExternalForm());
                    imageview.setPreserveRatio(true);
                    imageview.setPickOnBounds(true);
                    imageview.setFitHeight(50);
                    imageview.setFitWidth(60);
                    imageview.setLayoutX(960+x);
                    imageview.setLayoutY(540+y);
                    stackPane.getChildren().add(imageview);
                }
                Alert info = new Alert(Alert.AlertType.INFORMATION, dragon.toString());
                ButtonType OKbutton = new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE);
                if(isOwner) {
                    ButtonType editButton = new ButtonType("Edit");
                    info.getButtonTypes().setAll(OKbutton,editButton);
                    info.setOnCloseRequest(action -> {
                        ButtonType result = info.getResult();
                        if (result != null && result == editButton) {
                            info.hide();
                            Alert prefer = new Alert(Alert.AlertType.INFORMATION);
                            ButtonType changeNameButton = new ButtonType("change name");
                            ButtonType changeAgeButton = new ButtonType("change age");
                            ButtonType changeColorButton = new ButtonType("change color");
                            ButtonType changeDragonTypeButton = new ButtonType("change dragon type");
                            ButtonType changeDragonCharacterButton = new ButtonType("change dragon character");
                            prefer.getButtonTypes().setAll(changeNameButton,changeAgeButton,changeColorButton,changeDragonTypeButton,changeDragonCharacterButton);
                            prefer.setOnCloseRequest(actionInPrefer -> {
                                ButtonType preferResult = prefer.getResult();
                                if (preferResult != null && preferResult == changeNameButton) {
                                    TextInputDialog nameDialog = new TextInputDialog("walter white");
                                    nameDialog.setOnCloseRequest(actionName -> {
                                        UpdateID updateid = new UpdateID();
                                        updateid.setId(dragon.getId());
                                        System.out.println(dragon.getId());
                                        updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), nameDialog.getResult(),
                                                dragon.getCoordinates(),
                                                new Date(), dragon.getAge(), dragon.getColor(),
                                                dragon.getDragonType(), dragon.getDragonCharacter(), dragon.getDragonHead()));
                                        String ans = sender.Sender(updateid);
                                        Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                        upd.show();
                                    });
                                    nameDialog.show();
                                } else if (preferResult != null && preferResult == changeAgeButton) {
                                    TextInputDialog ageDialog = new TextInputDialog("walter white");
                                    ageDialog.setOnCloseRequest(actionAge -> {
                                        UpdateID updateid = new UpdateID();
                                        updateid.setId(dragon.getId());
                                        System.out.println(dragon.getId());
                                        updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), dragon.getName(),
                                                dragon.getCoordinates(),
                                                new Date(), Integer.valueOf(ageDialog.getResult()), dragon.getColor(),
                                                dragon.getDragonType(), dragon.getDragonCharacter(), dragon.getDragonHead()));
                                        String ans = sender.Sender(updateid);
                                        Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                        upd.show();
                                    });
                                    ageDialog.show();
                                } else if (preferResult != null && preferResult == changeColorButton) {
                                    System.out.println(dragon.getId());
                                    Alert color = new Alert(Alert.AlertType.INFORMATION);
                                    ButtonType blueColor = new ButtonType("blue");
                                    ButtonType yellowColor = new ButtonType("yellow");
                                    ButtonType blackColor = new ButtonType("black");
                                    color.getButtonTypes().setAll(blueColor,yellowColor,blackColor);
                                    prefer.setOnCloseRequest(actionColor -> {
                                        ButtonType colorResult = color.getResult();
                                        if (colorResult != null && colorResult == blueColor) {
                                            UpdateID updateid = new UpdateID();
                                            updateid.setId(dragon.getId());
                                            System.out.println(dragon.getId());
                                            updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), dragon.getName(),
                                                    dragon.getCoordinates(),
                                                    new Date(), dragon.getAge(), Color.BLUE,
                                                    dragon.getDragonType(), dragon.getDragonCharacter(), dragon.getDragonHead()));
                                            String ans = sender.Sender(updateid);
                                            Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                            upd.show();
                                        } else if (colorResult != null && colorResult == yellowColor) {
                                            UpdateID updateid = new UpdateID();
                                            updateid.setId(dragon.getId());
                                            System.out.println(dragon.getId());
                                            updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), dragon.getName(),
                                                    dragon.getCoordinates(),
                                                    new Date(), dragon.getAge(), Color.YELLOW,
                                                    dragon.getDragonType(), dragon.getDragonCharacter(), dragon.getDragonHead()));
                                            String ans = sender.Sender(updateid);
                                            Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                            upd.show();
                                        }else if (colorResult != null && colorResult == blackColor) {
                                            UpdateID updateid = new UpdateID();
                                            updateid.setId(dragon.getId());
                                            System.out.println(dragon.getId());
                                            updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), dragon.getName(),
                                                    dragon.getCoordinates(),
                                                    new Date(), dragon.getAge(), Color.BLACK,
                                                    dragon.getDragonType(), dragon.getDragonCharacter(), dragon.getDragonHead()));
                                            String ans = sender.Sender(updateid);
                                            Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                            upd.show();
                                        } else {
                                            color.hide();
                                        }

                                    });
                                    color.show();
                                    
                                } else if (preferResult != null && preferResult == changeDragonTypeButton) {
                                    Alert type = new Alert(Alert.AlertType.INFORMATION);
                                    ButtonType water = new ButtonType("water");
                                    ButtonType und = new ButtonType("underground");
                                    ButtonType air = new ButtonType("air");
                                    ButtonType fire = new ButtonType("fire");
                                    type.getButtonTypes().setAll(water,und,air,fire);
                                    prefer.setOnCloseRequest(actionType -> {
                                        DragonType types = null;
                                        ButtonType typeResult = type.getResult();
                                        if (typeResult != null && typeResult == water) {
                                            types = DragonType.WATER;
                                        } else if (typeResult != null && typeResult == und) {
                                            types = DragonType.UNDERGROUND;
                                        }else if (typeResult != null && typeResult == air) {
                                            types = DragonType.AIR;
                                        } else  if (typeResult != null && typeResult == fire){
                                            types = DragonType.FIRE;
                                        }
                                        UpdateID updateid = new UpdateID();
                                        updateid.setId(dragon.getId());
                                        System.out.println(dragon.getId());
                                        updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), dragon.getName(),
                                                dragon.getCoordinates(),
                                                new Date(), dragon.getAge(), dragon.getColor(),
                                                types, dragon.getDragonCharacter(), dragon.getDragonHead()));
                                        String ans = sender.Sender(updateid);
                                        Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                        upd.show();
                                    });
                                    type.show();
                                } else if (preferResult != null && preferResult == changeDragonCharacterButton) {
                                    Alert character = new Alert(Alert.AlertType.INFORMATION);
                                    ButtonType wise = new ButtonType("wise");
                                    ButtonType good = new ButtonType("good");
                                    ButtonType evil = new ButtonType("evil");

                                    character.getButtonTypes().setAll(wise,good,evil);
                                    prefer.setOnCloseRequest(actionChar -> {
                                        DragonCharacter chr = null;
                                        ButtonType characterResult = character.getResult();
                                        if (characterResult != null && characterResult == wise) {
                                            chr = DragonCharacter.WISE;
                                        } else if (characterResult != null && characterResult == good) {
                                            chr = DragonCharacter.GOOD;
                                        }else if (characterResult != null && characterResult == evil) {
                                            chr = DragonCharacter.EVIL;
                                        }
                                        UpdateID updateid = new UpdateID();
                                        updateid.setId(dragon.getId());
                                        System.out.println(dragon.getId());
                                        updateid.setDragon(new Dragon(dragon.getId(),dragon.getOwner(), dragon.getName(),
                                                dragon.getCoordinates(),
                                                new Date(), dragon.getAge(), dragon.getColor(),
                                                dragon.getDragonType(), chr, dragon.getDragonHead()));
                                        String ans = sender.Sender(updateid);
                                        Alert upd = new Alert(Alert.AlertType.INFORMATION, ans, ButtonType.OK);
                                        upd.show();
                                    });
                                    character.show();
                                } else {
                                    prefer.hide();
                                }
                            });
                            prefer.show();
                        } else {
                            info.hide();
                        }
                    });

                }


                Button btn = new Button();
                btn.setOpacity(0);
                btn.setOnAction((action) -> {
                    info.show();
                });

                stackPane.setLayoutX(960+x);
                stackPane.setLayoutY(540+y);
                stackPane.getChildren().add(btn);

                anchorRoot.getChildren().add(stackPane);

            }
            stage.setScene(new Scene(anchorRoot,1000,500));
            stage.show();
            System.out.println("дошла");

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
}