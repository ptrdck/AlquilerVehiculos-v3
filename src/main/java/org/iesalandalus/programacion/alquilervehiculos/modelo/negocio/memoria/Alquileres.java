package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;

public class Alquileres implements IAlquileres 
{
	//Inicializacion de lista (0..*)
	private List<Alquiler> coleccionAlquileres;
	
	//constructor por defecto
	public Alquileres()
	{
		coleccionAlquileres = new LinkedList<>();
	}
	
	//Método get para devolver una nueva lista con los mismos elementos
	public List<Alquiler> get()
	{
		List<Alquiler> copiaAlquileres = new LinkedList<>(coleccionAlquileres);
		
		
		return copiaAlquileres;
	}
	//Método get que toma por parámetro un cliente y devuelve una lista de alquileres asociados a dicho cliente
	public List<Alquiler> get(Cliente cliente)
	{
		//inicializamos la lista
		List<Alquiler> alquilerCliente = new LinkedList<>();
		
		//ciclo for each para recorrer la lista de alquileres
		for (Alquiler alquiler: coleccionAlquileres)
		{
			//Condición que evalúa cada alquiler extrayendo la información del cliente
			//La compara con el cliente ingresado por parámetro.
			if (alquiler.getCliente().equals(cliente))
			{
				//Si el cliente es el mismo, agregamos a la nueva lista el alquiler
				alquilerCliente.add(alquiler);
			}
		}
		
		return alquilerCliente;
		
	}
	
	//Método get que toma por parámetro un turismo y devuelve una lista de alquileres asociados a dicho elemento
	public List<Alquiler> get(Vehiculo vehiculo)
	{
		//inicializamos la lista
		List<Alquiler> alquilerTurismo = new LinkedList<>();
		
		//ciclo for each para recorrer la lista de alquileres
		for(Alquiler alquiler: coleccionAlquileres)
		{
			//Condición que evalúa cada alquiler extrayendo la información del turismo y la compara 
			//con el turismo pasado por parámetro
			if (alquiler.getVehiculo().equals(vehiculo))
			{
				//Si el turismo es el mismo que el ingresado por parámetro, lo agregamos a la nueva lista
				alquilerTurismo.add(alquiler);
			}
		}
		return alquilerTurismo;
	}
	
	public int getCantidad()
	{
		return coleccionAlquileres.size();
	}
	//método que comprueba si existe alquiler de un cliente, o de un turismo y coteja las fechas
	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler) throws OperationNotSupportedException
	{
		for (Alquiler alquiler : coleccionAlquileres) 
		{
			//comprueba que el turismo buscado no esté alquilado
	        if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null)
	        {
	            throw new OperationNotSupportedException("ERROR: El vehículo está actualmente alquilado.");
	        }
	        //comrueba que el cliente no tiene ningún alquiler pendiente de devolver
	        if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null)
	        {
	            throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
	        }
	        //Comprueba que el turismo tiene un alquiler reservado
	        if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() != null &&
	                alquiler.getFechaAlquiler().isAfter(fechaAlquiler))
	        {
	            throw new OperationNotSupportedException("ERROR: El vehiculo tiene un alquiler posterior.");
	        }
	        //comprueba si el cliente tiene un alquiler tiene un alquiler posterior
	        if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() != null &&
	                alquiler.getFechaAlquiler().isAfter(fechaAlquiler))
	        {
	            throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
	        }
		}
	}
	
	//método para insertar un alquiler en la lista de alquileres siempre y cuando no sea nulo
	// y se compruebe que puede insertarse a través del método anterior compruebaAlquiler
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException
	{
		//comprobación de que no sea nulo
		if (alquiler == null)
		{
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		
		 // Validar si el vehículo y el cliente ya existen en la lista de alquileres
	    for (Alquiler a : coleccionAlquileres) {
	        if (a.getVehiculo().equals(alquiler.getVehiculo()) && a.getCliente().equals(alquiler.getCliente())) {
	            throw new OperationNotSupportedException("ERROR: El alquiler ya existe.");
	        }
	    }
	    
		//Llamada al método para comprobar las condiciones para que pueda insertarse el alquiler en la lista 
		//de alquileres
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		
		coleccionAlquileres.add(alquiler);
	}
	
	//método devolver que ingreasa por parámetro un cliente y una fecha de devolución
	/*
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion)
	{
		//for para recorrer la coleccionAlquileres
		for (Alquiler a: coleccionAlquileres)
		{
			//si el alquiler es null, lanzamos la excepcion
			if (a == null)
			{
				throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
			}
			//Si el alquiler recorrido contiene al mismo cliente y la fecha de devolución es null
			//llamamos al método devolver de la clase Alquiler
			if(alquiler.getCliente().equals(a.getCliente()) && alquiler.getFechaDevolucion() == null)
			{
				alquiler.devolver(fechaDevolucion);
				//una vez asignada la fecha devolución, rompemos el ciclo.
				break;
			}
		}
	}
	*/
	
	//Método para buscar un alquiler por parámetro y que buscará en la lista de coleccionAlquileres
	//el alquiler requerido
	public Alquiler buscar(Alquiler alquiler)
	{
		if (alquiler == null)
		{
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		for (Alquiler buscaAlquiler: coleccionAlquileres)
		{
			if (buscaAlquiler.equals(alquiler))
			{
				return buscaAlquiler;
			}
		}
		
		return null;
	}
	
	//método para borrar un alquiler de la coleccion solo si este no es nulo y existe. 
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException
	{
		if (alquiler == null)
		{
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler))
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		else
			coleccionAlquileres.remove(alquiler);
	}

	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException
	{
		
        if (alquiler != null )
        {
        	
        	boolean bandera = false;
        	Iterator<Alquiler> iterador = coleccionAlquileres.iterator();
        	
        	while(iterador.hasNext() || bandera == false);
        	{
        		Alquiler alquilerDevolver = iterador.next();
        		
        		if (alquilerDevolver.equals(alquiler))
        		{
        			alquiler.devolver(fechaDevolucion);
        			bandera = true;
        		}
        		
        	}
            
        }
        if (fechaDevolucion != null)
        {
        	throw new OperationNotSupportedException("ERROR: el alquiler ya ha sido devuelto");
        }
        else
        	throw new OperationNotSupportedException("ERROR: no puede realizarse una devolución de un alquiler nulo");
        	
		
	}
	
/*
	@Override
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) 
	{
		Alquiler alquiler = getAlquilerAbierto(vehiculo);
		if (alquiler != null)
		{
			alquiler.devolver(fechaDevolucion);
		}
		else
		{
			System.out.println("No hay ningún alquiler abierto para el vehículo con matrícula: " + vehiculo.getMatricula());
		}
		
	}
	*/
	/*
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo)
	{
		for (Alquiler alquiler : coleccionAlquileres)
		{
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null)
			{
				return alquiler;
			}
		}
		
		return null;
	}

	*/
	
}
