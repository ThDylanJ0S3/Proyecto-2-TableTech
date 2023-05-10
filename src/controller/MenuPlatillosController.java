package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.AlertW;
import modelo.Platillos;
import modelo.Lista;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class MenuPlatillosController implements Initializable {

    @FXML
    private TextField txtNombrePlatillo;
    @FXML
    private TextField txtCantidadCalorias;
    @FXML
    private TextField txtTiempoPreparacion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnAgregarPlatillo;
    @FXML
    private TableView<Platillos> tablaPlatillos;
    @FXML
    private TableColumn<Platillos, String> columNombre;
    @FXML
    private TableColumn<Platillos, Integer> columCalorias;
    @FXML
    private TableColumn<Platillos, Integer> columTiempo;
    @FXML
    private TableColumn<Platillos, Integer> columPrecio;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    
    Lista listaPlatillos = new Lista();
    private int posicion;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarPlatillo(ActionEvent event) {
        
        String nombrePlatillo = txtNombrePlatillo.getText();
        int cantidadCalorias = Integer.parseInt(txtCantidadCalorias.getText());
        int tiempoPreparacion = Integer.parseInt(txtTiempoPreparacion.getText());
        int precio = Integer.parseInt(txtPrecio.getText());
        
        if(txtNombrePlatillo==null || txtCantidadCalorias==null || txtTiempoPreparacion==null || txtPrecio==null){
            AlertW.display("Error", "Es necesario llenar todos los espacios");
        }
        else{
            Platillos platillo = new Platillos();
            platillo.setNombrePlatillo(nombrePlatillo);
            platillo.setCantCalorias(cantidadCalorias);
            platillo.setTiempoPreparacion(tiempoPreparacion);
            platillo.setPrecio(precio);
            listaPlatillos.agregarFinal(platillo);
            System.out.println(platillo.toString());
            tablaPlatillos.getItems().add(platillo);
        }
    }

    @FXML
    private void modificarPlatillo(ActionEvent event) {
        
    }

    @FXML
    private void eliminarPlatillo(ActionEvent event) {
    }
    
}
