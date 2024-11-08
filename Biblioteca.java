import java.util.*;
public class Biblioteca
{
    private String nombre;
    private HashMap<Integer , Socio> socios;
    private ArrayList<Libro> libros;
    
    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setSocios(new HashMap<>());
        this.setLibros(new ArrayList<Libro>());
    }

    public Biblioteca(String p_nombre, HashMap<Integer , Socio> p_socios, ArrayList<Libro> p_libros){
        this.setNombre(p_nombre);
        this.setSocios(p_socios);
        this.setLibros(p_libros);
    }

    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }
    private void setSocios(HashMap<Integer , Socio> p_Socios){
        this.socios = p_Socios;
    }
    private void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

    public String getNombre(){
        return this.nombre;
    }
    public HashMap<Integer , Socio> getSocios(){
        return this.socios;
    }
    public ArrayList<Libro> getLibros(){
        return this.libros;
    }
    
    public void agregarSocio(Socio p_socio){
        if(this.getSocios().containsValue(p_socio)){ 
            System.out.println("El socio ya se encuentra registrado.");
        }
        else{
            System.out.println("El socio fue registrado correctamente.");
            this.getSocios().put(p_socio.getDniSocio(), p_socio);
        }
    }

 
    public void quitarSocio(Socio p_socio) {
        if(this.getSocios().containsValue(p_socio)){ 
            System.out.println("Se dio de baja al socio: " + p_socio.getNombre());
            this.getSocios().remove(p_socio.getDniSocio(), p_socio);
        }
        else{
            System.out.println("El socio que desea eliminar no existe.");
        }
    }

 
    private void agregarLibro(Libro p_libro) {
            this.getLibros().add(p_libro);
            System.out.println("El libro fue agregado correctamente a la biblioteca.");
    }


    public void quitarLibro(Libro libroAEliminar) {
    boolean libroEncontrado = false;

    for (Libro libro : this.getLibros()) {
        if (libro.getTitulo().equals(libroAEliminar.getTitulo()) &&
            libro.getEdicion() == libroAEliminar.getEdicion() &&
            libro.getEditorial().equals(libroAEliminar.getEditorial()) &&
            libro.getAnio() == libroAEliminar.getAnio() &&
            !libro.prestado()) {

            this.getLibros().remove(libro);
            System.out.println("Libro eliminado correctamente");
            libroEncontrado = true;
            break;
        }
    }

    if (!libroEncontrado) {
        System.out.println("El libro que desea eliminar no se encuentra en la biblioteca.");
    }
}

    
    
    public boolean prestarLibro(Calendar p_FechaRetiro, Socio p_Socio, Libro p_libro) {

    // Verifica si el libro no está prestado
    if (!(p_libro.prestado())) {

        // Crea un nuevo préstamo
        Prestamo unPrestamo = new Prestamo(p_FechaRetiro, p_Socio, p_libro); //(1)

        // Obtiene el socio y el libro usando el HashMap
        Socio socioPrestar = this.getSocios().get(p_Socio.getDniSocio());
        Libro libroPrestar = this.getLibros().get(this.getLibros().indexOf(p_libro));

        if (socioPrestar != null && libroPrestar != null) {
            // Asocia el préstamo con el libro y el socio
            libroPrestar.agregarPrestamo(unPrestamo); //(2)  
            socioPrestar.agregarPrestamo(unPrestamo); //(3)

            return true;
        }
    }

    return false;
}



  
    
    public void devolverLibro(Libro p_Libro) {
    // Verificar si el libro está prestado (si tiene al menos un préstamo)
    if (!p_Libro.getPrestamos().isEmpty()) {
        // Obtener el último préstamo del libro
        Prestamo ultimoPrestamo = p_Libro.ultimoPrestamo();
        
        // Si el libro está prestado (es decir, tiene fecha de devolución nula)
        if (ultimoPrestamo.getFechaDevolucion() == null) {
            Calendar fechaActual = Calendar.getInstance();
            
            // Registrar la fecha de devolución
            ultimoPrestamo.registrarFechaDevolucion(fechaActual);
            System.out.println("Fecha de devolución registrada: " + fechaActual.getTime());  // Depuración
        } else {
            System.out.println("El libro ya ha sido devuelto.");
        }
    } else {
        System.out.println("Este libro no ha sido prestado.");
    }
}



    
    public int cantidadDeSociosPortipo(String p_Objeto){
        int contador = 0;

        for(Map.Entry<Integer, Socio> socio : this.getSocios().entrySet()){ 
            
            if(socio.getValue().soyDeLaClase().equalsIgnoreCase(p_Objeto)){
                contador++;
            }
        }

        return contador;
    }
    
    public ArrayList<Prestamo> prestamosVencidos() {
        ArrayList<Prestamo> prestamosVencidos = new ArrayList<>();

        for (Map.Entry<Integer, Socio> e : socios.entrySet()) {
            for (Prestamo i : e.getValue().getPrestamos()) {
                if (i.vencido(new GregorianCalendar())) {
                    prestamosVencidos.add(i);
                }
            }
        }
        return prestamosVencidos;
    }
    
    public ArrayList<Docente> docentesResponsables() {
        ArrayList<Docente> docentesResponsables = new ArrayList<>();

        for (Map.Entry<Integer, Socio> e : socios.entrySet()) {
            if (e.getValue().getClass() == Docente.class) {
                if (((Docente) e.getValue()).esResponsable()) {
                    docentesResponsables.add((Docente) e.getValue());
                }
            }
        }
        return docentesResponsables;
    }
    
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException {
        for (Libro libro : this.getLibros()) {
            if (libro.equals(p_libro)) {
                if (libro.prestado()) {
                    // Obtener el último préstamo del libro
                    Prestamo ultimoPrestamo = libro.ultimoPrestamo();
                    return ultimoPrestamo.getSocio().getNombre();
                } else {
                    throw new LibroNoPrestadoException("El libro se encuentra en la biblioteca");
                }
            }
        }
        return "El libro no se encuentra en la biblioteca";
    }

    public Socio buscarSocio(int p_dni) {
        return this.getSocios().get( new Integer(p_dni) );
    }
   
    
    public String listaDeSocios() {
        if (this.getSocios().size() == 0) {
            return "No hay socios en la biblioteca";
        } else {
            int nroSocio = 0;
            String listaSocios = "Lista de Socios: \n";
            for (Map.Entry<Integer, Socio> e : socios.entrySet()) {
                listaSocios += ++nroSocio + ")D.N.I.: " + e.getValue().getDniSocio() +
                " || " + e.getValue().getNombre() + " (" + e.getValue().soyDeLaClase() + ") || " +
                "Libros Prestados: " + e.getValue().cantLibrosPrestados() + "\n";
            }
            listaSocios += "******\n";
            listaSocios += "Cant. Socios tipo Estudiante: " + this.cantidadDeSociosPortipo("Estudiante") + "\n";
            listaSocios += "Cant. Socios tipo Docente: " + this.cantidadDeSociosPortipo("Docente") + "\n";
            listaSocios += "******";
            return listaSocios;
        }
    }
    
    public String listaDeLibros() {
        String libros ="";
        int i =0;
        for( Libro unLibro: this.getLibros() ) {
            libros = libros+ (++i) +"Titulo: "+ unLibro.getTitulo() +" || "+"Pestado: "+"("+ (unLibro.prestado()? "Si":"No") +")"+"\n";}
        return libros;
    }
    
    public String listaDeTitulos() {
        String titulos ="";
        HashSet<Libro> listaLibros = new HashSet<>( this.getLibros() );
    
        for( Libro unLibro: listaLibros ) {
            titulos = titulos + unLibro.getTitulo() +"\n" ;
        }
        return titulos;
    }
    
    public String listaDeDocentesResponsables() {
        String docentes= "";
        for( Docente unDocente: this.docentesResponsables() ) {
            
            docentes = docentes + "* D.N.I.: "+ unDocente.getDniSocio() +" || "
                                              + unDocente.getNombre() 
                                              +"("+ unDocente.soyDeLaClase() +")"+" || "
                                              +"Libros Prestados: "+ unDocente.cantLibrosPrestados()+"\n";
            
        }
        return docentes;
    }
    
    public void nuevoLibro(String p_Titulo, int p_Edicion, String p_Editorial, int p_Anio){
        this.agregarLibro(new Libro(p_Titulo, p_Edicion, p_Editorial, p_Anio));
    }


    public void nuevoSocioEstudiante(int p_DniSocio, String p_Nombre, String p_Carrera){
        this.agregarSocio(new Estudiante(p_DniSocio, p_Nombre, 0, p_Carrera ));
    }


    public void nuevoSocioDocente(int p_DniSocio, String p_Nombre, String p_Area){
        this.agregarSocio(new Docente(p_DniSocio, p_Nombre, 0, p_Area));
    }
}

