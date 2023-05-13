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
public class PrincipalMasterController implements Initializable {

    @FXML
    private Button btnListaAdmi;
    @FXML
    private Button btnPlatillos;
    @FXML
    private TableColumn<?, ?> ColumPedido;
    @FXML
    private TableColumn<?, ?> ColumTiempo;
    @FXML
    private TableColumn<?, ?> ColumEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void mostrarMenuAdmi(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListaAdministradores.fxml"));

        Parent root = loader.load();

        ListaAdministradoresController controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void mostrarMenuPlatillos(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MenuPlatillos.fxml"));

        Parent root = loader.load();

        MenuPlatillosController controlador = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/menuPrincipalMaster.fxml"));

            Parent root = loader.load();

            MenuPrincipalControllerMaster controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalControllerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
