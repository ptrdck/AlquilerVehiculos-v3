package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler
{
	

	
	//Expresiones regulares y constantes
	
	final static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final static int PRECIO_DIA = 20;
	
	//Declaración de atributos
	
	private Cliente cliente;
	private Vehiculo vehiculo;
	
	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	
	//Constructor con parámetros
	public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
	{
		setCliente(cliente);
		setVehiculo(vehiculo);
		setFechaAlquiler(fechaAlquiler);
	}
	
	//Constructor copia
	public Alquiler(Alquiler alquiler)
	{
		if (alquiler == null)
		{
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		setCliente(alquiler.getCliente());
		setVehiculo(alquiler.getVehiculo());
		setFechaAlquiler(alquiler.getFechaAlquiler());
	}
	
	// getters
	public Cliente getCliente() {
		return cliente;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	
	//Setters
	
	
	private void setCliente(Cliente cliente) 
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}
	
	private void setVehiculo(Vehiculo vehiculo) 
	{
		if (vehiculo == null)
		{
			throw new NullPointerException("ERROR: El vehiculo no puede ser nulo.");
		}
		this.vehiculo = vehiculo;
	}
	
	//método set para evaluar que la fecha de alquiler no sea nula ni posterior a la fecha actual. 
	private void setFechaAlquiler(LocalDate fechaAlquiler)
	{
		if (fechaAlquiler == null)
		{
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if (fechaAlquiler.isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}
	
	//Este método comprueba que la devolución no sea nula, ni posterior a día de hoy, ni tampoco anterior o igual a la fecha de alquiler
	private void setFechaDevolucion(LocalDate fechaDevolucion)
	{
		if (fechaDevolucion == null)
		{
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		if (fechaDevolucion.isBefore(fechaAlquiler) || fechaDevolucion.isEqual(fechaAlquiler))
		{
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}
	
	//Método Devolver
	
	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException
	{
		if (this.fechaDevolucion != null)
		{
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}
	
	public int getPrecio()
	{
		//declaramos la variable local de tipo entero precio
		int precio;
		//Extraemos la cantidad de días entre fechas de alquiler y devolución y lo casteamos a un entero
		int numDias = (int) ChronoUnit.DAYS.between(fechaAlquiler, fechaDevolucion); 
		
		//establecemos el valor de precio con la fórmula establecida en la tarea.
		precio = (PRECIO_DIA + vehiculo.getFactorPrecio())*numDias;
		
		//retornamos el valor de precio
		return precio;
	}

	// hashCode y equals que verifican que dos objetos son iguales si su cliente, turismo y fechaAlquier son los mismos
	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, vehiculo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(vehiculo, other.vehiculo);
	}

	@Override
	public String toString() 
	{
		
		if(fechaDevolucion==null)
		{
			return String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo, FORMATO_FECHA.format(fechaAlquiler), 
					"Aún no devuelto", 0);
		}
		else
		{
			return String.format("%s <---> %s, %s - %s (%d€)", cliente, vehiculo, FORMATO_FECHA.format(fechaAlquiler), FORMATO_FECHA.format(fechaDevolucion), 10);
		}
	}	
	

}
