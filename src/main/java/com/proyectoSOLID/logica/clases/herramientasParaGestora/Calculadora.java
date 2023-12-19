package com.proyectosolid.logica.clases.herramientasParaGestora;

import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoCostoCompra;

//Esta clase se ocupa de, utilizando el patrón Strategy, recibir objetos Compra y llamar a la implementación de los métodos correspondiente,
//la cual dependerá del atributo LugarCompra.
public class Calculadora implements CalculoCostoCompra {
    private static Calculadora instancia;

    private Calculadora(){
    }

    public static Calculadora getInstance() { //Aplicamos Singleton para asegurar una sola calculadora.
        if (instancia == null) {
            instancia = new Calculadora();
        }
        return instancia;
    }

    @Override
    public double calcularCostoCompra(Compra compra) {
        double costoCompra;
        costoCompra = compra.getLugarCompra().calcularCostoCompra(compra);
        return costoCompra;
    }
}
