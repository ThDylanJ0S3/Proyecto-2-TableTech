/**package controller;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
 MANEJO DE JSON DE LOS PLATILLOS DEL SERVIDOR CODIGO INICIAL (NO FUNCIONAL)
public class Platillo {
    private String nombre;
    private String descripcion;
    private double precio;

    // constructor, getters y setters

    // Creación de una lista de objetos Platillo
    List<Platillo> platillos = new ArrayList<>();

    // Creación de un objeto Platillo y adición a la lista de platillos
    Platillo nuevoPlatillo = new Platillo();
    platillos.add();

    // Método para guardar una lista de objetos Platillo en un archivo
    private void guardarPlatillos(List<Platillo> platillos, String nombreArchivo) {
        Gson gson = new Gson();  // Creación de un objeto Gson
        String json = gson.toJson(platillos); // Conversión de la lista de objetos Platillo a un string en formato JSON

        try (Writer writer = new FileWriter(nombreArchivo)) { // Apertura de un archivo para escritura
            writer.write(json);  // Escritura del string JSON en el archivo
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de la excepción en caso de que ocurra un error al escribir en el archivo
        }
    }

    // Método para leer una lista de objetos Platillo de un archivo
    private List<Platillo> leerPlatillos(String nombreArchivo) {
        Gson gson = new Gson();  // Creación de un objeto Gson
        List<Platillo> platillos = new ArrayList<>(); // Creación de una nueva lista de objetos Platillo

        try (Reader reader = new FileReader(nombreArchivo)) { // Apertura de un archivo para lectura
            Type tipoListaPlatillos = new TypeToken<List<Platillo>>(){}.getType(); // Creación de un objeto TypeToken para especificar el tipo de objeto a leer
            platillos = gson.fromJson(reader, tipoListaPlatillos); // Conversión del contenido del archivo a una lista de objetos Platillo usando el objeto Gson
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de la excepción en caso de que ocurra un error al leer el archivo
        }

        return platillos; // Retorno de la lista de objetos Platillo leída del archivo
    }
}
 */