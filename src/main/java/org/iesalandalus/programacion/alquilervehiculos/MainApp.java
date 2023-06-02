package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.VistaTexto;


/**
 * 
 * @author Pedro Patricio Cárdenas Figueroa
 * Github: https://github.com/ptrdck/AlquilerVehiculos-v3.git
 * Tarea Online 10
 * Programación DAM 2022/23
 */
public class MainApp {

	public static void main(String[] args) {
		Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
		Vista vista = new VistaTexto();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

}
