/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.LectorUsuarios;
import modelo.Usuario;
import ServidorSockets.ConexionServidor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class MenuPrincipalControllerClient implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContra;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnCancelar;
    
    
    private Socket socket;
    
    
    private ObjectOutputStream out;
    private ObjectInputStream in;
    @FXML
    private Button btnServidor;
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        }    

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        String usuario= txtUsuario.getText();
        String contrasena= txtContra.getText();
        String tipo="cliente";
        out.writeObject(new String[]{usuario, contrasena,tipo});
        out.flush();
        
        boolean usuarioValido=in.readBoolean();
        
        if (usuarioValido){
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PrincipalClient.fxml"));

            Parent root = loader.load();

            PrincipalClientController controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.closeWindows());

            Stage myStage = (Stage) this.btnIniciarSesion.getScene().getWindow();
            myStage.close();
            } else {
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nombre de usuario o contraseña incorrectos");
            alert.setContentText("Nombre de usuario o contraseña incorrectos");
            alert.showAndWait();
        }
    }

    @FXML
    private void cerrarApp(ActionEvent event) {
        Button botonPresionado = (Button) event.getSource();
        Stage ventana = (Stage) botonPresionado.getScene().getWindow();
        ventana.close();
    }

    @FXML
    public void conectarServidor(ActionEvent event) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("localhost", 8080);
                    System.out.println("Conectado al servidor");
                    System.out.println("antes de in y out");
                    out = new ObjectOutputStream(socket.getOutputStream());
                    out.flush();
                    in = new ObjectInputStream(socket.getInputStream());
                    System.out.println("despues de in y out");
                    System.out.println(in + "esto es in");
                    System.out.println(out + "esto es out");

                } catch (IOException ex) {
                    System.out.println("Error al conectar al servidor");
                }
            }
        });
        t.start();
    }
}
