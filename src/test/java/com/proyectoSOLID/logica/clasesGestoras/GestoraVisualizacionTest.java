package com.proyectosolid.logica.clasesGestoras;

import static org.mockito.Mockito.*;

import com.proyectosolid.logica.clases.clasesGestoras.GestoraVisualizacion;
import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GestoraVisualizacionTest {

    private GestoraVisualizacion gestoraVisualizacionMock;

    @BeforeEach
    void setUp() {
        gestoraVisualizacionMock = mock(GestoraVisualizacion.class);
    }

    @Test
    void testVerInfoCompra() {
        // Crea un mock de Compra
        Compra compraMock = mock(Compra.class);
        List<Producto> listaProductos = new ArrayList<>();
        Producto productoMock = mock(Producto.class);
        listaProductos.add(productoMock);
        when(compraMock.getListaProductos()).thenReturn(listaProductos);
        doCallRealMethod().when(gestoraVisualizacionMock).verInfoCompra(compraMock);

        gestoraVisualizacionMock.verInfoCompra(compraMock);

        verify(gestoraVisualizacionMock).verInfoCompra(compraMock);
    }

    @Test
    void testVerInfoListaCompras() {
        List<Compra> listaCompras = new ArrayList<>();
        Compra compraMock = mock(Compra.class);
        listaCompras.add(compraMock);

        doCallRealMethod().when(gestoraVisualizacionMock).verInfoListaCompras(listaCompras);

        gestoraVisualizacionMock.verInfoListaCompras(listaCompras);

        verify(gestoraVisualizacionMock).verInfoListaCompras(listaCompras);
    }
}
