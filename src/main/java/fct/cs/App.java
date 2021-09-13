package fct.cs;

import fct.cs.commonUtil.AppUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("generate-reports"),1280,720);
        scene.getStylesheets().add(
                "https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Inconsolata:wght@300&family=Josefin+Sans&family=Montserrat:wght@300;400&family=Teko&family=Work+Sans&display=swap");
//        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Inconsolata:700");
        //'https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap'
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        AppUtils.enableDrag(scene,stage);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
