package fct.cs.NewEmployee;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.events.JFXDrawerEvent;
import fct.cs.data.Category;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.enums.Styles;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import java.util.ArrayList;

public class NewEmployeeController implements Initializable {

    /* TODO : Add makeManager function to update form */
    /* TODO : Improve styling of Add Employee form */

    public MFXTableView empTable;
    public JFXDrawer drawer;

    private NewEmployeeController thisController = this;

    private ObservableList<EmployeeData> employeeObservableList = FXCollections.observableArrayList();
    private FilteredList<EmployeeData> employeeFilteredList;

    private static EmployeeData selectedEmployee = null;
    private final MFXTextField searchField = new MFXTextField();
    private final MFXComboBox<String> searchCombo = new MFXComboBox<>();

    private ObservableList<Category> categoryList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setColumnProps();

        getEmployeeData();

        empTable.setHeaderSupplier(() -> {
            HBox mainContainer = new HBox();
            mainContainer.setPrefHeight(50);
            mainContainer.setPrefWidth(1030);
            mainContainer.setAlignment(Pos.CENTER_LEFT);
            mainContainer.setPadding(new Insets(10, 0, 5, 5));

            HBox spanMid = new HBox();
            spanMid.setMinWidth(610);
            spanMid.setMinHeight(50);

            HBox spanEnd = new HBox();
            spanEnd.setMinHeight(50);
            spanEnd.setMaxWidth(10);

            HBox searchContainer = new HBox(10);
            searchContainer.setMinHeight(50);
            searchContainer.setAlignment(Pos.CENTER_RIGHT);
            searchField.setPromptText("Search Employees");
            searchField.setIcon(new MFXIconWrapper(new MFXFontIcon("mfx-search", 28, Color.web("#4D4D4D")), 24));
            searchField.setIconInsets(new Insets(0, 0, 10, 0));
            searchField.setMinHeight(50);
            searchField.setStyle("-fx-font-size: 18px;");

            searchField.setOnKeyTyped(actionEvent -> {
                employeeFilteredList = new FilteredList<EmployeeData>(employeeObservableList);
                thisController.searchTableFromText(searchField.getText());
                empTable.setItems(employeeFilteredList);
            });

            searchCombo.setItems(FXCollections.observableArrayList("Name", "Salary", "Gender"));
            searchCombo.setPromptText("Select Category");
            searchCombo.setMinHeight(50);
            searchCombo.setComboStyle(Styles.ComboBoxStyles.STYLE2);
            searchCombo.setStyle("-fx-font-size: 30px;");

            FontIcon addIcon = new FontIcon("anto-plus-circle");
            addIcon.setIconColor(Color.WHITE);
            addIcon.setIconSize(25);

            MFXButton addBtn = new MFXButton();
            addBtn.setText("Add Employee");
            addBtn.setStyle("-fx-background-color: #2B2B2B;-fx-font-size: 20px;-fx-background-radius: 9,8,5,4,3;-fx-text-fill: #fff;");
            addBtn.setGraphic(addIcon);
            addBtn.setOnAction(actionEvent -> {
                thisController.addNewEntry();
            });


            /*MFXLabel testLabel = new MFXLabel("Header");
            HBox holderHbox = new HBox();
            holderHbox.getChildren().addAll(testLabel);
            holderHbox.setMaxWidth(Region.USE_PREF_SIZE);
            VBox box = new VBox(holderHbox);
            box.setAlignment(Pos.CENTER_RIGHT);*/

            searchContainer.getChildren().addAll(searchField);
            mainContainer.getChildren().addAll(searchContainer, spanMid, addBtn, spanEnd);
            return mainContainer;
        });

    }

    public void getEmployeeData() {
        ArrayList<EmployeeData> eList = EmployeeManager.getEmployeeList(100, 1);
        Platform.runLater(() -> {
            System.out.println("Later");
            employeeObservableList.clear();
            for (EmployeeData e :
                    eList) {
                System.out.println(e.toString());
                if(!e.isIsManager()){
                    employeeObservableList.add(e);
                }

            }
        });
        setTableData();

    }

    private void setTableData() {
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
                System.out.println("Update: " + employeeData.toString());

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/employee/employee-form.fxml"));
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


        empTable.getTableColumns().addAll(empIdColumn, fMameColumn, lNameColumn, salaryColumn, updateColumn, deleteColumn);
    }

    public void hideDrawer(JFXDrawerEvent jfxDrawerEvent) {
        drawer.toBack();
    }

    public void addNewEntry() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fct/cs/fxml/employee/add-employee-form.fxml"));
            VBox box = loader.load();
            EmployeeFormController controller = loader.getController();
            controller.setParentController(thisController);
            controller.setDrawer(drawer);
//                    controller.setInventoryManager(inventoryManager);
            controller.setAddingNew(true);
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

    public void searchTableFromText(String key) {
        System.out.println("Searching ...");
        employeeFilteredList.setPredicate(employeeData -> {
            String filter = key.toLowerCase();
            boolean nameMatches = String.valueOf(employeeData.getFirst_name()).toLowerCase().contains(filter)
                    || String.valueOf(employeeData.getLast_name()).toLowerCase().contains(filter);
            return nameMatches;
        });
    }
}
