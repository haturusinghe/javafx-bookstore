package fct.cs.Dash;


import eu.hansolo.fx.charts.CoxcombChart;
import eu.hansolo.fx.charts.data.ChartItem;

import io.github.palexdev.materialfx.controls.MFXFlowlessListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.utils.ColorUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class DashController implements Initializable {


    public VBox stockItem_vbox;
    public VBox revanue_vbox;
    public VBox StockItemList_vbox;
    @FXML
    private TextField totalItems;

    @FXML
    private TextField totalSales;

    @FXML
    private TextField totalOrders;

    @FXML
    private TextField itemsSold;

    public AreaChart revenueChart;

    public PieChart stockPieChart;

    @FXML
    private MFXListView<VBox> popularItemsList;

    private ObservableList<VBox> booksObservableList = FXCollections.observableArrayList();

    public void setManager(boolean isManager) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAreaCharts();
        setTextFields();
        initCoxcombChart();
        initPopularItemsList();
    }

    private void initPopularItemsList() {
        ArrayList<BookSale> list = new ArrayList<>();
        try {
            list = DashManager.getPopularBooks(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (BookSale e:
             list) {
            booksObservableList.add(createBookListItem(e));
        }
        popularItemsList.setItems(booksObservableList);
    }

    private void initCoxcombChart() {
        List<ChartItem> items = new ArrayList<>();

        try {
            Dictionary<String, Integer> dict = DashManager.getStockItems();
            Random rand = new Random();
            for (Enumeration<String> keys = dict.keys(); keys.hasMoreElements(); ) { // iterate over all keys
                String keyVal = keys.nextElement();
                int v = Integer.parseInt(totalItems.getText());
                int w = dict.get(keyVal) ;
                double d = ((double) w) / v;

                d = Math.round(d * 100);
                System.out.println(w);
                Color rColor = ColorUtils.getRandomColor();
                items.add(new ChartItem(keyVal,w,rColor));
                StockItemList_vbox.setSpacing(5);
                StockItemList_vbox.getChildren().add(createCoxcombLegend(keyVal,w,rColor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CoxcombChart chart = new CoxcombChart(items);
        chart.setAutoTextColor(true);
        stockItem_vbox.getChildren().add(chart);
    }

    private void initAreaCharts() {
        XYChart.Series dataSeries1 = new XYChart.Series();
        XYChart.Series dataSeries2 = new XYChart.Series();
        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries1.setName("Total Sales -  2021");
        dataSeries2.setName("Discount - 2021");
        dataSeries3.setName("Profit - 2021");

        ArrayList<MonthlyEntry> reportData = null;
        try {
            reportData = DashManager.getMonthlyReport();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (MonthlyEntry e: reportData) {
            dataSeries1.getData().add(new XYChart.Data(e.getOrderMonth(), e.getSalesPerMonth()));
            dataSeries2.getData().add(new XYChart.Data(e.getOrderMonth(), e.getDiscountPerMonth()));
            dataSeries3.getData().add(new XYChart.Data(e.getOrderMonth(), e.getProfitPerMonth()));
        }
        revenueChart.getData().addAll(dataSeries1);
        revenueChart.getData().addAll(dataSeries2);
        revenueChart.getData().addAll(dataSeries3);
        revenueChart.setLegendVisible(true);
        revenueChart.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        revenueChart.getYAxis().setTickMarkVisible(false);
    }

    private void setTextFields() {
        try {
            totalItems.setText(String.valueOf(DashManager.getTotalStockQuantity()));
            totalItems.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            totalSales.setText(String.valueOf(DashManager.getTotalCashSales()));
            totalSales.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            totalOrders.setText(String.valueOf(DashManager.getTotalOrders()));
            totalOrders.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            itemsSold.setText(String.valueOf(DashManager.getTotalQuantity()));
            itemsSold.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private VBox createBookListItem(BookSale e) {
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();



        FontIcon bookIcon = new FontIcon("cil-book");
        bookIcon.setIconColor(Color.BLACK);
        bookIcon.setIconSize(12);
        Label label1 = new Label("Title " + e.getTitle(), bookIcon);
        hBox1.getChildren().addAll(label1);

        Label label3 = new Label("Number of Orders: " + e.getNumOrders());
        hBox2.getChildren().addAll(label3);
        Label label2 = new Label("Amount Sold: " + e.getTotalQtySold());
        hBox3.getChildren().addAll(label2);

        vBox.getChildren().addAll(hBox1, hBox2,hBox3);
        return vBox;
    }

    private VBox createCoxcombLegend(String bookName, int count,Color color) {
//        HBox hBox = new HBox(20);
        HBox hBox = new HBox();
        VBox vBox  = new VBox();
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), Insets.EMPTY)));
        /*hBox.setPadding(new Insets(0, 10, 0, 10));
        hBox.setPrefSize(200, 30);*/


        FontIcon bookIcon = new FontIcon("cil-book");
        bookIcon.setIconColor(Color.WHITE);
        bookIcon.setIconSize(12);
        Label amountLabel = new Label("Amount: " + count);
        Label bookNameLabel = new Label("Title: " + bookName,bookIcon);
        amountLabel.setTextFill(Paint.valueOf("#fff"));
        bookNameLabel.setTextFill(Paint.valueOf("#fff"));

        hBox.getChildren().addAll(bookNameLabel,amountLabel);
        vBox.getChildren().addAll(bookNameLabel,amountLabel);
        return vBox;
    }

    /*private void updateColors() {
        if (state.get() == State.LEGACY) {
            cssView.setTrackColor(ColorUtils.getRandomColor());
            cssView.setThumbColor(ColorUtils.getRandomColor());
            cssView.setThumbHoverColor(ColorUtils.getRandomColor());
        } else {
            cssViewNew.setTrackColor(ColorUtils.getRandomColor());
            cssViewNew.setThumbColor(ColorUtils.getRandomColor());
            cssViewNew.setThumbHoverColor(ColorUtils.getRandomColor());
        }
    }*/
}
