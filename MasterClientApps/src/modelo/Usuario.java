package modelo;

import java.io.Serializable;

/**
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class Usuario implements Serializable{

    private String nombre;
    private String contrasena;

    /**
    *Constructor de la clase Usuario.
    *@param nombre el nombre del usuario
    *@param contrasena la contraseña del usuario
    */
    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    /**
    *Obtiene el nombre del usuario.
    *@return el nombre del usuario
    */
    public String getNombre() {
        return nombre;
    }

    /**
    *Obtiene la contraseña del usuario.
    *@return la contraseña del usuario
    */
    public String getContrasena() {
        return contrasena;
    }
 
    /**
    *Devuelve una representación en cadena del objeto Usuario.
    *@return la representación en cadena del objeto Usuario
    */
    @Override
    public String toString() {
        return "Nombre: " + this.nombre + ", Contraseña: " + this.contrasena;
    }  
    
    /**
    *Establece el nombre del usuario.
    *@param nombre el nombre del usuario a establecer
    */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
