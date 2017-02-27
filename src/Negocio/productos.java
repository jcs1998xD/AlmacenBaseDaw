package Negocio;

import Modelo.Lavadora;
import Modelo.Mueble;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class productos {

    private List<Producto> productos;
    private ventas ventas;

    productos() {
        productos = new ArrayList<>();
    }

    public void setproductosVentas(ventas lista) {
        ventas = lista;
    }

    public void introducirProducto(Producto p) {
        try {
            productos.add(p);
        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el producto\n" + e.getMessage());
        }
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

 

    public void elimninarProducto(int nproducto) throws RuntimeException {
        try {
            Producto productoEliminar = null;
            //Eliminamos de ventas el producto seleccionado

            ventas.eliminarVentasProducto(nproducto);

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

}
