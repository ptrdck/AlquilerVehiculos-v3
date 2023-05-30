package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista
{
	public VistaTexto()
	{
		Accion.setVista(this);
	}
	

	@Override
	public void comenzar()
	{
		Consola.mostrarCabecera("Gestión de Alquileres");
		Accion accion;
		do 
		{
			
			Consola.mostrarMenuAcciones();
			accion = Consola.elegirOpcion();
			accion.ejecutar();
		}while(accion != Accion.SALIR);
	}	
	
	public void terminar()
	{
		controlador.terminar();
	}
	
	
	protected void insertarCliente()
	{
		
	    Consola.mostrarCabecera("Insertar cliente\n");
	    try 
	    {
	    	controlador.insertar(Consola.leerCliente());
	    	System.out.println("Cliente insertado con éxito");
	    }
	    catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e)
	    {
	    	System.out.println(e.getMessage());
	    }
	}

	protected void insertarVehiculo()
	{
	    Consola.mostrarCabecera("Insertar vehiculo\n");
	    
	    try
	    {
	    	controlador.insertar(Consola.leerVehiculo());
		    	System.out.println("Vehiculo insertado con éxito");
	    }
	    catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e)
	    {
	    	System.out.println(e.getMessage());
	    }

	}

	protected void insertarAlquiler()
	{
		 Consola.mostrarCabecera("Insertar alquiler\n");
		    
		 try
		 {
			 controlador.insertar(Consola.leerAlquiler());
			 System.out.println("Alquiler insertado con éxito");
		 }
		 catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e)
		 {
			 System.out.println(e.getMessage());
		 }
	}

	protected void buscarCliente()
	{
		 Consola.mostrarCabecera("Buscar cliente\n");
		 Cliente cliente = Consola.leerClienteDni();
		    try
		    {
		    	cliente = controlador.buscar(cliente);
		    }
		    catch (NullPointerException | IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}

	protected void buscarVehiculo()
	{
		 Consola.mostrarCabecera("Buscar turismo\n");
		 Vehiculo vehiculo = Consola.leerVehiculoMatricula();
		    try
		    {
		    	vehiculo = controlador.buscar(vehiculo);
		    }
		    catch (NullPointerException | IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }

	    
	}

	protected void buscarAlquiler()
	{
		 Consola.mostrarCabecera("Buscar alquiler\n");
		 Alquiler alquiler = Consola.leerAlquiler();
		    try
		    {
		    	alquiler = controlador.buscar(alquiler);
		    	
		    }
		    catch (NullPointerException | IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}

	protected void modificarCliente()
	{
		 Consola.mostrarCabecera("Modificar cliente\n");
		 Cliente cliente = Consola.leerClienteDni();
		 String nombre = Consola.leerNombre();
		 String telefono = Consola.leerTelefono();
		    try
		    {
		    	controlador.modificar(cliente, nombre, telefono);
		    	
		    }
		    catch (NullPointerException | OperationNotSupportedException| IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}
	
	protected void devolverAlquiler()
	{
		Consola.mostrarCabecera("Devolver alquiler\n");
		Alquiler alquiler = Consola.leerAlquiler();
		LocalDate fechaDevolucion = Consola.leerFechaDevolucion();
		
		try
		{
			controlador.devolver(alquiler, fechaDevolucion);	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
/*
	protected void devolverAlquilerCliente()
	{
		 Consola.mostrarCabecera("Devolver alquiler por cliente\n");
		
;		    try
		    {
		    	controlador.devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
		    	System.out.println("Fecha de devolución correcta");
		    }
		    catch (NullPointerException | OperationNotSupportedException| IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}
	protected void devolverAlquilerVehiculo()
	{
		 Consola.mostrarCabecera("Devolver alquiler por vehículo\n");
		 
		    try
		    {
		    	controlador.devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
		    	System.out.println("Fecha de devolución correcta");
		    }
		    catch (NullPointerException | OperationNotSupportedException| IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}
*/
	protected void borrarCliente()
	{
		 Consola.mostrarCabecera("Borrar cliente\n");
		 
		    try
		    {
		    	controlador.borrar(Consola.leerClienteDni());
		    	System.out.println("El cliente ha sido borrado con éxito");
		    	
		    }
		    catch (NullPointerException | OperationNotSupportedException| IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}

	protected void borrarVehiculo()
	{
		 Consola.mostrarCabecera("Borrar vehiculo\n");
		 
		    try
		    {
		    	controlador.borrar(Consola.leerVehiculoMatricula());
		    	System.out.println("El vehiculo ha sido borrado con éxito");
		    	
		    }
		    catch (NullPointerException | OperationNotSupportedException| IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }
	}

	protected void borrarAlquiler()
	{
		 Consola.mostrarCabecera("Borrar alquiler\n");
		 
		    try
		    {
		    	controlador.borrar(Consola.leerAlquiler());
		    	System.out.println("El alquiler ha sido borrado con éxito");
		    	
		    }
		    catch (NullPointerException | OperationNotSupportedException| IllegalArgumentException e)
		    {
		    	System.out.println(e.getMessage());
		    }

	  
	}

	protected void listarClientes()
	{
	    Consola.mostrarCabecera("Listado de clientes: ");
	    List<Cliente> clientes = controlador.getClientes();

	    if (clientes.size() > 0)
	    {
	    	for(Iterator<Cliente> it = clientes.iterator(); it.hasNext();)
	    	{
	    		Cliente cliente = it.next();
	    		System.out.println(cliente);
	    	}
	    	
	    }
	    else
	    	System.out.println("ERROR: No hay clientes que listar. Por favor, inserte primero un cliente en el sistema.");
	}

	protected void listarVehiculos()
	{
		 Consola.mostrarCabecera("Listado de vehiculos: ");
		 List<Vehiculo> vehiculos = controlador.getVehiculos();

		 if (vehiculos.size() > 0)
		 {
		   	for(Iterator<Vehiculo> it = vehiculos.iterator(); it.hasNext();)
	    	{
	    		Vehiculo turismo = it.next();
	    		System.out.println(turismo);
		    }
		    	
		 }
		 else
			 System.out.println("ERROR: No hay turismos que listar. Por favor, inserte primero un turismo en el sistema.");
	}

	protected void listarAlquileres()
	{
		 Consola.mostrarCabecera("Listado de alquileres: ");
		 List<Alquiler> alquileres = controlador.getAlquileres();

		 if (alquileres.size() > 0)
		 {
			 for(Iterator<Alquiler> it = alquileres.iterator(); it.hasNext();)
			 {
				 Alquiler alquiler = it.next();
				 System.out.println(alquiler);
			 }
		    	
		 }
		 else
			 System.out.println("ERROR: No hay alquileres que listar. Por favor, inserte primero un alquiler en el sistema.");
		
	}
	
	protected void listarAlquileresCliente()
	{
		 Consola.mostrarCabecera("Listado de alquileres por cliente: ");
		 List<Alquiler> alquileresClientes = controlador.getAlquileres(Consola.leerClienteDni());

		 if (alquileresClientes.size() > 0)
		 {
			 for(Iterator<Alquiler> it = alquileresClientes.iterator(); it.hasNext();)
			 {
				 Alquiler alquiler = it.next();
				 System.out.println(alquiler);
			 }
		    	
		 }
		 else
			 System.out.println("ERROR: No hay alquileres para dicho cliente.");	
	}
	
	protected void listarAlquileresVehiculo()
	{
		 Consola.mostrarCabecera("Listado de alquileres por vehiculo: ");
		 List<Alquiler> alquileresVehiculo = controlador.getAlquileres(Consola.leerVehiculoMatricula());

		 if (alquileresVehiculo.size() > 0)
		 {
			 for(Iterator<Alquiler> it = alquileresVehiculo.iterator(); it.hasNext();)
			 {
				 Alquiler alquiler = it.next();
				 System.out.println(alquiler);
			 }
		    	
		 }
		 else
			 System.out.println("ERROR: No hay alquileres para dicho turismo.");	
	}


}
