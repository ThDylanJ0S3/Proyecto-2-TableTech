/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDatos;

/**
 *
 * @author Personal
 */
public class ArbolBinarioBusqueda {
    
    private ArbolBinarioBusqueda arbolUsuarios;
    private ArbolBinarioBusqueda arbolClientes;

    private NodoBinarioBusqueda raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(String nombreUsuario, String contrasena) {
        raiz = insertarRecursivamente(raiz, nombreUsuario, contrasena);
    }

    private NodoBinarioBusqueda insertarRecursivamente(NodoBinarioBusqueda nodo, String nombreUsuario, String contrasena) {
        if (nodo == null) {
            return new NodoBinarioBusqueda(nombreUsuario, contrasena);
        }

        if (nombreUsuario.compareTo(nodo.getNombreUsuario()) < 0) {
            nodo.setHijoIzquierdo(insertarRecursivamente(nodo.getHijoIzquierdo(), nombreUsuario, contrasena));
        } else if (nombreUsuario.compareTo(nodo.getNombreUsuario()) > 0) {
            nodo.setHijoDerecho(insertarRecursivamente(nodo.getHijoDerecho(), nombreUsuario, contrasena));
        }

        return nodo;
    }

    public boolean buscar(String nombreUsuario, String contrasena) {
        return buscarRecursivamente(raiz, nombreUsuario, contrasena);
    }

    private boolean buscarRecursivamente(NodoBinarioBusqueda nodo, String nombreUsuario, String contrasena) {
        if (nodo == null) {
            return false;
        }

        if (nombreUsuario.equals(nodo.getNombreUsuario()) && contrasena.equals(nodo.getContrasena())) {
            return true;
        }

        if (nombreUsuario.compareTo(nodo.getNombreUsuario()) < 0) {
            return buscarRecursivamente(nodo.getHijoIzquierdo(), nombreUsuario, contrasena);
        } else {
            return buscarRecursivamente(nodo.getHijoDerecho(), nombreUsuario, contrasena);
        }
    }
}
