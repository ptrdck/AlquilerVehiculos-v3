package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Autobus extends Vehiculo
{


	private final static int FACTOR_PLAZAS = 2;
	private int plazas;
	
	
	public Autobus(String marca, String modelo, int plazas,  String matricula) 
	{
		super(marca, modelo, matricula);
		
		setPlazas(plazas);
		
	}


	public Autobus(Autobus autobus)
	{
		super(autobus);
		
		setPlazas(autobus.getPlazas());
	}
	
	
	@Override
	protected int getFactorPrecio() {
	
		return plazas*FACTOR_PLAZAS;
	}
	

	public int getPlazas() {
		return plazas;
	}


	private void setPlazas(int plazas) 
	{
		if (plazas < 0)
			throw new IllegalArgumentException("ERROR: El número de plazas debe ser un entero positivo.");
		this.plazas = plazas;
	}
	
	@Override
	public String toString() 
	{
		return String.format("%s %s (%sCV) - %s", super.getMarca(), super.getModelo(), plazas, super.getMatricula(), "disponible");
	}


	
	

}
