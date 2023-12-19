package com.proyectosolid.logica.clases.lugaresCompra;

import com.proyectosolid.logica.clases.entidades.Compra;
import com.proyectosolid.logica.clases.entidades.Producto;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoCostoCompra;
import com.proyectosolid.logica.interfaces.lugaresCompra.CalculoCostoProducto;


public abstract class LugarCompra implements CalculoCostoCompra, CalculoCostoProducto {
    protected String nombre;
    protected String signoMonetario;

    public LugarCompra() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSignoMonetario() {
        return signoMonetario;
    }

    public void setSignoMonetario(String signoMonetario) {
        this.signoMonetario = signoMonetario;
    } //Método no usado, pero serviría en una hipótetica actualizacion que permitiera al usuario crear su propio Lugar de Compra personalizado.

    @Override
    public double calcularCostoCompra(Compra compra){
        double costoCompra=0;
        for(Producto producto:compra.getListaProductos()){
            costoCompra= costoCompra + calcularCostoProducto(producto);
        }
        costoCompra = Math.floor(costoCompra* 100) / 100;
        compra.setPrecioTotalEnPesos(costoCompra);
        return costoCompra;
    }

    @Override
    public double calcularCostoProducto(Producto producto){
        double costoProducto = producto.getPrecio();
        //Truncar en dos decimales
        costoProducto = Math.floor(costoProducto* 100) / 100;
        producto.setPrecioEnPesos(costoProducto);
        return costoProducto;
    }
}
