/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DatosBDA.ConexionGeneral;
import DatosBDA.DetallepackDAO;
import Modelo.Actividad;
import Modelo.Detallepack;
import Modelo.ErrorFecha;
import Modelo.Pack;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cosmin
 */
public class FechasPlazasController implements Initializable {

    private ConexionGeneral cg = new ConexionGeneral();
    private DetallepackDAO gestionDP = new DetallepackDAO();
    private Detallepack dp = new Detallepack();
    private Pack p;

    @FXML
    private ListView<Actividad> listActsDP;
    @FXML
    private TextField numPlazasField;
    @FXML
    private Label textPlazas;
    @FXML
    private DatePicker fechaInicioAct;
    @FXML
    private Label textInicio;
    @FXML
    private Label textFinal;
    @FXML
    private DatePicker fechaFinalAct;
    @FXML
    private Label textTitle;
    @FXML
    private Button aplicarDetalles;
    @FXML
    private Label textPrecio;
    @FXML
    private TextField precioActDP;
    @FXML
    private Label textHoraInicio;
    @FXML
    private TextField horaInicio;
    @FXML
    private Label textHoraFin;
    @FXML
    private TextField horaFinal;

    /**
     * Initializes the controller class.
     */
    public void antesDeInitialize() throws SQLException {
        cg.conectar();
    }

    public void setDP(Pack p) {
        this.p = p;
        dp.setPack(p);
        dp.setIdPack();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void aplicarDetalles() {
        Actividad act;
        Alert alert = alertaAct();
        Detallepack siguiente;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        act = listActsDP.getSelectionModel().getSelectedItem();
        if (act == null) {
            alert.showAndWait();
        } else {
            int plazas = Integer.parseInt(numPlazasField.getText());
            double precio = Double.parseDouble(precioActDP.getText());

            try {
                LocalDateTime fechaInicio = LocalDateTime.of(fechaInicioAct.getValue(), LocalTime.parse(horaInicio.getText(), dtf));
                LocalDateTime fechaFinal = LocalDateTime.of(fechaFinalAct.getValue(), LocalTime.parse(horaFinal.getText(), dtf));
                dp.setFechas(fechaInicio, fechaFinal);
                dp.comprobarFechaFinal(fechaFinal);
                dp.comprobarFechaInicio(fechaInicio);
                dp.crearFilasDP(act, plazas, precio);
                p.insertarDP(dp);
                siguiente = dp;
                dp = new Detallepack();
                dp.setPack(p);
                dp.setIdPack();
                dp.setNumLinea(siguiente.getNumLinea());
                System.out.println(act);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Se han aplicado los detalles.");
                alert.setContentText("Sigue con la siguiente actividad.");
                alert.setTitle("Confirmados detalles.");
                alert.showAndWait();
                numPlazasField.setText("");
                fechaInicioAct.setValue(null);
                fechaFinalAct.setValue(null);
                horaInicio.setText("");
                horaFinal.setText("");
                precioActDP.setText("");
            } catch (ErrorFecha ef) {
                System.out.println(ef.getMessage());
            }
        }
//        p.introducirActividad(act);
    }

    public void setListView(ObservableList<Actividad> a) {
        listActsDP.getItems().addAll(a);
    }

    public Alert alertaAct() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Selecciona una actividad");
        alert.setHeaderText("No puedes ponerle detalles a algo vacío");
        alert.setContentText("Comprueba tu seleccion");

        return alert;
    }
}
