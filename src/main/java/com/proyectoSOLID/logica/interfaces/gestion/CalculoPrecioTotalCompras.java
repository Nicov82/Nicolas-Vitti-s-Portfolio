package com.proyectosolid.logica.interfaces.gestion;

import com.proyectosolid.logica.clases.entidades.Compra;

import java.util.List;

public interface CalculoPrecioTotalCompras {
    void calcularPrecioTotalCompras(List<Compra> listaDeCompras);
}
