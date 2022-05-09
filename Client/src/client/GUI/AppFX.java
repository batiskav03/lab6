package client.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class AppFX extends Application {
    public static void main(String[] args) {
        Application.launch();

    }
    @Override
    public void start(Stage stage) throws IOException {
        InputStream iconStream = getClass().getResourceAsStream("img.png");
        Image image = new Image(iconStream);
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/AppFX.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.setTitle("USSR");
        stage.show();

    }
}
