package com.proyectoSOLID.logica.lugaresCompra;

import com.proyectoSOLID.logica.clases.entidades.*;
import com.proyectoSOLID.logica.clases.lugaresCompra.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExteriorComunTest {

    private  ExteriorComun  exteriorComunTest = new ExteriorComun() {

        @Override
        public double calcularConversionMoneda(double monto) {
            return monto; //Este arreglo es porque ExteriorComun no tiene conversion de moneda (es clase abstracta
            //sobreescribirlo así me permite probar todos los métodos acá y no en cada clase concreta que extienda de ExteriorComun.
            //(retorna el mismo monto que recibe justamente para no afectar el cálculo)
        }
    };

    private Producto producto1;

    @BeforeEach
    void setUp() {
        Compra compra = new Compra(exteriorComunTest);
        producto1 = new Producto("Producto1", 1.0, compra);
    }

    @Test
    void testCalcularCostoProducto() {
        producto1.setPrecio(1.0);

        double resultado = exteriorComunTest.calcularCostoProducto(producto1);

        assertEquals(1.75, resultado, 0.001);
        assertEquals(1.75, producto1.getPrecioEnPesos(), 0.001);
    }

    @Test
    void testCalcularCostoProductoPrecioAlto() {
        producto1.setPrecio(1_000_000.0);

        double resultado = exteriorComunTest.calcularCostoProducto(producto1);

        assertEquals(1_750_000.0, resultado, 0.001);
        assertEquals(1_750_000.0, producto1.getPrecioEnPesos(), 0.001);
    }

    @Test
    void testCalcularCostoProductoPrecioBajo() {
        producto1.setPrecio(0.01);

        double resultado = exteriorComunTest.calcularCostoProducto(producto1);

        assertEquals(0.0175, resultado, 0.001);
        assertEquals(0.0175, producto1.getPrecioEnPesos(), 0.001);
    }

    @Test
    void testCalcularImpuestoPais() {
        double resultado = exteriorComunTest.calcularImpuestoPais(1.0);

        assertEquals(0.3, resultado, 0.001);
    }

    @Test
    void testCalcularImpuestoPaisMontoGrande() {
        double resultado = exteriorComunTest.calcularImpuestoPais(1_000_000.0);

        assertEquals(300_000.0, resultado, 0.001);
    }

    @Test
    void testCalcularImpuestoPaisMontoPequeno() {
        double resultado = exteriorComunTest.calcularImpuestoPais(0.01);

        assertEquals(0.003, resultado, 0.001);
    }

    @Test
    void testCalcularRetencionMontoEstandar() {
        double resultado = exteriorComunTest.calcularRetencion(1.0);

        assertEquals(0.45, resultado, 0.001);
    }

    @Test
    void testCalcularRetencionMontoGrande() {
        double resultado = exteriorComunTest.calcularRetencion(1_000_000.0);

        assertEquals(450_000.0, resultado, 0.001);
    }

    @Test
    void testCalcularRetencionMontoPequeno() {
        double resultado = exteriorComunTest.calcularRetencion(0.01);

        assertEquals(0.0045, resultado, 0.001);
    }
}
