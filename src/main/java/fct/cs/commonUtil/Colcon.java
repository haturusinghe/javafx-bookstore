package fct.cs.commonUtil;



import io.github.palexdev.materialfx.utils.ColorUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Colcon {

    public Label text;
    public TextField total;
    @FXML
    private Button btn;

    @FXML
    private VBox bg;

    @FXML
    private TextField red;

    @FXML
    private TextField green;

    @FXML
    private TextField blue;

    @FXML
    void randomColor(ActionEvent event) {
        Color color = ColorUtils.getRandomColor();
        double r = color.getRed() ;
        double g = color.getGreen();
        double b = color.getBlue();
        bg.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));

        red.setText(String.valueOf(r));
        green.setText(String.valueOf(g));
        blue.setText(String.valueOf(b));


        Color textColor = null;
        double totally = (r*0.299 + g*0.587 + b*0.114)*100;
        total.setText(String.valueOf(totally));

        if (totally > 50){
            textColor = Color.BLACK;
        }else{
            textColor = Color.WHITE;
        }
        text.setTextFill(textColor);

    }

}
