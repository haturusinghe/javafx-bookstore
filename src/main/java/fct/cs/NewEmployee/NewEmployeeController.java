package fct.cs.NewEmployee;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.base.AbstractMFXDialog;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.enums.DialogType;
import io.github.palexdev.materialfx.controls.factories.MFXDialogFactory;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import java.util.ArrayList;

public class NewEmployeeController implements Initializable {
    public MFXTableView empTable;
    public JFXDrawer drawer;
    private NewEmployeeController thisController = this;
    private ObservableList<EmployeeData> employeeObservableList = FXCollections.observableArrayList();
    private FilteredList<EmployeeData> employeeFilteredList = new FilteredList<>(employeeObservableList);
    private static EmployeeData selectedEmployee = null;

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getEmployeeData();
        setColumnProps();
    }

    public void getEmployeeData() {
        ArrayList<EmployeeData> eList = EmployeeManager.getEmployeeList(100, 1);
        employeeObservableList.clear();
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
        MFXTableColumn<EmployeeData> updateColumn = new MFXTableColumn<>("", Comparator.comparing(EmployeeData::getEmployee_id));
        MFXTableColumn<EmployeeData> deleteColumn = new MFXTableColumn<>("", Comparator.comparing(EmployeeData::getEmployee_id));

        empIdColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));
        fMameColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getFirst_name())));
        lNameColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getLast_name())));
        salaryColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getSalary())));

//        updateColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));
//        deleteColumn.setRowCellFunction(employee -> new MFXTableRowCell(String.valueOf(employee.getEmployee_id())));

        updateColumn.setMinWidth(100);
        updateColumn.setShowLockIcon(false);
        updateColumn.setRowCellFunction(employeeData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Update");
            rowCell.setGraphicTextGap(5);
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Update");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/employee-form.fxml"));
                    VBox box = loader.load();
                     EmployeeFormController controller = loader.getController();

                    controller.setParentController(thisController);
                    controller.setDrawer(drawer);
//                    controller.setInventoryManager(inventoryManager);
                    controller.setEntry(employeeData);
                    controller.setAddingNew(false);
                    drawer.setSidePane(box);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (drawer.isHidden()) {
                    drawer.open();
                    drawer.toFront();
                    System.out.println("open");
                } else {
                    drawer.toBack();
                    drawer.close();

                    System.out.println("close");
                }

            });
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

        deleteColumn.setMinWidth(100);
        deleteColumn.setShowLockIcon(false);
        deleteColumn.setRowCellFunction(employeeData -> {
            MFXTableRowCell rowCell = new MFXTableRowCell("Delete");
            rowCell.setGraphicTextGap(5);
//            rowCell.setStyle("-fx-background-color:grey");
            rowCell.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                System.out.println("Delete");
                EmployeeManager.deleteSingleEmployee(employeeData);
                getEmployeeData();
            });
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

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewEntry(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/employee-form.fxml"));
            VBox box = loader.load();
            EmployeeFormController controller = loader.getController();

            controller.setParentController(thisController);
            controller.setDrawer(drawer);
//                    controller.setInventoryManager(inventoryManager);
//            controller.setEntry(employeeData);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (drawer.isHidden()) {
            drawer.open();
            drawer.toFront();
            System.out.println("open");
        } else {
            drawer.toBack();
            drawer.close();

            System.out.println("close");
        }
    }
}
