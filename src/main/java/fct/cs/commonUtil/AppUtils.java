package fct.cs.commonUtil;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppUtils {
    int i = 1;
    private double xOffset = 0;
    private double yOffset = 0;

    public void changeScene(String pathOfFXML) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(pathOfFXML));

        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        stage.show();
        scene.setFill(Color.TRANSPARENT);

        enableDrag(scene,stage);

    }

    public FXMLLoader createStage(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        loader.load();
        //AddBookController addEntryWindow = loader.getController();
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        return loader;
    }

    public void createDynamicStage(){

        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        TextField textField[] = new TextField[15];
        Button btn = new Button("Add TextField");
        root.add(btn, 0, 0);
        btn.setOnAction(e -> {
            textField[i] = new TextField();
            root.add(textField[i], 5, i);
            i = i + 1;

        });
        Stage stage = new Stage();
        final Scene scene = new Scene(root, 500, 400);


        stage.setScene(scene);
        enableDrag(scene,stage);
        stage.show();

    }

    public void enableDrag(Scene scene, Stage stage){
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}
