/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

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
    private TableColumn<?, ?> columNombre;
    @FXML
    private TableColumn<?, ?> columCalorias;
    @FXML
    private TableColumn<?, ?> columTiempo;
    @FXML
    private TableColumn<?, ?> columPrecio;
    @FXML
    private Button btnModificar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarPlatillo(ActionEvent event) {
    }

    @FXML
    private void modificarPlatillo(ActionEvent event) {
    }
    
}
