package Modelo;

public class Televisor extends Electrodomestico {

    protected double pulgadas;
    protected tipoTelevisores tipo;

    public Televisor() {
        super();
    }

    public enum tipoTelevisores {
        PLASMA, LCD, LED, OLED
    }

    @Override
    public void setPrecio(double precioBase) {
        if (pulgadas > 40 && tipo == tipoTelevisores.PLASMA) {
            precioBase = precioBase * 1.10;
            this.precio = precioBase * 0.9;
        } else if (tipo == tipoTelevisores.PLASMA) {
            this.precio = precioBase * 0.9;
        } else if (pulgadas > 40) {
            this.precio = precioBase * 1.10;
        } else {
            this.precio = precioBase;
        }

    }

    @Override
    public String imprimirProducto() {
        String res = String.format("%-1s tipo de TV: %-8s con %-6f pulgadas",super.imprimirProducto(), this.tipo,this.pulgadas);
        return res;

    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public tipoTelevisores getTipo() {
        return tipo;
    }

    public void setTipo(tipoTelevisores tipo) {
        this.tipo = tipo;
    }

}
