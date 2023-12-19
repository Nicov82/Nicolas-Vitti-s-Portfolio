package com.proyectosolid.logica.interfaces.gestion;

import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;

import java.util.List;

public interface NuevaCompra {
    void iniciarCompra(List<Compra> listaCompras, LugarCompra lugarCompra);
}
