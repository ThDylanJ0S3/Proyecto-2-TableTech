package modelo;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
/**
 *
 * @author Dylan Meza
 */

public class AlertW {
    
    /**
     * Metodo utilizado para crear una ventana emergente
     * @param tittle nombre que aparece en la ventana emergente
     * @param message mensaje que aparecera en la ventana emergente
     */
    public static void display(String tittle, String message){
        Stage window = new Stage();
        
        //Hace que la pantalla de error tenga que ser cerrada para continuar editando
        window.initModality(Modality.APPLICATION_MODAL); 
        
        window.setTitle(tittle);
        window.setMinWidth(600);
        
        Label label = new Label();
        label.setText(message);
        
        //Boton para cerrar
        Button botonCerrar = new Button();
        botonCerrar.setText("Aceptar");
        botonCerrar.setOnAction((f) -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, botonCerrar);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        
        
    }
}