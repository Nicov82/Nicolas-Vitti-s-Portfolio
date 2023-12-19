package com.proyectosolid.logica.entidades;

import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.lugaresCompra.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CompraTest { //Para testear que funcione el constructor asignacndo nombres sucesivos automáticos a las compras
                            // Y también que funcione el toString() con distintos signos moentarios según el lugar de compra
    private int contadorCompras = 0; // Variable de contador para que cada Test arranque en Compra 1
    private final LugarCompra[] lugaresCompraArray = {
            Argentina.getInstance(),
            FreeShop.getInstance(),
            new Exterior("Brasil", "R$", 68.99 ),
            new FreeShopExterior("")
    };

    @BeforeEach
    public void setUp() {
        contadorCompras = 0; // Reinicia el contador antes de cada prueba
    }

    @Test
    void testConstructorBucle() {
        int i = 1;
        while (i <= 5) {
            contadorCompras++; // Incrementa el contador
            LugarCompra lugarCompra = lugaresCompraArray[i % lugaresCompraArray.length];
            Compra compra = new Compra(lugarCompra);

            assertNotNull(compra);
            assertEquals("Compra " + contadorCompras, compra.getNombre());
            assertEquals(lugarCompra, compra.getLugarCompra());
            assertEquals(0.0, compra.getPrecioTotal());
            assertEquals(0.0, compra.getPrecioTotalEnPesos());
            i++;
        }
    }

    @Test
    void testToString() {
        for (LugarCompra lugarCompra : lugaresCompraArray) {
            contadorCompras++; // Incrementa el contador
            Compra compra = new Compra(lugarCompra);

            String expectedString = compra.getNombre() +
                    " - Lugar: " + lugarCompra.getNombre() +
                    " - Precio: " + lugarCompra.getSignoMonetario() + compra.getPrecioTotal() +
                    " - Precio convertido: ARS$" + compra.getPrecioTotalEnPesos();

            assertEquals(expectedString, compra.toString());
        }
    }
}
