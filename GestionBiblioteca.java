import java.util.*;

public class GestionBiblioteca {
    public static void main(String[] args) throws LibroNoPrestadoException {
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

        
        Libro libro1 = new Libro("Programando con JAVA”", 1, "Editorial X", 2020);
        Libro libro2 = new Libro("Introducción a la Programación", 3, "Editorial Y", 2018);
        Libro libro3 = new Libro("Patrones de Diseño", 2, "Editorial Z", 2021);
        Libro libro4 = new Libro("Bases de Datos Avanzadas", 4, "Editorial W", 2019);
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);

        Biblioteca biblioteca1 = new Biblioteca("Libros de Lujo", socios, libros);
        System.out.println("Cantidad de socios de tipo Estudiante: "+biblioteca1.cantidadDeSociosPortipo("Estudiante"));
        
        System.out.println("\nLista de docentes que nunca han adeudado ni adeudan libros: ");
        System.out.println(biblioteca1.docentesResponsables());
        
        System.out.println("\n\n\nLista de libros y socios:");
        System.out.println(biblioteca1.listaDeLibros());
        System.out.println(biblioteca1.listaDeSocios());
        
        
        
        Calendar fechaRetiro = Calendar.getInstance();
        biblioteca1.prestarLibro(fechaRetiro, estudiante1, libro1);
        System.out.println("\n\n\n¿Qué socio tiene prestado el libro “Programando con JAVA”? ");
        System.out.println(biblioteca1.quienTieneElLibro(libro1));
  

    }
}
