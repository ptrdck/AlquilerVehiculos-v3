package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;


import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;


public abstract class Modelo {

    protected IClientes clientes;
    protected IVehiculos vehiculos;
    protected IAlquileres alquileres;
    protected IFuenteDatos fuenteDatos;

    protected void setFuenteDatos(IFuenteDatos fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }

    public void comenzar() {
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        alquileres = fuenteDatos.crearAlquileres();
    }

    public void terminar() {
        System.out.println("Modelo terminado");
    }

    public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;

    public abstract void insertar(Vehiculo turismo) throws OperationNotSupportedException;

    public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;

    public abstract Cliente buscar(Cliente cliente);

    public abstract Vehiculo buscar(Vehiculo vehiculo);

    public abstract Alquiler buscar(Alquiler alquiler);

    public abstract void modificar(Cliente cliente, String nombre, String telefono)
            throws OperationNotSupportedException;

    public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;

    public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;

    public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;

    public abstract List<Cliente> getClientes();

    public abstract List<Vehiculo> getVehiculos();

    public abstract List<Alquiler> getAlquileres();

    public abstract List<Alquiler> getAlquileres(Cliente cliente);

    public abstract List<Alquiler> getAlquileres(Vehiculo vehiculo);

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		// TODO Auto-generated method stub
		
	}

}




/*
 * 
 * 
public abstract class Modelo 
{

	//declaramos las clases anteriores como atributes de clase de modelo (0,1)
	protected IClientes clientes;
	protected IVehiculos vehiculos;
	protected IAlquileres alquileres;
	protected IFuenteDatos fuenteDatos;
	
	//Constructor
	protected void setFuenteDatos(IFuenteDatos fuenteDatos)
	{
		fuenteDatos = new FuenteDatosMemoria();
	}
	
	//Método donde creamos las instancias de las clases negocio declaradas al comienzo
	public void comenzar()
	{
		clientes = new Clientes();
		vehiculos = new Vehiculos();
		alquileres = new Alquileres();
	}
	
	//método para terminar el programa que simplemente muestra un mensaje de salida
	public void terminar()
	{
		System.out.println("Modelo terminado");
	}
	
	//Método insertar un cliente
	public abstract void insertar(Cliente cliente) throws OperationNotSupportedException;
	
	//Método para insertar un Turismo
	public abstract void insertar(Vehiculo turismo)throws OperationNotSupportedException;

	//método para insertar Alquiler
	public abstract void insertar(Alquiler alquiler) throws OperationNotSupportedException;
	
	public abstract Cliente buscar(Cliente cliente);
	
	public abstract Vehiculo buscar(Vehiculo vehiculo);
	
	public abstract Alquiler buscar(Alquiler alquiler);
	
	public abstract void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException;
	
	public abstract void borrar(Cliente cliente) throws OperationNotSupportedException;
	
	public abstract void borrar(Vehiculo vehiculo) throws OperationNotSupportedException;
	
	public abstract void borrar(Alquiler alquiler) throws OperationNotSupportedException;
	
	
	//método get para obtener una nueva lista de clientes
	public abstract List<Cliente> getClientes();
	
	//método get para obtener una nueva lista de turismos
	public abstract List<Vehiculo> getVehiculos();
	
	
	//método get para obtener una nueva lista de alquileres
	public abstract List<Alquiler> getAlquileres();
	
	//método get para obtener alquileres por cliente
	public abstract List<Alquiler> getAlquileres(Cliente cliente);
	
	//método get para obtener alquileres por turismo
	public abstract List<Alquiler> getAlquileres(Vehiculo vehiculo);
	
	public abstract void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException;
	
	public abstract void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException;
	
}*/
