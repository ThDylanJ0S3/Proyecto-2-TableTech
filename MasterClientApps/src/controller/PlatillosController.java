/**package controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Platillos;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**Este código define una clase llamada `PlatillosController` que contiene dos métodos para
 * guardar y cargar una lista de objetos `Platillos` en/desde un archivo JSON en un servidor.

 El método `guardarPlatillosEnJson` toma una lista de objetos `Platillos`, convierte la lista
 en una cadena JSON utilizando la biblioteca Gson, y luego escribe la cadena JSON en un archivo
 en el servidor. Si el archivo no existe, lo crea.

 El método `cargarPlatillosDesdeJson` lee la cadena JSON del archivo en el servidor, la convierte
 de nuevo en una lista de objetos `Platillos` utilizando la biblioteca Gson y luego devuelve la lista.
 Si el archivo no existe, devuelve una lista vacía.

 En resumen, estos métodos permiten guardar y cargar la lista de platillos en un formato fácilmente
 procesable (JSON) en el servidor.
public class PlatillosController {
    // ...

    // Ruta del archivo JSON en el servidor
    private static final String PLATILLOS_FILE = "/var/www/html/platillos.json";

    // Método para guardar la lista de platillos en un archivo JSON
    private void guardarPlatillosEnJson(List<Platillos> platillos) {
        Gson gson = new Gson();
        String json = gson.toJson(platillos);

        // Se escribe el archivo JSON en el servidor
        try {
            Path filePath = Paths.get(PLATILLOS_FILE);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            FileWriter writer = new FileWriter(PLATILLOS_FILE);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Método para cargar la lista de platillos desde un archivo JSON
    private List<Platillos> cargarPlatillosDesdeJson() {
        List<Platillos> platillos = new ArrayList<>();
        // Se lee el archivo JSON desde el servidor
        try {
            Path filePath = Paths.get(PLATILLOS_FILE);
            if (Files.exists(filePath)) {
                String json = Files.readString(filePath);
                Gson gson = new Gson();
                Type type = new TypeToken<List<Platillos>>(){}.getType();
                platillos = gson.fromJson(json, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return platillos;
    }
}*/




