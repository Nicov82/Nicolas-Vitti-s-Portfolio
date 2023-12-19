package com.proyectosolid.logica.clases.lugaresCompra;
import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoConversionMoneda;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoImpuestoPais;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoRetencion;

//Luego del refactoreo, esta clase no es abstracta, y permite fácilmente incorporar nuevos páíses,
//ya que su constructor permite asignarle nombre, signo monetario y tipo de cambio.
public class Exterior extends LugarCompra implements CalculoConversionMoneda, CalculoImpuestoPais, CalculoRetencion{

    private double valorEnPesosMoneda; //Este campo NO debe ser final, ya que la cotización es cambiante. A futuro la APP mediante una API debería actualizar el valor de la moneda (por la constante fluctuación del peso argentino)

    public double getValorEnPesosMoneda() {
        return valorEnPesosMoneda;
    }

    public Exterior(String nombre, String signoMonetario, double valorEnPesosMoneda) {
        this.nombre = nombre;
        this.signoMonetario = signoMonetario;
        this.valorEnPesosMoneda = valorEnPesosMoneda;
    }
    @Override
    public double calcularCostoProducto(Producto producto) {

        double costoProducto;
        costoProducto = calcularConversionMoneda(producto.getPrecio());
        costoProducto = costoProducto + calcularImpuestoPais(costoProducto) + calcularRetencion(costoProducto);
        //Truncar en dos decimales
        costoProducto = Math.floor(costoProducto* 100) / 100;
        producto.setPrecioEnPesos(costoProducto);
        return costoProducto;
    }

    @Override
    public double calcularImpuestoPais(double monto) { //
        double tasaImpuestoPais = 0.3;
        double  impuestoPais = monto*tasaImpuestoPais; //Warning indica redundancia, pero creo que le da orden y claridad.
        return impuestoPais;
    }

    @Override
    public double calcularRetencion(double monto) {
        double tasaRetencion = 0.45;
        double retencion = monto*tasaRetencion; //Warning indica redundancia, pero creo que le da orden y claridad.
        return retencion;
    }

    @Override
    public double calcularConversionMoneda(double monto) {
        double cotizacionMonedaOficial = this.getValorEnPesosMoneda();
        double montoConvertido= monto * cotizacionMonedaOficial; //El warning me sugiere que es una variable redundante pero yo creo que le da claridad y orden al código
        return montoConvertido;
    }

}
