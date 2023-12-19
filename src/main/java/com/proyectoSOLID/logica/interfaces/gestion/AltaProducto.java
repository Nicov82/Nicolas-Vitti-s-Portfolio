package com.proyectosolid.logica.interfaces.gestion;

import com.proyectosolid.logica.clases.entidades.Compra;

public interface AltaProducto {
    void agregarProducto(String nombre, double precio, Compra compra);
}
