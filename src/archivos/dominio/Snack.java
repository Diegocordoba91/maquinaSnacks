package archivos.dominio;
import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable{

    private static int contador_snacks;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack(){
        //Por cada objeto creado de tipo Snack se almancena en idSnack y también se preincrementa contado_Snacks, ya que inicia en cero.
        this.idSnack= ++ Snack.contador_snacks;
    }

    public Snack(String nombre, double precio) {
        this();//LLama al constructor vacío, se debe poner al inicio
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public int getIdSnack(){
        return this.idSnack;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    public static int getContador_snacks() {
        return contador_snacks;
    }

    @Override
    public String toString() {
        return "Snack [idSnack=" + idSnack + ", nombre=" + nombre + ", precio=" + precio + "]";
    }

    public String escribirSnack(){
        return idSnack +","+nombre+","+precio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack,nombre,precio);
    }

    @Override
    public boolean equals(Object obj) {//Indica si el méd
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Snack other = (Snack) obj;
        if (idSnack != other.idSnack)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        return true;
    }

    

    

    

    

    

    
}
