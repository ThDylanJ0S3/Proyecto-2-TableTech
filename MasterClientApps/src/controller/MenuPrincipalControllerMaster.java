package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.LectorUsuarios;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
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

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    @FXML
    private Button btnServidor;

    /**
     * Constructor por defecto de la clase MenuPrincipalControllerMaster.
     */
    public MenuPrincipalControllerMaster() {
        }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Iniciar Sesión".
     * Verifica las credenciales del usuario y abre la ventana principal de Administrador si son válidas.
     *
     * @param event el evento de clic del botón
     * @throws IOException si ocurre un error al cargar la vista de la ventana
     */
    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = txtUsuario.getText();
        String contrasena = txtContra.getText();
        String tipo = "usuario";
        out.writeObject(new String[]{usuario, contrasena,tipo});
        out.flush();

        // Recibir la respuesta del servidor
        boolean usuarioValido = in.readBoolean();

        if (usuarioValido) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PrincipalMaster.fxml"));
            Parent root = loader.load();
            PrincipalMasterController controlador = loader.getController();
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
    
    /**
     * Método que se ejecuta cuando se hace clic en el botón "Cancelar".
     * Cierra la aplicación.
     *
     * @param event el evento de clic del botón
     */
    @FXML
    private void cerrarApp(ActionEvent event) {
        Button botonPresionado = (Button) event.getSource();
        Stage ventana = (Stage) botonPresionado.getScene().getWindow();
        ventana.close();
    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón "Conectar al Servidor".
     * Establece una conexión con el servidor.
     *
     * @param event el evento de clic del botón
     */
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
                    System.out.println(in+"esto es in");
                    System.out.println(out+"esto es out");

                } catch (IOException ex) {
                    System.out.println("Error al conectar al servidor");
                }
            }
        });
        t.start();
    }
    
}
