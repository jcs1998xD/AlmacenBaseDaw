package Negocio;

import Modelo.Cliente;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class NegociosService {

    private List<Producto> productos;
    private List<Cliente> clientes;
    private List<Venta> ventas;

    NegociosService() {
        productos = new ArrayList();
        clientes = new ArrayList();
        ventas = new ArrayList();
    }

    public void introducirProducto(Producto p) {

        try {
            productos.add(p);

        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el producto\n" + e.getMessage());
        }
    }

    public void introducirCliente(Cliente c) {
        try {
            clientes.add(c);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public int introducirVenta(int ncliente, int nproducto, String vend) throws Exception{
        Venta v = new Venta();
        Cliente clienteVenta;
        Producto productoVenta;
        
            clienteVenta = null;
            clienteVenta = buscarCliente(ncliente);

            productoVenta = null;
            productoVenta = buscarProducto(nproducto);
        
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

    public Producto buscarProducto(int np) throws Exception {
        Producto producto = null;
        boolean parar = false;
        for (int i = 0; i < productos.size() && parar == false; i++) {
            if (productos.get(i).getId() == np) {
                producto = productos.get(i);
                parar = true;
            }
        }

        if (producto == null) {
            throw new Exception("El producto no existe.");
        }
        return producto;
    }

    public Cliente buscarCliente(int numeroCliente) throws Exception {
        Cliente cliente = null;
        boolean parar = false;
        for (int i = 0; i < this.clientes.size() && parar == false; i++) {
            if (this.clientes.get(i).getIdCliente() == numeroCliente) {
                cliente = this.clientes.get(i);
                parar = true;
            }
        }
        if (cliente == null) {
            throw new Exception("Cliente no existe");
        }
        return cliente;
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

    public void elimninarProducto(int nproducto) throws RuntimeException {
        try {
            Producto productoEliminar = null;
            //Eliminamos de ventas el producto seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : ventas) {
                if (v.getProducto().getId() == nproducto) {
                    ventasEliminar.add(v);

                }
            }
            ventas.removeAll(ventasEliminar);

            //Eliminamos el producto
            for (Producto p : productos) {
                if (p.getId() == nproducto) {
                    productoEliminar = p;
                }
            }
            productos.remove(productoEliminar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar producto");
        }

    }

    public void eliminarCliente(int numCliente) {
        try {
            // Al eliminar un cliente también eliminamos las ventas asociadas a el

            //Eliminamos las ventas del cliente seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : ventas) {
                if (v.getCliente().getIdCliente() == numCliente) {
                    ventasEliminar.add(v);

                }
            }
            ventas.removeAll(ventasEliminar);

            //Eliminamos el cliente
            Cliente clienteBorrar = null;
            for (int i = 0; i < clientes.size() && clienteBorrar == null; i++) {
                if (clientes.get(i).getIdCliente() == numCliente) {
                    clienteBorrar = clientes.get(i);
                }
            }

            clientes.remove(clienteBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar cliente");
        }

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

    public String imprimirTodosProductos() {
        String res = "";
        if (productos.isEmpty()) {
            res = "No hay productos introducidos.";

        } else {
            for (Producto p : productos) {
                if (p instanceof Televisor) {
                    Televisor t = (Televisor) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  TAMAÑO   TIPO    PULGADAS" + "\n" + t.getId() + "   " + t.getNombre() + "   " + t.getPrecio() + t.getMarca() + "   " + t.getFabricante() + "   " + t.getTamanyo() + t.getTipo() + "  " + t.getPulgadas();

                }

                if (p instanceof Lavadora) {
                    Lavadora l = (Lavadora) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  REVOLUCIONES  CARGA" + "\n" + l.getId() + "   " + l.getNombre() + "   " + l.getPrecio() + "  " + l.getMarca() + "         " + l.getFabricante() + "   " + l.getTamanyo() + "     " + l.getRevoluciones() + "     " + l.getCarga();

                }

                if (p instanceof Mueble) {
                    Mueble m = (Mueble) p;
                    res += "\n ID NOMBRE  PRECIO     AÑO FABRICACION              MADERA  ESTILO" + "\n" + m.getId() + "   " + m.getNombre() + "   " + m.getPrecio() + "  " + m.getAnyoFab() + "   " + m.getTipoMadera() + "     " + m.getEstilo();

                }
            }
        }
        return res;
    }

    public String imprimirTodosClientes() {
        String res = "";
        if (clientes.isEmpty()) {
            res = "No hay clientes introducidos.";

        } else {
            for (Cliente c : clientes) {

                if (c instanceof Mayorista) {
                    Mayorista m = (Mayorista) c;
                    res += String.format("\n ID  NOMBRE   RAZONSOCIAL  CIF  TIPO    DESCUENTO \n  %-5d %-9s %-12s %-6s %-8s %-1s" ,m.getIdCliente(),m.getNombre(),m.getRazonSocial(),m.getCif(),m.getTipoMayorista(),m.getDescuento());   

                }
                if (c instanceof Particular) {
                    Particular p = (Particular) c;
                    res += String.format("\n ID  NOMBRE     RAZONSOCIAL       DNI \n  %-3d %-9s %-12s %-3s",p.getIdCliente(),p.getNombre(),p.getRazonSocial(),p.getDni());

                }
            }
        }
        return res;
    }

    public String imprimirtodasVentas() {
        String res = "";
        if (ventas.isEmpty()) {
            res = "No hay ventas introducidas.";

        } else {
            for (Venta v : ventas) {
                res += String.format("\n IDVENTA  VENDEDOR  CLIENTE   PRODUCTO  PRECIOVENTA \n    %-6d %-11s %-10d %-8d %-13s",v.getIdVenta(),v.getVendedor(),v.getCliente().getIdCliente(),v.getProducto().getId(),v.getPrecioVentaFormateado());       

            }
        }
        return res;
    }
}
