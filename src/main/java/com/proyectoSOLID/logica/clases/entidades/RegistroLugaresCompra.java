package com.proyectosolid.logica.clases.entidades;

import com.proyectosolid.logica.clases.lugaresCompra.*;
import com.proyectosolid.logica.interfaces.gestion.NuevoLugarCompra;

import java.util.ArrayList;

import java.util.List;

//Creamos esta clase para mover acá los Lugares de Compra y quitarle a la Fachada la responsabilidad de crearlos.
//En lugar de eso, la fachada simplemente tendrá como atributo esta clase.

public class RegistroLugaresCompra implements NuevoLugarCompra {

    private static RegistroLugaresCompra instancia;
    private List<LugarCompra> listaLugaresCompra;
    private List<LugarCompra> lugaresFreeShop; //Utilizamos listas distintas
    // para los Lugares de  Compra "comunes" y los FreeShops para facilitar el
    // llamarlos/crearlos "en paralelo" (cuando se agrega un país a la lista,
    //también se crea su FreeShop correspondiente en la otra.


    private RegistroLugaresCompra() {
        this.listaLugaresCompra = new ArrayList<>();
        this.lugaresFreeShop = new ArrayList<>();
    }

    public static RegistroLugaresCompra getInstance() { //Aplicamos Singleton para asegurar un solo Registro de Lugares.
        if (instancia == null) {
            instancia = new RegistroLugaresCompra();
        }
        return instancia;
    }


    public List<LugarCompra> getListaLugaresCompra() {
        return listaLugaresCompra;
    }

    public void setListaLugaresCompra(List<LugarCompra> listaLugaresCompra) {
        this.listaLugaresCompra = listaLugaresCompra;
    }

    public List<LugarCompra> getLugaresFreeShop() {
        return lugaresFreeShop;
    }

    public void setLugaresFreeShop(List<LugarCompra> lugaresFreeShop) {
        this.lugaresFreeShop = lugaresFreeShop;
    }


    @Override
    public void agregarLugarCompra(String nombre, String signoMonetario, double valorEnPesosMoneda) {
        Exterior nuevoLugarCompra = new Exterior(nombre, signoMonetario, valorEnPesosMoneda);
        this.getListaLugaresCompra().add(nuevoLugarCompra);
        FreeShopExterior nuevoFreeshop = new FreeShopExterior(nombre);
        this.getLugaresFreeShop().add(nuevoFreeshop);
    }
}
