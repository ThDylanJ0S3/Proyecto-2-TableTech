package controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class PrincipalClientController implements Initializable {

    @FXML
    private Button btnHistorico;
    @FXML
    private TableColumn<?, ?> columnaPedidoActivo;
    @FXML
    private TableColumn<?, ?> columEstadoPedido;
    @FXML
    private Button btnRealizarPedido;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar la tabla y configurar las columnas
        // Aquí debes vincular las columnas con los datos de tus pedidos, y configurar su contenido
    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Historial de Pedidos".
     * Abre la ventana de historial de pedidos.
     *
     * @param event el evento de clic del botón
     * @throws IOException si ocurre un error al cargar la vista de la ventana
     */
    @FXML
    private void mostrarHistorialPedidos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/HistorialPedidos.fxml"));
        AnchorPane root = loader.load();
        HistorialPedidosController controlador = loader.getController();

        // Aquí debes obtener los datos de los pedidos desde alguna fuente, como una base de datos o una lista en memoria
        List<Pedido> listaPedidos = obtenerPedidos();

        // Luego, pasas los datos de los pedidos al controlador del historial
        controlador.setPedidos((ObservableList<Pedido>) listaPedidos);

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private List<Pedido> obtenerPedidos() {
        return null;
    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Realizar Pedido".
     * Abre la ventana de menú de pedido.
     *
     * @param event el evento de clic del botón
     * @throws IOException si ocurre un error al cargar la vista de la ventana
     */
    @FXML
    private void mostrarMenuPlatillos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/menuPedido.fxml"));
        AnchorPane root = loader.load();
        MenuPedidoController controlador = loader.getController();
        // Aquí debes configurar el controlador del menú de platillos

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Cierra la ventana actual y abre el menú principal del cliente.
     */
    void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/menuPrincipalClient.fxml"));
            AnchorPane root = loader.load();
            MenuPrincipalControllerClient controlador = loader.getController();
            // Aquí debes configurar el controlador del menú principal

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipalControllerMaster.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
