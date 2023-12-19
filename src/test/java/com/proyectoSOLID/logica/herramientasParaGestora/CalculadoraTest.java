package com.proyectosolid.logica.herramientasParaGestora;
import static org.mockito.Mockito.*;
import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.clases.herramientasParaGestora.Calculadora;
import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraTest {

    private Calculadora calculadoraMock;
    private Compra compraTest;
    private LugarCompra lugarCompraTest;

    @BeforeEach
    void setUp() {
        calculadoraMock = mock(Calculadora.class);
        lugarCompraTest = mock(LugarCompra.class);
        compraTest = mock(Compra.class);


        List<Producto> listaProductos = new ArrayList<>();
        Producto producto1 = new Producto("Producto 1", 10.0, compraTest);
        Producto producto2 = new Producto("Producto 2", 20.0, compraTest);
        listaProductos.add(producto1);
        listaProductos.add(producto2);

        when(compraTest.getListaProductos()).thenReturn(listaProductos);
        when(compraTest.getLugarCompra()).thenReturn(lugarCompraTest);

    }


    @Test
    void testCalcularCostoCompra() { //Dado que ya fue probado con éxito el método CalcularCostoCompra en las subclases
        // de LugarCompra, acá sólo verificamos que ante una instancia de lugar Compra, la Calculadora llame a CalcularCostoCompra correctamente.
        doCallRealMethod().when(calculadoraMock).calcularCostoCompra(compraTest);
        doCallRealMethod().when(lugarCompraTest).calcularCostoCompra(compraTest);
        calculadoraMock.calcularCostoCompra(compraTest);
        verify(lugarCompraTest).calcularCostoCompra(compraTest);
    }


}
