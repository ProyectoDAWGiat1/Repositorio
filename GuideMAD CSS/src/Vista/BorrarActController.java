/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DatosBDA.ActividadDAO;
import DatosBDA.ConexionGeneral;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cosmin
 */
public class BorrarActController implements Initializable {

    private ConexionGeneral cg = new ConexionGeneral();
    @FXML
    private Label textIDBorrar;
    @FXML
    private TextField idActBorrar;
    @FXML
    private Label textTitulo;
    @FXML
    private Button borrarAct;

    private ActividadDAO gestionAct = new ActividadDAO();

    /**
     * Initializes the controller class.
     */
    public void antesDeInitialize() throws SQLException {
        cg.conectar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void borrarActividad(ActionEvent event) {
        int idActividad = Integer.parseInt(idActBorrar.getText());
        Alert alert = alertaDelete();
        try {
            int i = gestionAct.eliminarActividad(idActividad);
            alert.setHeaderText(alert.getHeaderText() + " " + idActividad);
            alert.setContentText(alert.getContentText() + " " + i);
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println("Error al borrar." + e.getErrorCode());
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar!");
            alert.setHeaderText("Ha surgido algún problema a la hora de eliminar la actividad.");
            alert.setContentText("Fijate bien si has puesto correctamente el ID.");
            alert.showAndWait();
        }
    }

    public Alert alertaDelete() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete en la tabla actividades");
        alert.setHeaderText("Se ha eliminado correctamente la actividad con la ID: ");
        alert.setContentText("No ha surgido ningun problema, num de registros afectados: ");

        return alert;
    }

}
