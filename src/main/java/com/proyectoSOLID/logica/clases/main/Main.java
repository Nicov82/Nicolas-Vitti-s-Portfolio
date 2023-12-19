package com.proyectosolid.logica.clases.main;

import com.proyectosolid.logica.clases.clasesGestoras.*;

import com.proyectosolid.logica.clases.entidades.RegistroDeCompras;
import com.proyectosolid.logica.clases.entidades.RegistroLugaresCompra;
import com.proyectosolid.logica.clases.herramientasParaGestora.Calculadora;
import com.proyectosolid.logica.clases.lugaresCompra.*;
import com.proyectosolid.menu.MenuPrincipal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        //Creamos la gestora y todas sus dependencias correspondientes, estas ya no son creadas por ella misma, sino
        //que aplicamos inyección de dependencias para prevenir que sea una "clase todopoderosa", y facilitar a futuro
        //la extensibilidad de la aplicación (por ejemplo asignando otra visualizadora que sea en otro idioma).
        Gestora gestora = Gestora.getInstance();
        GestoraCompras gestoraCompras = GestoraCompras.getInstance();
        GestoraVisualizacion gestoraVisualizacion = GestoraVisualizacion.getInstance();
        RegistroDeCompras registroDeCompras = new RegistroDeCompras();
        RegistroLugaresCompra registroLugaresCompra = RegistroLugaresCompra.getInstance();
        Calculadora calculadora = Calculadora.getInstance();
        List<LugarCompra> listaLugaresCompra = new ArrayList<>(Arrays.asList(
                Argentina.getInstance(),
                new Exterior("Brasil", "R$", 68.99) //Creamos una isntancia de Exterior llamada Brasil. En la versión anterioe, cada país ocupaba una clase.
        ));

        List<LugarCompra> lugaresFreeShop = new ArrayList<>(Arrays.asList(
                FreeShop.getInstance(),
                new FreeShopExterior("Brasil")
        ));


        registroLugaresCompra.setListaLugaresCompra(listaLugaresCompra);
        registroLugaresCompra.setLugaresFreeShop(lugaresFreeShop);
        gestoraCompras.setCalculadora(calculadora);
        gestora.setGestoraCompras(gestoraCompras);
        gestora.setGestoraVisualizacion(gestoraVisualizacion);
        gestora.setRegistroDeCompras(registroDeCompras);
        gestora.setRegistroLugaresCompra(registroLugaresCompra);

        MenuPrincipal menu = MenuPrincipal.getInstance(gestora);
        menu.inicializarMenu();
        menu.saludoBienvenida();
        menu.mostrarMenuPrincipal();
    }
}