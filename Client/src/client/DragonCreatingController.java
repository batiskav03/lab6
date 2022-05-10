package client;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class DragonCreatingController {

    public StackPane parentContainer;
    public AnchorPane anchorRoot;
    public TextArea textArea;
    public TextField keyFiled;
    public Button enterKey;

    public Integer getKey() {
        return key;
    }

    private Integer key;

    public void enterKeyRemoveByKey(ActionEvent actionEvent) {
        key = Integer.valueOf(keyFiled.getText());

    }

}
