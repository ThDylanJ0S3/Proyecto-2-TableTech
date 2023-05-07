/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.awt.geom.Rectangle2D;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.LectorUsuarios;

/**
 * Metodo principal que corre el juego
 *
 * @author Vidal Flores Montero 2021579554
 */
public class Main extends Application {



        /**
         * se declara el contructor del metodo main para poder hacer ejecutable el
         * proyecto
         *
         * @param args se envian los argumentos y comandos de linea
         */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    /**
     * Método principal que genera el hilo necesario para poder ejecutar la GUI
     * y todos sus elementos.
     *
     * @param stage se envia como parametro el escenario de la GUI
     *
     */
    public void start(Stage stage) throws Exception {
        
        javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        
        Parent rootMaster = FXMLLoader.load(getClass().getResource("/GUI/menuPrincipalMaster.fxml"));
        Scene sceneMaster = new Scene(rootMaster);
        Stage stageMaster = new Stage();
        stageMaster.setTitle("Menú principal - Administradores");
        stageMaster.setScene(sceneMaster);
        stageMaster.setX(screenBounds.getMinX() + 100); // Colocar la ventana a 100 píxeles de la izquierda
        stageMaster.setY(screenBounds.getMinY() + 100); // Colocar la ventana a 100 píxeles desde la parte superior

        Parent rootClient = FXMLLoader.load(getClass().getResource("/GUI/menuPrincipalClient.fxml"));
        Scene sceneClient = new Scene(rootClient);
        Stage stageClient = new Stage();
        stageClient.setTitle("Menú principal - Clientes");
        stageClient.setScene(sceneClient);
        stageClient.setX(stageMaster.getX() + 620); // Colocar la ventana a la derecha del maestro con un espacio de 20 píxeles
        stageClient.setY(screenBounds.getMinY() + 100); // Colocar la ventana a la misma altura que el maestro

        stageMaster.show();
        stageClient.show();


    }


}
