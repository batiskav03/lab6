package client.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppFX extends Application {
    public static ResourceBundle languageResource;
    public static void main(String[] args) {
        String uriString = new File("Client/src/client/GUI/Data/song.wav").toURI().toString();
        MediaPlayer player = new MediaPlayer( new Media(uriString));
        player.play();
        Application.launch();
    }
    @Override
    public void start(Stage stage) throws IOException{
        Locale.setDefault(Locale.FRANCE);
        languageResource = ResourceBundle.getBundle("client.resources.resources", Locale.getDefault());

        InputStream iconStream = getClass().getResourceAsStream("Data/img.png");
        Image image = new Image(iconStream);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../FXML/AppFX.fxml"));
        loader.setResources(languageResource);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.setTitle("USSR");
        stage.show();
    }
}