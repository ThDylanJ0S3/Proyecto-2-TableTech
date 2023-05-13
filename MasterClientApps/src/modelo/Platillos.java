package modelo;

/**
 *
 * @author Dylan Meza
 */
public class Platillos {

    private String nombrePlatillo;
    private int cantCalorias;
    private int precio;
    private int tiempoPreparacion;

    public Platillos() {
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
}