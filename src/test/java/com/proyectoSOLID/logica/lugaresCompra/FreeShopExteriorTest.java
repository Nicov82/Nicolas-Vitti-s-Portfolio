package com.proyectosolid.logica.lugaresCompra;
import com.proyectosolid.logica.clases.entidades.*;
import com.proyectosolid.logica.clases.lugaresCompra.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class FreeShopExteriorTest {

    private final FreeShopExterior FreeShopExtTest = new FreeShopExterior("Brasil");

    private Producto producto1;


    @BeforeEach
    void setUp() {
        Compra compra = new Compra(FreeShopExtTest);
        producto1 = new Producto("Producto1", 1.0, compra);
    }

    @Test
    void testCalcularCostoProducto() {
        double resultado = FreeShopExtTest.calcularCostoProducto(producto1);

        // Verificación
        assertEquals(475.15, resultado); // El precio del producto no cambia en este lugar de compra
        assertEquals(475.15, producto1.getPrecioEnPesos()); // El precio en pesos del producto
    }

    @Test
    void testCalcularCostoProductoConPreciosAltos() {

        producto1.setPrecio(1_000_000.0); // Precio muy alto

        double resultado = FreeShopExtTest.calcularCostoProducto(producto1);

        assertEquals(475_150_000.0, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(475_150_000.0, producto1.getPrecioEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }
    @Test
    void testCalcularCostoProductoConPreciosBajos() {
        producto1.setPrecio(0.01); // Precio muy bajo

        double resultado = FreeShopExtTest.calcularCostoProducto(producto1);

        assertEquals(4.75, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(4.75, producto1.getPrecioEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }

    @Test
    void testCalcularConversionMoneda() {
        double monto = 1.0;
        double resultado = FreeShopExtTest.calcularConversionMoneda(monto);
        assertEquals(365.5, resultado);
    }

    @Test
    void testCalcularConversionMonedaConMontoPequeno() {
        double montoPequeno = 0.01;
        double resultadoPequeno = FreeShopExtTest.calcularConversionMoneda(montoPequeno);
        assertEquals(3.655, resultadoPequeno, 0.001); // Tolerancia de 0.001 para redondeos
    }

    @Test
    void testCalcularConversionMonedaConMontoGrande() {
        double montoGrande = 1000000;
        double resultadoGrande = FreeShopExtTest.calcularConversionMoneda(montoGrande);
        assertEquals(365500000, resultadoGrande);
    }

    @Test
    void testCalcularImpuestoPais(){
        double monto =1.0;
        double resultado = FreeShopExtTest.calcularImpuestoPais(monto);
        assertEquals(0.3, resultado);
    }

    @Test
    void testCalcularImpuestoPaisMontoPequeno() {
        double montoPequeno = 0.01;
        double resultadoPequeno = FreeShopExtTest.calcularImpuestoPais(montoPequeno);
        assertEquals(0.003, resultadoPequeno, 0.001); // Prueba con tolerancia de 0.001 para posibles errores de redondeo
    }

    @Test
    void testCalcularImpuestoPaisMontoGrande() {
        double montoGrande = 1000000;
        double resultadoGrande = FreeShopExtTest.calcularImpuestoPais(montoGrande);
        assertEquals(300000.0, resultadoGrande);
    }

}
