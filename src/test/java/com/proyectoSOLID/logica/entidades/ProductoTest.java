package com.proyectoSOLID.logica.entidades;

import com.proyectoSOLID.logica.clases.entidades.Compra;
import com.proyectoSOLID.logica.clases.entidades.Producto;
import com.proyectoSOLID.logica.clases.lugaresCompra.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductoTest {

    private final LugarCompra[] lugaresCompraArray = {
            new ArgentinaComun(),
            new ArgentinaFreeShop(),
            new Brasil(),
            new ExteriorFreeShop("Brasil")
    };

    @Test
    void testToString() {
        for (LugarCompra lugarCompra : lugaresCompraArray) {
            Compra compra = new Compra(lugarCompra);
            Producto producto = new Producto("Producto1", 10.0, compra);

            String expectedString = "Producto - Producto1, precio=" + lugarCompra.getSignoMonetario() + "10.0, precio convertido: ARS$0.0";
            String resultado = producto.toString();

            assertEquals(expectedString, resultado);
        }
    }
}
