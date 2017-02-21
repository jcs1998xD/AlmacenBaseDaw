package Negocio;

import Modelo.Cliente;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.TipoMayorista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MenuPrincipal {

    NegociosService servicio;

    MenuPrincipal() {
        servicio = new NegociosService();
    }

    private void introducirDatos() {
        DateTimeFormatter patron = DateTimeFormatter.ofPattern("dd/MM/yy");
        // Introducir Cliente
        Cliente c = new Particular();
        c.setNombre("Alfredo");
        servicio.introducirCliente(c);
        // Introducir mueble
        Mueble mueble = new Mueble();
        mueble.setPrecio(100);
        mueble.setNombre("TeleHD");
        String fecha = "15/04/12";
        LocalDate fecha1 = LocalDate.parse(fecha, patron);
        mueble.setAnyoFab(fecha1);
        mueble.setTipoMadera(Mueble.Madera.HAYA);
        mueble.setEstilo("estilos");
        servicio.introducirProducto(mueble);
    }

    public void inciarAplicacion() {
        introducirDatos();

        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println();
                System.out.println("1.Productos");
                System.out.println("2.Clientes");
                System.out.println("3.Ventas");
                System.out.println("0. Para Salir");
                System.out.print("Introduzca la opcion: ");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                System.out.println();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println();
                System.out.println("1.Introducir Producto");
                System.out.println("2.Dar de baja un producto");
                System.out.println("3.Imprimir los datos de un producto");
                System.out.println("4.Imprimir todos los productos");
                System.out.println("0. Salir del menu");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();
                System.out.println();
                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    servicio.introducirProducto(p);
                    System.out.println("Producto añadido");
                    System.out.println("La id del producto es " + p.getId());
                }
                if (opcionProductos == 2) {
                    System.out.print("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    servicio.elimninarProducto(num);
                    System.out.println("Producto eliminado");
                }
                if (opcionProductos == 3) {
                    System.out.print("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    System.out.println(servicio.buscarProducto(nprod).imprimirProducto());
                }
                if (opcionProductos == 4) {
                    System.out.println(servicio.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida " + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;
        do {
            System.out.print("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.print("Introduzca precio base: ");
            precio = Double.parseDouble(sc.nextLine());

            System.out.println("Introduzca el tipo de producto: Escriba(1,2,3)");
            System.out.println("1. Mueble");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisor");
            opcion = sc.nextInt();
            if (opcion == 1) {
                producto = pedirMueble();
            }
            if (opcion == 2) {
                producto = pedirLavadora();
            }
            if (opcion == 3) {
                producto = pedirTelevisor();
            }
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return producto;
    }

    public Mueble pedirMueble() throws ParseException {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);

        m.setTipoMadera(pedirMadera());

        System.out.print("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());

        try {
            System.out.println("Introduzca la fecha de fabricación como (04(dia)-agosto-17(año)): ");
            m.setAnyoFab(this.validarFecha(sc.nextLine()));
        } catch (FormatoFechaErroneo e) {
            System.out.println(e.getMessage());
        }

        return m;

    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();

        System.out.print("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.print("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        return l;
    }

    public Televisor pedirTelevisor() {
        Televisor tv = new Televisor();
        Scanner sc = new Scanner(System.in);
        boolean error = false;

        do {
            try {
                error = false;
                System.out.print("Introduzca las pulgadas: ");
                double pulgadas = Double.parseDouble(sc.nextLine());
                tv.setPulgadas(pulgadas);
            } catch (Exception e) {
                System.out.println("Inserte las pulgadas separadas con un punto(.) no coma(,)");
                error = true;
            }
        } while (error == true);

        tv.setTipo(pedirTipoTelevisor());

        return tv;
    }

    private Televisor.tipoTelevisores pedirTipoTelevisor() {
        Televisor.tipoTelevisores m = null;
        int opcion;
        Scanner sc = new Scanner(System.in);
        Televisor.tipoTelevisores[] televisores = null;
        
        do {
            System.out.println("Introduzca el tipo de Televisor: Escribe(1,2,3,4)");
            int contador = 0;
            
            for (Televisor.tipoTelevisores televisor : Televisor.tipoTelevisores.values()) {
                contador++;
                System.out.println(contador+" "+televisor);
                televisores[contador] = televisor;
            }

            opcion = Integer.parseInt(sc.nextLine());

        } while (opcion < 0 || opcion > Televisor.tipoTelevisores.values().length);

            m = televisores[opcion];
        

        return m;
    }

    private Mueble.Madera pedirMadera() {
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Madera");
            System.out.println("1.Pino");
            System.out.println("2.Roble");
            System.out.println("3.Haya");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        return m;
    }

    private void introducirCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digame su nombre: ");
        String nombre = sc.nextLine();
        boolean repetir;
        do {
            repetir = false;
            System.out.println("Usted es mayorista o particular?");
            String tipo = sc.nextLine();
            if (tipo.equalsIgnoreCase("mayorista")) {
                crearMayorista(nombre);
            } else if (tipo.equalsIgnoreCase("particular")) {
                crearParticular(nombre);
            } else {
                repetir = true;
            }
        } while (repetir);
    }

    private void crearParticular(String nombre) {
        Scanner sc = new Scanner(System.in);
        String dni = null;
        boolean repetir;
        do {
            repetir = false;
            try {
                System.out.print("Digame su DNI: ");
                dni = sc.nextLine();
                dni = comprobar(dni);
            } catch (dniError e) {
                System.out.println(e.getMessage());
                repetir = true;
            }
        } while (repetir);
        Particular c = new Particular();
        c.setNombre(nombre);
        c.setDni(dni);
        servicio.introducirCliente(c);
        System.out.println("Su número de cliente es: " + c.getIdCliente());
    }

    private String comprobar(String dni) throws dniError {
        String dni1 = null;

        if (dni.length() == 8) {
            dni1 = dni.concat(Character.toString(calcularLetraDNI(dni)));
            System.out.println("Dni introducido: " + dni1);
        } else if (dni.length() == 9) {
            if (Character.isDigit(dni.charAt(8))) {
                throw new dniError();
            }
            dni1 = dni.substring(0, 8).concat(Character.toString(calcularLetraDNI(dni)));
            if (!dni.equalsIgnoreCase(dni1)) {
                System.out.println("Introdujo mal la letra del dni, dni correcto: " + dni1);
            }
        } else {
            throw new dniError();
        }

        return dni1;
    }

    private char calcularLetraDNI(String dni) throws dniError {
        try {
            String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKE";
            int numero = Integer.parseInt(dni.substring(0, 8));
            int resto = numero % 23;
            char letra = juegoCaracteres.charAt(resto);
            return letra;
        } catch (NumberFormatException e) {
            throw new dniError();
        }
    }

    private void crearMayorista(String nombre) {
        Scanner sc = new Scanner(System.in);
        String cif;
        TipoMayorista tipoMayorista = null;
        double descuento;
        System.out.print("Digame su CIF: ");
        cif = sc.nextLine();
        String respuestaTipoMayorista;
        do {
            System.out.println("Digame el tipo de mayorista que es: (1 o 2) ");
            System.out.println("1. Grandes Superficies ");
            System.out.println("2. Tienda");
            respuestaTipoMayorista = sc.nextLine();
            if (respuestaTipoMayorista.equals("1")) {
                tipoMayorista = TipoMayorista.GRAN_SUPERFICIE;
            }
            if (respuestaTipoMayorista.equals("2")) {
                tipoMayorista = TipoMayorista.TIENDA;
            }
        } while (!respuestaTipoMayorista.equals("1") && !respuestaTipoMayorista.equals("2"));
        System.out.print("Digame su descuento: ");
        String descuentoo = sc.nextLine();
        descuento = Double.parseDouble(descuentoo) / 100;
        Mayorista c = new Mayorista();
        c.setNombre(nombre);
        c.setCif(cif);
        c.setDescuento(descuento);
        c.setTipoMayorista(tipoMayorista);
        servicio.introducirCliente(c);
        System.out.println("Su número de cliente es: " + c.getIdCliente());
    }

    private void bajaCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digame el num del cliente que quieres borrar: ");
        String numeroo = sc.nextLine();
        int numero = Integer.parseInt(numeroo);
        try {
            servicio.eliminarCliente(numero);
            System.out.println("Cliente eliminado con éxito");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void imprimirCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digame el num del cliente: ");
        String nume = sc.nextLine();
        int num = Integer.parseInt(nume);
        try {
            System.out.println(servicio.buscarCliente(num).imprimir());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);

        try {
            int opcionClientes = -1;
            do {
                System.out.println();
                System.out.println("1.Introducir Cliente");
                System.out.println("2.Dar de baja un cliente");
                System.out.println("3.Imprimir los datos de un cliente");
                System.out.println("4.Imprimir todos los clientes");
                System.out.println("0. Salir del menu");

                opcionClientes = sc.nextInt();
                System.out.println();
                if (opcionClientes == 1) {
                    introducirCliente();
                }
                if (opcionClientes == 2) {
                    bajaCliente();
                }
                if (opcionClientes == 3) {
                    imprimirCliente();
                }
                if (opcionClientes == 4) {
                    System.out.println(servicio.imprimirTodosClientes());
                }

            } while (opcionClientes != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuClientes();
        }

    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println();
                System.out.println("1.Introducir Venta");
                System.out.println("2.Dar de baja una venta");
                System.out.println("3.Imprimir los datos de una venta");
                System.out.println("4.Imprimir todas las ventas");
                System.out.println("0. Salir del menu");
                opcionVentas = sc.nextLine();
                System.out.println();
                if (opcionVentas.equals("1")) {
                    pedirIntroducirCliente();
                }
                if (opcionVentas.equals("2")) {
                    System.out.print("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    servicio.eliminarVenta(nv);
                    System.out.println("Venta eliminada");
                }
                if (opcionVentas.equals("3")) {
                    System.out.print("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println(servicio.buscarVenta(nv).imprimirVenta());
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(servicio.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuVentas();
        }

    }

    private void pedirIntroducirCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el número de cliente: ");
        int nv = Integer.parseInt(sc.nextLine());
        System.out.print("Introduce el número de producto: ");
        int np = Integer.parseInt(sc.nextLine());
        System.out.print("Introduce el nombre del vendedor: ");
        String v = sc.nextLine();
        int añadido = 0;
        try {
        añadido = servicio.introducirVenta(nv, np, v);
        System.out.println("Venta añadida");
        System.out.println("Id de venta " + añadido);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private LocalDate validarFecha(String fecha) throws FormatoFechaErroneo {
        LocalDate respuesta = null;
        DateTimeFormatter patron = DateTimeFormatter.ofPattern("dd-MMMM-yy");
        try {
            LocalDate fec = LocalDate.parse(fecha, patron);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaErroneo();
        }
        return respuesta;
    }

}
