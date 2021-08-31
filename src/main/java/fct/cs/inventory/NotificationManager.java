package fct.cs.inventory;


import io.github.palexdev.materialfx.controls.MFXDialog;
import io.github.palexdev.materialfx.controls.MFXNotification;
import io.github.palexdev.materialfx.controls.SimpleMFXNotificationPane;
import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.enums.DialogType;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import io.github.palexdev.materialfx.notifications.NotificationPos;
import io.github.palexdev.materialfx.notifications.NotificationsManager;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;


//import org.kordamp.ikonli.javafx.FontIcon; #TODO : Need to import this

import java.util.ArrayList;
import java.util.Random;



public class NotificationManager {

    private ArrayList<StockEntry> lowItems = new ArrayList<>();

    public void setLowItems(ArrayList<StockEntry> lowItems) {
        this.lowItems = lowItems;
    }

    @FXML
    void showBottomRight() {
        NotificationPos pos = NotificationPos.BOTTOM_RIGHT;
        showNotification(pos);
    }

    public void showBottomRight(ArrayList<StockEntry> lowItems) {
        NotificationPos pos = NotificationPos.BOTTOM_RIGHT;
        setLowItems(lowItems);
        showNotification(pos);
    }

    private String generateMessageFromList(ArrayList<StockEntry> lowItems) {
        String message = "";
        for (StockEntry current : lowItems) {
            message = message + "Book ID: " + current.getBook_id() + "\n";
        }
        return message;
    }


    private void showNotification(NotificationPos pos) {
        MFXNotification notification = buildNotification();
        NotificationsManager.send(pos, notification);
    }

    private MFXNotification buildNotification() {
        Region template = getRandomTemplate();
        MFXNotification notification = new MFXNotification(template, true, true);
        notification.setHideAfterDuration(Duration.seconds(3));

        if (template instanceof SimpleMFXNotificationPane) {
            SimpleMFXNotificationPane pane = (SimpleMFXNotificationPane) template;
            pane.setCloseHandler(closeEvent -> notification.hideNotification());
        } else {
            MFXDialog dialog = (MFXDialog) template;
            dialog.setCloseHandler(closeEvent -> notification.hideNotification());
        }

        return notification;
    }

    private Region getRandomTemplate() {
        final int rand = 1;
        String message = "The following items :\n" + generateMessageFromList(lowItems) + "are low on stock !!";
        switch (rand) {
            /*case 0:
                FontIcon icon1 = new FontIcon("fas-info-circle");
                icon1.setIconColor(Color.LIGHTBLUE);
                icon1.setIconSize(15);
                return new SimpleMFXNotificationPane(
                        icon1,
                        "Dummy Notification",
                        title,
                        dummy
                );
            case 1:
                FontIcon icon2 = new FontIcon("fas-cocktail");
                icon2.setIconColor(Color.GREEN);
                icon2.setIconSize(15);
                return new SimpleMFXNotificationPane(
                        icon2,
                        "Fast Food",
                        title,
                        "Hello username, your order is on the way!"
                );
            case 2:
                FontIcon icon3 = new FontIcon("fab-whatsapp");
                icon3.setIconColor(Color.GREEN);
                icon3.setIconSize(15);
                return new SimpleMFXNotificationPane(
                        icon3,
                        "Whatsapp Notification",
                        title,
                        "Hi Mark, it's been ages since we last spoke!\nHow are you?"
                );*/
            case 1:
                AbstractMFXDialog dialog = MFXDialogFactory.buildDialog(DialogType.WARNING, "Warning ! Some items are running low on stock ...", message);
                dialog.setVisible(true);
                return dialog;
            default:
                return null;
        }
    }


}
