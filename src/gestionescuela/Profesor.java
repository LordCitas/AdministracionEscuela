package gestionescuela;
import java.util.Scanner;
public class Profesor {
    Scanner scan = new Scanner(System.in);
    
    //Atributos de clase: 
    private String dni;
    private String nombre;
    private int numHoras;
    private float sueldoHora;
    private static int numProfesores = 0;
    
    //Constructores:
    public Profesor(String d, String n, float s){
        dni = d;
        nombre = n;
        sueldoHora = s;
        numProfesores++;
        numHoras = 0;
    }
    
    //Métodos:
    public String getDni(){
        return dni;
    }
    
    public void setDni(String d){
        dni = d;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String n){
        dni = n;
    }
    
    public int getNumHoras(){
        return numHoras;
    }
    
    public float getSueldoHora(){
        return sueldoHora;
    }
    
    public void setSueldoHora(float s){
        sueldoHora = s;
    }
    
    public static int getNumProf(){
        return numProfesores;
    }
    
    public void sumarHoras(int n){
        numHoras += n;
    }
    
    public boolean estaSobreexplotado(){
        boolean lleno = (numHoras == 20)? true : false;
        if(lleno) System.out.println("Este profesor ya ha cubierto su máximo de horas. Por favor, elige otro: ");
        return lleno;
    }
    
    public boolean sePasaria(Asignatura a){
        boolean pasado = (numHoras + a.getHoras()*((a.getNumEntradas()==0)? 1 : a.getNumEntradas()) > 20)? true : false;
        if(pasado) System.out.println(nombre + " no podría impartir " + a.getNombre() + " porque superaría su máximo de horas semanales.");
        return pasado;
    }
    
    @Override
    public String toString(){
        return nombre + ", DNI: " + dni + ", " + sueldoHora + "€/h, " + numHoras + " horas ya asignadas";
    }
}
