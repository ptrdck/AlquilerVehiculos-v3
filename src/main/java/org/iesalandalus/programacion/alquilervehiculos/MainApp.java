package org.iesalandalus.programacion.alquilervehiculos;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.FactoriaVistas;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;


/**
 * 
 * @author Pedro Patricio Cárdenas Figueroa
 * Github: https://github.com/ptrdck/AlquilerVehiculos-v3.git
 * Tarea Online 9
 * Programación DAM 2022/23
 */
public class MainApp {

	public static void main(String[] args) 
	{
		try
		{
			FactoriaVistas vista = FactoriaVistas.GRAFICOS;
			
			
			Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.FICHEROS);
			Controlador controlador = new Controlador(modelo, vista.crear());
			
			controlador.comenzar();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
