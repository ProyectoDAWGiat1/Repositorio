/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DatosBDA.ConexionGeneral;
import DatosBDA.DetallepackDAO;
import DatosBDA.PackDAO;
import Modelo.Detallepack;
import Modelo.Pack;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cosmin
 */
public class PonerNombrePack implements Initializable {

    private ConexionGeneral cg = new ConexionGeneral();
//    private Controlador mainController = new Controlador();
    @FXML
    private Label textNombre;
    @FXML
    private TextField nombrePack;
    @FXML
    private Label textTitulo;
    @FXML
    private Button registrarPack;

    private PackDAO gestionPack = new PackDAO();
    private DetallepackDAO gestionDP = new DetallepackDAO();

    private Pack pack;
    @FXML
    private Label textDescrip;
    @FXML
    private TextArea descripPack;

    //Necesario para poder acceder a los datos de la anterior ventana.
    public void antesDeInitialize() throws SQLException {
        cg.conectar();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setPack(Pack p1) {
        this.pack = p1;
//        pack.setIdPack(p1.getIdPack());
//        pack.setFecha(p1.getFecha());

    }

    public void ponerNombreYDescrip() {
        pack.setNombre(nombrePack.getText());
        pack.setDescripcion(descripPack.getText());
    }

    @FXML
    private void enviarABD(ActionEvent event) {
        Alert alerta = alertaInsert();
        int b = 0;
        try {
            ponerNombreYDescrip();
            int i = gestionPack.insertarPack(pack);
            for (Detallepack dp : pack.getDPs()) {
                 b += gestionDP.insertarDP(dp);
            }
            alerta.setHeaderText(alerta.getHeaderText() + i+"\nEn detalle packs: "+b);
            alerta.showAndWait();
        } catch (SQLException e) {
            System.out.println("Error"+e.getErrorCode());
            if (e.getErrorCode() == 1062) {
                alerta.setAlertType(Alert.AlertType.WARNING);
                alerta.setHeaderText("Error de duplicidad");
                alerta.setContentText("Debes probar con otro ID de pack distinto.");
                alerta.showAndWait();
            } else if (e.getErrorCode() == 1452) {
                alerta.setAlertType(Alert.AlertType.WARNING);
                alerta.setHeaderText("Error de clave foránea");
                alerta.setContentText("Problemas de FK.");
                alerta.showAndWait();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Alert alertaInsert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Insert en la tabla packs");
        alert.setHeaderText("Se ha agregado correctamente el pack, num de registros: ");
        alert.setContentText("No ha surgido ningun problema a la hora de introducir datos.");

        return alert;
    }

}
