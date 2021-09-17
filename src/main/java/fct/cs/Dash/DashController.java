package fct.cs.Dash;


import eu.hansolo.fx.charts.ChartType;
import eu.hansolo.fx.charts.CoxcombChart;
import eu.hansolo.fx.charts.data.ChartItem;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.utils.ColorUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
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
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DashController implements Initializable {


    public VBox stockItem_vbox;
    public VBox revenue_vbox;
    public VBox StockItemList_vbox;
    public Label stockItemsLabel;
    public MFXComboBox<Long> revYearCombo;
    public MFXButton chartRefreshBtn;


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

    @FXML
    private MFXListView stockItemListView;
    private ObservableList<VBox> stockObservableList = FXCollections.observableArrayList();

    private ObservableList<Long> yearObservableList = FXCollections.observableArrayList();

    ArrayList<MonthlyEntry> reportData;

    XYChart.Series dataSeries1 = new XYChart.Series();
    XYChart.Series dataSeries2 = new XYChart.Series();
    XYChart.Series dataSeries3 = new XYChart.Series();

    public void setManager(boolean isManager) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FontIcon refreshIcon = new FontIcon("cil-low-vision");
        refreshIcon.setIconColor(Color.BLACK);
        refreshIcon.setIconSize(20);

        chartRefreshBtn.setText("");
        chartRefreshBtn.setGraphic(refreshIcon);
        chartRefreshBtn.setOnAction(actionEvent -> {
            loadChartData();
        });

        initAreaCharts();
        setInfoCards();
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
        for (BookSale e :
                list) {
            booksObservableList.add(createBookListItem(e));
        }
        popularItemsList.setItems(booksObservableList);
    }

    private void initCoxcombChart() {
        List<ChartItem> items = new ArrayList<>();
        stockItemListView.setItems(stockObservableList);

        try {
            Dictionary<String, Integer> dict = DashManager.getStockItems();
            Random rand = new Random();
            for (Enumeration<String> keys = dict.keys(); keys.hasMoreElements(); ) { // iterate over all keys
                String keyVal = keys.nextElement();
                int v = Integer.parseInt(totalItems.getText());
                int w = dict.get(keyVal);
                double d = ((double) w) / v;

                d = Math.round(d * 100);
//                System.out.println(w);
                Color rColor = ColorUtils.getRandomColor();
                items.add(new ChartItem(keyVal, w, rColor));
                stockObservableList.add(createCoxcombLegend(keyVal, w, rColor));
//                StockItemList_vbox.setSpacing(5);
//                StockItemList_vbox.getChildren().add(createCoxcombLegend(keyVal, w, rColor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CoxcombChart chart = new CoxcombChart(items);
        chart.setAutoTextColor(true);
        stockItem_vbox.getChildren().add(chart);
    }

    private void initAreaCharts() {
        dataSeries1.setName("Total Sales");
        dataSeries2.setName("Discount");
        dataSeries3.setName("Profit");

        try {
            Set<Long> years = new HashSet();
            reportData = DashManager.getMonthlyReport();
            System.out.println("OK");
            for (MonthlyEntry e : reportData) {
                System.out.println(e.toString());
                years.add(e.getOrderYear());
            }
            years.forEach(year -> {
                yearObservableList.add(year);
            });
            revYearCombo.setItems(yearObservableList);
            revYearCombo.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadChartData();

        revenueChart.getData().addAll(dataSeries1);
        revenueChart.getData().addAll(dataSeries2);
        revenueChart.getData().addAll(dataSeries3);
        revenueChart.setLegendVisible(true);
        revenueChart.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        revenueChart.getYAxis().setTickMarkVisible(false);
    }

    private void loadChartData() {
        dataSeries1.getData().clear();
        dataSeries2.getData().clear();
        dataSeries3.getData().clear();
        for (MonthlyEntry e : reportData) {
            if(e.getOrderYear() == revYearCombo.getSelectionModel().getSelectedItem()){
                dataSeries1.getData().add(new XYChart.Data(e.getOrderMonth(), e.getSalesPerMonth()));
                dataSeries2.getData().add(new XYChart.Data(e.getOrderMonth(), e.getDiscountPerMonth()));
                dataSeries3.getData().add(new XYChart.Data(e.getOrderMonth(), e.getProfitPerMonth()));
            }
        }
    }

    private void setYearCombo() {

    }

    private void setInfoCards() {
        try {
            totalItems.setText(String.valueOf(DashManager.getTotalStockQuantity()));
            totalItems.setEditable(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            totalSales.setText("Rs." + String.valueOf(DashManager.getTotalCashSales()));
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
        Color color = ColorUtils.getRandomColor();
        VBox vBox = new VBox();
//        vBox.setStyle("-fx-padding: 10px 15px;");
//        vBox.setStyle("-fx-background-radius: 10px;");
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setPadding(new Insets(5, 0, 5, 10));
        HBox titleHbox = new HBox();
        HBox orderHbox = new HBox();
        HBox amountHbox = new HBox();

        Color textColor = null;
        if ((color.getRed()*0.299 + color.getGreen()*0.587 + color.getBlue()*0.114) > 150){
            textColor = Color.BLACK;
        }else{
            textColor = Color.WHITE;
        }


        FontIcon bookIcon = new FontIcon("ps-book-tag");
        bookIcon.setIconColor(textColor);
        bookIcon.setIconSize(20);

        Label titleLabel = new Label("Title: " + e.getTitle(), bookIcon);
        titleLabel.setTextFill(textColor);
        //font-family: 'Josefin Sans', sans-serif;
//        titleLabel.setStyle("-fx-font-size: 20;");
//        titleLabel.setStyle("-fx-padding: 10px 15px;");
        titleHbox.getChildren().addAll(titleLabel);

        FontIcon amountIcon = new FontIcon("ps-data-board");
        amountIcon.setIconColor(textColor);
        amountIcon.setIconSize(16);
        Label amountLabel = new Label("Amount Sold: " + e.getTotalQtySold(),amountIcon);
        amountLabel.setTextFill(textColor);
//        amountLabel.setStyle("-fx-padding: 10px 15px;");

        FontIcon ordersIcon = new FontIcon("ps-cart-supermarket");
        ordersIcon.setIconColor(textColor);
        ordersIcon.setIconSize(14);
        Label orderLabel = new Label("Number of Orders: " + e.getNumOrders(),ordersIcon);
        orderLabel.setTextFill(textColor);

        amountHbox.getChildren().addAll(amountLabel);
        orderHbox.getChildren().addAll(orderLabel);

        titleLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 20;");
        amountLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 16;");
        orderLabel.setStyle("-fx-font-family: 'Work Sans'; -fx-font-size: 14;");
        vBox.getChildren().addAll(titleHbox, amountHbox, orderHbox);
        return vBox;
    }

    private VBox createCoxcombLegend(String bookName, int count, Color color) {
        Color textColor = null;
        if ((color.getRed()*0.299 + color.getGreen()*0.587 + color.getBlue()*0.114) > 150){
            textColor = Color.BLACK;
        }else{
            textColor = Color.WHITE;
        }

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), Insets.EMPTY)));
        /*hBox.setPadding(new Insets(0, 10, 0, 10));
        hBox.setPrefSize(200, 30);*/

        FontIcon bookIcon = new FontIcon("fas-book");
        bookIcon.setIconColor(textColor);
        bookIcon.setIconSize(12);
        Label bookNameLabel = new Label("Title: " + bookName, bookIcon);

        FontIcon amountIcon = new FontIcon("fas-box");
        amountIcon.setIconColor(textColor);
        amountIcon.setIconSize(12);
        Label amountLabel = new Label("Amount: " + count, amountIcon);

        amountLabel.setTextFill(textColor);
        bookNameLabel.setTextFill(textColor);

        hBox1.getChildren().addAll(bookNameLabel);
        hBox2.getChildren().addAll(amountLabel);
        vBox.getChildren().addAll(hBox1, hBox2);
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
