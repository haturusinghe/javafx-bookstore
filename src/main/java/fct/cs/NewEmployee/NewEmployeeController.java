package fct.cs.NewEmployee;


import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class NewEmployeeController implements Initializable {
    public MFXTableView empTable;

    private ObservableList<EmployeeData> employeeObservableList = FXCollections.observableArrayList();
    private FilteredList<EmployeeData> employeeFilteredList = new FilteredList<>(employeeObservableList);
    private static EmployeeData selectedEmployee = null;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getEmployeeData();
        setColumnProps();

    }

    private void getEmployeeData() {
        ArrayList<EmployeeData> eList = EmployeeManager.getEmployeeList(100, 1);
        for (EmployeeData e:
             eList) {
            employeeObservableList.add(e);

        }
        setDataData();
    }

    private void setDataData() {
        empTable.setItems(employeeObservableList);
    }

    @SuppressWarnings("unchecked")
    private void setColumnProps() {

        MFXTableColumn<EmployeeData> empIdColumn = new MFXTableColumn<>("Employee ID", Comparator.comparing(EmployeeData::getEmployee_id));
        MFXTableColumn<EmployeeData> fMameColumn = new MFXTableColumn<>("First Name", Comparator.comparing(EmployeeData::getFirst_name));
        MFXTableColumn<EmployeeData> lNameColumn = new MFXTableColumn<>("Last Name", Comparator.comparing(EmployeeData::getLast_name));
        MFXTableColumn<EmployeeData> salaryColumn = new MFXTableColumn<>("Salary", Comparator.comparing(EmployeeData::getSalary));
        MFXTableColumn<EmployeeData> updateColumn = new MFXTableColumn<>("Update", Comparator.comparing(EmployeeData::getEmployee_id));
        MFXTableColumn<EmployeeData> deleteColumn = new MFXTableColumn<>("Delete", Comparator.comparing(EmployeeData::getEmployee_id));

        empIdColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));
        fMameColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getFirst_name())));
        lNameColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getLast_name())));
        salaryColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getSalary())));

//        updateColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));
//        deleteColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));

        updateColumn.setMinWidth(30);
        updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(employeeData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("");
            rowCell.setGraphicTextGap(0);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> System.out.println("Update"));
            MFXFontIcon icon = new MFXFontIcon("mfx-file", 25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.LIMEGREEN, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> employeeData.setState(employeeData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });

        deleteColumn.setMinWidth(30);
        deleteColumn.setShowLockIcon(false);
        deleteColumn.setRowCellFunction(employeeData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("");
            rowCell.setGraphicTextGap(0);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent -> System.out.println("Delete"));
            MFXFontIcon icon = new MFXFontIcon("mfx-minus-circle", 25);
//            FontIcon icon = new FontIcon("antf-edit");
//            icon.setIconSize(10);
            rowCell.setRowAlignment(Pos.CENTER);
            rowCell.setLeadingGraphic(icon);
            rowCell.borderProperty().set(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
//            rowCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> employeeData.setState(employeeData.getState() == ONLINE ? OFFLINE : ONLINE));
            rowCell.setPadding(new Insets(5, 5, 5, 5));
            return rowCell;
        });
//        updateColumn.setResizable(false);
//        updateColumn.setMaxWidth(20);


        empTable.getTableColumns().addAll(empIdColumn, fMameColumn,lNameColumn, salaryColumn,updateColumn,deleteColumn);
    }
}
