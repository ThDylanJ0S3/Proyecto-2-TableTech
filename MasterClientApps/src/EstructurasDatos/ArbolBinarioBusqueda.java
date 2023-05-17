package EstructurasDatos;

/**
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class ArbolBinarioBusqueda {
    
    private ArbolBinarioBusqueda arbolUsuarios;
    private ArbolBinarioBusqueda arbolClientes;
    private NodoBinarioBusqueda raiz;

    /**
     * Crea un nuevo árbol binario de búsqueda.
     */
    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    /**
     * Inserta un nuevo nodo en el árbol binario de búsqueda.
     * 
     * @param nombreUsuario El nombre de usuario del nodo a insertar.
     * @param contrasena La contraseña asociada al nombre de usuario.
     */
    public void insertar(String nombreUsuario, String contrasena) {
        raiz = insertarRecursivamente(raiz, nombreUsuario, contrasena);
    }

    /**
     * Inserta un nuevo nodo en el árbol binario de búsqueda de forma recursiva.
     *
     * @param nodo El nodo actual en el proceso de inserción.
     * @param nombreUsuario El nombre de usuario del nodo a insertar.
     * @param contrasena La contraseña asociada al nombre de usuario.
     * @return El nodo actualizado después de la inserción.
     */
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

    /**
     * Busca un nodo con el nombre de usuario y contraseña especificados en el árbol binario de búsqueda.
     * 
     * @param nombreUsuario El nombre de usuario a buscar.
     * @param contrasena La contraseña asociada al nombre de usuario.
     * @return true si se encuentra el nodo, false en caso contrario.
     */
    public boolean buscar(String nombreUsuario, String contrasena) {
        return buscarRecursivamente(raiz, nombreUsuario, contrasena);
    }

    /**
     * Realiza la búsqueda de un nodo con el nombre de usuario y contraseña especificados en el árbol binario de búsqueda de forma recursiva.
     *
     * @param nodo El nodo actual en el proceso de búsqueda.
     * @param nombreUsuario El nombre de usuario a buscar.
     * @param contrasena La contraseña asociada al nombre de usuario.
     * @return true si se encuentra el nodo, false en caso contrario.
     */
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
