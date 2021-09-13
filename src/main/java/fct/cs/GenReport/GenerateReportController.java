package fct.cs.GenReport;

import com.jfoenix.controls.JFXButton;
import fct.cs.Dash.DashManager;
import fct.cs.Dash.MonthlyEntry;
import fct.cs.NewEmployee.EmployeeData;
import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class GenerateReportController implements Initializable {

    public MFXTableView reportsTable;
    public JFXButton generateBtn;
    private ObservableList<MonthlyEntry> monthlyObservableList = FXCollections.observableArrayList();

    public void generateMonthly(ActionEvent actionEvent) {
        JasperPrint jp;
        Map params = new HashMap();

        params.put("year",2021);
        params.put("month",3);
        try {
            Connection conn = DatabaseHandler.getInstance().getConn();

//            JasperReport jr = JasperCompileManager.compileReport(f.getAbsolutePath());
            File f = new File("jasper_files/monthly/book_sales_monthly.jasper");
            System.out.println(f.getAbsolutePath());
            jp = JasperFillManager.fillReport(f.getAbsolutePath(),params,conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Report");
            viewer.setVisible(true);

        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void generateYearly(ActionEvent actionEvent) {
        JasperPrint jp;
        Map params = new HashMap();

        params.put("year",2020);
        try {
            Connection conn = DatabaseHandler.getInstance().getConn();

//            JasperReport jr = JasperCompileManager.compileReport(f.getAbsolutePath());
            File f = new File("jasper_files/yearly/sales_monthly.jasper");
            System.out.println(f.getAbsolutePath());
            jp = JasperFillManager.fillReport(f.getAbsolutePath(),params,conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Report");
            viewer.setVisible(true);

        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getMonthlyData();
        initColumns();
        setColumnAttr();
    }

    public void getMonthlyData() {
        ArrayList<MonthlyEntry> list = null;
        try {
            list = DashManager.getMonthlyReport();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        monthlyObservableList.clear();
        for (MonthlyEntry e :
                list) {
            monthlyObservableList.add(e);

        }
        setDataData();
    }

    private void setDataData() {
        reportsTable.setItems(monthlyObservableList);
    }

    private void initColumns() {

        MFXTableColumn<MonthlyEntry> yearCol = new MFXTableColumn<>("Year", Comparator.comparing(MonthlyEntry::getOrderYear));
        MFXTableColumn<MonthlyEntry> monthCol = new MFXTableColumn<>("Month", Comparator.comparing(MonthlyEntry::getOrderMonth));
        MFXTableColumn<MonthlyEntry> salesCol = new MFXTableColumn<>("Sales (Rs.)", Comparator.comparing(MonthlyEntry::getSalesPerMonth));
        MFXTableColumn<MonthlyEntry> discountCol = new MFXTableColumn<>("Discount (Rs.)", Comparator.comparing(MonthlyEntry::getDiscountPerMonth));
        MFXTableColumn<MonthlyEntry> profitCol = new MFXTableColumn<>("Profit (Rs.)", Comparator.comparing(MonthlyEntry::getProfitPerMonth));
        MFXTableColumn<MonthlyEntry> ordersCol = new MFXTableColumn<>("Orders", Comparator.comparing(MonthlyEntry::getOrdersPerMonth));
        MFXTableColumn<MonthlyEntry> qtyCol = new MFXTableColumn<>("Quantity", Comparator.comparing(MonthlyEntry::getQuantityPerMonth));

        yearCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getOrderYear())));
        monthCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getOrderMonth())));
        salesCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getSalesPerMonth())));
        discountCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getDiscountPerMonth())));
        ordersCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getOrdersPerMonth())));
        profitCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getProfitPerMonth())));
        qtyCol.setRowCellFunction(monthlyEntry -> new MFXTableRowCell(String.valueOf(monthlyEntry.getQuantityPerMonth())));

        reportsTable.getTableColumns().addAll(yearCol, monthCol, salesCol, discountCol, profitCol, ordersCol, qtyCol);
        reportsTable.setMaxWidth(950);


    }

    private void setColumnAttr() {
        ObservableList<MFXTableColumn> colList = reportsTable.getTableColumns();

        for (MFXTableColumn col :
                colList) {
            col.setMinWidth(30);
            col.setShowLockIcon(false);
            System.out.println(col.getWidth());
        }
    }
}
