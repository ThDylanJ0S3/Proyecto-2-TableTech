package modelo;

/**
 *
 * @author Dylan Meza
 */
public class Lista {
    private Node head;
    public int size;
    
    /**
     * Constructor de la clase Lista que inicializa la cabeza de la lista como null y el tamaño en 0.
     */
    public void Lista(){
        this.head = null;
        this.size = 0;
    }
    
    /**
    * Devuelve el objeto platillos en la posición especificada de la lista.
    * @param posicion La posición del objeto platillos en la lista (comenzando desde 0).
    * @return El objeto platillos en la posición especificada.
    */
   public Platillos getPlatillos(int posicion){
       if (posicion < 0 || posicion >= size) {
           throw new IndexOutOfBoundsException("Posición fuera de rango");
       }

       Node auxNode = head;
       int i = 0;
       while (i < posicion) {
           auxNode = auxNode.getNext();
           i++;
       }

       return (Platillos) auxNode.getData();
   }

    
    /**
     * Verifica si la lista está vacía.
     * @return true si la lista está vacía, false de lo contrario.
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    
    /**
     * Devuelve el tamaño de la lista.
     * @return El tamaño de la lista.
     */
    public int size(){
        return this.size;
    }
    
    /**
     * Agrega un nuevo nodo con el objeto platillos pasado como parámetro al final de la lista.
     * @param data El objeto platillos a agregar al final de la lista.
     */
    public void agregarFinal(Platillos data){
        Node newNode = new Node();
        newNode.setData(data);
        
        if(isEmpty()){
            head = newNode;
        }
        else{
            Node auxNode = head;
            while(auxNode.getNext()!=null){
                auxNode = auxNode.getNext();
            }
            auxNode.setNext(newNode);
        }
        size++;
    }
    
    /**
    * Elimina el nodo en la posición especificada de la lista.
    * @param posicion La posición del nodo a eliminar (comenzando desde 0).
    */
    public void delete(int posicion){
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        if (posicion == 0) {
            head = head.getNext();
        }
        else{
            Node prevNode = getNodeEnPosicion(posicion - 1);
            Node currentNode = prevNode.getNext();
            prevNode.setNext(currentNode.getNext());
        }

        size--;
    }
    
    /**
    * Obtiene el nodo en la posición especificada de la lista.
    * @param posicion La posición del nodo (comenzando desde 0).
    * @return El nodo en la posición especificada.
    */
   private Node getNodeEnPosicion(int posicion) {
       Node auxNode = head;
       int i = 0;
       while (i < posicion) {
           auxNode = auxNode.getNext();
           i++;
       }
       return auxNode;
   }
}

