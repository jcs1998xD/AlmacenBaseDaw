
package Negocio;


public class FormatoFechaErroneo extends RuntimeException{
    public FormatoFechaErroneo(){
    super("Error formato fecha");
    }
    
    public FormatoFechaErroneo(String mensaje){
        super(mensaje);
    }

}
