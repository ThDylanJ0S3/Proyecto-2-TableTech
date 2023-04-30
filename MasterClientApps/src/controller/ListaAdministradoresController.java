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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class ListaAdministradoresController implements Initializable {

    @FXML
    private Button btnAgregarAdmin;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtContra;
    @FXML
    private Button btnEliminarAdmin;
    @FXML
    private Button btnModificarAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarAdmin(ActionEvent event) {
    }

    @FXML
    private void eliminarAdmin(ActionEvent event) {
    }

    @FXML
    private void modificarAdmin(ActionEvent event) {
    }
    
}
