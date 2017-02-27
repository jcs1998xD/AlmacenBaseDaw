package Negocio;

import Modelo.Cliente;
import Modelo.Mayorista;
import Modelo.Particular;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class clientes {

    private List<Cliente> clientes;
    private ventas ventas;

    clientes() {
        clientes = new ArrayList<>();
    }
    
    public void setclientesVentas(ventas lista){
        this.ventas = lista;
    }

    public void introducirCliente(Cliente c) {
        try {
            clientes.add(c);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

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

    public void eliminarCliente(int numCliente) {
        try {
            // Al eliminar un cliente también eliminamos las ventas asociadas a el

            //Eliminamos las ventas del cliente seleccionado
            
            ventas.eliminarVentasCliente(numCliente);

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

    public String imprimirTodosClientes() {
        String res = "";
        if (clientes.isEmpty()) {
            res = "No hay clientes introducidos.";

        } else {
            for (Cliente c : clientes) {

                if (c instanceof Mayorista) {
                    Mayorista m = (Mayorista) c;
                    res += String.format("\n ID  NOMBRE   RAZONSOCIAL  CIF  TIPO    DESCUENTO \n  %-5d %-9s %-12s %-6s %-8s %-1s", m.getIdCliente(), m.getNombre(), m.getRazonSocial(), m.getCif(), m.getTipoMayorista(), m.getDescuento());

                }
                if (c instanceof Particular) {
                    Particular p = (Particular) c;
                    res += String.format("\n ID  NOMBRE     RAZONSOCIAL       DNI \n  %-3d %-9s %-12s %-3s", p.getIdCliente(), p.getNombre(), p.getRazonSocial(), p.getDni());

                }
            }
        }
        return res;
    }

}
