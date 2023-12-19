package com.proyectosolid.logica.lugaresCompra;

import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;
import com.proyectosolid.logica.clases.entidades.Compra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LugarCompraTest {

    private LugarCompra lugarCompra;
    private Compra compra;
    private Producto producto1;
    private Producto producto2;

    @BeforeEach
    void setUp() {
        lugarCompra = new LugarCompra() {

        };

        compra = new Compra(lugarCompra);
        producto1 = new Producto("Producto1", 1.0, compra);
        producto2 = new Producto("Producto2", 2.0, compra);

        compra.getListaProductos().add(producto1);
        compra.getListaProductos().add(producto2);
    }

    @Test
    void testCalcularCostoCompra() {
        double resultado = lugarCompra.calcularCostoCompra(compra);
        assertEquals(3.0, resultado); // La suma de los precios de los productos
        assertEquals(3.0, compra.getPrecioTotalEnPesos()); // El precio total de la compra
    }
    @Test
    void testCalcularCostoCompraConPreciosAltos() {
        producto1.setPrecio(1_000_000.0); // Precio de un millón
        producto2.setPrecio(2_000_000.0); // Precio de dos millones

        double resultado = lugarCompra.calcularCostoCompra(compra);

        assertEquals(3_000_000.0, resultado);
        assertEquals(3_000_000.0, compra.getPrecioTotalEnPesos());
    }

    @Test
    void testCalcularCostoCompraConPreciosBajos() {
        producto1.setPrecio(0.01); // Precio muy bajo
        producto2.setPrecio(0.02); // Precio muy bajo

        double resultado = lugarCompra.calcularCostoCompra(compra);

        assertEquals(0.03, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(0.03, compra.getPrecioTotalEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }

    @Test
    void testCalcularCostoCompraVacia() {
        compra.getListaProductos().clear(); // Elimina todos los productos de la compra

        double resultado = lugarCompra.calcularCostoCompra(compra);

        assertEquals(0.0, resultado);
        assertEquals(0.0, compra.getPrecioTotalEnPesos());
    }
    @Test
    void testCalcularCostoProducto() {
        double resultado1 = lugarCompra.calcularCostoProducto(producto1);
        double resultado2 = lugarCompra.calcularCostoProducto(producto2);

        assertEquals(1.0, resultado1); // El precio del producto no cambia en este lugar de compra
        assertEquals(2.0, resultado2); // El precio del producto no cambia en este lugar de compra
        assertEquals(1.0, producto1.getPrecioEnPesos()); // Verificar que el precio en pesos de producto1 es 1.0
        assertEquals(2.0, producto2.getPrecioEnPesos());
    }

    @Test
    void testCalcularCostoProductoConPreciosAltos() {
        producto1.setPrecio(1_000_000.0); // Precio muy alto

        double resultado = lugarCompra.calcularCostoProducto(producto1);

        assertEquals(1_000_000.0, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(1_000_000.0, producto1.getPrecioEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }

    @Test
    void testCalcularCostoProductoConPreciosBajos() {
        producto1.setPrecio(0.01); // Precio muy bajo

        double resultado = lugarCompra.calcularCostoProducto(producto1);

        assertEquals(0.01, resultado, 0.001); // Verificar con tolerancia de 0.001 para posibles errores de redondeo
        assertEquals(0.01, producto1.getPrecioEnPesos(), 0.001); // Verificar con tolerancia de 0.001
    }
}