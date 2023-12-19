package com.proyectosolid.logica.clases.clasesGestoras;

import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.interfaces.gestion.DisplayInfoCompra;
import com.proyectosolid.logica.interfaces.gestion.DisplayInfoListaCompras;

import java.util.List;

//Clase que se ocupa de implementaciones relativas a la visualización de datos en pantalla.
public class GestoraVisualizacion implements DisplayInfoCompra, DisplayInfoListaCompras {

    private static GestoraVisualizacion instancia;

    private GestoraVisualizacion(){
    }
    public static GestoraVisualizacion getInstance(){ //Patrón singleton para que haya una sola gestora de visualización.
        if (instancia == null) {
            instancia = new GestoraVisualizacion();
        }
        return instancia;
    }
    @Override
    public void verInfoCompra(Compra compra) {
        if (compra.getListaProductos().isEmpty()) {
            System.out.println("La compra aún no contiene productos.");
            return;
        }
        System.out.println(compra);
        for (Producto producto : compra.getListaProductos()){
            System.out.println(producto);
        }
    }


    @Override
    public void verInfoListaCompras(List listaCompras) {
        if (listaCompras.isEmpty()) {
            System.out.println("Aún no se han realizado compras!");
            return;
        }
        for(Object compra :listaCompras){
                verInfoCompra((Compra) compra);
                System.out.println(" ");
        }

    }

}
