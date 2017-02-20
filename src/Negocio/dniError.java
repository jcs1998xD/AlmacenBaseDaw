package Negocio;

public class dniError extends RuntimeException {

    public dniError() {
        super("DNI mal formado");
    }
    
    public dniError(String mensaje){
        super(mensaje);
    }

}
