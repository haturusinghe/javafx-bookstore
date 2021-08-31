package fct.cs.reporting;

import fct.cs.dbUtil.DatabaseConnector;
import javafx.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportsPageController {

    public void showReport(ActionEvent actionEvent){
        JasperPrint jp ;
        Map param = new HashMap();

        try{
            DatabaseConnector connectDB = new DatabaseConnector();
            jp = JasperFillManager.fillReport("reports/inventory.jasper" ,
                    param,
                    connectDB.getConn());

            JasperViewer viewer = new JasperViewer(jp,false);
            viewer.setTitle("Report");
            viewer.setVisible(true);

        }catch(JRException e){
            System.out.println(e.getMessage());
        }


    }
}
