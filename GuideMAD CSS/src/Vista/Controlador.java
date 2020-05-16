package Vista;

import DatosBDA.ActividadDAO;
import DatosBDA.ConexionGeneral;
import Modelo.Actividad;
import Modelo.Detallepack;
import Modelo.Pack;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Controlador implements Initializable {

    private ActividadDAO gestionAct = new ActividadDAO();

    public Pack p1 = new Pack();

    public Map<Integer, Actividad> map1 = new HashMap<>();

    private ObservableList<Actividad> listaActividades;

    public ConexionGeneral cg = new ConexionGeneral();

    @FXML
    private Label estConexion;
    @FXML
    private TableView<Actividad> tablaActividades;
    @FXML
    private TableColumn<Actividad, Integer> colIdAct;
    @FXML
    private TableColumn<Actividad, String> colNombre;
    @FXML
    private TableColumn<Actividad, String> colDescrip;
    @FXML
    private TableColumn<Actividad, String> colURL;
    @FXML
    private TableColumn<Actividad, Blob> colImagen;
    @FXML
    private TableColumn<Actividad, Integer> colIdSubt;
    @FXML
    private Label textAct;
    @FXML
    private Button addToPack;
    @FXML
    private Button crearAct;
    @FXML
    private Button packToDB;
    @FXML
    private Button eliminarAct;
    @FXML
    private Button actualizarAct;
    @FXML
    private Label textPack;
    private ListView<Actividad> listaActsPack;
    @FXML
    private Label textImporte;
    @FXML
    private Label importePack;
    @FXML
    private Label textIDPack;

    public void setConexion() {
        try {
            cg.conectar();
            estConexion.setText("CONECTADO");
            estConexion.setTextFill(Paint.valueOf("green"));
        } catch (SQLException e) {
            estConexion.setText("DESCONECTADO");
            estConexion.setTextFill(Paint.valueOf("red"));
            System.out.println("a");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setConexion();
            Set<Actividad> actividades = gestionAct.buscarActividad();

            listaActividades = FXCollections.observableArrayList(actividades);
            tablaActividades.setItems(listaActividades);

            tablaActividades.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            colIdAct.setCellValueFactory(new PropertyValueFactory<>("idActividad"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            colURL.setCellValueFactory(new PropertyValueFactory<>("url"));
            colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
            colIdSubt.setCellValueFactory(new PropertyValueFactory<>("idSubtipo"));
            textIDPack.setText(Integer.toString(p1.getIdPack()));
            
        } catch (SQLException s) {
            estConexion.setText("DESCONECTADO");
            estConexion.setTextFill(Paint.valueOf("red"));
        }

    }

    @FXML
    private void abrirVentanaCrear(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("VentanaCrearAct.fxml"));
            root = loader.load(); // el meotodo initialize() se ejecuta

            //OBTENER EL CONTROLADOR DE LA VENTANA
            CrearActController controllerCrear = loader.getController();

            //PASAMOS UN DATO AL CONTROLADOR
            controllerCrear.cargarSubtipos();

            //PODEMOS EJECUTAR UN METODO DEL CONTROLADOR ANTES DE QUE SE VEA LA VENTANA
            Stage escenario = new Stage();
            escenario.setTitle("Crear Actividad");
            escenario.getIcons().add(new Image("/Images/logo.png"));
            escenario.initModality(Modality.APPLICATION_MODAL);  // NO PERMITE ACCESO A LA VENTANA PRINCIPAL
            escenario.setScene(new Scene(root));
            escenario.showAndWait();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void abrirVentanaBorrar(ActionEvent event) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("VentanaBorrarAct.fxml"));
            root = loader.load(); // el meotodo initialize() se ejecuta

            //OBTENER EL CONTROLADOR DE LA VENTANA
            BorrarActController controllerCrear = loader.getController();

            controllerCrear.antesDeInitialize();

            //PODEMOS EJECUTAR UN METODO DEL CONTROLADOR ANTES DE QUE SE VEA LA VENTANA
            Stage escenario = new Stage();
            escenario.setTitle("Borrar actividad");
            escenario.getIcons().add(new Image("/Images/logo.png"));
            escenario.initModality(Modality.APPLICATION_MODAL);  // NO PERMITE ACCESO A LA VENTANA PRINCIPAL
            escenario.setScene(new Scene(root));
            escenario.showAndWait();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void actualizarActs(ActionEvent event) {
        try {
            Set<Actividad> actividades = gestionAct.buscarActividad();

            listaActividades = FXCollections.observableArrayList(actividades);
            tablaActividades.setItems(listaActividades);
        } catch (SQLException e) {
            System.out.println("Error.");
        }

    }

    @FXML
    private void addToPack() {
        int i = tablaActividades.getSelectionModel().getSelectedIndices().size();


        if (i == 0) {
            System.out.println("Selecciona actividades!");
        } else {

            Parent root;

            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("VentanaPonerFechas.fxml"));
                root = loader.load(); // el meotodo initialize() se ejecuta

                //OBTENER EL CONTROLADOR DE LA VENTANA
                FechasPlazasController controllerCrear = loader.getController();

                controllerCrear.antesDeInitialize();
                controllerCrear.setListView(cargarActs());
                controllerCrear.setDP(p1);
                //PODEMOS EJECUTAR UN METODO DEL CONTROLADOR ANTES DE QUE SE VEA LA VENTANA
                Stage escenario = new Stage();
                escenario.setTitle("Gestion de fechas y plazas");
                escenario.getIcons().add(new Image("/Images/logo.png"));
                escenario.initModality(Modality.APPLICATION_MODAL);  // NO PERMITE ACCESO A LA VENTANA PRINCIPAL
                escenario.setScene(new Scene(root));
                escenario.showAndWait();

            } catch (Exception e) {
                System.out.println("Error"+e.getMessage());
            }
        }
    }

    public ObservableList<Actividad> cargarActs() {
        ObservableList<Actividad> actSelecionadas = tablaActividades.getSelectionModel().getSelectedItems();

        return actSelecionadas;
    }

    @FXML
    private void PackABD(ActionEvent event) {
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("VentanaNombrePack.fxml"));
            root = loader.load(); // el meotodo initialize() se ejecuta

            //OBTENER EL CONTROLADOR DE LA VENTANA
            PonerNombrePack controllerCrear = loader.getController();

            controllerCrear.antesDeInitialize();
            controllerCrear.setPack(p1);
            //PODEMOS EJECUTAR UN METODO DEL CONTROLADOR ANTES DE QUE SE VEA LA VENTANA
            Stage escenario = new Stage();
            escenario.setTitle("Enviar pack actual a BD");
            escenario.getIcons().add(new Image("/Images/logo.png"));
            escenario.initModality(Modality.APPLICATION_MODAL);  // NO PERMITE ACCESO A LA VENTANA PRINCIPAL
            escenario.setScene(new Scene(root));
            escenario.showAndWait();
            System.out.println(p1.getIdPack());
            System.out.println(p1.precioPack(p1.getIdPack()));
            
            importePack.setText(Double.toString(p1.precioPack(p1.getIdPack()))+"€");
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage()+e.getLocalizedMessage());
        }
        vaciarPack();

    }

    public void vaciarPack() {
        p1 = new Pack();
        textIDPack.setText(Integer.toString(p1.getIdPack()));
    }

}
