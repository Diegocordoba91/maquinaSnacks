package archivos.servicio;
import java.util.List;
import archivos.dominio.Snack;

public interface IServicioSnacks {
    void agregarSnack(Snack snack);
    void mostrarSnacks();    
    List<Snack> getSnacks();
    
} 

    
    

