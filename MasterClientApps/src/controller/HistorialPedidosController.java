package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Platillos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class HistorialPedidosController implements Initializable {

    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private TableColumn<Pedido, String> columnaNombre;
    @FXML
    private TableColumn<Pedido, Integer> columnaCalorias;
    @FXML
    private TableColumn<Pedido, Integer> columnaTiempo;
    @FXML
    private TableColumn<Pedido, Double> columnaPrecio;

    private ObservableList<Pedido> listaPedidosRealizados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaPedidosRealizados = FXCollections.observableArrayList();

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombrePlatillo"));
        columnaCalorias.setCellValueFactory(new PropertyValueFactory<>("cantCalorias"));
        columnaTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoPreparacion"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaPedidos.setItems(listaPedidosRealizados);
    }

    public void setPedidos(ObservableList<Pedido> pedidos) {
        listaPedidosRealizados.setAll(pedidos);

    // Resto del código...
}
 }
