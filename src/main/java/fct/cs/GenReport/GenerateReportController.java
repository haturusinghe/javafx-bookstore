package fct.cs.GenReport;

import fct.cs.dbUtil.DatabaseHandler;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.enums.StepperToggleState;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.BindingUtils;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class GenerateReportController implements Initializable {

    /* TODO : Improve UI */

    public MFXStepper stepper;
    private final MFXTextField reportTypeSelected;
    private final MFXTextField selectedYear;
    private final MFXTextField selectedMonth;
    private final MFXComboBox<String> reportTypeCombo;
    private final MFXComboBox<Integer> yearCombo;
    private final MFXComboBox<SimpleMonth> monthCombo;
    private MFXLabel monthLabel1 = new MFXLabel();
    private MFXLabel monthLabel2 = new MFXLabel();

    ObservableList<String> reportTypesList;

    ObservableList<Integer> yearList;

    ObservableList<SimpleMonth> monthList;


    public GenerateReportController() {
        reportTypeSelected = new MFXTextField();
        selectedYear = new MFXTextField();
        selectedMonth = new MFXTextField();
        reportTypeCombo = new MFXComboBox<>();
        yearCombo = new MFXComboBox<>();
        monthCombo = new MFXComboBox<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initLists();
        initFields();


        List<MFXStepperToggle> stepperToggles = createSteps();
        stepper.getStepperToggles().addAll(stepperToggles);

        stepper.setOnLastNext(event -> {
            if (reportTypeCombo.getSelectionModel().getSelectedItem() == "Yearly Report") {
                generateYearly(yearCombo.getSelectedValue());
            } else {
                generateMonthly(yearCombo.getSelectedValue(), monthCombo.getSelectedValue().getMonthNum());
            }
            resetForm();
            stepper.getStepperToggles().clear();
            stepper.getStepperToggles().addAll(stepperToggles);

        });


    }

    private void initFields() {
        reportTypeCombo.setItems(reportTypesList);
        reportTypeCombo.setPromptText("Select Report Type");
        reportTypeCombo.setValidated(true);
        reportTypeCombo.getValidator().add(BindingUtils.toProperty(reportTypeCombo.getSelectionModel().selectedIndexProperty().isNotEqualTo(-1)), "A value must be selected");

        yearCombo.setItems(yearList);
        yearCombo.setPromptText("Select Year");
        yearCombo.setValidated(true);
        yearCombo.getValidator().add(BindingUtils.toProperty(yearCombo.getSelectionModel().selectedIndexProperty().isNotEqualTo(-1)), "A value must be selected");

        monthCombo.setItems(monthList);
        monthCombo.setPromptText("Select Month");
        monthCombo.setValidated(true);
        monthCombo.getValidator().add(BindingUtils.toProperty(monthCombo.getSelectionModel().selectedIndexProperty().isNotEqualTo(-1)), "A value must be selected");
    }

    private void initLists() {
        reportTypesList = FXCollections.observableArrayList(List.of(
                "Yearly Report",
                "Monthly Report"
        ));

        yearList = FXCollections.observableArrayList(List.of(
                2021,
                2020
        ));

        monthList = FXCollections.observableArrayList(List.of(
                new SimpleMonth("January", 1),
                new SimpleMonth("February", 2),
                new SimpleMonth("March", 3),
                new SimpleMonth("April", 4),
                new SimpleMonth("May", 5),
                new SimpleMonth("June", 6),
                new SimpleMonth("July", 7),
                new SimpleMonth("August", 8),
                new SimpleMonth("September", 9),
                new SimpleMonth("October", 10),
                new SimpleMonth("November", 11),
                new SimpleMonth("December", 12)
        ));
    }

    private List<MFXStepperToggle> createSteps() {
        FontIcon reportIcon = new FontIcon("antf-fund");
        reportIcon.setIconSize(16);
        reportIcon.setIconColor(Color.web("#f1c40f"));


        MFXStepperToggle selectTypeStep1 = new MFXStepperToggle("Select Report Type", reportIcon);

        VBox step1Box = new VBox(40,createLabel("Select Report Type"), reportTypeCombo);

        step1Box.setAlignment(Pos.CENTER);
        selectTypeStep1.setContent(step1Box);
        selectTypeStep1.getValidator().addDependencies(reportTypeCombo.getValidator());

        MFXStepperToggle selectParamStep = new MFXStepperToggle("Select Parameters", new MFXFontIcon("mfx-user", 16, Color.web("#49a6d7")));
        VBox step2Box = new VBox(40,createLabel("Select Parameters") ,yearCombo, monthCombo);
        step2Box.setAlignment(Pos.CENTER);
        selectParamStep.setContent(step2Box);

        MFXStepperToggle step3 = new MFXStepperToggle("Step 3", new MFXFontIcon("mfx-variant7-mark", 16, Color.web("#85CB33")));
        Node step3Grid = createGrid();
        step3.setContent(step3Grid);

        stepper.setOnNext(mfxStepperEvent -> {
            if (reportTypeCombo.getSelectionModel().getSelectedItem() == "Yearly Report") {
                System.out.println("Yearly OK N");
                monthCombo.setVisible(false);
                selectParamStep.getValidator().addDependencies(yearCombo.getValidator());
                selectParamStep.getValidator().removeDependencies(monthCombo.getValidator());
                monthLabel1.setVisible(false);
                monthLabel2.setVisible(false);
            } else {
                System.out.println("Yearly X N");
                monthCombo.setVisible(true);
                selectParamStep.getValidator().addDependencies(yearCombo.getValidator(), monthCombo.getValidator());
                monthLabel1.setVisible(true);
                monthLabel2.setVisible(true);
            }
        });

        stepper.setOnPrevious(mfxStepperEvent -> {
            if (reportTypeCombo.getSelectionModel().getSelectedItem() == "Yearly Report") {
                System.out.println("Yearly OK P");
                monthCombo.setVisible(false);
                selectParamStep.getValidator().removeDependencies(monthCombo.getValidator());
                selectParamStep.getValidator().addDependencies(yearCombo.getValidator());
                monthLabel1.setVisible(false);
                monthLabel2.setVisible(false);
            } else {
                System.out.println("Yearly X P");
                monthCombo.setVisible(true);
                selectParamStep.getValidator().addDependencies(yearCombo.getValidator(), monthCombo.getValidator());
                monthLabel1.setVisible(true);
                monthLabel2.setVisible(true);
            }
        });


        return List.of(selectTypeStep1, selectParamStep, step3);
    }

    private Node createGrid() {


        MFXLabel reportTypeLabel1 = createLabel("Report Type: ");
        MFXLabel reportTypeLabel2 = createLabel("");
        reportTypeLabel2.textProperty().bind(Bindings.createStringBinding(
                () -> reportTypeCombo.getSelectedValue() != null ? reportTypeCombo.getSelectedValue() : "",
                reportTypeCombo.selectedValueProperty()
        ));

        MFXLabel yearLabel1 = createLabel("Selected Year: ");
        MFXLabel yearLabel2 = createLabel("");
        yearLabel2.textProperty().bind(Bindings.createStringBinding(
                () -> yearCombo.getSelectedValue() != null ? yearCombo.getSelectedValue().toString() : "",
                yearCombo.selectedValueProperty()
        ));

        monthLabel1 = createLabel(monthLabel1, "Selected Month: ");
        monthLabel2 = createLabel(monthLabel2, "");
        monthLabel2.textProperty().bind(Bindings.createStringBinding(
                () -> monthCombo.getSelectedValue() != null ? monthCombo.getSelectedValue().getMonthName() : "",
                monthCombo.selectedValueProperty()
        ));


        HBox reportTypeHbox = new HBox(reportTypeLabel1, reportTypeLabel2);
        HBox yearHbox = new HBox(yearLabel1, yearLabel2);
        HBox monthHbox = new HBox(monthLabel1, monthLabel2);
        if (reportTypeCombo.getSelectionModel().getSelectedItem() == "Yearly Report") {
            monthHbox.setVisible(false);
        } else {
            monthHbox.setVisible(true);
        }

        MFXLabel completedLabel = new MFXLabel("Click Next to Generate Report");
        completedLabel.setFont(Font.font("Open Sans Bold", 24));
        completedLabel.setTextFill(Color.web("#85CB33"));
        HBox completedBox = new HBox(completedLabel);

        reportTypeHbox.setMaxWidth(Region.USE_PREF_SIZE);
        yearHbox.setMaxWidth(Region.USE_PREF_SIZE);
        monthHbox.setMaxWidth(Region.USE_PREF_SIZE);
        completedBox.setMaxWidth(Region.USE_PREF_SIZE);

        VBox box = new VBox(10, reportTypeHbox, yearHbox, monthHbox,completedBox);
        box.setAlignment(Pos.CENTER);
        StackPane.setAlignment(box, Pos.CENTER);
        return box;
    }

    private void resetForm() {
        reportTypeCombo.getSelectionModel().clearSelection();
        yearCombo.getSelectionModel().clearSelection();
        monthCombo.getSelectionModel().clearSelection();
    }

    private MFXLabel createLabel(String text) {
        return createLabel(new MFXLabel(), text);
    }

    private MFXLabel createLabel(MFXLabel label, String text) {
        label.setText(text);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setPrefWidth(200);
        label.setMinWidth(Region.USE_PREF_SIZE);
        label.setMaxWidth(Region.USE_PREF_SIZE);
        return label;
    }

    public void generateMonthly(int year, int month) {
        JasperPrint jp;
        Map params = new HashMap();

        params.put("year", year);
        params.put("month", month);
        try {
            Connection conn = DatabaseHandler.getInstance().getConn();
            InputStream path = getClass().getResourceAsStream("/fct/cs/jasper/monthly.jasper");

            jp = JasperFillManager.fillReport(path, params, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Monthly Report");
            viewer.setVisible(true);

        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void generateYearly(int year) {
        JasperPrint jp;
        Map params = new HashMap();

        params.put("year", year);
        try {
            Connection conn = DatabaseHandler.getInstance().getConn();
            InputStream path = getClass().getResourceAsStream("/fct/cs/jasper/yearly.jasper");
            jp = JasperFillManager.fillReport(path, params, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Yearly Report");
            viewer.setVisible(true);

        } catch (JRException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private class SimpleMonth {
        String monthName;
        int monthNum;

        public SimpleMonth(String monthName, int monthNum) {
            this.monthName = monthName;
            this.monthNum = monthNum;
        }

        public String getMonthName() {
            return monthName;
        }

        public void setMonthName(String monthName) {
            this.monthName = monthName;
        }

        public int getMonthNum() {
            return monthNum;
        }

        public void setMonthNum(int monthNum) {
            this.monthNum = monthNum;
        }

        @Override
        public String toString() {
            return monthName;
        }
    }
}
