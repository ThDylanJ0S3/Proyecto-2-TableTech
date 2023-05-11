package ServidorSockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import modelo.LectorUsuarios;
import modelo.Usuario;

public class Servidor implements Runnable{
    private ServerSocket servidorSocket;
    private Socket socket;
    
    private LectorUsuarios lectorUsuariosClientes=new LectorUsuarios();
    private LectorUsuarios lectorUsuariosUsuarios=new LectorUsuarios();

    public Servidor(int puerto) {
        try {
            servidorSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lectorUsuariosClientes.leerUsuarios("./MasterClientApps/src/usuarios/UsuariosClientes.xml", "clientes");
        lectorUsuariosUsuarios.leerUsuarios("./MasterClientApps/src/usuarios/UsuariosAdmis.xml", "usuario");
        
        System.out.println(lectorUsuariosClientes.getClientes());
        System.out.println(lectorUsuariosUsuarios.getClientes());
        
    }

    public void iniciarServidor() {
        while (true) {
            try {
                System.out.println("Esperando conexión...");
                System.out.println(socket+"esto es socket");
                socket = servidorSocket.accept();
                manejarConexion(socket);
                System.out.println("Conexión aceptada de " + socket.getInetAddress());
                // Aquí puedes manejar la conexión con el cliente en otro hilo o clase
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cerrarServidor() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (servidorSocket != null) {
                servidorSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        iniciarServidor();
    }
    
    public void manejarConexion(Socket socket) {
        try {
            // Crear los streams de entrada y salida de datos
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // Leer la información de usuario y contraseña enviada por el cliente
            String[] credenciales = (String[]) input.readObject();
            String usuario = credenciales[0];
            String contrasena = credenciales[1];
            String tipo = credenciales[2];
            
            System.out.println(usuario + "esto es el usuario q llega al servidor");
            System.out.println(contrasena + "esto es la contraseña q llega al servidor");

            // Validar las credenciales del usuario
            boolean usuarioValido = validarUsuario(usuario, contrasena,tipo);

            // Enviar la respuesta al cliente
            output.writeBoolean(usuarioValido);
            output.flush();

            // Cerrar los streams y el socket
            input.close();
            output.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean validarUsuario(String usuario, String contrasena, String tipo) {
        boolean usuarioValido = false;

        if (tipo.equals("usuario")) {
            for (Usuario u : lectorUsuariosUsuarios.getUsuarios()) {
                if (u.getNombre().equals(usuario) && u.getContrasena().equals(contrasena)) {
                    usuarioValido = true;
                    break;
                }
            }
        } else {
            for (Usuario u : lectorUsuariosClientes.getClientes()) {
                if (u.getNombre().equals(usuario) && u.getContrasena().equals(contrasena)) {
                    usuarioValido = true;
                    break;
                }
            }
        }

        return usuarioValido;

    }
}
