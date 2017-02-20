
package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;



public abstract  class Producto {
    protected int id;
    protected String nombre;
    protected double precio;
    protected List<Venta> ventas;
    protected static int contador=1; 
    
    public Producto(){
        ventas= new ArrayList();
        this.id=contador;
        contador++;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public int getId() {
        return id;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    
    public String getPrecioFormateado(){
        DecimalFormat patron = new DecimalFormat("00.00€");
        return patron.format(precio);
    }

    
    
    public abstract void setPrecio(double precioBase);
    
    
    
    public String imprimirProducto(){
        String res = String.format("El id es: %-3d precio: %-8s nombre: %-9s ",this.id,this.getPrecioFormateado(),this.nombre);
        return res;
    }
    
    
    public static String imprimirNumProductos(){
        
        String res = String.format("El número de productos es: %-4d",+contador);
        return res;
    }

        
    }
    
    

