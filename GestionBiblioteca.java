import java.util.*;

public class GestionBiblioteca {
    public static void main(String[] args) throws LibroNoPrestadoException {
        
        
        /*
        Cargar Libro
        Dar de Baja un Libro
        Cargar Socio
        Dar de baja Socio
        Prestar Libro
        Devolver Libro"+
        Lista de Libros"+
        Buscar Libro"+
        Docentes Responsables
        Lista Socios
        * 
        */
        HashMap<Integer, Socio> socios = new HashMap<>();
        ArrayList<Libro> libros = new ArrayList<>();
        
        Estudiante estudiante1 = new Estudiante(12345, "Juan Pérez", 15, "Ingeniería");
        Estudiante estudiante2 = new Estudiante(67890, "Ana Gómez", 10, "Arquitectura");
        
        Docente docente1 = new Docente(11223, "Carlos López", 30, "Matemáticas");
        Docente docente2 = new Docente(44556, "Marta Ruiz", 30, "Física");
        
        socios.put(estudiante1.getDniSocio(), estudiante1);
        socios.put(estudiante2.getDniSocio(), estudiante2);
        socios.put(docente1.getDniSocio(), docente1);
        socios.put(docente2.getDniSocio(), docente2);

        
        Libro libro1 = new Libro("Java para Principiantes", 1, "Editorial X", 2020);
        Libro libro2 = new Libro("Introducción a la Programación", 3, "Editorial Y", 2018);
        Libro libro3 = new Libro("Patrones de Diseño", 2, "Editorial Z", 2021);
        Libro libro4 = new Libro("Bases de Datos Avanzadas", 4, "Editorial W", 2019);
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);

        
    
        Calendar fechaRetiro = Calendar.getInstance();

        
        Biblioteca biblioteca1 = new Biblioteca("Libros de Lujo", socios, libros);
        System.out.println(biblioteca1.listaDeLibros());
        System.out.println(biblioteca1.listaDeSocios());
        
        
        System.out.println("\n\nLibro prestado");
        biblioteca1.prestarLibro(fechaRetiro, estudiante1, libro1);
        System.out.println(biblioteca1.listaDeLibros());
        System.out.println(biblioteca1.listaDeSocios());
        
        
        System.out.println("\n\nLibro devuelto");
        biblioteca1.devolverLibro(libro1);
        System.out.println(biblioteca1.listaDeLibros());
        System.out.println(biblioteca1.listaDeDocentesResponsables());

    }
}
