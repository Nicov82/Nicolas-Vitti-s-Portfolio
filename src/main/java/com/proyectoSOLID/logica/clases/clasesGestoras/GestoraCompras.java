package com.proyectosolid.logica.clases.clasesGestoras;

import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.clases.herramientasParaGestora.Calculadora;
import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;
import com.proyectosolid.logica.interfaces.gestion.AltaProducto;
import com.proyectosolid.logica.interfaces.gestion.CalculoPrecioTotalCompras;
import com.proyectosolid.logica.interfaces.gestion.NuevaCompra;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoCostoCompra;

import java.util.List;
//Clase que centraliza los métodos de gestión de compras de la aplicación.
public class GestoraCompras implements NuevaCompra, AltaProducto, CalculoCostoCompra, CalculoPrecioTotalCompras {

    private static GestoraCompras instancia;
    private Calculadora calculadora;

    private GestoraCompras(){
    }

    public static GestoraCompras getInstance() { //Patrón Singleton para que haya una sola.
        if (instancia == null) {
            instancia = new GestoraCompras();
        }
        return instancia;
    }

    public Calculadora getCalculadora() {
        return calculadora;
    }

    public void setCalculadora(Calculadora calculadora) {
        this.calculadora = calculadora;
    }
    @Override
    public void iniciarCompra(List<Compra> listaCompras, LugarCompra lugarCompra) {
        Compra nuevaCompra = new Compra(lugarCompra);
        listaCompras.add(nuevaCompra);
    }
    @Override
    public void agregarProducto(String nombre, double precio, Compra compra) {
        try {
            if (precio < 0) {
                throw new IllegalArgumentException("El precio debe ser un número positivo");
            }

            Producto nuevoProducto = new Producto(nombre, precio, compra);
            compra.setPrecioTotal(compra.getPrecioTotal() + nuevoProducto.getPrecio());
            compra.getListaProductos().add(nuevoProducto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un número válido");
        }
    }
    @Override
    public double calcularCostoCompra(Compra compra) {
        if (this.getCalculadora() == null) {
            throw new IllegalStateException("La calculadora no está disponible. Agregar calculadora a GestoraCompras.");
        }

        return this.getCalculadora().calcularCostoCompra(compra);
    }

    @Override
    public void calcularPrecioTotalCompras(List<Compra> listaDeCompras) {
        if (listaDeCompras.isEmpty()) {
            System.out.println("Aún no se han realizado compras!");
            return;
        }
        double precioTotalCompras=0;
        for(Compra compra:listaDeCompras){
            precioTotalCompras = precioTotalCompras + compra.getPrecioTotalEnPesos();
        }
        precioTotalCompras = Math.floor(precioTotalCompras * 100) / 100;
        System.out.println("Precio Total Compras: ARS$"+precioTotalCompras);
    }

}
