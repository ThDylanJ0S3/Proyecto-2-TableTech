package modelo;

import java.util.Objects;

/**
 *
 * @author Dylan Meza
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

    public Platillos(String nombrePlatillo, int cantidadCalorias, int tiempoPreparacion, int precio) {
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public int getCantCalorias() {
        return cantCalorias;
    }

    public void setCantCalorias(int cantCalorias) {
        this.cantCalorias = cantCalorias;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(int tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    @Override
    public String toString() {
        return "Platillo: " + nombrePlatillo + "\n" +
                "Calorías: " + cantCalorias + "\n" +
                "Precio: $" + precio + "\n" +
                "Tiempo de preparación: " + tiempoPreparacion + " segundos";
    }


    public boolean getId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}