package controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Personal
 */
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
    /**
     * Initializes the controller class.
     */    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void agregarPlatillo(ActionEvent event) {

        // Se obtienen los valores de los campos de texto correspondientes al nombre del platillo, cantidad de calorías, tiempo de preparación y precio
        String nombrePlatillo = txtNombrePlatillo.getText();
        int cantidadCalorias = Integer.parseInt(txtCantidadCalorias.getText());
        int tiempoPreparacion = Integer.parseInt(txtTiempoPreparacion.getText());
        int precio = Integer.parseInt(txtPrecio.getText());

        // Si alguno de los campos de texto está vacío, se muestra una alerta de error
        if (txtNombrePlatillo == null || txtCantidadCalorias == null || txtTiempoPreparacion == null || txtPrecio == null) {
            AlertW.display("Error", "Es necesario llenar todos los espacios");
        } else {
            // Si todos los campos de texto tienen valores, se crea un objeto Platillos con esos valores y se agrega a la lista observable de platillos
            Platillos platillo = new Platillos();
            platillo.setNombrePlatillo(nombrePlatillo);
            platillo.setCantCalorias(cantidadCalorias);
            platillo.setTiempoPreparacion(tiempoPreparacion);
            platillo.setPrecio(precio);
            platos.add(platillo);

            // Se crea un objeto JSON con la información del platillo
            JSONObject platilloJson = new JSONObject();
            platilloJson.put("nombrePlatillo", nombrePlatillo);
            platilloJson.put("cantCalorias", cantidadCalorias);
            platilloJson.put("tiempoPreparacion", tiempoPreparacion);
            platilloJson.put("precio", precio);

            // Se guarda la información del platillo en un archivo JSON
            try (FileWriter file = new FileWriter("platillos.json")) {
                file.write(platilloJson.toString());
                file.flush();
                System.out.println("Platillo guardado en platillos.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Se envía la información del platillo al servidor
            try {
                URL url = new URL("./MasterClientApps/src/ServidorSockets/Servidor.java");//cambiar por la url del servidor
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = platilloJson.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Se actualiza la tabla con la nueva lista de platillos
            tablaPlatillos.setItems(platos);
            // Se imprime la información del platillo agregado en la consola
            System.out.println(platillo.toString());
        }
    }


    @FXML
    private void modificarPlatillo(ActionEvent event) {
        // Función que se implementará para permitir la modificación de un platillo existente en la lista de platillos
    }

    @FXML
    private void eliminarPlatillo(ActionEvent event) {
        // Función que se implementará para permitir la eliminación de un platillo existente en la lista de platillos
    }
}