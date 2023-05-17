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
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
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

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Lista de Administradores".
     * Abre la ventana de lista de administradores.
     *
     * @param event el evento de clic del botón
     * @throws IOException si ocurre un error al cargar la vista de la ventana
     */
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

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Menú de Platillos".
     * Abre la ventana de menú de platillos.
     *
     * @param event el evento de clic del botón
     * @throws IOException si ocurre un error al cargar la vista de la ventana
     */
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
    
    /**
     * Cierra la ventana actual y abre el menú principal del administrador.
     */
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
