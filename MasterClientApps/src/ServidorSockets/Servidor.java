package ServidorSockets;

import EstructurasDatos.ArbolBinarioBusqueda;
import EstructurasDatos.NodoBinarioBusqueda;
import controller.ListaAdministradoresController;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Clase que representa un servidor que acepta conexiones de clientes y maneja diversas operaciones.
 *
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class Servidor implements Runnable{
    
    private ArbolBinarioBusqueda arbolUsuarios;
    private ArbolBinarioBusqueda arbolClientes;
    private ServerSocket servidorSocket;
    private Socket socket;

    private LectorUsuarios lectorUsuariosClientes = new LectorUsuarios();
    private LectorUsuarios lectorUsuariosUsuarios = new LectorUsuarios();
    
    private List<Usuario> lista;

    /**
     * Constructor de la clase Servidor que inicializa el objeto Servidor y carga los usuarios desde archivos XML.
     *
     * @param puerto el número de puerto en el que se va a ejecutar el servidor.
     */
    public Servidor(int puerto) {
        try {
            servidorSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lectorUsuariosClientes.leerUsuarios("./MasterClientApps/src/usuarios/UsuariosClientes.xml", "clientes");
        lectorUsuariosUsuarios.leerUsuarios("./MasterClientApps/src/usuarios/UsuariosAdmis.xml", "usuario");
        arbolUsuarios = new ArbolBinarioBusqueda();
        arbolClientes = new ArbolBinarioBusqueda();

        System.out.println(lectorUsuariosClientes.getClientes() + "lector clientes");
        System.out.println(lectorUsuariosUsuarios.getUsuarios() + "lector admis");
        cargarAdmisEnArbol();
        cargarClientesEnArbol();
        
    }

    /**
     * Método para iniciar el servidor y aceptar conexiones entrantes.
     * Se ejecuta en un bucle infinito para mantener el servidor activo.
     */
    public void iniciarServidor() {
        while (true) {
            try {
                System.out.println("Esperando conexión...");
                System.out.println(socket+"esto es socket");
                socket = servidorSocket.accept();
                recibirSocket(socket);
                System.out.println("Conexión aceptada de " + socket.getInetAddress());
                // Aquí puedes manejar la conexión con el cliente en otro hilo o clase
        
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método para cerrar el servidor y liberar los recursos utilizados.
     */
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

    /**
     * Método run de la interfaz Runnable.
     * Llama al método iniciarServidor para iniciar el servidor.
     */
    @Override
    public void run() {
        iniciarServidor();
    }

    /**
     * Método para recibir el socket y realizar operaciones basadas en los datos enviados por el cliente.
     *
     * @param socket el socket de la conexión establecida con el cliente.
     */
    public void recibirSocket(Socket socket) {
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            Object inputData = input.readObject();
            System.out.println("lee el input y lo hace object");
            if (inputData != null) {
            if (inputData instanceof String[]) {
                String[] data = (String[]) inputData;
                if (data.length == 3) {
                    System.out.println("entra a manejaLogin");
                    boolean usuarioValido=manejarLogin(data);
                    out.writeBoolean(usuarioValido);
                    out.flush();
                    
                } else if (data.length == 5) {
                    manejarArchivosXml(data);
                } else if (data.length==1){
                    lista=cargarUsuariosDesdeXML("./MasterClientApps/src/usuarios/UsuariosAdmis.xml");
                    out.writeObject(lista);
                    out.flush();
                    System.out.println("se envio la lista del servidor al controlador");
                }
            } else {
                System.out.println("Input inválido. Tipo de objeto incorrecto.");
            }
        }

        input.close();
        out.close();
        socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método para manejar el inicio de sesión de un usuario.
     * 
     * @param Datos los datos de inicio de sesión del usuario
     * @return true si el inicio de sesión es válido, false de lo contrario
     */
    public boolean manejarLogin(String[] Datos) {
        
            String usuario = Datos[0];
            String contrasena = Datos[1];
            String tipo = Datos[2];
            
            
            // Validar las credenciales del usuario
            return validarUsuario(usuario, contrasena,tipo);

        
    }
   
    /**
     * Método para eliminar o modificar los datos de los usuarios.
     * 
     * @param Datos los datos de inicio de sesión del usuario
     * @return false si no se eliminan o modifican datos
     */
    public boolean manejarArchivosXml(String[] Datos) {

        String oldName = Datos[0];
        String oldContrasena = Datos[1];
        String newName = Datos[2];
        String newContra = Datos[3];
        String tipo = Datos[4];

        if (tipo.equals("modificar")) {
            modificarAdmin(oldName, oldContrasena, newName, newContra);
        } else if (tipo.equals("eliminar")){
                eliminarAdmin(oldName,oldContrasena,newName,newContra);
            }
        return false;
    
    }

    /**
     * Método para validar un usuario cliente.
     *
     * @param usuario    el nombre de usuario
     * @param contrasena la contraseña
     * @return true si el usuario cliente es válido, false de lo contrario
     */
    public boolean validarUsuario(String usuario, String contrasena, String tipo) {
        boolean usuarioValido=false;
        
        if (tipo.equals("usuario")) {
            System.out.println("se busca en el arbol binario la validacion admis");
            usuarioValido= arbolUsuarios.buscar(usuario, contrasena);
        } else if (tipo.equals("cliente")){
            System.out.println("se busca en el arbol binario la validacion clientes");
            usuarioValido= arbolClientes.buscar(usuario, contrasena);
        } else if(tipo.equals("agregar")){
            return agregarUsuario(usuario, contrasena, tipo);
        } 
        return usuarioValido;
    }

    public boolean agregarUsuario(String nombre, String contrasena, String tipo) {

        try {
            System.out.println("entra a agregar al usuario");
            // Crear el objeto DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear el objeto DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML existente para obtener el objeto Document
            Document documento = builder.parse("./MasterClientApps/src/usuarios/UsuariosAdmis.xml");

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
            StreamResult result = new StreamResult("./MasterClientApps/src/usuarios/UsuariosAdmis.xml");

            // Realizar la transformación y escribir el archivo XML modificado
            transformer.transform(source, result);

            System.out.println("Usuario agregado al archivo XML correctamente.");
        } catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return true;

    }

    /**
     * Método para cargar los usuarios admis en el árbol binario de búsqueda.
     */
    private void cargarAdmisEnArbol() {
        // Carga los usuarios en el árbol binario de búsqueda
        for (Usuario u : lectorUsuariosUsuarios.getUsuarios()) {
            arbolUsuarios.insertar(u.getNombre(), u.getContrasena());
        }
    }
    
    /**
     * Método para cargar los usuarios clientes en el árbol binario de búsqueda.
     */
    private void cargarClientesEnArbol() {
        // Carga los usuarios en el árbol binario de búsqueda
        for (Usuario u : lectorUsuariosClientes.getClientes()) {
            arbolClientes.insertar(u.getNombre(), u.getContrasena());
        }
    }

    private boolean modificarAdmin(String oldName,String oldContra,String newName,String newContra){
        try {
            // Crear el objeto DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear el objeto DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML existente para obtener el objeto Document
            Document documento = builder.parse(new File("./MasterClientApps/src/usuarios/UsuariosAdmis.xml"));

            // Obtener todos los elementos 'usuario' en el documento
            NodeList usuarios = documento.getElementsByTagName("usuario");

            // Iterar sobre los elementos 'usuario' y realizar la modificación
            for (int i = 0; i < usuarios.getLength(); i++) {
                Element usuario = (Element) usuarios.item(i);

                // Obtener los elementos 'nombre' y 'contrasena' del usuario actual
                Element nombreElement = (Element) usuario.getElementsByTagName("nombre").item(0);
                Element contrasenaElement = (Element) usuario.getElementsByTagName("contrasena").item(0);

                // Obtener los valores actuales de 'nombre' y 'contrasena'
                String nombre = nombreElement.getTextContent();
                String contrasena = contrasenaElement.getTextContent();

                // Verificar si los valores coinciden con los parámetros oldName y oldContra
                if (nombre.equals(oldName) && contrasena.equals(oldContra)) {
                    // Realizar la modificación cambiando el nombre y la contraseña
                    nombreElement.setTextContent(newName);
                    contrasenaElement.setTextContent(newContra);

                    // Guardar los cambios en el archivo XML
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                    // Crear la fuente DOM para la transformación
                    DOMSource source = new DOMSource(documento);

                    // Especificar la ubicación y nombre del archivo XML de salida
                    StreamResult result = new StreamResult(new File("./MasterClientApps/src/usuarios/UsuariosAdmis.xml"));

                    // Realizar la transformación y escribir el archivo XML modificado
                    transformer.transform(source, result);

                    // Devolver true para indicar que el cambio fue exitoso
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolver false si no se encontró el usuario con los valores especificados
        return false;
    
    }
    
    private boolean eliminarAdmin(String oldName,String oldContrasena,String newName,String newContra){
        try {
        // Crear el objeto DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Crear el objeto DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parsear el archivo XML existente para obtener el objeto Document
        Document documento = builder.parse(new File("./MasterClientApps/src/usuarios/UsuariosAdmis.xml"));

        // Obtener todos los elementos 'usuario' en el documento
        NodeList usuarios = documento.getElementsByTagName("usuario");

        // Iterar sobre los elementos 'usuario' y realizar la eliminación
        for (int i = 0; i < usuarios.getLength(); i++) {
            Element usuarioElement = (Element) usuarios.item(i);

            // Obtener los elementos 'nombre' y 'contrasena' del usuario actual
            Element nombreElement = (Element) usuarioElement.getElementsByTagName("nombre").item(0);
            Element contrasenaElement = (Element) usuarioElement.getElementsByTagName("contrasena").item(0);

            // Obtener los valores de 'nombre' y 'contrasena'
            String nombre = nombreElement.getTextContent();
            String contrasenaActual = contrasenaElement.getTextContent();

            // Verificar si los valores coinciden con los parámetros usuario y contrasena
            if (nombre.equals(oldName) && contrasenaActual.equals(oldContrasena)) {
                // Eliminar el usuario
                usuarioElement.getParentNode().removeChild(usuarioElement);

                // Guardar los cambios en el archivo XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                // Crear la fuente DOM para la transformación
                DOMSource source = new DOMSource(documento);

                // Especificar la ubicación y nombre del archivo XML de salida
                StreamResult result = new StreamResult(new File("./MasterClientApps/src/usuarios/UsuariosAdmis.xml"));

                // Realizar la transformación y escribir el archivo XML modificado
                transformer.transform(source, result);

                System.out.println("Usuario eliminado del archivo XML correctamente.");
                }
        }

        System.out.println("No se encontró el usuario en el archivo XML.");

    } catch (ParserConfigurationException | TransformerException | IOException | SAXException e) {
        e.printStackTrace();
    }
        return true; // Terminar el método después de eliminar el usuario
     
    }

    /**
     * Método para cargar usuarios desde un archivo XML.
     *
     * @param archivoXML la ruta del archivo XML
     * @return una lista de usuarios
     */
    public List<Usuario> cargarUsuariosDesdeXML(String archivoXML) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            // Crear el objeto DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear el objeto DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML existente para obtener el objeto Document
            Document documento = builder.parse(archivoXML);

            // Obtener todos los elementos 'usuario' en el documento
            NodeList listaUsuarios = documento.getElementsByTagName("usuario");

            // Iterar sobre los elementos 'usuario' y crear objetos Usuario
            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Element usuarioElement = (Element) listaUsuarios.item(i);

                // Obtener los elementos 'nombre' y 'contrasena' del usuario actual
                Element nombreElement = (Element) usuarioElement.getElementsByTagName("nombre").item(0);
                Element contrasenaElement = (Element) usuarioElement.getElementsByTagName("contrasena").item(0);

                // Obtener los valores de 'nombre' y 'contrasena'
                String nombre = nombreElement.getTextContent();
                String contrasena = contrasenaElement.getTextContent();

                // Crear un objeto Usuario y agregarlo a la lista
                Usuario usuario = new Usuario(nombre, contrasena);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
