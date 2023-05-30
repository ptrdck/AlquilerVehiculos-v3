package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.Vehiculos;

public abstract class Vehiculo {

    private static final String ER_MARCA = "^(Seat|Land Rover|KIA|Rolls-Royce|SsangYong)$";
    private static final String ER_MATRICULA = "^[0-9]{1,4}(?!.*(LL|CH))[BCDFGHJKLMNPRSTVWXYZ]{3}";

    private String marca;
    private String modelo;
    private String matricula;

    protected Vehiculo(String marca, String modelo, String matricula) {
        setMarca(marca);
        setModelo(modelo);
        setMatricula(matricula);
    }

    protected Vehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new NullPointerException("ERROR: No es posible copiar un vehículo nulo.");
        }

        setMarca(vehiculo.getMarca());
        setModelo(vehiculo.getModelo());
        setMatricula(vehiculo.getMatricula());
    }

    public static Vehiculo copiar(Vehiculo vehiculo) {

        if (vehiculo instanceof Turismo) {
            return new Turismo((Turismo) vehiculo);
        } else if (vehiculo instanceof Autobus) {
            return new Autobus((Autobus) vehiculo);
        } else if (vehiculo instanceof Furgoneta) {
            return new Furgoneta((Furgoneta) vehiculo);
        } else {
            throw new IllegalArgumentException("ERROR: El tipo vehículo no corresponde.");
        }
    }

    public static Vehiculo getVehiculoConMatricula(String matricula) throws OperationNotSupportedException 
    {
    	
		Vehiculos listaVehiculos = new Vehiculos();
		List<Vehiculo> copiaVehiculo = listaVehiculos.get();
		Iterator<Vehiculo> iterator = copiaVehiculo.iterator();

		while (iterator.hasNext()) 
		{
		    Vehiculo vehiculo = iterator.next();
		    if (vehiculo.getMatricula().equals(matricula)) 
		    {
		        return vehiculo;
		    } else
		throw new OperationNotSupportedException("ERROR: La matrícula no está registrada. ");
		}
		return new Turismo("Seat", "Panda",1900, matricula);

    }

    protected abstract int getFactorPrecio();

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    protected void setMarca(String marca) {
        if (marca == null) {
            throw new NullPointerException("ERROR: La marca no puede ser nula.");
        }

        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
        }

        this.marca = marca;
    }

    protected void setModelo(String modelo) {
        if (modelo == null) {
            throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
        }

        if (modelo.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
        }

        this.modelo = modelo;
    }

    protected void setMatricula(String matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
        }

        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
        }

        this.matricula = matricula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehiculo vehiculo = (Vehiculo) obj;
        return Objects.equals(matricula, vehiculo.matricula);
    }
}
