package com.proyectosolid.menu;
import com.proyectosolid.logica.clases.clasesGestoras.Gestora;
import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;


import java.util.List;
import java.util.Scanner;
//Luego del refactoring, cambia y se simplifica el Menú Principal de la aplicación. En primer lugar,
//recibe una instancia de gestora en el constructor, ya no tiene que pasársela de método a método.
public class MenuPrincipal {
    private static MenuPrincipal instancia;
    private static final Scanner leer = new Scanner(System.in);
    private final Gestora gestora;

    //asignamos como atriburos las listas de la gestora para llamarlas más fácilmente y aumentar la claridad del código.
    private List<LugarCompra> listaLugaresCompra;
    private List<LugarCompra> listaFreeShops;
    private List<Compra> registroDeCompras;

    //Declaramos Strings de Errores de entrada para llamarlos más fácilmente y mejorar la claridad del código.
    private static final String entradaInvalida = "Entrada inválida. Ingrese un número.";
    private static final String opcionInvalida = "Opción inválida. Intente nuevamente.";


    private MenuPrincipal(Gestora gestora) {
        this.gestora = gestora;
    }

    public static MenuPrincipal getInstance(Gestora gestora){
        if(instancia==null){
            instancia = new MenuPrincipal(gestora);
        }
        return instancia;
    }
    public void inicializarMenu() { //Inyectamos al menú sus dependencias. Aseguramos que sus atributos sean los correctos.
        try {
            this.listaLugaresCompra = gestora.getRegistroLugaresCompra().getListaLugaresCompra();
            this.listaFreeShops = gestora.getRegistroLugaresCompra().getLugaresFreeShop();
            this.registroDeCompras = gestora.getRegistroDeCompras().getListaCompras();
        } catch (NullPointerException e) {
            System.err.println("Error: Alguno de los objetos necesarios no ha sido inicializado correctamente.");
            e.printStackTrace();
        }
    }

    public void saludoBienvenida() {
        System.out.println("Bienvenido a la Calculadora viajera Argentina!");
        System.out.println(" ");
    }

    public void mostrarMenuPrincipal() {
            int opcion;
        do {
            opcion=0;
            System.out.println("Por favor, elija una opción: ");
            System.out.println("1- Cargar nueva Compra");
            System.out.println("2- Ver compras anteriores");
            System.out.println("3- Agregar país");
            System.out.println("4- Salir");
            System.out.println(" ");


            if (leer.hasNextInt()) {
                opcion = leer.nextInt();

                switch (opcion) {
                    case 1 -> mostrarMenuElegirLugarCompra();
                    case 2 -> {
                        if (this.gestora.getRegistroDeCompras().getListaCompras().isEmpty()) {
                            System.out.println("No hay compras realizadas!");
                            System.out.println(" ");
                        } else {
                            gestora.verInfoListaCompras(registroDeCompras);
                            gestora.calcularPrecioTotalCompras(registroDeCompras);
                            System.out.println(" ");
                        }
                    }
                    case 3 -> mostrarMenuAgregarLugarCompra();
                    case 4 -> {
                        System.out.println("¡Hasta luego!");
                        System.out.println(" ");
                    }
                    default -> {
                        System.out.println(opcionInvalida);
                        System.out.println(" ");
                    }
                }
            } else {
                System.out.println(entradaInvalida);
                System.out.println(" ");
                leer.next();
            }
        } while (opcion != 4);
    }

    public void mostrarMenuElegirLugarCompra() {

        int opcion=0;
        int opcionFreeshop=0;

        do {
            System.out.println("Elija según donde haya realizado su compra:");
            for(int i=1; i<=listaLugaresCompra.size(); i++){
                System.out.print(i+"- ");
                System.out.println(listaLugaresCompra.get(i-1).getNombre());
            }

            if (leer.hasNextInt()) {
                opcion = leer.nextInt();

                if(opcion >= 1 && opcion <= listaLugaresCompra.size()){
                    do{
                        System.out.println("Elija según su compra haya sido o no realizada en un FreeShop");
                        System.out.println("1-Sí");
                        System.out.println("2-No");

                        if(leer.hasNextInt()){
                            opcionFreeshop = leer.nextInt();

                            if(opcionFreeshop<0 || opcionFreeshop>2){
                                System.out.println(opcionInvalida);
                            }

                        } else{
                            System.out.println(entradaInvalida);
                            leer.next();
                        }
                    }while(opcionFreeshop!=1 && opcionFreeshop!=2);

                        switch (opcionFreeshop) {
                            case 1 -> {
                                gestora.iniciarCompra(registroDeCompras, listaFreeShops.get(opcion - 1));
                                mostrarMenuAgregarProducto();
                            }
                            case 2 -> {
                                gestora.iniciarCompra(registroDeCompras, listaLugaresCompra.get(opcion - 1));
                                mostrarMenuAgregarProducto();
                            }
                            default -> System.out.println(opcionInvalida);
                        }


                } else {
                    System.out.println(opcionInvalida);
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                leer.next();
                System.out.println(" ");
            }
        } while (opcion < 1 || opcion > listaFreeShops.size());
    }

    public  void mostrarMenuAgregarLugarCompra(){
        int opcion=0;
        do{
            System.out.println("1-Agregar nuevo país a la lista.");
            System.out.println("2-Volver");
            if (leer.hasNextInt()){
                opcion = leer.nextInt();
                leer.nextLine();
                if(opcion<1 || opcion>2){
                    System.out.println(opcionInvalida);
                }
            } else{
                System.out.println(entradaInvalida);
                leer.next();
            }
        }while(opcion !=1 && opcion!=2);

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el nombre del país que desea agregar a la Lista:");
                    String nombrePais = leer.nextLine();
                    System.out.println("Ahora ingrese el signo monetario correspondiente al país a agregar:");
                    String signoMonetarioPais = leer.nextLine();
                    double valorEnPesosMonedaPais=0;


                    do {
                        System.out.println("Por último, ingrese el valor oficial EN PESOS ARGENTINOS de la moneda del país elegido:");

                        if (leer.hasNextDouble()) {
                            valorEnPesosMonedaPais = leer.nextDouble();

                            if (valorEnPesosMonedaPais <= 0) {
                                System.out.println("El valor de la moneda debe ser positivo!");
                            }
                        } else {
                            System.out.println(entradaInvalida);
                            leer.next(); // Limpiamos la entrada no válida
                        }

                    } while (valorEnPesosMonedaPais <= 0);

                    leer.nextLine();
                    gestora.agregarLugarCompra(nombrePais, signoMonetarioPais, valorEnPesosMonedaPais);
                    System.out.println(" ");
                    System.out.println(nombrePais + " ha sido agregado a la lista de países!");
                    mostrarMenuAgregarLugarCompra();
                }
                case 2 -> mostrarMenuPrincipal();
                default -> System.out.println(opcionInvalida);
            }

    }

    public  void mostrarMenuAgregarProducto() {
        String nombre;
        double precio=0;
        Compra compraActual = registroDeCompras.get(Gestora.getNumeroDeCompra());

        do {
            System.out.println("Ingrese el nombre del Producto comprado:");
            if (leer.hasNext()) {
                nombre = leer.next();
                System.out.println(" ");
                break;
            } else {
                System.out.println("Nombre inválido. Por favor, ingrese un texto.");
                System.out.println(" ");
                leer.next();
            }
        } while (true);

        do {
            System.out.println("Ingrese el precio del Producto comprado -en "+ compraActual.getLugarCompra().getSignoMonetario()+"-:");
            System.out.println(" ");

            if (leer.hasNextDouble()) {
                precio = leer.nextDouble();

                if(precio<0){
                    System.out.println("El precio debe ser positivo! vuelva a intentar.");
                }

            }else {
                System.out.println("Precio inválido. Por favor, ingrese un número.");
                System.out.println(" ");
                leer.next();
            }
        } while(precio<0);

        this.gestora.agregarProducto(nombre, precio, compraActual);
        String respuesta;

        do{
            System.out.println("¿Desea añadir otro producto? (S/N)");
            System.out.println(" ");
            respuesta = leer.next();

            switch (respuesta) {
                case ("S"), ("s") -> mostrarMenuAgregarProducto();
                case ("N"), ("n") -> {
                    this.gestora.calcularCostoCompra(compraActual);
                    this.gestora.verInfoCompra(compraActual);
                    System.out.println(" ");
                    mostrarMenuPrincipal();
                }
                default -> System.out.println(opcionInvalida);
            }
        }while(!respuesta.equalsIgnoreCase("s")&& !respuesta.equalsIgnoreCase("n"));

    }

}

