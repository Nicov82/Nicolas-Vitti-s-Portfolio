package com.proyectosolid.logica.lugaresCompra;
import com.proyectosolid.logica.clases.entidades.*;
import com.proyectosolid.logica.clases.lugaresCompra.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FreeShopTest {

    private final FreeShop FreeShopTest = mock(FreeShop.class);

    private Producto producto1;


    @BeforeEach
    void setUp() {
        Compra compra = new Compra(FreeShopTest);
        producto1 = new Producto("Producto1",1.0,compra);
        compra.getListaProductos().add(producto1);
        doCallRealMethod().when(FreeShopTest).calcularConversionMoneda(anyDouble());

    }

    @Test
    void testCalcularCostoProducto() {
        doCallRealMethod().when(FreeShopTest).calcularCostoProducto(any(Producto.class));

        double resultado = FreeShopTest.calcularCostoProducto(producto1);

        // Verificación
        assertEquals(365.5, resultado); // El precio del producto no cambia en este lugar de compra
        assertEquals(365.5, producto1.getPrecioEnPesos()); // El precio en pesos del producto
    }

    @Test
    void testCalcularCostoProductoConPreciosAltos() {

        producto1.setPrecio(1_000_000.0); // Precio muy alto

        doCallRealMethod().when(FreeShopTest).calcularCostoProducto(any(Producto.class));


        double resultado = FreeShopTest.calcularCostoProducto(producto1);

        assertEquals(365_500_000.0, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(365_500_000.0, producto1.getPrecioEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }

    @Test
    void testCalcularCostoProductoConPreciosBajos() {
        producto1.setPrecio(0.01); // Precio muy bajo

        doCallRealMethod().when(FreeShopTest).calcularCostoProducto(any(Producto.class));


        double resultado = FreeShopTest.calcularCostoProducto(producto1);

        assertEquals(3.65, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(3.65, producto1.getPrecioEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }



    @Test
    void testCalcularConversionMoneda() {
        double monto = 1.0;
        double resultado = FreeShopTest.calcularConversionMoneda(monto);
        assertEquals(365.5, resultado);
    }

    @Test
    void testCalcularConversionMonedaConMontoPequeno() {
        double montoPequeno = 0.01;
        double resultadoPequeno = FreeShopTest.calcularConversionMoneda(montoPequeno);
        assertEquals(3.655, resultadoPequeno, 0.001); // Tolerancia de 0.001 para redondeos
    }

    @Test
    void testCalcularConversionMonedaConMontoGrande() {
        double montoGrande = 1000000;
        double resultadoGrande = FreeShopTest.calcularConversionMoneda(montoGrande);
        assertEquals(365500000, resultadoGrande);
    }




}
