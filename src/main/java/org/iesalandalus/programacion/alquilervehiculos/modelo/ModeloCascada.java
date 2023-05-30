package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
public class ModeloCascada extends Modelo {

	public ModeloCascada(IFuenteDatos fuenteDatos) {
		setFuenteDatos(fuenteDatos);
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		clientes.insertar(cliente);
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		vehiculos.insertar(vehiculo);
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (clientes.buscar(alquiler.getCliente()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (vehiculos.buscar(alquiler.getVehiculo()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el vehículo del alquiler.");
		}
		alquileres.insertar(alquiler);
	}

	@Override
	public Cliente buscar(Cliente cliente) 
	{
		Cliente buscarCliente = new Cliente(clientes.buscar(cliente));
		return buscarCliente;
	}

	@Override
	public Vehiculo buscar(Vehiculo vehiculo) 
	{
		Vehiculo buscarVehiculo = (vehiculos.buscar(vehiculo));
		return buscarVehiculo;
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) 
	{
		Alquiler buscaAlquiler = new Alquiler(alquileres.buscar(alquiler));
		return buscaAlquiler;
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get()) {
			if (alquiler.getCliente().equals(cliente)) {
				borrar(alquiler);
			}
		}
		clientes.borrar(cliente);
	}

	@Override
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get()) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				borrar(alquiler);
			}
		}
		vehiculos.borrar(vehiculo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}

	@Override
	public List<Cliente> getClientes() 
	{
		
		List<Cliente> clientesLista = new LinkedList<>();
		Iterator<Cliente> iterador = clientes.get().iterator();
		
		while(iterador.hasNext())
		{
			Cliente cliente = iterador.next();
			if(cliente != null)
			{
				clientesLista.add(new Cliente(cliente));
			}
		}
	
		return clientesLista;
	}

	@Override
	public List<Vehiculo> getVehiculos()
	{
		List<Vehiculo> vehiculosLista = new LinkedList<>();
		Iterator<Vehiculo> iterador = vehiculos.get().iterator();
		
		while(iterador.hasNext())
		{
			Vehiculo vehiculo = iterador.next();
			if(vehiculo != null)
			{
				vehiculosLista.add(vehiculo.copiar(vehiculo));
			}
		}
	
		return vehiculosLista;
	}

	@Override
	public List<Alquiler> getAlquileres() 
	{
		List<Alquiler> alquileresLista = new LinkedList<>();
		Iterator<Alquiler> iterador = alquileres.get().iterator();
		
		while(iterador.hasNext())
		{
			Alquiler alquiler = iterador.next();
			if(alquiler != null)
			{
				alquileresLista.add(new Alquiler(alquiler));
			}
		}
	
		return alquileresLista;
	}

	@Override
	public List<Alquiler> getAlquileres(Cliente cliente) {
		List<Alquiler> alquileresCliente = new LinkedList<>();
		for (Alquiler alquiler : alquileres.get()) {
			if (alquiler.getCliente().equals(cliente)) {
				alquileresCliente.add(alquiler);
			}
		}
		return alquileresCliente;
	}

	@Override
	public List<Alquiler> getAlquileres(Vehiculo vehiculo) {
		List<Alquiler> alquileresVehiculo = new LinkedList<>();
		for (Alquiler alquiler : alquileres.get()) {
			if (alquiler.getVehiculo().equals(vehiculo)) {
				alquileresVehiculo.add(alquiler);
			}
		}
		return alquileresVehiculo;
	}

	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquileres.buscar(alquiler) == null) {
			throw new OperationNotSupportedException("ERROR: No exite tal alquiler registrado.");
		}
		alquileres.devolver(alquiler, fechaDevolucion);
	}

	
}


