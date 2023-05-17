package GUI;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Servidor extends Application {
    private ServerSocket servidor;

    public Servidor() {
        try {
            servidor = new ServerSocket(8080);
        } catch (IOException ex) {
            // manejar excepciones aquí
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void run() {
        while (true) {
            try {
                Socket socket = servidor.accept();
                Thread hilo = new Thread(new ManejadorConexion(socket));
                hilo.start();
            } catch (IOException ex) {
                // manejar excepciones aquí
            }
        }
    }

    private class ManejadorConexion implements Runnable {
        private Socket socket;

        public ManejadorConexion(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            // manejar la conexión aquí
        }
    }
}
