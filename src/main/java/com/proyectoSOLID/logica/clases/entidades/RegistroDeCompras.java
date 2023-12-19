package com.proyectosolid.logica.clases.entidades;

import java.util.ArrayList;
import java.util.List;

//Clase para llevar registro de compras en una entidad aparte.
public class RegistroDeCompras { //No aplicamos Singleton, ya que en futuras actualizaciones podrían guardarse distitnos registros por viaje, mes, etc.

    private List<Compra> listaCompras;

    public RegistroDeCompras() {
        this.listaCompras = new ArrayList<>();
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    } //Método no usado, pero puede ser útil para futuros tests.


}
