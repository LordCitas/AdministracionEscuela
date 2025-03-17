package gestionescuela;
import java.util.Scanner;
public class Alumno {
    Scanner scan = new Scanner(System.in);
    
    //Atributos de clase:
    private String dni;
    private String nombre;
    private boolean plazos;
    
    //Constructores:
    public Alumno(String d, String n, boolean p){
        dni = d;
        nombre = n;
        plazos = p;
    }
    
    //Métodos:
    public String getDni(){
        return dni;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public boolean getPlazos(){
        return plazos;
    }  
}
