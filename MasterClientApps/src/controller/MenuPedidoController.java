/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;
import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Platillos;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
public class MenuPedidoController implements Initializable {

    @FXML
    private Button btnRealizarPedido;

    @FXML
    private TableView<Platillos> tablaPlatillos;

    @FXML
    private TableColumn<Platillos, String> columnaNombre;

    @FXML
    private TableColumn<Platillos, Integer> columnaCalorias;

    @FXML
    private TableColumn<Platillos, Integer> columnaTiempo;

    @FXML
    private TableColumn<Platillos, Integer> columnaPrecio;

    private ObservableList<Platillos> listaPlatillos;
    private ObservableList<Pedido> listaPedidosRealizados;


    /**
     * Método llamado al inicializar el controlador.
     *
     * @param url el URL de inicialización
     * @param resourceBundle el ResourceBundle utilizado para la inicialización
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaPedidosRealizados = FXCollections.observableArrayList();

        // Configurar las columnas de la tabla para mostrar los atributos de los platillos
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombrePlatillo"));
        columnaCalorias.setCellValueFactory(new PropertyValueFactory<>("cantCalorias"));
        columnaTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoPreparacion"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Asignar la lista observable de pedidos realizados a la tabla de pedidos
        ChoiceBox<Pedido> tablaPedidos = new ChoiceBox<>();
        tablaPedidos.setItems(listaPedidosRealizados);

        // Crear una lista observable de platillos vacía
        listaPlatillos = FXCollections.observableArrayList();

        // Asignar la lista observable de platillos a la tabla de platillos
        tablaPlatillos.setItems(listaPlatillos);

        // Cargar los platillos desde el archivo JSON y mostrarlos
        List<Platillos> platillos = cargarPlatillosDesdeJson("platillos.json");
        listaPlatillos.addAll(platillos);
    }

    /**
     * Método llamado cuando se hace clic en el botón "Realizar Pedido".
     *
     * @param event el evento de acción asociado al clic del botón
     */
    @FXML
    private void realizarPedido(ActionEvent event) {
        // Obtener el platillo seleccionado
        Platillos platilloSeleccionado = tablaPlatillos.getSelectionModel().getSelectedItem();

        if (platilloSeleccionado != null) {
            // Crear un nuevo pedido con los datos del platillo seleccionado
            Pedido pedido = new Pedido(((Platillos) platilloSeleccionado).getNombrePlatillo(), "Pendiente");

            // Agregar el pedido a la lista de pedidos realizados
            listaPedidosRealizados.add(pedido);

            // Mostrar un mensaje de confirmación
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pedido Realizado");
            alert.setHeaderText(null);
            alert.setContentText("El pedido ha sido realizado con éxito.");
            alert.showAndWait();
        } else {
            // Mostrar un mensaje de error si no se selecciona ningún platillo
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar un platillo.");
            alert.showAndWait();
        }
    }







    /**
     * Método para cargar la lista de platillos desde un archivo JSON local.
     *
     * @param nombreArchivo el nombre del archivo JSON
     * @return la lista de platillos cargada desde el archivo JSON
     */
    private List<Platillos> cargarPlatillosDesdeJson(String nombreArchivo) {
        List<Platillos> platillos = new ArrayList<>();

        try {
            // Ruta completa del archivo JSON en el servidor
            String rutaArchivo = "./MasterClientApps/src/Json/" + nombreArchivo;
            Path filePath = Paths.get(rutaArchivo);

            if (Files.exists(filePath)) {
                String json = Files.readString(filePath);

                Gson gson = new Gson();
                Type tipo = new TypeToken<List<Platillos>>(){}.getType();
                platillos = gson.fromJson(json, tipo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return platillos;
    }

    private class Platillo {
    }
}
