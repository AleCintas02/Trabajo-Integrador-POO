import java.util.ArrayList;
import java.util.Calendar;

public class Estudiante extends Socio
{
    private String carrera;
   
    public Estudiante(int p_dniSocio, String p_nombre, int p_diasPrestamo, String p_carrera)
    {
        super(p_dniSocio, p_nombre, p_diasPrestamo);
        this.setCarrera(p_carrera);
    }
    
    private void setCarrera(String p_carrera){
        this.carrera = p_carrera;
    }
    
    public String getCarrera(){
        return this.carrera;
    }
    
    public boolean puedePedir() {
        Calendar fechaHoy = Calendar.getInstance();
        
        for (Prestamo prestamo : this.getPrestamos()) {
            long diferenciaMilis = fechaHoy.getTimeInMillis() - prestamo.getFechaRetiro().getTimeInMillis();
            long diasDiferencia = diferenciaMilis / (1000 * 60 * 60 * 24);
            
            if (diasDiferencia >= 20) {
                return false;
            }
        }
    
        return this.cantLibrosPrestados() <= 3; 
    }
    
    public String soyDeLaClase(){
        return "Estudiante";
    }

    
    

    
}
