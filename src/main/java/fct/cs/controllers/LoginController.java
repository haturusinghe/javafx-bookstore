package fct.cs.controllers;

import fct.cs.App;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoginController {
    public MFXTextField user_txtfield;
    public MFXPasswordField pass_textfield;
    public HBox buttons_hbox;
    public MFXButton cancelBtn;
    public MFXButton loginBtn;
    public VBox login_vbox;

    public void cancel(MouseEvent mouseEvent) {

    }

    public void login(MouseEvent mouseEvent) throws IOException {
        switchToManagerDashboard();
    }

    @FXML
    private void switchToManagerDashboard() throws IOException {
        App.setRoot("manager-dash");
    }
}
