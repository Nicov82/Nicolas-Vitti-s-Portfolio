package com.proyectosolid.logica.clases.entidades;

//Clase que almacena datos de los productos

public class Producto {
    private String nombre;
    private double precio;
    private double precioEnPesos;
   private Compra compra; //Hacer una relación ManyToOne Producto/compra facilita colocar el signo monetario en el toString () (llamás al getSignoMonetario de la Compra)-.



    public Producto(String nombre, double precio, Compra compra) {
        this.nombre = nombre;
        this.precio = Math.floor(precio * 100) / 100;
        this.compra = compra;
    }

    public double getPrecioEnPesos() {
        return precioEnPesos;
    }

    public void setPrecioEnPesos(double precioEnPesos) {
        // Truncar a dos decimales
        this.precioEnPesos = Math.floor(precioEnPesos * 100) / 100;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        // Truncar a dos decimales
        this.precio = Math.floor(precio * 100) / 100;
    }
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "Producto - " +
                nombre +
                ", precio=" + this.compra.getLugarCompra().getSignoMonetario()+ precio +
                ", precio convertido: ARS$"+this.getPrecioEnPesos();
    }


}
