package com.proyectosolid.logica.herramientasParaGestora;

import static org.mockito.Mockito.*;
import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.clases.clasesGestoras.GestoraVisualizacion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GestoraVisualizacionTest {
    GestoraVisualizacion gestoraVisualizacionMock = mock(GestoraVisualizacion.class);
    Compra compraMock = mock(Compra.class);
    List<Producto> listaProductos = new ArrayList<>();
    Producto productoMock = mock(Producto.class);
    List<Compra> listaCompras = new ArrayList<>();

    @Test
    void testVerInfoCompra() {
        listaProductos.add(productoMock);
        doCallRealMethod().when(gestoraVisualizacionMock).verInfoCompra(any(Compra.class));
        gestoraVisualizacionMock.verInfoCompra(compraMock);
        verify(compraMock).getListaProductos();
    }
    @Test
    void testVerInfoListaCompras() {
        listaCompras.add(compraMock);

        GestoraVisualizacion visualizadoraMock = mock(GestoraVisualizacion.class);

        doCallRealMethod().when(visualizadoraMock).verInfoListaCompras(anyList());

        visualizadoraMock.verInfoListaCompras(listaCompras);

        verify(visualizadoraMock).verInfoCompra(any(Compra.class));
    }
}
