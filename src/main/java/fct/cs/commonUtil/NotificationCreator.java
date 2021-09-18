package fct.cs.commonUtil;


import fct.cs.inventory.StockEntry;
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
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;


public class NotificationCreator {
    /* TODO : Reimplement Inventory Page Table in MFXTable */
    /* TODO : Change add new entry drawer promt text */


    private static ArrayList<StockEntry> lowItems = new ArrayList<>();

    public static void setLowItems(ArrayList<StockEntry> items) {
        lowItems = items;
    }

    @FXML
    public static void showBottomRight() {
        NotificationPos pos = NotificationPos.BOTTOM_RIGHT;
        showNotification(pos);
    }

    public static void showBottomRight(ArrayList<StockEntry> lowItems) {
        NotificationPos pos = NotificationPos.BOTTOM_RIGHT;
        setLowItems(lowItems);
        showNotification(pos);
    }

    private static String generateMessageFromList(ArrayList<StockEntry> lowItems) {
        String message = "";
        for (StockEntry current : lowItems) {
            message = message + "Book ID: " + current.getBook_id() + "\n";
        }
        return message;
    }

    private static void showNotification(NotificationPos pos) {
        MFXNotification notification = buildNotification();
        NotificationsManager.send(pos, notification);
    }

    private static MFXNotification buildNotification() {
        Region template = getLowTemplate();
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

    private static Region getLowTemplate() {
        String message = "The following items :\n" + generateMessageFromList(lowItems) + "are low on stock !!";
        AbstractMFXDialog dialog = MFXDialogFactory.buildDialog(DialogType.WARNING, "Warning ! Some items are running low on stock ...", message);
        dialog.setVisible(true);
        return dialog;
    }

    private static Region getWarningTemplate(String title ,String content) {
        FontIcon warnIcon = new FontIcon("antf-warning");
        warnIcon.setIconColor(Color.RED);
        warnIcon.setIconSize(15);
        return new SimpleMFXNotificationPane(
                warnIcon,
                "Warning",
                title,
                content);

    }

    private static Region getSuccessTemplate(String title ,String content) {
        FontIcon successIcon = new FontIcon("antf-check-circle");
        successIcon.setIconColor(Color.GREEN);
        successIcon.setIconSize(15);
        return new SimpleMFXNotificationPane(
                successIcon,
                "Success",
                title,
                content);

    }

    public static void showErrorBottomRight(String title, String message) {
        NotificationPos pos = NotificationPos.BOTTOM_RIGHT;
        Region template = getWarningTemplate(title,message);
        MFXNotification notification = new MFXNotification(template, true, true);
        notification.setHideAfterDuration(Duration.seconds(3));
        SimpleMFXNotificationPane pane = (SimpleMFXNotificationPane) template;
        pane.setCloseHandler(closeEvent -> notification.hideNotification());
        NotificationsManager.send(pos, notification);
    }

    public static void showSuccessBottomRight(String title, String message) {
        NotificationPos pos = NotificationPos.BOTTOM_RIGHT;
        Region template = getSuccessTemplate(title,message);
        MFXNotification notification = new MFXNotification(template, true, true);
        notification.setHideAfterDuration(Duration.seconds(3));
        SimpleMFXNotificationPane pane = (SimpleMFXNotificationPane) template;
        pane.setCloseHandler(closeEvent -> notification.hideNotification());
        NotificationsManager.send(pos, notification);
    }
}



