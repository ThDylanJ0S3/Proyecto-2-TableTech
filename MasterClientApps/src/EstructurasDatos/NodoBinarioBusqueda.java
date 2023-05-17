package EstructurasDatos;

/**
 * Representa un nodo en un árbol binario de búsqueda.
 * Cada nodo contiene un nombre de usuario y una contraseña asociada.
 * También mantiene referencias a sus hijos izquierdo y derecho en el árbol.
 *
 *@author Jefferson Arias
 *@author Vidal Flores
 *@author Dylan Meza
 */
public class NodoBinarioBusqueda {
    String nombreUsuario;
    String contrasena;
    NodoBinarioBusqueda hijoIzquierdo;
    NodoBinarioBusqueda hijoDerecho;

    /**
     * Crea un nuevo nodo con el nombre de usuario y contraseña especificados.
     * Los hijos izquierdo y derecho se inicializan como nulos.
     * 
     * @param nombreUsuario El nombre de usuario asociado al nodo.
     * @param contrasena La contraseña asociada al nombre de usuario.
     */
    public NodoBinarioBusqueda(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        hijoIzquierdo = null;
        hijoDerecho = null;
    }

    /**
     * Obtiene el nombre de usuario asociado al nodo.
     * 
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario asociado al nodo.
     * 
     * @param nombreUsuario El nombre de usuario a establecer.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña asociada al nombre de usuario del nodo.
     * 
     * @return La contraseña.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña asociada al nombre de usuario del nodo.
     * 
     * @param contrasena La contraseña a establecer.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el hijo izquierdo del nodo.
     * 
     * @return El hijo izquierdo.
     */
    public NodoBinarioBusqueda getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    /**
     * Establece el hijo izquierdo del nodo.
     * 
     * @param hijoIzquierdo El nodo que se establecerá como hijo izquierdo.
     */
    public void setHijoIzquierdo(NodoBinarioBusqueda hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    /**
     * Obtiene el hijo derecho del nodo.
     * 
     * @return El hijo derecho.
     */
    public NodoBinarioBusqueda getHijoDerecho() {
        return hijoDerecho;
    }

    /**
     * Establece el hijo derecho del nodo.
     * 
     * @param hijoDerecho El nodo que se establecerá como hijo derecho.
     */
    public void setHijoDerecho(NodoBinarioBusqueda hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
}
