/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaproyecto;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author x8430056g
 */
public class ProyectoGIATController implements Initializable {

    @FXML
    private Pane menuInicio;
    @FXML
    private AnchorPane fondo;
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwdInput;
    @FXML
    private Label textoEmail;
    @FXML
    private Label textoPasswd;
    @FXML
    private Button registrate;
    @FXML
    private Label textInvitado;
    @FXML
    private Button iniciarSesion;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void pruebaCambioVentanas(javafx.event.ActionEvent event) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            //CARGAMOS OTRO FXML
            loader.setLocation(getClass().getResource("/scenes/MenuInicioIniciado.fxml"));
            Parent root = loader.load(); // el metodo initialize() se ejecuta

            Stage escenarioVentana = (Stage) iniciarSesion.getScene().getWindow();
            escenarioVentana.setTitle("Menú inicio");
            //CARGAMOS OTRA ESCENA(fxml) EN ESTA MISMA VENTANA
            escenarioVentana.setScene(new Scene(root));
        } catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("ERROR " + ex.getMessage());
            alerta.showAndWait();
        }

    }
    
    public String getNombre(){
        String nombre;

        nombre = textoEmail.getText();
        
        return nombre;
    }

}
