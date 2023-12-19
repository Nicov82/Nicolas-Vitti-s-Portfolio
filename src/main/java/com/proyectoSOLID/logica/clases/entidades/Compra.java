package com.proyectosolid.logica.clases.entidades;

import com.proyectosolid.logica.clases.lugaresCompra.LugarCompra;

import java.util.ArrayList;
import java.util.List;


    public class Compra {
        private static int contadorCompras = 0;
        private List<Producto> listaProductos;
        private String nombre;

        private double precioTotal;

        private double precioTotalEnPesos;

        private LugarCompra lugarCompra; //Podría ser un campo final pero hipotéticamente se podría añadir una función para "corregir" el Lugar dw Compra mal ingresado.


        public Compra(LugarCompra lugarCompra) {
                contadorCompras++;
                this.nombre = "Compra " + contadorCompras;
                listaProductos = new ArrayList<>();
                this.lugarCompra = lugarCompra;
            }

        public double getPrecioTotal() {
            return precioTotal;
        }

        public void setPrecioTotal(double precioTotal) {
            this.precioTotal = precioTotal;
        }

        public double getPrecioTotalEnPesos() {
            return precioTotalEnPesos;
        }

        public void setPrecioTotalEnPesos(double precioTotalEnPesos) {
            this.precioTotalEnPesos = precioTotalEnPesos;
        }

        public LugarCompra getLugarCompra() {
            return lugarCompra;
        }

        public List<Producto> getListaProductos() {
            return listaProductos;
        }

        public void setListaProductos(List<Producto> listaProductos) {
            this.listaProductos = listaProductos;
        } //Método no usado, puede ser útil para testeos futuros.

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public String toString() {
            return nombre +
                    " - Lugar: "+lugarCompra.getNombre() +
                    " - Precio: "+ lugarCompra.getSignoMonetario()+precioTotal+
                    " - Precio convertido: ARS$"+ precioTotalEnPesos;
        }
    }
