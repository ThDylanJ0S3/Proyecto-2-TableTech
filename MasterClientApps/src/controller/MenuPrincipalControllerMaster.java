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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.LectorUsuarios;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class MenuPrincipalControllerMaster implements Initializable {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContra;
    @FXML
    private Button btnIniciarSesion;
    @FXML
    private Button btnCancelar;

    private LectorUsuarios lectorUsuariosUsuarios=new LectorUsuarios();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lectorUsuariosUsuarios.leerUsuarios("C:\\Users\\tecno\\Desktop"
              + "\\Proyecto_Datos1\\Proyecto1\\MasterClientApps\\src\\usuarios\\UsuariosAdmis.xml","usuario");
        System.out.println(lectorUsuariosUsuarios.getUsuarios());
}

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String usuario= txtUsuario.getText();
        String contrasena= txtContra.getText();
        boolean usuarioValido=false;
        
        for (Usuario u : lectorUsuariosUsuarios.getUsuarios()) {
            if (u.getNombre().equals(usuario) && u.getContrasena().equals(contrasena)){
                usuarioValido=true;
                break;
            }
        }
        
        if (usuarioValido){
            javafx.scene.control.Alert alert =new javafx.scene.control.Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inicio de sesion correcto");
            alert.setContentText("Inicio de sesion correcto");
            alert.showAndWait();
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
    
}
