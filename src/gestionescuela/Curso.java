package gestionescuela;
import java.util.Scanner;
public class Curso {
    Scanner scan = new Scanner(System.in);
    
    //Atributos de clase:
    private String nombre;
    private int anio;
    private Alumno[] alumnos;
    private int numAlumnos = 0;
    private Asignatura[] asignaturas;
    private int numAsignaturas = 0;
    private int id;
    private static int numCursos = 0;
    private double precio;
    
    //Constructores:
    public Curso(String n, int a, double p){
        nombre = n;
        anio = a;
        alumnos = new Alumno[30];
        asignaturas = new Asignatura[5];
        numCursos++;
        id = numCursos;
        precio = p;
    }
    
    public Curso(Curso c){
        this.nombre = c.nombre;
        this.anio = c.anio;
        this.alumnos = new Alumno[30];
        
        
        //AQUÍ PODEMOS COPIAR O NO EL ARRAY DE ASIGNATURAS, DEPENDIENDO DE CUÁNTO TE QUIERAS ROMPER LOS HUEVOS
        
        
        this.asignaturas = new Asignatura[5];
        numCursos++;
        this.id = numCursos;
        this.precio = c.precio;
    }
    
    //Métodos:
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String n){
        nombre = n;
    }
    
    public int getAnio(){
        return anio;
    }
    
    public Alumno[] getAlumnos(){
        return alumnos;
    }
    
    public int getNumAlumnos(){
        return numAlumnos;
    }
    
    public Asignatura[] getAsignaturas(){
        return asignaturas;
    }
    
    public void setAsignaturas(Asignatura[] a){
        asignaturas = a;
    }
    
    public int getNumAsignaturas(){
        return numAsignaturas;
    }
    
    public int getId(){
        return id;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(double p){
        precio = p;
    }
    
    public void insertaAsignatura(Asignatura a){
        if(a.getProfesor() != null){
            if(a.getProfesor().sePasaria(a)) a.setProfesor(null);
            else{
                a.getProfesor().sumarHoras(a.getHoras());
                asignaturas[numAsignaturas++] = a;
                System.out.println("La asignación de la asignatura se ha llevado a cabo con éxito.");
            }            
        }
        
        if(a.getProfesor() == null){
            if((a.getNumEntradas() + 1) * a.getHoras() > 20) System.out.println("Asignar esta asignatura haría que se excediese el límite de horas semanales del profesor que la fuese a impartir.");
            else{
                a.nuevaEntrada();
                asignaturas[numAsignaturas++] = a;
                System.out.println("La asignación de la asignatura se ha llevado a cabo con éxito.");
            }
        } else if(a.getProfesor().getNumHoras() + a.getHoras()*a.getNumEntradas() > 20) System.out.println("El profesor asignado a la asignatura no puede impartirla más veces sin exceder su máximo de horas semanal.");
    }
    
    public void insertaAlumno(Alumno a){
        if(numAlumnos == 30) System.out.println("Este curso ya está completo.");
        else{
            alumnos[numAlumnos++] = a;
        }
    }
    
    public static int getNumCursos(){
        return numCursos;
    }
    
    public void imprimir(){
        System.out.println(anio + "º de " + nombre + " (ID = " + id + ")");
    }
    
    @Override
    public String toString(){
        return anio + "º de " + nombre + " (ID = " + id + ")";
    }
           
    public boolean asignaturaIncluida(Asignatura a){
        boolean encontrada = false;
        //ATENCION POSIBLE ERROR
        for(int i=0; i<numAsignaturas && !encontrada; i++) encontrada = (asignaturas[i] == a)? true : false;
        if(encontrada) System.out.print(a.getNombre() + " ya está asignada a este curso. Por favor, introduce otra opción: ");
        return encontrada;
    }
    
    public boolean estaLlenoAsig(){
        boolean lleno = (numAsignaturas >= 5)? true : false;
        if(lleno) System.out.print("Este curso ya no admite más asignaturas. Por favor, elige otro: ");
        return lleno;
    }
    
    public boolean estaLlenoAlum(){
        boolean lleno = (numAlumnos >= 30)? true : false;
        if(lleno) System.out.println("Este curso ya no admite más alumnos.");
        return lleno;
    }
}

