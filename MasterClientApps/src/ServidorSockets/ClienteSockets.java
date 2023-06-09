package ServidorSockets;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import modelo.Usuario;

/**
 * 
 *Representa un cliente de sockets que se conecta a un servidor para obtener una lista de administradores.
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class ClienteSockets {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * Obtiene la lista de administradores conectándose al servidor.
     *
     * @return La lista de usuarios administradores obtenida del servidor.
     */
    public List<Usuario> obtenerAdministradores() {
        List<Usuario> usuarios = null;
        try {
            // Conectar al servidor
            socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            // Enviar solicitud al servidor
            out.writeObject("obtenerAdministradores");
            out.flush();

            // Recibir la respuesta del servidor
            usuarios = (List<Usuario>) in.readObject();

            // Cerrar conexiones
            in.close();
            out.close();
            socket.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al conectar al servidor: " + ex.getMessage());
        }
        return usuarios;
    }
}