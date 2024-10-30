package archivos.servicio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import archivos.dominio.Snack;

public class ServicioSnacksArchivos implements IServicioSnacks {

    private final String NONMBRE_ARCHIVO = "snacks.txt";
    private List<Snack> snacks = new ArrayList<>();

    //Agregar constructor de la clase
    public ServicioSnacksArchivos(){
        //Crear archivo con validación de existencia
        var archivo = new File(NONMBRE_ARCHIVO);
        var existe = false;
        
        try{

            existe = archivo.exists();
            if(existe){
                this.snacks = obtenerSnack();
            }else{//crear el archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();//Se guarda el archivo en disco.
                System.out.println("Se ha creado el archivo.");
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error: "+e.getMessage());
        }
        
        //Si no existe, el archivo se cargan Snacks inicial.
        if(!existe){
          this.cargarSnackInicial();
        }


        
    }

    private void cargarSnackInicial(){

        this.agregarSnack(new Snack("Papas",70));
        this.agregarSnack(new Snack("Gaseosa",25));
        this.agregarSnack(new Snack("Chocolatina",15));


    }

    private List<Snack> obtenerSnack (){
        var snacks = new ArrayList<Snack>();
        try{
            List<String> lineas= Files.readAllLines(Paths.get(NONMBRE_ARCHIVO));
            for (String linea : lineas) {
                String [] lineaSnack = linea.split(",");//Separa cada linea por coma
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]) ;
                var snack = new Snack(nombre,precio);
                snacks.add(snack);//acá se agrega el snack leido en la linea a la lista.
                               
            }
        }catch(Exception e){
            System.out.println("Error lectura arcvhivo: "+e.getMessage());
            e.printStackTrace();
        }
        return snacks;
    }

    @Override
    public void agregarSnack(Snack snack) {
        //Agregamos el nuevo snack
        //1. Aregar a la lista.
        this.snacks.add(snack);
        //2. Guardar en archivo
        this.agregarSnackArchivo(snack);
        
    }

    private void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo =new File(NONMBRE_ARCHIVO);

        try{
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close();//Guarda el archivo

        }catch(Exception e){
            System.out.println("Error agregar snack al archivo: "+e);
        }


    }

    @Override
    public void mostrarSnacks() {
        System.out.println("----------- Snacks En el inventario ------------------");
        var inventarioSnacks="";
        for (Snack snack : this.snacks) {
            inventarioSnacks+=snack.toString()+"\n";
          
        }
        System.out.println(inventarioSnacks);
        
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }


    
}
