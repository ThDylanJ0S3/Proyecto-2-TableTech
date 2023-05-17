package modelo;

import java.util.Objects;

/**
 *
*@author Jefferson Arias
*@author Vidal Flores
*@author Dylan Meza
*/
public class Platillos {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Platillos other = (Platillos) obj;
        return Objects.equals(id, other.id);
    }

    private String nombrePlatillo;
    private int cantCalorias;
    private int precio;
    private int tiempoPreparacion;
    private boolean id;
    
    /**
     * Constructor de la clase Platillos.
     *
     * @param nombrePlatillo    el nombre del platillo
     * @param cantidadCalorias  la cantidad de calorías del platillo
     * @param tiempoPreparacion el tiempo de preparación del platillo
     * @param precio            el precio del platillo
     */
    public Platillos(String nombrePlatillo, int cantidadCalorias, int tiempoPreparacion, int precio) {
    }

    /**
     * Obtiene el nombre del platillo.
     *
     * @return el nombre del platillo
     */
    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    /**
     * Establece el nombre del platillo.
     *
     * @param nombrePlatillo el nombre del platillo a establecer
     */
    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    /**
     * Obtiene la cantidad de calorías del platillo.
     *
     * @return la cantidad de calorías del platillo
     */
    public int getCantCalorias() {
        return cantCalorias;
    }

    /**
     * Establece la cantidad de calorías del platillo.
     *
     * @param cantCalorias la cantidad de calorías del platillo a establecer
     */
    public void setCantCalorias(int cantCalorias) {
        this.cantCalorias = cantCalorias;
    }

    /**
     * Obtiene el precio del platillo.
     *
     * @return el precio del platillo
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del platillo.
     *
     * @param precio el precio del platillo a establecer
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el tiempo de preparación del platillo.
     *
     * @return el tiempo de preparación del platillo
     */
    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    /**
     * Establece el tiempo de preparación del platillo.
     *
     * @param tiempoPreparacion el tiempo de preparación del platillo a establecer
     */
    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    /**
     * Devuelve una representación en cadena del objeto Platillos.
     *
     * @return la representación en cadena del objeto Platillos
     */
    @Override
    public String toString() {
        return "Platillo: " + nombrePlatillo + "\n" +
                "Calorías: " + cantCalorias + "\n" +
                "Precio: $" + precio + "\n" +
                "Tiempo de preparación: " + tiempoPreparacion + " segundos";
    }

    /**
    * Obtiene el identificador del platillo.
    *
    * @return el identificador del platillo
    */
    public boolean getId() {
        return id;
    }

    /**
     * Establece el identificador del platillo.
     *
     * @param id el identificador del platillo a establecer
     */
    public void setId(boolean id) {
        this.id = id;
    }
}