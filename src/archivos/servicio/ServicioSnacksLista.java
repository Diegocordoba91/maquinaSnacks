package archivos.servicio;
import java.lang.foreign.Addressable;
import java.util.ArrayList;
import java.util.List;

import archivos.dominio.Snack;

public class ServicioSnacksLista implements IServicioSnacks{
    
    //Atributo estatico de la clase, esta clase no tiene objetos.
    private static final List <Snack> snacks;

    //Bloque estatico inicializador. Es como un constructor para objetos estaticos
    //inicializa atributos estaticos, y así inicializa la lista para poder acceder.
        
    static{
        snacks = new ArrayList<>();//Una vez creada la lista no se puede asignar una lista disitinta pero si se puede cambiar el contendio.
        snacks.add(new Snack("Papas",50));
        snacks.add(new Snack("Refresco",15));
        snacks.add(new Snack("Cocosette",9));
    }


    public void agregarSnack(Snack snack){
        snacks.add(snack);    
    }

    public void mostrarSnacks(){
        var inventarioSnacks="";
        for (Snack snack : snacks) {
            inventarioSnacks+=snack.toString() + "\n";
        }
        System.out.println("------- Inventario Snacks --------");
        System.out.println(inventarioSnacks);
    }

    public List<Snack> getSnacks() {
        return snacks;
    }    
    



}
