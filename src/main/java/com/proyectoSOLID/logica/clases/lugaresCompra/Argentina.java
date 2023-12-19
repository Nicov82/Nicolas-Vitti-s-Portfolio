package com.proyectosolid.logica.clases.lugaresCompra;

//Esta clase podría ser la primera de la jerarquía y no existir LugarCompra, pero creo que le otorga más claridad al diseño,
//O incluso no existir y que haya que crearla como a cualquier otro país en la App.
//Pero la idea es que Argentina sea predeterminada.
public final class Argentina extends LugarCompra {

    private static Argentina instancia;

    private  Argentina() {
        this.signoMonetario= "ARS $";
        this.nombre = "Argentina";
    }

    public static Argentina getInstance(){
        if(instancia == null){
            instancia = new Argentina();
        }
        return instancia;
    }



}
