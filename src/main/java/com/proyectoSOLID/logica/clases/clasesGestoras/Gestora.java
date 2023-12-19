package com.proyectosolid.logica.clases.clasesGestoras;

import com.proyectosolid.logica.clases.entidades.*;
import com.proyectosolid.logica.clases.lugaresCompra.*;
import com.proyectosolid.logica.interfaces.gestion.*;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoCostoCompra;

import java.util.List;


//Clase que centraliza todos los métodos de gestión de la aplicación (llamando a las Gestoras de Compras y Visualización que a su vez implementan los métodos). Es una implementación del patrón Facade.
public class Gestora implements NuevaCompra, AltaProducto, CalculoCostoCompra, DisplayInfoCompra, DisplayInfoListaCompras, CalculoPrecioTotalCompras, NuevoLugarCompra { //Esta clase Centraliza TODA la App (no sé s hacia falta una capa más de abstracción pero habiendo llegado hasta acá elegí profundizar.
    private static Gestora instancia;
    private static int numeroDeCompra =-1; //Variable para asignarle a cada compra como nombre "Compra n" secuancialmente.
    private GestoraCompras gestoraCompras;
    private GestoraVisualizacion gestoraVisualizacion;
    private RegistroDeCompras registroDeCompras;
    private RegistroLugaresCompra registroLugaresCompra;


    private Gestora() {
    }

    public static Gestora getInstance(){ //Patrón Singleton para crear una sola gestora y tener punto de acceso a ella.
        if (instancia == null){
            instancia = new Gestora();
        }
        return instancia;
    }

    public RegistroLugaresCompra getRegistroLugaresCompra() {
        return registroLugaresCompra;
    }

    public void setRegistroLugaresCompra(RegistroLugaresCompra registroLugaresCompra) {
        this.registroLugaresCompra = registroLugaresCompra;
    }



    public RegistroDeCompras getRegistroDeCompras() {
        return registroDeCompras;
    }

    public void setRegistroDeCompras(RegistroDeCompras registroDeCompras) {
        this.registroDeCompras = registroDeCompras;
    }

    public static int getNumeroDeCompra() {
        return numeroDeCompra;
    }

    public GestoraVisualizacion getGestoraVisualizacion() {
        return gestoraVisualizacion;
    }

    public void setGestoraVisualizacion(GestoraVisualizacion gestoraVisualizacion) {
        this.gestoraVisualizacion = gestoraVisualizacion;
    }

    public GestoraCompras getGestoraCompras() {
        return gestoraCompras;
    }

    public void setGestoraCompras(GestoraCompras gestoraCompras) {
        this.gestoraCompras = gestoraCompras;
    }

    @Override
    public void iniciarCompra(List<Compra> listaCompras, LugarCompra lugarCompra) {
        if (this.getGestoraCompras() == null) {
            throw new IllegalStateException("La gestora de compras no está disponible. No se puede iniciar una nueva compra.");
        }
        numeroDeCompra++;
        this.getGestoraCompras().iniciarCompra(listaCompras, lugarCompra);
    }

    @Override
    public void agregarProducto(String nombre, double precio, Compra compra) {
        if (this.getGestoraCompras() == null) {
            throw new IllegalStateException("La gestora de compras no está disponible. No se puede agregar un producto a la compra.");
        }
        this.getGestoraCompras().agregarProducto(nombre, precio, compra);
    }

    @Override
    public double calcularCostoCompra(Compra compra) {
        if (this.getGestoraCompras() == null) {
            throw new IllegalStateException("La gestora de compras no está disponible. No se puede calcular el costo de la compra.");
        }
        return this.getGestoraCompras().calcularCostoCompra(compra);
    }


    @Override
    public void verInfoCompra(Compra compra) {
        if (this.getGestoraVisualizacion() == null) {
            throw new IllegalStateException("La gestora de visualización no está disponible. No se puede ver la información de la compra.");
        }
        this.getGestoraVisualizacion().verInfoCompra(compra);
    }

    @Override
    public void verInfoListaCompras(List<Compra> listaDeCompras) {
        if (this.getGestoraVisualizacion() == null) {
            throw new IllegalStateException("La gestora de visualización no está disponible. No se puede ver la información de la lista de compras.");
        }
        if (this.getRegistroDeCompras() == null) {
            throw new IllegalStateException("El registro de compras no está disponible. No se puede ver la información de la lista de compras.");
        }
        this.getGestoraVisualizacion().verInfoListaCompras(listaDeCompras);
    }

    @Override
    public void calcularPrecioTotalCompras(List<Compra> listaDeCompras) {
        if (this.getGestoraCompras() == null) {
            throw new IllegalStateException("La gestora de compras no está disponible. No se puede calcular el precio total de las compras.");
        }
        if (this.getRegistroDeCompras() == null) {
            throw new IllegalStateException("El registro de compras no está disponible. No se puede calcular el precio total de las compras.");
        }
        this.getGestoraCompras().calcularPrecioTotalCompras(listaDeCompras);
    }

    //Nuevo método para agregar países a la lista de Lugares de Compra (agrega automáticamente su FreeShop correspondiente).
    @Override
    public void agregarLugarCompra(String nombre, String signoMonetario, double valorEnPesosMoneda) {
        this.getRegistroLugaresCompra().agregarLugarCompra(nombre, signoMonetario, valorEnPesosMoneda);
    }
}
