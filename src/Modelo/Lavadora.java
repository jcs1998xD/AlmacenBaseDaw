package Modelo;

public class Lavadora extends Electrodomestico {

    int revoluciones;
    double carga;

    public Lavadora() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        if (revoluciones > 500 && carga < 8) {
            precioBase = precioBase * 1.10;
            this.precio = precioBase * 0.85;
        } else if (carga < 8) {
            this.precio = precioBase * 0.85;
        } else if (revoluciones > 500) {
            this.precio = precioBase * 1.10;
        } else {
            this.precio = precioBase;
        }

    }

    public int getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(int revoluciones) {
        this.revoluciones = revoluciones;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "   revoluciones: " + this.revoluciones + "   carga: " + this.carga;
        return res;
    }

}
