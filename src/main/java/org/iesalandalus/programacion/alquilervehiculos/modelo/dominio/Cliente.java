package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
	
	// Creación de atributos de clase
	
	//Declaración de formatos válidos de las variables:
	
	//caso nombre, utilizaremos este método para poder ingresar nombres con carácteres extranjeros
	private static final String ER_NOMBRE = "^([A-Z][a-z]+)(\\s[A-Z][a-z]+){1,2}$";
	//Para verificar DNI y Teléfono, utilizaremos las expresiones regulares usadas en tareas anteriroes
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_TELEFONO = "[679][0-9]{8}";
	
	private String nombre;
	private String dni;
	private String telefono;
	
	
	//Constructor con parámetros
	public Cliente(String nombre, String dni, String telefono)
	{
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}
	
	//Constructor Copia
	public Cliente(Cliente cliente)
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		}
		
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setTelefono(cliente.getTelefono());
	}

	//getters
	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	//setters
	
	//validación de nombre
	public void setNombre(String nombre)
	{
		if (nombre == null)
		{
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (!nombre.matches(ER_NOMBRE))
		{
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		this.nombre = nombre;
	}
	
	//validación de formato dni
	private void setDni(String dni) 
	{
		if (dni == null)
		{
			throw new NullPointerException("ERROR: El DNI no puede ser nulo.");
		}
		if (!dni.matches(ER_DNI))
		{
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		}
		if (!comprobarLetraDni(dni))
		{
			throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
		}
		
		this.dni = dni.toUpperCase();
	}
	
	//Reciclamos el método de comprobación de letra váida de dni de tareas anteriores
	private boolean comprobarLetraDni (String dni)
	{
		//inicialización de variables locales
		
		//letras validas para el DNI según su posición se evalua su validez. 
		String[] letraCorrecta = {"T","R","W","A","G","M","Y","F",
				"P","D","X","B","N","J","Z","S",
				"Q","V","H","L","C","K","E"};
		
		boolean comprueba = false;
		
		Pattern patron = Pattern.compile(ER_DNI);
		Matcher compara = patron.matcher(dni);
		
		if (compara.matches())
		{
			if (compara.group(2).toUpperCase().equals(letraCorrecta[Integer.parseInt(compara.group(1)) % 23]))
			{
				comprueba = true;
			}
		}
			
		return comprueba;
		
	}

	//validación de teléfono
	public void setTelefono(String telefono)
	{
		if (telefono == null)
		{
			throw new NullPointerException("ERROR: El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO))
		{
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
		this.telefono = telefono;
	}
	
	//método que devuelve un cliente existente ingresando un dni válido.
	public static Cliente getClienteConDni (String dni)
	{
		return new Cliente("Pedro Cardenas", dni, "644493758");
	}
	
	//métodos hashCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
	//toString de Cliente según formato test.
	@Override
	public String toString() {
		return String.format("%s - %s (%s)", nombre, dni, telefono) ;
	}
	
	
	

}
