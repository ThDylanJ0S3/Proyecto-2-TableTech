/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label admin1;
    @FXML
    private Label admi2;
    @FXML
    private Label admi3;
    @FXML
    private Label admin4;
    @FXML
    private Label admin5;
    
    private Socket Socket;
    
    private ObjectInputStream in;
    
    private ObjectOutputStream out;
    @FXML
    private TextField txtNameModificar;
    @FXML
    private TextField txtContraModificar;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        
                try {
                    Socket = new Socket("localhost", 8080);
                    System.out.println("Conectado al servidor");
                    out = new ObjectOutputStream(Socket.getOutputStream());
                    out.flush();
                    in = new ObjectInputStream(Socket.getInputStream());
                } catch (IOException ex) {
                    System.out.println("Error al conectar al servidor");
                }
    }    

    @FXML
    private void agregarAdmin(ActionEvent event) throws IOException {
        String nombre=txtUsername.getText();
        String contrasena=txtContra.getText();
        String accion = "agregar";
        out.writeObject(new String[]{nombre, contrasena,accion});
        out.flush();

        // Recibir la respuesta del servidor
        boolean resultado = in.readBoolean();
        
        in.close();
        out.close();
        Socket.close();
    }

    @FXML
    private void eliminarAdmin(ActionEvent event) {
    }

    @FXML
    private void modificarAdmin(ActionEvent event) {
    }
    
}
