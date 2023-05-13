package ServidorSockets;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class SocketManager {
    private static Socket socket;
    
    public static void crearSocket(String host, int port) {
        try {
            socket = new Socket(host, port);
            System.out.println("Conectado al servidor");
        } catch (IOException ex) {
            System.out.println("Error al conectar al servidor");
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void closeSocket() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
