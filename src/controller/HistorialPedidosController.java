package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class HistorialPedidosController implements Initializable {

    @FXML
    private TableColumn<?, ?> columPedido;
    @FXML
    private TableColumn<?, ?> columCalorias;
    @FXML
    private TableColumn<?, ?> columTiempo;
    @FXML
    private TableColumn<?, ?> columPrecio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
