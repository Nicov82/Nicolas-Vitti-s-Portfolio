package com.proyectosolid.logica.clases.lugaresCompra;

//Package que contiene distintas clases Lugares de compra, que implementarán interfaces en modo distinto para realizar los
// cálculos pertunentes a cada compra (Impuestos, conversion de moneda, etc.) siguiendo el patrón Strategy.
//Luego del refactoreo, eliminamos clases e hicimos más extensible la aplicación, facilitando la incorporación
//de nuevos países, que ya no necesitará de nuevas clases, sino simplemente crear una nueva instancia de
//la Clase Exterior.