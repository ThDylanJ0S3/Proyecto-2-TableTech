/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Personal
 */
public class Usuario implements Serializable{

    private String nombre;
    private String contrasena;

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }
 
    @Override
    public String toString() {
        return "Nombre: " + this.nombre + ", Contraseña: " + this.contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
