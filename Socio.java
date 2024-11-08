
import java.util.ArrayList;

public abstract class Socio{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;
    

    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }
    

    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
    }
    
    private void setDniSocio(int p_dniSocio){
        this.dniSocio = p_dniSocio;
    }
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    public void setDiasPrestamo(int p_dias){
        this.diasPrestamo = p_dias;
    }
    public void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    
    public int getDniSocio(){return dniSocio;}
    public String getNombre(){return nombre;}
    public int getDiasPrestamo() {return diasPrestamo;}
    public ArrayList<Prestamo> getPrestamos(){return prestamos;}
    

    public void agregarPrestamo(Prestamo p_prestamo){
        this.getPrestamos().add(p_prestamo);
    }

    public void quitarPrestamo(Prestamo p_prestamo){
        if(this.getPrestamos().size() > 0){
            if(this.getPrestamos().remove(p_prestamo)){
                System.out.println("Prestamo eliminado correctamente.");
            }
            else{
                System.out.println("El prestamo que desea eliminar no existe.");
            }
        }
        else{
            System.out.println("No hay prestamos para eliminar.");
        }
    }
    
    /**Otra opcion por si te gusta*/
    /*public int cantLibrosPrestados(){
        int cantLibrosPrestados = 0;
        
        for(Prestamo prestamo : this.getPrestamos()){
            if(prestamo.getLibro().prestado()){
                cantLibrosPrestados++;
            }
        }
        
        return cantLibrosPrestados;
    }*/
    
    public int cantLibrosPrestados(){
        int prestados = 0;
        for (Prestamo prestamo:this.getPrestamos()){
            if (prestamo.getFechaDevolucion() == null){
                prestados++;
            }            
        }
        return prestados;
    }
    
  
    public String toString(){
        if(this.soyDeLaClase() == "Docente"){
            return "D.N.I: " + this.getDniSocio() + " || " + this.getNombre() + "(" + this.soyDeLaClase() + ")" + " || Libros Prestados: "+ this.cantLibrosPrestados();
        }
        else{
            return "D.N.I: " + this.getDniSocio() + " || " + this.getNombre() + "(" + this.soyDeLaClase() + ")" + " || Libros Prestados: "+ this.cantLibrosPrestados();
        }
    }
    
   
    /*
       public boolean puedePedir(){
        
        boolean pedir = true;
        for (int i =0; pedir && i < this.getPrestamos().size();i++){
            Prestamo unPrestamo = this.getPrestamos().get(i);
            if(unPrestamo.getFechaDevolucion()==null){
                if(unPrestamo.vencido(unPrestamo.getFechaDevolucion())){
                    pedir=false;
                }
            }
        }
        return pedir;
    }
       */
    
    /**Metodo modificado (no probe si el tuyo funciona)*/
    public boolean puedePedir(){
        int prestamosVencidos = 0;
        
        for(Prestamo prestamo : this.getPrestamos()){
            if(prestamo.vencido(prestamo.getFechaDevolucion())){
                prestamosVencidos++;
            }
        }
                
        return prestamosVencidos == 0 ? true : false;
    }

    public abstract String soyDeLaClase();
}