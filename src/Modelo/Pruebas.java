package Modelo;

import Negocio.MenuPrincipal;
import java.util.Scanner;

public class Pruebas {

    public static void main(String[] args) {
        //Producto P1 = new Televisor(); --> Al haberlo crado como producto.
        // Televisor t = (Televisor) P1; --> lo tenemos que convertir a tipo televisor para poder ver los metodos de la clase televisor.

        Televisor.tipoTelevisores m = null;
        int opcion;
        Scanner sc = new Scanner(System.in);
        Televisor.tipoTelevisores[] televisores = new Televisor.tipoTelevisores[Televisor.tipoTelevisores.values().length];

        do {
            System.out.println("Introduzca el tipo de Televisor: Escribe(1,2,3,4)");
            int contador = 0;

            for (Televisor.tipoTelevisores televisor : Televisor.tipoTelevisores.values()) {
                contador++;
                System.out.println(contador + " " + televisor);
                televisores[contador] = televisor;
            }

            opcion = Integer.parseInt(sc.nextLine());

        } while (opcion < 0 || opcion > Televisor.tipoTelevisores.values().length);

        m = televisores[opcion];

        System.out.println(m);

    }

}
