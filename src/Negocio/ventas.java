package Negocio;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class ventas {

    private List<Venta> ventas;
    private clientes clientes;
    private productos productos;
    
    ventas() {
        this.ventas = new ArrayList<>();
    }

    public void setventasClientes(clientes lista){
        this.clientes = lista;
    }
    public void setventasProductos(productos lista){
        this.productos = lista;
    }
    
    public int introducirVenta(int ncliente, int nproducto, String vend) throws Exception {
        Venta v = new Venta();
        Cliente clienteVenta;
        Producto productoVenta;

        clienteVenta = null;
        clienteVenta = this.clientes.buscarCliente(ncliente);

        productoVenta = null;
        productoVenta = this.productos.buscarProducto(nproducto);

        try {
            v.setCliente(clienteVenta);
            v.setVendedor(vend);
            v.setProducto(productoVenta);
            v.setPrecioVenta(); //calcula el precio de la venta segun el cliente-mayorista

            ventas.add(v);

            clienteVenta.getCompras().add(v);
            productoVenta.getVentas().add(v);

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible introducir la venta" + e.getMessage());
        }
        return v.getIdVenta();
    }

    public Venta buscarVenta(int nv) throws Exception {
        Venta venta = null;
        try {
            boolean parar = false;
            for (int i = 0; i < ventas.size() && parar == false; i++) {
                if (ventas.get(i).getIdVenta() == nv) {
                    venta = ventas.get(i);
                    parar = true;
                }
            }
            if (venta == null) {
                throw new Exception("La venta con id: " + nv + " no existe");
            }
//            System.out.println(venta.imprimirVenta());
        } catch (Exception e) {
            throw new Exception("No ha sido posible imprimir la venta" + e.getMessage());
        }
        return venta;
    }

    public void eliminarVenta(int nv) {

        try {

            Venta ventaBorrar = null;
            for (int i = 0; i < ventas.size() && ventaBorrar != null; i++) {

                if (ventas.get(i).getIdVenta() == nv) {
                    ventaBorrar = ventas.get(i);

                }
            }
            if (ventaBorrar == null) {
                // hacemos saltar una excepcion que nos lleva directamente al catch
                throw new Exception("No existe ninguna venta con ese Id");
            }
            // este código solo se ejecuta si todo va bien
            ventas.remove(ventaBorrar);
            System.out.println("La venta se ha removido con exito");

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar la venta");
        }

    }

    public String imprimirtodasVentas() {
        String res = "";
        if (ventas.isEmpty()) {
            res = "No hay ventas introducidas.";

        } else {
            for (Venta v : ventas) {
                res += String.format("\n IDVENTA  VENDEDOR  CLIENTE   PRODUCTO  PRECIOVENTA \n    %-6d %-11s %-10d %-8d %-13s", v.getIdVenta(), v.getVendedor(), v.getCliente().getIdCliente(), v.getProducto().getId(), v.getPrecioVentaFormateado());

            }
        }
        return res;
    }

}
