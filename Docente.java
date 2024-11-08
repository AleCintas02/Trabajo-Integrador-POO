import java.util.Calendar;

public class Docente extends Socio
{
    private String area;
    
    public Docente(int p_dniSocio, String p_nombre, int p_diasPrestamo, String p_area)
    {
       super(p_dniSocio, p_nombre, p_diasPrestamo);
       this.setArea(p_area);
    }
    
    private void setArea(String p_area){
        this.area=p_area;
    }
    
    public String getArea(){
        return this.area;
    }
    
    public String soyDeLaClase(){
        return "Docente";
    }
    
    public void cambiarDiasDePrestamos(int p_dias){
        this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
    }
    
    public boolean esResponsable(){
            Calendar fechaActual = Calendar.getInstance();
            
            for(Prestamo prestamo : this.getPrestamos()){
                Calendar fechaRetiro = prestamo.getFechaRetiro();
                long milisegundosDiferencia = fechaActual.getTimeInMillis() - fechaRetiro.getTimeInMillis();
                long diasDiferencia = milisegundosDiferencia / (24 * 60 * 60 * 1000);
                
                if(((this.getDiasPrestamo() - diasDiferencia) + 1) < 0){
                    return false;
                }
    
            }   
            return true;   
    }
    
    public boolean puedePedir(){
        return this.esResponsable();
    }
    
    
}
