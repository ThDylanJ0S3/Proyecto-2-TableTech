/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import ServidorSockets.ClienteSockets;
import ServidorSockets.Servidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class ListaAdministradoresController implements Initializable {

    @FXML
    private Button btnAgregarAdmin;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtContra;
    @FXML
    private Button btnEliminarAdmin;
    @FXML
    private Button btnModificarAdmin;
    
    private Socket Socket;
    
    private ObjectInputStream in;
    
    private ObjectOutputStream out;
    @FXML
    private TextField txtNameModificar;
    @FXML
    private TextField txtContraModificar;
    @FXML
    private TableView<Usuario> tablaAdmis;
    
    ObservableList<Usuario> admis;
    
    @FXML
    private TableColumn<Usuario, String> columName;
    @FXML
    private TableColumn<Usuario, String> columContra;
    
    private ClienteSockets cliente;
    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());

            out.writeObject(new String[]{"ListaAdmis"});
            out.flush();

            // Recibir la respuesta del servidor
            List<Usuario> resultado = (List<Usuario>) in.readObject();
            setUsuarios(resultado);
            System.out.println(resultado + "esto es lo que se recibe");
            
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("Error al conectar al servidor");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListaAdministradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void agregarAdmin(ActionEvent event) throws IOException, ClassNotFoundException {
        String nombre = txtUsername.getText();
        String contrasena = txtContra.getText();
        String accion = "agregar";
        try {
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());

            out.writeObject(new String[]{nombre, contrasena, accion});
            out.flush();

            // Recibir la respuesta del servidor
            boolean resultado = in.readBoolean();

            
            in.close();
            out.close();
            
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());

            out.writeObject(new String[]{"ListaAdmis"});
            out.flush();

            // Recibir la respuesta del servidor
            List<Usuario> resultado1 = (List<Usuario>) in.readObject();
            setUsuarios(resultado1);
            System.out.println(resultado + "esto es lo que se recibe");
            
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("Error al conectar al servidor");
        }
    }

    @FXML
    private void eliminarAdmin(ActionEvent event) throws ClassNotFoundException {
        String oldName=txtUsername.getText();
        String oldContra=txtContra.getText();
        String newName=" ";
        String newContra=" ";
        String tipo="eliminar";
        try {
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());
        
            out.writeObject(new String[]{oldName, oldContra, newName, newContra, tipo});
            out.flush();

            // Recibir la respuesta del servidor
            boolean resultado = in.readBoolean();

            
            in.close();
            out.close();
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());

            out.writeObject(new String[]{"ListaAdmis"});
            out.flush();

            // Recibir la respuesta del servidor
            List<Usuario> resultado1 = (List<Usuario>) in.readObject();
            setUsuarios(resultado1);
            System.out.println(resultado + "esto es lo que se recibe");
            
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("Error al conectar al servidor");
        }
    }

    @FXML
    private void modificarAdmin(ActionEvent event) throws ClassNotFoundException {
        String oldName=txtUsername.getText();
        String oldContra=txtContra.getText();
        String newName=txtNameModificar.getText();
        String newContra=txtContraModificar.getText();
        String tipo="modificar";
        try {
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());

            out.writeObject(new String[]{oldName, oldContra, newName, newContra, tipo});
            out.flush();

            // Recibir la respuesta del servidor
            boolean resultado = in.readBoolean();

            in.close();
            out.close();
            
            Socket = new Socket("localhost", 8080);
            out = new ObjectOutputStream(Socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(Socket.getInputStream());

            out.writeObject(new String[]{"ListaAdmis"});
            out.flush();

            // Recibir la respuesta del servidor
            List<Usuario> resultado1 = (List<Usuario>) in.readObject();
            setUsuarios(resultado1);
            System.out.println(resultado + "esto es lo que se recibe");
            
            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("Error al conectar al servidor");
        }

    }

    public void setUsuarios(List<Usuario> usuarios) {
        admis = FXCollections.observableArrayList(usuarios);
        columName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columContra.setCellValueFactory(new PropertyValueFactory<>("contrasena"));

        tablaAdmis.setItems(admis);
    }

}
