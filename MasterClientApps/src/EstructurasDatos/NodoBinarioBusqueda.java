/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDatos;

/**
 *
 * @author Personal
 */
public class NodoBinarioBusqueda {
    String nombreUsuario;
    String contrasena;
    NodoBinarioBusqueda hijoIzquierdo;
    NodoBinarioBusqueda hijoDerecho;

    public NodoBinarioBusqueda(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        hijoIzquierdo = null;
        hijoDerecho = null;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public NodoBinarioBusqueda getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinarioBusqueda hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinarioBusqueda getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinarioBusqueda hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
}
