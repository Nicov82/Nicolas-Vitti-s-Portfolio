package com.proyectosolid.logica.clasesGestoras;
import com.proyectosolid.logica.clases.clasesGestoras.*;
import com.proyectosolid.logica.clases.entidades.*;
import com.proyectosolid.logica.clases.herramientasParaGestora.*;
import com.proyectosolid.logica.clases.lugaresCompra.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GestoraComprasTest {

    private GestoraCompras gestoraMock;
    private Calculadora calculadoraMock;
    private Compra compraTest;

    private LugarCompra lugarCompraMock;

    @BeforeEach
    void setUp() {
        calculadoraMock = mock(Calculadora.class);
        gestoraMock = mock(GestoraCompras.class);
         lugarCompraMock = mock(LugarCompra.class);
        compraTest = new Compra (lugarCompraMock);
        when(gestoraMock.getCalculadora()).thenReturn(calculadoraMock);
    }

    @Test
    void testIniciarCompra() {
        List<Compra> listaCompras = new ArrayList<>();
        doCallRealMethod().when(gestoraMock).iniciarCompra(listaCompras,lugarCompraMock);
        // Llamamos al método que queremos probar
        gestoraMock.iniciarCompra(listaCompras, compraTest.getLugarCompra());
        // Verificamos que se haya agregado una compra a la lista de compras;
        Assertions.assertFalse(listaCompras.isEmpty());
    }

    @Test
    void testAgregarProducto(){
    doCallRealMethod().when(gestoraMock).agregarProducto("Producto1", 1.0,compraTest);
        gestoraMock.agregarProducto("Producto1", 1.0, compraTest);
        Assertions.assertSame("Producto1", compraTest.getListaProductos().get(0).getNombre());
        //Verificamos que el producto se haya agregado al primer índice de la lista de productos de la compra.
        Assertions.assertTrue((compraTest.getListaProductos().get(0).getPrecio()==1.0));
    }

    @Test
    void testCalcularCostoCompra(){ //Acá no hace falta verificar montos, porque ya testeamos el método en Calculadora
        //Simplemente comprobamos que llamándolo en la Gestora de Compras, se llama al de la calculadora.
        doCallRealMethod().when(gestoraMock).calcularCostoCompra(compraTest);
        gestoraMock.calcularCostoCompra(compraTest);
        verify(calculadoraMock).calcularCostoCompra(compraTest);
    }

}
