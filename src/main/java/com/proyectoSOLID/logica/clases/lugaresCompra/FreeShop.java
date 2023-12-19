package com.proyectosolid.logica.clases.lugaresCompra;

import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoConversionMoneda;

public class FreeShop extends LugarCompra implements CalculoConversionMoneda {

    private static FreeShop instancia;

    protected FreeShop() {
        this.nombre = " Free shop Argentina";
        this.signoMonetario = "USD $";
    }

    public static FreeShop getInstance(){
        if(instancia == null){
            instancia = new FreeShop();
        }
        return instancia;
    }



    @Override
    public double calcularCostoProducto(Producto producto) {
        double costoProducto;
        costoProducto = calcularConversionMoneda(producto.getPrecio());
        //Truncar en dos decimales
        costoProducto = Math.floor(costoProducto* 100) / 100;
        producto.setPrecioEnPesos(costoProducto);
        return costoProducto;
    }

    @Override
    public double calcularConversionMoneda(double monto) {
        double cotizacionDolarOficial = 365.5;
        double montoConvertido= monto * cotizacionDolarOficial; //Warning indica redundancia, pero creo que le da orden y claridad.
        return montoConvertido;
    }


}
