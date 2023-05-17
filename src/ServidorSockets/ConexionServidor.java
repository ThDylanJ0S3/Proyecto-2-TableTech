package ServidorSockets;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ConexionServidor {
    private static ConexionServidor instancia;
    private Socket socket;
    
    private ConexionServidor() {
        try {
            socket = new Socket("localhost", 8080);
        } catch (IOException ex) {
            // manejar excepciones aquí
        }
    }
    
    public static ConexionServidor obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConexionServidor();
        }
        return instancia;
    }
    
    public Socket obtenerSocket() {
        return socket;
    }
}