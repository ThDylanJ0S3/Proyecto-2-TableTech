package ServidorSockets;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.NodoBinarioBusqueda;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.LectorUsuarios;
import modelo.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class Servidor implements Runnable{
    
    private ArbolBinarioBusqueda arbolUsuarios;
    private ArbolBinarioBusqueda arbolClientes;
    private ServerSocket servidorSocket;
    private Socket socket;

    private LectorUsuarios lectorUsuariosClientes = new LectorUsuarios();
    private LectorUsuarios lectorUsuariosUsuarios = new LectorUsuarios();

    public Servidor(int puerto) {
        try {
            servidorSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lectorUsuariosClientes.leerUsuarios("C:\\Users\\tecno\\Desktop\\Proyecto_Datos1\\Proyecto2\\MasterClientApps\\src\\usuarios\\UsuariosClientes.xml", "clientes");
        lectorUsuariosUsuarios.leerUsuarios("C:\\Users\\tecno\\Desktop\\Proyecto_Datos1\\Proyecto2\\MasterClientApps\\src\\usuarios\\UsuariosAdmis.xml", "usuario");
        arbolUsuarios = new ArbolBinarioBusqueda();
        arbolClientes = new ArbolBinarioBusqueda();

        System.out.println(lectorUsuariosClientes.getClientes() + "lector clientes");
        System.out.println(lectorUsuariosUsuarios.getUsuarios() + "lector admis");
        cargarAdmisEnArbol();
        cargarClientesEnArbol();
    }

    public void iniciarServidor() {
        while (true) {
            try {
                System.out.println("Esperando conexión...");
                System.out.println(socket+"esto es socket");
                socket = servidorSocket.accept();
                manejarLogin(socket);
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
    
    public void manejarLogin(Socket socket) {
        try {
            // Crear los streams de entrada y salida de datos
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // Leer la información de usuario y contraseña enviada por el cliente
            String[] credenciales = (String[]) input.readObject();
            String usuario = credenciales[0];
            String contrasena = credenciales[1];
            String tipo = credenciales[2];
            
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
    
    public void manejaArchivosXml(Socket socket){
        try {
            
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // Leer la información de usuario y contraseña enviada por el cliente
            String[] credenciales = (String[]) input.readObject();
            String usuario = credenciales[0];
            String contrasena = credenciales[1];
            String accion = credenciales[2];
            
            if(accion.equals("agregar")){
                agregarUsuario(usuario, contrasena, accion);
            }else if (accion.equals("modificar")){
            
            }else {
            
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    
    }

    public boolean validarUsuario(String usuario, String contrasena, String tipo) {
        boolean usuarioValido = false;

        if (tipo.equals("usuario")) {
            System.out.println("se busca en el arbol binario la validacion admis");
            return arbolUsuarios.buscar(usuario, contrasena);
        } else {
            System.out.println("se busca en el arbol binario la validacion clientes");
            return arbolClientes.buscar(usuario, contrasena);
        
        }

    }

    public boolean agregarUsuario(String nombre, String contrasena, String tipo) {
        
        if (tipo.equals("agregar")) {
            try {
                // Crear el objeto DocumentBuilderFactory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                // Crear el objeto DocumentBuilder
                DocumentBuilder builder = factory.newDocumentBuilder();

                // Parsear el archivo XML existente para obtener el objeto Document
                Document documento = builder.parse("C:\\Users\\tecno\\Desktop\\Proyecto_Datos1\\Proyecto2\\MasterClientApps\\src\\usuarios\\UsuariosAdmis.xml");

                // Crear el nuevo elemento 'usuario'
                Element elementoUsuario = documento.createElement("usuario");

                // Crear el elemento 'nombre' y asignar el valor
                Element elementoNombre = documento.createElement("nombre");
                elementoNombre.appendChild(documento.createTextNode(nombre));

                // Crear el elemento 'contrasena' y asignar el valor
                Element elementoContrasena = documento.createElement("contrasena");
                elementoContrasena.appendChild(documento.createTextNode(contrasena));

                // Agregar los elementos 'nombre' y 'contrasena' al elemento 'usuario'
                elementoUsuario.appendChild(elementoNombre);
                elementoUsuario.appendChild(elementoContrasena);

                // Agregar el nuevo usuario al documento
                documento.getDocumentElement().appendChild(elementoUsuario);

                // Preparar la transformación para escribir el archivo XML modificado
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                // Crear la fuente DOM para la transformación
                DOMSource source = new DOMSource(documento);

                // Especificar la ubicación y nombre del archivo XML de salida
                StreamResult result = new StreamResult("C:\\Users\\tecno\\Desktop\\Proyecto_Datos1\\Proyecto2\\MasterClientApps\\src\\usuarios\\UsuariosAdmis.xml");

                // Realizar la transformación y escribir el archivo XML modificado
                transformer.transform(source, result);

                System.out.println("Usuario agregado al archivo XML correctamente.");
            } catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
                e.printStackTrace();
            }
        } else if (tipo.equals("modificar")) {
            try {
                // Crear el objeto DocumentBuilderFactory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                // Crear el objeto DocumentBuilder
                DocumentBuilder builder = factory.newDocumentBuilder();

                // Parsear el archivo XML existente para obtener el objeto Document
                Document documento = builder.parse("C:\\Users\\tecno\\Desktop\\Proyecto_Datos1\\Proyecto2\\MasterClientApps\\src\\usuarios\\UsuariosClientes.xml");

                // Crear el nuevo elemento 'usuario'
                Element elementoUsuario = documento.createElement("cliente");

                // Crear el elemento 'nombre' y asignar el valor
                Element elementoNombre = documento.createElement("nombre");
                elementoNombre.appendChild(documento.createTextNode(nombre));

                // Crear el elemento 'contrasena' y asignar el valor
                Element elementoContrasena = documento.createElement("contrasena");
                elementoContrasena.appendChild(documento.createTextNode(contrasena));

                // Agregar los elementos 'nombre' y 'contrasena' al elemento 'usuario'
                elementoUsuario.appendChild(elementoNombre);
                elementoUsuario.appendChild(elementoContrasena);

                // Agregar el nuevo usuario al documento
                documento.getDocumentElement().appendChild(elementoUsuario);

                // Preparar la transformación para escribir el archivo XML modificado
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                // Crear la fuente DOM para la transformación
                DOMSource source = new DOMSource(documento);

                // Especificar la ubicación y nombre del archivo XML de salida
                StreamResult result = new StreamResult("C:\\Users\\tecno\\Desktop\\Proyecto_Datos1\\Proyecto2\\MasterClientApps\\src\\usuarios\\UsuariosClientes.xml");

                // Realizar la transformación y escribir el archivo XML modificado
                transformer.transform(source, result);

                System.out.println("Cliente agregado al archivo XML correctamente.");
            } catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
                e.printStackTrace();
            }
        }
        return true;

    }

    
    private void cargarAdmisEnArbol() {
        // Carga los usuarios en el árbol binario de búsqueda
        for (Usuario u : lectorUsuariosUsuarios.getUsuarios()) {
            arbolUsuarios.insertar(u.getNombre(), u.getContrasena());
        }
    }
    private void cargarClientesEnArbol() {
        // Carga los usuarios en el árbol binario de búsqueda
        for (Usuario u : lectorUsuariosClientes.getClientes()) {
            arbolClientes.insertar(u.getNombre(), u.getContrasena());
        }
    }
    
}
