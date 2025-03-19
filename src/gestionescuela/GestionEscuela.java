package gestionescuela;
import java.util.Scanner;
public class GestionEscuela {
    public static void main(String[] args) {
        //Definimos el scanner
        Scanner scan = new Scanner(System.in);
        
        //Definimos las variables que van a ir controlando el flujo del programa
        int respuesta, respuesta2, longitud;
        
        //Unos arrays para generar nombres automáticamente cuando no queramos introducirlos a mano
        String[] nombres = {"Daniel", "David", "Martina", "Blanca", "Carlos",
                            "Álvaro", "Pablo", "Inés", "Ana", "Alejandro",
                            "Diego", "Pepe", "Desiree", "Juanjo", "Érica",
                            "Lucía", "Pedro", "Andrés", "Teresa"};
        String[] apellidos = {"Aybar", "Soto", "Terreu", "González", "López",
                              "Aguado", "Hermoso", "Flores", "Zurita", "Gallego",
                              "Portero", "Montoya", "Aguilera", "Pelta", "Pérez",
                              "Ayuso", "Rajoy", "Zapatero", "Sánchez"};

        //Otro string para mostrar los meses del año
        String[] meses = {"Septiembre", "Octubre", "Noviembre", "Diciembre", "Enero",
                          "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto"};
        
        //Pedimos el número de aulas (cursos) con los que vamos a trabajar (como máximo)
        System.out.print("Introduce el número de aulas con las que cuenta la escuela: ");
        int numCursos = scan.nextInt();
        while(numCursos < 1){
            System.out.print("La escuela debe tener como mínimo un aula. Por favor, introduce otro número: ");
            numCursos = scan.nextInt();
        }
        
        //Limpiamos el buffer
        scan.nextLine();
        
        //Definimos los arrays que van a almacenar toda la información de la escuela
        Curso[] cursos = new Curso[numCursos];
        Asignatura[] asignaturas = new Asignatura[numCursos*5];
        Profesor[] profesores = new Profesor[numCursos*5];
        
        //Empezamos con el bucle que va a servir de interfaz para los métodos
        do{
            //Pedimos que se elija una función del programa a ejecutar
            System.out.print("\n¿Qué operación quieres realizar?"
                                    + "\n\t1- Crear un curso"
                                    + "\n\t2- Crear una asignatura"
                                    + "\n\t3- Asignar una asignatura a un curso"
                                    + "\n\t4- Registrar un profesor"
                                    + "\n\t5- Asignar un profesor a una asignatura"
                                    + "\n\t6- Registrar un alumno"
                                    + "\n\t7- Mostrar toda la información de la escuela"
                                    + "\n\t8- Calcular el márgen de ganancia bruto de la escuela"
                                    + "\n\t9- Salir"
                             + "\nTu respuesta: ");
            //Debemos comprobar que la opción elegida esté recogida entre las que hemos definido, y volver a pedirla si no es el caso
            respuesta = seleccionarOpcionInt(1, 9);
            
            //Dependiendo de la respuesta que hayamos recogido, ejecutamos una parte del programa u otra
            switch(respuesta){
                case 1: //Crear un curso
                    //Si ya hemos llenado todas las aulas, no podemos hacer más cursos
                    if(Curso.getNumCursos() == numCursos) System.out.println("Ya no se pueden añadir más cursos.");
                    else{ //Si todavía nos queda espacio, permitimos crear el curso
                        
                        //Pedimos los datos con los que vamos a construir el curso
                        System.out.print("Nombre del curso: ");
                        String nCurso = scan.nextLine();

                        System.out.print("Año del curso: ");
                        int aCurso = scan.nextInt();

                        System.out.print("Precio del curso: ");
                        int pCurso = scan.nextInt();
                        
                        //Limpiamos el buffer
                        scan.nextLine();
                        
                        //Creamos el curso con los datos recogidos
                        cursos[Curso.getNumCursos()] = new Curso(nCurso, aCurso, pCurso);
                    }
                break;
                
                case 2: //Crear una asignatura
                    //Si ya hemos definido un número de asignaturas igual a 5 veces el de cursos, no podemos crear otra
                    if(Asignatura.getNumAsig() == asignaturas.length) System.out.println("No se pueden crear más asignaturas.");
                    else{ //Si todavía nos queda espacio, permitimos crear la asignatura
                        
                        //Pedimos el nombre que se le va a dar a la asignatura
                        System.out.print("Nombre de la asignatura: ");
                        String nAsignatura = scan.nextLine();
                        
                        //Pedimos el número de horas que se van a impartir cada semana, y comprobamos que el valor sea válido
                        System.out.print("Número de horas semanales de la asignatura (1-20): ");
                        int hAsignatura = seleccionarOpcionInt(1, 20);
                        
                        //Creamos la asignatura
                        asignaturas[Asignatura.getNumAsig()] = new Asignatura(nAsignatura, hAsignatura);
                    }
                break;
                
                case 3: //Asignar una asignatura a un curso SIN FALLOS POR AHORA
                    //Si todavía no hemos definido ningún curso/asignatura, no podemos hacer la asignación
                    if(Curso.getNumCursos() == 0) System.out.println("No hay ningún curso registrado.");
                    else if(Asignatura.getNumAsig() == 0) System.out.println("No hay ninguna asignatura registrada.");
                    else{ //Si ya tenemos al menos uno de cada, permitimos asignar una asignatura
                        
                        //Mostramos la información de los cursos, pedimos que se seleccione uno y comprobamos que la elección es válida
                        System.out.println("Elige el curso al que quieres asignarle una asignatura: ");
                        imprimirLista(cursos, 1);
                        longitud = Curso.getNumCursos()+1;
                        System.out.print("\t" + longitud + "- Salir\nTu respuesta: ");
                        do{
                            respuesta2 = seleccionarOpcionInt(1, longitud);
                        } while (respuesta2 != longitud && cursos[respuesta2-1].estaLlenoAsig());
                        
                        //Sólo continuamos con el proceso si no hemos elegido la opción "Salir"
                        if(respuesta2 != longitud){
                            //Mostramos la información de las asignaturas, pedimos que se seleccione una y comprobamos que la elección es válida
                            System.out.println("Elige la asignatura que quieres asignarle a este curso: ");
                            imprimirLista(asignaturas, 1);
                            longitud = Asignatura.getNumAsig()+1;
                            System.out.print("\t" + longitud + "- Salir\nTu respuesta: ");
                            do{
                                respuesta = seleccionarOpcionInt(1, longitud);
                            } while (cursos[respuesta2-1].asignaturaIncluida(asignaturas[respuesta-1]) && respuesta != longitud);
                            
                            //Sólo continuamos con el proceso si no hemos elegido la opción "Salir" y si 
                            if(respuesta != longitud){
                                cursos[respuesta2-1].insertaAsignatura(asignaturas[respuesta-1]);
                            }
                        }
                    }
                break;
                
                case 4: //Registrar un profesor
                    //Si ya hemos definido un número de profesores igual a 5 veces el de cursos, no podemos crear otro más
                    if(Profesor.getNumProf() == profesores.length) System.out.println("No se pueden registrar más profesores.");
                    else{ //Si todavía nos queda espacio, permitimos registrar al nuevo profesor
                        System.out.print("¿Quieres introducir los datos del profesor manualmente?\n\t1- Sí\n\t2- No\nTu respuesta: ");
                        respuesta = seleccionarOpcionInt(1, 2);
                        
                        //Defino variables que voy a usar en ambos casos
                        String nProfesor, dniProfesor;
                        float sProfesor;
                        
                        if(respuesta == 1){
                            //Pedimos el nombre del profesor
                            System.out.print("Nombre y apellidos del profesor: ");
                            nProfesor = scan.nextLine();

                            //Pedimos el dni del profesor
                            System.out.print("DNI del profesor: ");
                            dniProfesor = scan.nextLine();
                            
                            //Pedimos el salario del profesor
                            System.out.print("Sueldo por horas del profesor: ");
                            sProfesor =  seleccionarOpcionFloat(10, 100);
                        } else { //Vamos a generar un profesor automáticamente
                            //Generamos un nombre aleatorio usando los arrays de nombres que hemos definido al principio
                            nProfesor = nombres[(int)(Math.random()*19)] + " " + apellidos[(int)(Math.random()*19)] + 
                                               " " + apellidos[(int)(Math.random()*19)];
                            
                            //Generamos un DNI aleatorio
                            dniProfesor = generaDNI();
                            
                            //Generamos un sueldo aleatorio asegurándonos de que sea mayor que 10 (le pongo de máximo 40€ por poner algo)
                            sProfesor = (float)((10 + Math.random()*30));
                            //Truncamos pa que quede bonito
                            sProfesor -= sProfesor%0.01;
                        }
                        
                        //Construimos el profesor y lo introducimos en el array
                        profesores[Profesor.getNumProf()] = new Profesor(dniProfesor, nProfesor, sProfesor);
                    }
                break;
                
                case 5: //Asignar un profesor a una asignatura
                    //Si todavía no hemos definido ningún profesor/asignatura, no podemos hacer la asignación
                    if(Profesor.getNumProf() == 0) System.out.println("No hay ningún profesor registrado.");
                    else if(Asignatura.getNumAsig() == 0) System.out.println("No hay ninguna asignatura registrada.");
                    else{ //Si ya tenemos por lo menos uno de cada registrado, continuamos con el proceso
                        System.out.println("Elige al profesor que quieres asignar a una asignatura:");
                        imprimirLista(profesores, 1);
                        longitud = Profesor.getNumProf()+1;
                        System.out.print("\t" + longitud + "- Salir\nTu respuesta: ");
                        do{
                            respuesta2 = seleccionarOpcionInt(1, longitud);
                        } while (respuesta2 != longitud && profesores[respuesta2-1].estaSobreexplotado());
                        
                        //Sólo continuamos con el proceso si no hemos elegido la opción "Salir"
                        if(respuesta2 != longitud){
                            //Mostramos la información de las asignaturas, pedimos que se seleccione una y comprobamos que la elección es válida
                            System.out.println("Elige la asignatura que quieres asignarle a " + profesores[respuesta2-1].getNombre() + ": ");
                            imprimirLista(asignaturas, 1);
                            longitud = Asignatura.getNumAsig()+1;
                            System.out.print("\t" + longitud + "- Salir\nTu respuesta: ");
                            do{
                                respuesta = seleccionarOpcionInt(1, longitud);
                            } while (respuesta != longitud && profesores[respuesta2-1].sePasaria(asignaturas[--respuesta]));
                            
                            //Sólo continuamos con el proceso si no hemos elegido la opción "Salir"
                            if(respuesta != longitud){
                                //Una vez nos hemos asegurado de que los datos son válidos, pasamos a asignar el profesor a la asignatura
                                asignaturas[respuesta].setProfesor(profesores[respuesta2-1]);
                            }
                        }
                                
                    }
                break;
                case 6: //Registrar un alumno
                    //Si no hemos registrado ningún curso, no vamos a permitir introducir alumnos
                    if(Curso.getNumCursos() == 0) System.out.println("No hay ningún curso registrado, crea uno para registrar un alumno.");
                    else{
                        //Mostramos la información de los cursos, pedimos que se seleccione uno y comprobamos que la elección es válida
                        System.out.println("Elige el curso al que se va a matricular el alumno: ");
                        imprimirLista(cursos, 1);
                        longitud = Curso.getNumCursos()+1;
                        System.out.print("\t" + longitud + "- Salir\nTu respuesta: ");
                        do{
                            respuesta2 = seleccionarOpcionInt(1, longitud);
                        } while (respuesta2 != longitud && cursos[respuesta2-1].estaLlenoAlum());

                        //Sólo continuamos con el proceso si no elegimos la opción de "Salir"
                        if(respuesta2 != longitud){
                            //Igual que con los profesores, vamos a dar la opción de introducir automáticamente datos de los alumnos
                            System.out.print("¿Quieres introducir los datos del alumno manualmente?\n\t1- Sí\n\t2- No\nTu respuesta: ");
                            respuesta = seleccionarOpcionInt(1, 2);
                            
                            //Defino variables que voy a usar en ambos casos
                            String nAlumno, dniAlumno;
                            boolean plazos;
                            
                            if(respuesta == 1){
                                //Pedimos el nombre del alumno
                                System.out.print("Nombre y apellidos del alumno: ");
                                nAlumno = scan.nextLine();
    
                                //Pedimos el dni del alumno
                                System.out.print("DNI de " + nAlumno + ": ");
                                dniAlumno = scan.nextLine();
                                
                                //Pedimos el método de pago del alumno
                                System.out.print("¿Cómo va a pagar el curso?\n\t1- A plazos\n\t2- Completo\nTu respuesta: ");
                                respuesta = seleccionarOpcionInt(1, 2);
                                plazos = (respuesta == 1)? true : false;

                                //Una vez lo tenemos todo, creamos y asignamos el alumno al curso elegido
                                cursos[respuesta2-1].insertaAlumno(new Alumno(dniAlumno, nAlumno, plazos));
                            } else { //Vamos a generar un alumno automáticamente (o varios)
                                System.out.print("¿Quieres llenar el curso automáticamente?\n\t1- Sí\n\t2- No\nTu respuesta: ");
                                boolean auto = (seleccionarOpcionInt(1, 2) == 1)? true : false;

                                int veces = (auto)? 30 - cursos[respuesta2-1].getNumAlumnos() : 1;

                                for(int i=0; i<veces; i++){
                                    //Generamos un nombre aleatorio usando los arrays de nombres que hemos definido al principio
                                    nAlumno = nombres[(int)(Math.random()*19)] + " " + apellidos[(int)(Math.random()*19)] + 
                                    " " + apellidos[(int)(Math.random()*19)];
                
                                    //Generamos un DNI aleatorio
                                    dniAlumno = generaDNI();
                                    
                                    //Ponemos un tipo de pago al azar
                                    plazos = (Math.random() < 0.5)? true : false;

                                    //Una vez lo tenemos todo, creamos y asignamos el alumno al curso elegido
                                    cursos[respuesta2-1].insertaAlumno(new Alumno(dniAlumno, nAlumno, plazos));
                                }
                                //Un sout para dar aviso de la operación
                                System.out.println("Se ha(n) matriculado " + veces + " alumno(s) en " + cursos[respuesta2-1]);
                            }
                        }
                    }
                break;

                case 7: //Mostrar toda la información de la escuela
                    System.out.println("CURSOS:");
                    longitud = Curso.getNumCursos();
                    if(longitud == 0) System.out.println("\tNo se ha registrado ningún curso por ahora.");
                    else{
                        for(int i=0; i<Curso.getNumCursos(); i++){
                            System.out.println("\t" + (i+1) + "- " + cursos[i]);
                            if(cursos[i].getNumAsignaturas() != 0){
                                System.out.print("\t\tASIGNATURAS:\n");
                                imprimirLista(cursos[i].getAsignaturas(), 3);
                            }
                            if(cursos[i].getNumAlumnos() != 0){
                                System.out.print("\t\tALUMNOS:\n");
                                imprimirLista(cursos[i].getAlumnos(), 3);
                            }
                        }
                    }

                    longitud = Asignatura.getNumAsig();
                    System.out.println("\nASIGNATURAS:");
                    if(longitud == 0) System.out.println("\tNo se ha registrado ninguna asignatura por ahora.");
                    else imprimirLista(asignaturas, 1);

                    longitud = Profesor.getNumProf();
                    System.out.println("\nPROFESORES:");
                    if(longitud == 0) System.out.println("\tNo se ha registrado ningún profesor por ahora.");
                    else imprimirLista(profesores, 1);
                break;
                case 8: //Calcular el márgen de ganancia bruto de la escuela
                    longitud = Curso.getNumCursos();
                    if(longitud == 0) System.out.println("Todavía no se ha definido ningún curso.");
                    else{
                        System.out.print("Elige el mes hasta el que quieres calcular el márgen de ganancia de la escuela:\n");
                        imprimirLista(meses, 1);
                        System.out.print("Tu respuesta: ");
                        respuesta = seleccionarOpcionInt(1, 12);

                        int numPlazos, numCompletos, ganancia = 0;
                        for(int i=0; i<longitud; i++){
                            if(cursos[i].getNumAlumnos() >= 10){
                                numPlazos = 0;
                                numCompletos = 0;
                                for(int j=0; j<cursos[i].getNumAlumnos(); j++){
                                    if(cursos[i].getAlumnos()[j].getPlazos() == true) numPlazos++;
                                    else numCompletos++;
                                }
                                for(int j=0; j<cursos[i].getNumAsignaturas(); j++){
                                    if(cursos[i].getAsignaturas()[j].getProfesor() != null) 
                                        ganancia -= cursos[i].getAsignaturas()[j].getHoras() * cursos[i].getAsignaturas()[j].getProfesor().getSueldoHora() * 4 * respuesta;
                                }
                                ganancia += (numPlazos*respuesta*cursos[i].getPrecio()/12) + numCompletos*cursos[i].getPrecio();
                                System.out.println("En " + cursos[i].getNombre() + " hay " + numPlazos + " pagos en plazos, y " + numCompletos + " completos");
                            }
                        }
                        System.out.println("Los ingresos de la escuela son de " + ganancia + "€");
                    }
                break;

                case 9: //Salir
                    respuesta = -1;
                break;
            }
        } while(respuesta != -1);
    }
    
    public static void imprimirLista(Object[] lista, int num){
        for (int i = 0; i < lista.length; i++) {
            if(lista[i] != null){
                for(int j=0; j<num; j++) System.out.print("\t");
                System.out.println((i+1) + "- " + lista[i]);
            } else break;            
        }
    }
    
    public static float seleccionarOpcionFloat(int min, int max){
        Scanner scan = new Scanner(System.in);
        
        float respuesta;
        respuesta = scan.nextFloat();
        while(respuesta < min || respuesta > max){
            System.out.print("El valor introducido no es válido. Por favor, introduce otro: ");
            respuesta = scan.nextFloat();
        }
        return respuesta;
    }
    
    public static int seleccionarOpcionInt(int min, int max){
        return (int) seleccionarOpcionFloat(min, max);
    }
    
    public static String generaDNI(){
        int numero, suma = 0;
        String codigo = "";
        for(int i=0; i<8; i++){
            numero = (int)(Math.random()*10);
            codigo += numero;
            suma += numero;
        }
        char[] lista = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        suma %= 23;
        codigo += lista[suma];
        
        return codigo;
    }
}
