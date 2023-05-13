package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class PrincipalClientController implements Initializable {

    @FXML
    private Button btnHistorico;
    @FXML
    private TableColumn<?, ?> columPedidoActivo;
    @FXML
    private TableColumn<?, ?> columEstadoPedido;
    @FXML
    private Button btnRealizarPedido;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarHistorialPedidos(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/HistorialPedidos.fxml"));

            Parent root = loader.load();

            HistorialPedidosController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

    }

    @FXML
    private void mostrarMenuPlatillos(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/menuPedido.fxml"));

            Parent root = loader.load();

            MenuPedidoController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

    }
    void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/menuPrincipalClient.fxml"));

            Parent root = loader.load();

            MenuPrincipalControllerClient controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalControllerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
