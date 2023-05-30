package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola
{
	//Declaración de patrones fecha y formato del cual se hará comparación entre ellos
	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private Consola()
	{
		
	}
	
	public static void mostrarCabecera(String mensaje)
	{
		System.out.printf("%n%s%n", mensaje);
		String formatoCadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoCadena, 0).replace("0", "-"));
	}
	
	public static void mostrarMenuAcciones()
	{
		//recorremos el array del menú con el for each
		for(Accion accion: Accion.values())
		{
			System.out.println(accion);
		}
		
	}
	
	public static Accion elegirAccion()
	{
		Accion accion;
		do
		{
			int ordinal = leerEntero("Introduzca su opción: ");
			accion = Accion.values()[ordinal];
		}while (accion != Accion.SALIR);
		
		return accion;
	}

	private static String leerCadena (String mensaje)
	{
		System.out.print(mensaje);
		
		return Entrada.cadena();
	}
	
	private static int leerEntero(String mensaje)
	{
		
		System.out.print(mensaje);
		
		return Entrada.entero();
	}
	
	private static LocalDate leerFecha(String mensaje)
	{
		LocalDate fecha = null;
	    do {
	        try {
	            System.out.print(mensaje);
	            String fechaStr = Entrada.cadena();
	            fecha = LocalDate.parse(fechaStr, FORMATO_FECHA);
	        } catch (DateTimeParseException e) {
	            System.out.println("La fecha introducida no tiene el formato válido (" + PATRON_FECHA + ")");
	        }
	    } while (fecha == null);
	    return fecha;
	}
	
	public static Accion elegirOpcion()
	{
		int opcion = 0;
		boolean verificador =false;
		do 
		{
			try 
			{
				opcion = leerEntero("\nPor favor, seleccione una Opción: ");
				Accion.get(opcion);
				verificador = true;
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
				verificador = false;
			}
			
		}while (!verificador);
		
		return Accion.get(opcion);
	}
	
	public static Cliente leerCliente()
	{
		Cliente cliente = null;
		String dni = leerCadena("\nIntroduzca el dni: ");
		String nombre = leerNombre();
		String telefono = leerTelefono();
		
		try 
		{
			return cliente = new Cliente(nombre, dni, telefono);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return cliente;
	}
	
	public static Cliente leerClienteDni()
	{
		Cliente cliente = null;
		String dni = null;
		
		do {
			dni = leerCadena("Introduzca el DNI del cliente: ");
			try
			{
				
				return Cliente.getClienteConDni(dni);
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		} while (cliente == null);
		
		
		
	
		return new Cliente("Pedro Cardenas", dni, "644493658");
		
			
	}
	
	public static String leerNombre()
	{
		System.out.print("\nIntroduzca el nombre del cliente: ");
		String nombre = leerCadena("\nNombre: ");
		return nombre;
	}
	
	public static String leerTelefono()
	{
		System.out.print("\nIntroduzca el número de teléfono del cliente");
		String telefono = leerCadena("\nTelefono: ");
		return telefono;
	}
	
	public static Vehiculo leerVehiculo(TipoVehiculo tipoVehiculo)
	{
		String marca;
		String modelo;
		String matricula;
		int cilindrada;
		int plazas;
		int pma;
		
		Vehiculo vehiculo = null;
		
		switch (tipoVehiculo)
		{
		case TURISMO:
			marca = leerCadena("\nMarca " + "(Seat|Land Rover|KIA|Rolls-Royce|SsangYong):\n");
			modelo = leerCadena("\nModelo: ");
			matricula = leerCadena("\nMatrícula: ");
			cilindrada = leerEntero("\nCilindrada: ");
			
			try {
				vehiculo = new Turismo(marca, modelo, cilindrada, matricula);
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			break;
		
		case AUTOBUS:
			marca = leerCadena("\nMarca " + "(Seat|Land Rover|KIA|Rolls-Royce|SsangYong):\n");
			modelo = leerCadena("\nModelo: ");
			matricula = leerCadena("\nMatrícula: ");
			plazas = leerEntero("\nPlazas: ");
			
			try {
				vehiculo = new Autobus(marca, modelo, plazas, matricula);
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			break;
			
		case FURGONETA:
			marca = leerCadena("\nMarca " + "(Seat|Land Rover|KIA|Rolls-Royce|SsangYong):\n");
			modelo = leerCadena("\nModelo: ");
			matricula = leerCadena("\nMatrícula: ");
			plazas = leerEntero("\nPlazas: ");
			pma = leerEntero("\nPMA: ");
			
			try {
				vehiculo = new Furgoneta(marca, modelo, plazas, pma, matricula);
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			break;
		
		}
		
		return vehiculo;
	}
	
	private static void mostrarMenuTiposVehiculos()
	{
		mostrarCabecera("\nSeleccione el tipo de vehículo: \n");
		
		for(TipoVehiculo tipoVehiculo : TipoVehiculo.values())
		{
			System.out.println(tipoVehiculo);
		}
	}
	
	private static TipoVehiculo elegirTipoVehiculo()
	{
	
		TipoVehiculo tipoVehiculo;
			
		do
		{
			int ordinal = leerEntero("\nIngrese su opción de vehículo: \n");
			tipoVehiculo = TipoVehiculo.get(ordinal);
		}while (tipoVehiculo == null);
		
		
		return tipoVehiculo;
	}
	
	public static Vehiculo leerVehiculo()
	{
		Vehiculo vehiculo;
		
		mostrarMenuTiposVehiculos();
		
		vehiculo = leerVehiculo(elegirTipoVehiculo());
		
		return vehiculo;
	}
	
	public static Vehiculo leerVehiculoMatricula()
	{
		
		Vehiculo vehiculo = null;
		String matricula = null;
		
		do {
			matricula = leerCadena("Introduzca la matrícula del automóvil: ");
			try
			{
				
				return Vehiculo.getVehiculoConMatricula(matricula);
				
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		} while (vehiculo == null);
		
	
		return new Turismo("Seat" , "Cordoba", 1900, matricula);
	}
	
	public static Alquiler leerAlquiler()
	{
		Cliente cliente = leerClienteDni();
		Vehiculo vehiculo = leerVehiculoMatricula();
		LocalDate fechaAlquiler = leerFecha("Fecha alquiler: ");
		
		return new Alquiler(cliente, vehiculo, fechaAlquiler);
	}
	
	public static LocalDate leerFechaDevolucion()
	{
		
		LocalDate fechaDevolucion;
		
		do
		{
			fechaDevolucion = leerFecha("Fecha devolucion: ");
			
		}while (fechaDevolucion == null);
		
		
		
		return fechaDevolucion;
	}

}
