package com.proyectosolid.logica.clasesGestoras;

import com.proyectosolid.logica.clases.clasesGestoras.Gestora;
import com.proyectosolid.logica.clases.clasesGestoras.GestoraCompras;
import com.proyectosolid.logica.clases.clasesGestoras.GestoraVisualizacion;
import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.RegistroDeCompras;
import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GestoraTest { //En esta clase testeamos que cada método llame a su vez a los métodos de las gestoras de compras y visualizacion

    private Gestora gestoraMock;
    private GestoraCompras gestoraComprasMock;
    private GestoraVisualizacion gestoraVisualizacionMock;
    private RegistroDeCompras registroDeComprasMock;
    private LugarCompra lugarCompraTest;
    private Compra compraTest;

    private List<Compra> listaComprasTest;

    @BeforeEach
    void setUp(){
        gestoraMock = mock(Gestora.class);
        gestoraComprasMock = mock(GestoraCompras.class);
        gestoraVisualizacionMock = mock(GestoraVisualizacion.class);
        registroDeComprasMock = mock(RegistroDeCompras.class);
        lugarCompraTest = new LugarCompra() {
        };
        compraTest = new Compra(lugarCompraTest);
        listaComprasTest = new ArrayList<>();
        listaComprasTest.add(compraTest);
        when(gestoraMock.getRegistroDeCompras()).thenReturn(registroDeComprasMock);
        when(registroDeComprasMock.getListaCompras()).thenReturn(listaComprasTest);
        when(gestoraMock.getGestoraVisualizacion()).thenReturn(gestoraVisualizacionMock);
        when(gestoraMock.getGestoraCompras()).thenReturn(gestoraComprasMock);
    }

    @Test
    void testIniciarCompra(){
        doCallRealMethod().when(gestoraMock).iniciarCompra(anyList(), any(LugarCompra.class));
        gestoraMock.iniciarCompra(gestoraMock.getRegistroDeCompras().getListaCompras(), lugarCompraTest);
        verify(gestoraMock.getGestoraCompras()).iniciarCompra(gestoraMock.getRegistroDeCompras().getListaCompras(), lugarCompraTest);
    }

    @Test
    void testAgregarProducto(){
        String nombre = "Producto1";
        double precio = 1.0;
        doCallRealMethod().when(gestoraMock).agregarProducto(nombre, precio, compraTest);
        gestoraMock.agregarProducto(nombre, precio, compraTest);
        verify(gestoraMock.getGestoraCompras()).agregarProducto(nombre, precio, compraTest);
    }

    @Test
    void testCalcularCostoCompra(){
        doCallRealMethod().when(gestoraMock).calcularCostoCompra(compraTest);
        gestoraMock.calcularCostoCompra(compraTest);
        verify(gestoraMock.getGestoraCompras()).calcularCostoCompra(compraTest);
    }

    @Test
    void testVerInfoCompra(){
        doCallRealMethod().when(gestoraMock).verInfoCompra(compraTest);
        gestoraMock.verInfoCompra(compraTest);
        verify(gestoraMock.getGestoraVisualizacion()).verInfoCompra(compraTest);

    }

    @Test
    void testVerInfoListaCompras(){
        doCallRealMethod().when(gestoraMock).verInfoListaCompras(anyList());
        gestoraMock.verInfoListaCompras(registroDeComprasMock.getListaCompras());
        verify(gestoraMock.getGestoraVisualizacion()).verInfoListaCompras(gestoraMock.getRegistroDeCompras().getListaCompras());

    }

    @Test
    void testGetRegistroDeCompras() {
        RegistroDeCompras resultado = gestoraMock.getRegistroDeCompras();
        assertEquals(registroDeComprasMock, resultado);
    }

    @Test
    void testCalcularPrecioTotalCompras() {
        doCallRealMethod().when(gestoraMock).calcularPrecioTotalCompras(anyList());
        gestoraMock.calcularPrecioTotalCompras(gestoraMock.getRegistroDeCompras().getListaCompras());
        verify(gestoraMock.getGestoraCompras()).calcularPrecioTotalCompras(gestoraMock.getRegistroDeCompras().getListaCompras());
    }
}

