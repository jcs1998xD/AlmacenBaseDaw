package Modelo;

import java.text.DecimalFormat;

public class Venta {

    private int idVenta;
    private String vendedor;
    private Cliente cliente;
    private Producto producto;
    private static int numVentas = 0;
    private double precioVenta;

    public Venta() {
        numVentas++;
        this.idVenta = numVentas;

    }

   
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public static int getNumVentas() {
        return numVentas;
    }

    public static void setNumVentas(int numVentas) {
        Venta.numVentas = numVentas;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

  

    public double getPrecioVenta() {
        return precioVenta;
    }
    
    public String getPrecioVentaFormateado(){
        DecimalFormat patron = new DecimalFormat("00.00€");
        return patron.format(precioVenta);
    }

    public void setPrecioVenta() {
        Double precio = producto.getPrecio();
        if (cliente instanceof Mayorista) {
            Mayorista m = (Mayorista) cliente;
            precio = precio - (precio * m.getDescuento()/100);
        }
        this.precioVenta = precio;
    }
    
      public String imprimirVenta() {
        String res = null;
        res = String.format("ID de venta: %-3d  vendedor: %-1s \n           Producto: \n%-1s",
                this.getIdVenta(),this.getVendedor(),this.producto.imprimirProducto());
                  
        return res;
    }

}
