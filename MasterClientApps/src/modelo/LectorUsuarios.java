package modelo;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Personal
 */
public class LectorUsuarios {

    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    private static ArrayList<Usuario> clientes = new ArrayList<Usuario>();

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public static ArrayList<Usuario> getClientes() {
        return clientes;
    }

    public static void leerUsuarios(String archivo, String tipoArchivo) {
        try {
            File inputFile = new File(archivo);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("usuario");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element eElement = (Element) nList.item(temp);
                String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                String contrasena = eElement.getElementsByTagName("contrasena").item(0).getTextContent();
                Usuario usuario = new Usuario(nombre, contrasena);
                usuarios.add(usuario);
            }
            
            //Si el tipo de archivo es "clientes", lee los clientes en lugar de los usuarios
            if (tipoArchivo.equals("clientes")) {
                NodeList noList = doc.getElementsByTagName("cliente");

                for (int temp = 0; temp < noList.getLength(); temp++) {
                    Element enElement = (Element) noList.item(temp);
                    String cliente = enElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String contrasenaa = enElement.getElementsByTagName("contrasena").item(0).getTextContent();
                    Usuario clientenew = new Usuario(cliente, contrasenaa);
                    clientes.add(clientenew);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
