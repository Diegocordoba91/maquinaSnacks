package archivos.presentacion;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;
import javax.lang.model.element.ModuleElement.ProvidesDirective;
import archivos.dominio.Snack;
import archivos.servicio.IServicioSnacks;
import archivos.servicio.ServicioSnacksArchivos;
import archivos.servicio.ServicioSnacksLista;



public class MaquinaSnacks {


    
    public static void main(String[] args) {
        
        maquinaSnacks();
        
    }
    
    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Dado que el método de la clase Snacks ya no es estático se debe crear un objeto, para poder acceder
        IServicioSnacks servicioSnack = new ServicioSnacksArchivos();
        List<Snack> productosNuevos = new ArrayList<>();
        System.out.println("********************** MAQUINA DE SNACKS ***************************");
        servicioSnack.mostrarSnacks();

        while (!salir) {
            try{
                var opcion  = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion,consola,productosNuevos, servicioSnack);
            }catch(Exception e){
                System.out.println("Ocurrio un error : "+e);        
    
            }finally{
                System.out.println("Gracias por usar la maquina de Snacks");        
    
            }
            
            
        }
        
    }

    private static int mostrarMenu(Scanner consola){
        System.out.printf("""
                \nMenú:
                1. Comprar Snack
                2. Mostrar Ticket
                3. Agregar Snack
                4. Inventario Snack
                5. Salir
                Elige una opción:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion,Scanner consola, List<Snack> productos, IServicioSnacks servicioSnack){

        var salir = false;
        switch(opcion){
            case 1->comprarSnack(consola, productos, servicioSnack);
            case 2->mostrarTicket(productos);
            case 3->agregarSnack(consola, servicioSnack);
            case 4 ->listarInventarioSnacks(consola,servicioSnack);
            case 5->{
                salir=true;
                System.out.println("Regresa pronto!");
            }
            
            default->{
                System.out.println("Opción invalida "+opcion);
                }
                
        }
        
        
        return salir;

    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IServicioSnacks servicioSnack){

        System.out.print("Que snack quieres comprar: ");
        var idSnack = Integer.parseInt(consola.nextLine());
        //Validar que la lista exista en la lista de snack
        var snackEncontrado=false;
        for(var snack:servicioSnack.getSnacks()){
            if(idSnack==snack.getIdSnack()){
                productos.add(snack);
                System.out.println("OK, Snack agregado: "+ snack);
                snackEncontrado=true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("Snack no encontrado (id) = "+idSnack);
        }

    }

    private static void mostrarTicket(List<Snack> productos){
        var tiquet = "******* TICKET DE VENTA *******";
        //var resultado="";
        var precioTotal=0d;
        for(var snack:productos){
            tiquet += "\n\t"+snack.getNombre()+" $"+snack.getPrecio();
            precioTotal += snack.getPrecio();
        }
        
        tiquet += "\n\tTotal --> $"+precioTotal;
        System.out.println(tiquet);
        /* 
        System.out.printf("""
                El precio total de los snack agregados es $%.2f
                Los Snack de la orden son:
                %s
                """,precioTotal,resultado);*/
             
    }

    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnack){

        System.out.print("Ingrese el nombre del snack: ");
        var nombre = consola.nextLine();
        System.out.print("Ingrese el precio: ");
        var precio = Double.parseDouble(consola.nextLine());

        servicioSnack.agregarSnack(new Snack(nombre,precio));
        System.out.println("Se ha agregado el Snack");
        servicioSnack.mostrarSnacks();
     

        
    }

    private static void listarInventarioSnacks(Scanner consola,IServicioSnacks servicioSnacks ){

        servicioSnacks.mostrarSnacks();

    }
}


