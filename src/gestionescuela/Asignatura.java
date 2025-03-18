package gestionescuela;
import java.util.Scanner;
public class Asignatura {
    Scanner scan = new Scanner(System.in);
    
    //Atributos de clase:
    private int horas;
    private Profesor docente;
    private int codigo;
    private String nombre;
    private static int numAsignaturas = 0;
    private int numCursos;
    
    //Constructores:
    public Asignatura(String n, int h){
        horas = h;
        nombre = n;
        numAsignaturas++;
        codigo = numAsignaturas;
        numCursos = 0;
    }
    
    //Métodos:
    public int getHoras(){
        return horas;
    }
    
    public void setHoras(int h){
        horas = h;
    }
    
    public Profesor getProfesor(){
        return docente;
    }
    
    public void setProfesor(Profesor p){
        docente = p;
        if(p != null) p.sumarHoras(horas*numCursos);
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String n){
        nombre = n;
    }
    
    public static int getNumAsig(){
        return numAsignaturas;
    }
    
    public int getNumEntradas(){
        return numCursos;
    }
    
    public void nuevaEntrada(){
        numCursos++;
    }
    
    public void imprimir(){
        System.out.print(nombre + ", " + horas + " horas semanales (ID = " + codigo + ")");
        if(docente == null) System.out.print("\n");
        else System.out.println(" impartida por " + docente.getNombre());
    }
    
    @Override
    public String toString(){
        String res = nombre + ", " + horas + " h/semana, (ID = " + codigo + ")";
        if(docente != null) res += " impartida por " + docente;
        
        return res;
    }
}
