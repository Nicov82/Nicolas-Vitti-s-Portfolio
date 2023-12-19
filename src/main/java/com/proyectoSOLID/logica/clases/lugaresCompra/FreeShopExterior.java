package com.proyectosolid.logica.clases.lugaresCompra;

import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoImpuestoPais;

//Si bien todos los FreeShops que no sean de Argentina actúan exactamente igual y podría ser una opción
//no crear más y que todos los otros países usen el mismo objeto, el crear un objeto para cada país facilita
//que se pueda ver en el registro justamente EN QUÉ PAÍS estaba el Freeshop de la compra. Esto también podría
//lograrse convirtiendo toda la compra en String para el registro, pero eso dificultaría una hipotética
//(y deseable) futura función que permitiera corregir errores en las compras.
public final class FreeShopExterior extends FreeShop implements CalculoImpuestoPais {

    public FreeShopExterior(String nombre) {
        super();
        this.nombre = "FreeShop "+nombre;
        this.signoMonetario ="USD $";
    }


    @Override
    public double calcularCostoProducto(Producto producto) {

        double costoProducto;
        costoProducto = calcularConversionMoneda(producto.getPrecio());
        costoProducto = costoProducto + calcularImpuestoPais(costoProducto);
        //Truncar en dos decimales
        costoProducto = Math.floor(costoProducto* 100) / 100;
        producto.setPrecioEnPesos(costoProducto);
        return costoProducto;
    }

    @Override
    public double calcularConversionMoneda(double monto) {
        double cotizacionDolarOficial = 365.5; //Se calcula la conversión de dóñar a peso. El índice puede modificarse si cambiara la cotización
        double montoConvertido= monto * cotizacionDolarOficial;
        return montoConvertido;
    }
    @Override
    public double calcularImpuestoPais(double monto) {
        double tasaImpuestoPais = 0.3; //Se calcula el impuesto país. El índice podría cambiar si cambiara la tasa.
        double  impuestoPais = monto*tasaImpuestoPais;
        return impuestoPais;
    }

}
