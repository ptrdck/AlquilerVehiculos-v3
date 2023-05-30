package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public class Turismo extends Vehiculo
{
	
	private final static int FACTOR_CILINDRADA = 10;
	private int cilindrada;
	
	
	//Constructor con parámetros
	public Turismo(String marca, String modelo, int cilindrada, String matricula)
	{
		super(marca, modelo, matricula);
		
		setCilindrada(cilindrada);
		
	}
	
	//Constructor copia
	public Turismo (Turismo turismo)
	{
		super(turismo);
		setCilindrada(turismo.getCilindrada());
		
	}
	public int getCilindrada() {
		
		return this.cilindrada;
	}
	
	private void setCilindrada(int cilindrada)
	{
		if (cilindrada<0 || cilindrada>5000)
		{
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
		this.cilindrada = cilindrada;
	}
	
	

	@Override
	protected int getFactorPrecio() 
	{
		
		return cilindrada/FACTOR_CILINDRADA;
	}
	
	public static Vehiculo getVehiculoConMatricula(String matricula) {
	    return new Turismo("Seat", "Cordoba", 1900, matricula);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cilindrada);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turismo other = (Turismo) obj;
		return cilindrada == other.cilindrada;
	}

	@Override
	public String toString() 
	{
		return String.format("%s %s (%sCV) - %s", super.getMarca(), super.getModelo(), cilindrada, super.getMatricula(), "disponible");
	}


}
