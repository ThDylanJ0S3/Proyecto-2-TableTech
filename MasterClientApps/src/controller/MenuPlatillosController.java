package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.AlertW;
import modelo.Platillos;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuPlatillosController implements Initializable {

    // Declaración de objetos y variables de la interfaz gráfica.
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

    // Declaración de una lista observable para almacenar objetos Platillos.
    ObservableList<Platillos> platos;

    // Declaración de una variable para almacenar la posición del objeto Platillos seleccionado en la tabla.
    private int posicion;

    /**
     * Initializes the controller class.
     * Método que se llama después de que se hayan inicializado los objetos de la interfaz gráfica.
     * Este método inicializa la lista observable de Platillos y establece los valores de las propiedades de las columnas.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Se inicializa una lista observable de platillos vacía
        platos = FXCollections.observableArrayList();
        // Se configura cada columna de la tabla con la propiedad correspondiente del objeto Platillos
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombrePlatillo"));
        columCalorias.setCellValueFactory(new PropertyValueFactory<>("cantCalorias"));
        columTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempoPreparacion"));
        columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Se asigna la lista observable de platillos a la tabla
        tablaPlatillos.setItems(platos);
    }

    @FXML
    private void agregarPlatillo() {
        // Se obtienen los valores de los campos de texto correspondientes al nombre del platillo, cantidad de calorías, tiempo de preparación y precio
        String nombrePlatillo = txtNombrePlatillo.getText();
        int cantidadCalorias, tiempoPreparacion, precio;

        try {
            cantidadCalorias = Integer.parseInt(txtCantidadCalorias.getText());
            tiempoPreparacion = Integer.parseInt(txtTiempoPreparacion.getText());
            precio = Integer.parseInt(txtPrecio.getText());
        } catch (NumberFormatException e) {
            AlertW.display("Error", "Los campos de calorías, tiempo de preparación y precio deben ser números");
            return;
        }

        // Si alguno de los campos de texto está vacío, se muestra una alerta de error
        if (nombrePlatillo.isEmpty() || txtCantidadCalorias.getText().isEmpty() || txtTiempoPreparacion.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            AlertW.display("Error", "Es necesario llenar todos los espacios");
            return;
        }

        // Si todos los campos de texto tienen valores, se crea un objeto Platillos con esos valores y se agrega
    }

    @FXML
    private void modificarPlatillo(ActionEvent event) {
        // Se obtiene el objeto Platillos seleccionado en la tabla
        Platillos platillo = tablaPlatillos.getSelectionModel().getSelectedItem();
        if (platillo == null) {
            // Si no se ha seleccionado ningún objeto Platillos, se muestra una alerta de error
            AlertW.display("Error", "Por favor seleccione un platillo de la tabla");
        } else {
            // Si se ha seleccionado un objeto Platillos, se muestra una ventana para modificar los valores de sus propiedades
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modificar Platillo");
            dialog.setHeaderText("Modificar Platillo");
            dialog.setContentText("Ingrese los nuevos valores para el platillo:");

            // Se establecen los valores actuales del objeto Platillos en los campos de texto de la ventana de diálogo
            dialog.getEditor().setText(platillo.getNombrePlatillo() + "," + platillo.getCantCalorias() + "," + platillo.getTiempoPreparacion() + "," + platillo.getPrecio());

            // Se muestra la ventana de diálogo y se obtiene la respuesta del usuario
            Optional<String> resultado = dialog.showAndWait();

            if (resultado.isPresent()) {
                // Si el usuario ingresó una respuesta, se separa la cadena en sus componentes y se modifica el objeto Platillos
                String[] valores = resultado.get().split(",");
                platillo.setNombrePlatillo(valores[0]);
                platillo.setCantCalorias(Integer.parseInt(valores[1]));
                platillo.setTiempoPreparacion(Integer.parseInt(valores[2]));
                platillo.setPrecio(Integer.parseInt(valores[3]));
                // Se actualiza la tabla para reflejar los cambios
                tablaPlatillos.refresh();
            }
        }
    }

    @FXML
    private void eliminarPlatillo(ActionEvent event) {
        // Se obtiene el objeto Platillos seleccionado en la tabla
        Platillos platillo = tablaPlatillos.getSelectionModel().getSelectedItem();
        if (platillo == null) {
            // Si no se ha seleccionado ningún objeto Platillos, se muestra una alerta de error
            AlertW.display("Error", "Por favor seleccione un platillo de la tabla");
        } else {
            // Si se ha seleccionado un objeto Platillos, se muestra una ventana de confirmación antes de eliminarlo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar Platillo");
            alert.setHeaderText("Eliminar Platillo");
            alert.setContentText("¿Está seguro de que desea eliminar el platillo seleccionado?");

            // Se muestra la ventana de confirmación y se obtiene la respuesta del usuario
            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == ButtonType.OK) {
                // Si el usuario confirmó la eliminación, se elimina el objeto Platillos de la lista observable de platillos
                platos.remove(platillo);
            }
        }
    }
}
