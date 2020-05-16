/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DatosBDA.ActividadDAO;
import DatosBDA.ConexionGeneral;
import DatosBDA.SubtipoDAO;
import Modelo.Actividad;
import Modelo.Subtipo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cosmin
 */
public class CrearActController implements Initializable {

    private ConexionGeneral cg = new ConexionGeneral();
    @FXML
    private Label textTitulo;
    @FXML
    private Label textNombre;
    @FXML
    private TextField nombreAct;
    @FXML
    private TextArea descripAct;
    @FXML
    private Label textDescripcion;
    @FXML
    private Label textURL;
    @FXML
    private Label textIDSubtipo;
    @FXML
    private Button crearActBD;
    @FXML
    private TextField urlAct;
    @FXML
    private TextField idSubAct;
    @FXML
    private Label textImagen;
    @FXML
    private ListView<Subtipo> listaSubtipos;

    private ObservableList<Subtipo> listaSubtiposOb;

    private SubtipoDAO gestionSubtipo = new SubtipoDAO();
    private ActividadDAO gestionAct = new ActividadDAO();

    /**
     * Initializes the controller class.
     */
    public void cargarSubtipos() {
        try {
            cg.conectar();
            Set<Subtipo> listaSubtiposSet = gestionSubtipo.buscarSubtipos();

            listaSubtipos.getItems().addAll(listaSubtiposSet);

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    @FXML
    private void grabarActBD(ActionEvent event) {
        Actividad act = new Actividad();
        act.setIdActividad(0);
        act.setNombre(nombreAct.getText());
        act.setDescripcion(descripAct.getText());
        act.setUrl(urlAct.getText());
        act.setIdSubtipo(Integer.parseInt(idSubAct.getText()));
        Alert alerta = alertaInsert();
        try {
            int i = gestionAct.insertarActividad(act);
            alerta.setHeaderText(alerta.getHeaderText() + i);
            alerta.showAndWait();
        } catch (SQLException e) {
            System.out.println("Error");
            if (e.getErrorCode() == 1062) {
                alerta.setAlertType(Alert.AlertType.WARNING);
                alerta.setHeaderText("Error de duplicidad");
                alerta.setContentText("Debes probar con otro ID de actividad distinto.");
                alerta.showAndWait();
            } else if (e.getErrorCode() == 1452) {
                alerta.setAlertType(Alert.AlertType.WARNING);
                alerta.setHeaderText("Error de clave foránea");
                alerta.setContentText("No existe esa ID de subtipo prueba con otra.");
                alerta.showAndWait();
            }
        }

    }

    public Alert alertaInsert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Insert en la tabla actividades");
        alert.setHeaderText("Se ha agregado correctamente el parque, num de registros: ");
        alert.setContentText("No ha surgido ningun problema a la hora de introducir datos.");

        return alert;
    }

}
