package client.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AppFX extends Application {
    public static void main(String[] args) {
        String uriString = new File("Client/src/client/GUI/Data/song.wav").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
        Application.launch();
    }
    @Override
    public void start(Stage stage) throws IOException, InvocationTargetException {
        InputStream iconStream = getClass().getResourceAsStream("Data/img.png");
        Image image = new Image(iconStream);
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/AppFX.fxml"));
        Scene scene = new Scene(root);

        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.setTitle("USSR");
        stage.show();

    }
}
