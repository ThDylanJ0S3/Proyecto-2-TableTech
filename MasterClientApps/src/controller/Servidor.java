package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket servidor;

    public Servidor() {
        try {
            servidor = new ServerSocket(8080);
        } catch (IOException ex) {
            // manejar excepciones aquí
        }
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
            try {
                // obtener los streams de entrada y salida del socket
                InputStream entrada = socket.getInputStream();
                OutputStream salida = socket.getOutputStream();

                // leer los datos del cliente
                byte[] buffer = new byte[1024];
                int longitud = entrada.read(buffer);
                String mensaje = new String(buffer, 0, longitud);

                // enviar una respuesta al cliente
                String respuesta = "Hola cliente!";
                salida.write(respuesta.getBytes());
            } catch (IOException ex) {
                // manejar excepciones aquí
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    // manejar excepciones aquí
                }
            }
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.run();
    }
}
