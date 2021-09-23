package fct.cs;

import fct.cs.commonUtil.AppUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage){

        checkLaunch();

//        scene = new Scene(loadFXML("fxml/settings/getting-started"),1280,720);
//        scene = new Scene(loadFXML("test"),1280,720);

        /*try {
            scene = new Scene(loadFXML("main-dash"),1280,720);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        scene.getStylesheets().add(
                "https://fonts.googleapis.com/css2?family=Comfortaa:wght@300&family=Inconsolata:wght@300&family=Josefin+Sans&family=Montserrat:wght@300;400&family=Teko&family=Work+Sans&display=swap");
//        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Inconsolata:700");
        //'https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap'
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        AppUtils.enableDrag(scene,stage);
        stage.show();
    }

    private void checkLaunch() {
        File myObj = new File("config.xml");
        try{
            if (myObj.createNewFile()) {
                scene = new Scene(loadFXML("fxml/settings/getting-started"), 1280, 720);
            } else {
                Properties configFile = new Properties();
                InputStream f = new FileInputStream("config.xml");
                configFile.loadFromXML(f);
                f.close();
                String writeDate = "";
                writeDate = configFile.getProperty("writeToDb");

                if(writeDate == null || writeDate.length() < 1) {
                    scene = new Scene(loadFXML("fxml/settings/getting-started"), 1280, 720);
                }else{
                    scene = new Scene(loadFXML("fxml/login/login"), 1280, 720);
                }
            }
        } catch (InvalidPropertiesFormatException e) {
            try {
                scene = new Scene(loadFXML("fxml/settings/getting-started"), 1280, 720);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            try {
                scene = new Scene(loadFXML("fxml/settings/getting-started"), 1280, 720);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            try {
                scene = new Scene(loadFXML("fxml/settings/getting-started"), 1280, 720);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
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
