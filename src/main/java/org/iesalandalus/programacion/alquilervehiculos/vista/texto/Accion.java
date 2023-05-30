package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

public enum Accion 
{
	SALIR("Salir")
	{
		public void ejecutar()
		{
			vistaTexto.terminar();
		}
	},
	INSERTAR_CLIENTE("Insertar cliente")
	{
		public void ejecutar()
		{
			vistaTexto.insertarCliente();
		}
	},
	INSERTAR_VEHICULO("Insertar vehiculo")
	{
		public void ejecutar()
		{
			vistaTexto.insertarVehiculo();
		}
	},
	INSERTAR_ALQUILER("Insertar alquiler")
	{
		public void ejecutar()
		{
			vistaTexto.insertarAlquiler();
		}
	},
	BUSCAR_CLIENTE("Buscar cliente")
	{
		public void ejecutar()
		{
			vistaTexto.buscarCliente();
		}
	},
	BUSCAR_VEHICULO("Buscar vehiculo")
	{
		public void ejecutar()
		{
			vistaTexto.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Buscar alquiler")
	{
		public void ejecutar()
		{
			vistaTexto.buscarAlquiler();
		}
	},
	MODIFICAR_CLIENTE("Modificar cliente")
	{
		public void ejecutar()
		{
			vistaTexto.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER("Devolver alquiler")
	{
		public void ejecutar()
		{
			vistaTexto.devolverAlquiler();
		}
	},
	
	BORRAR_CLIENTE("Borrar cliente")
	{
		public void ejecutar()
		{
			vistaTexto.borrarCliente();
		}
	},
	BORRAR_VEHICULO("Borrar vehiculo")
	{
		public void ejecutar()
		{
			vistaTexto.borrarVehiculo();
		}
	},
	BORRAR_ALQUILER("Borrar alquiler")
	{
		public void ejecutar()
		{
			vistaTexto.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("Listar clientes")
	{
		public void ejecutar()
		{
			vistaTexto.listarClientes();
		}
	},
	LISTAR_VEHICULOS("Listar vehiculos")
	{
		public void ejecutar()
		{
			vistaTexto.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Listar alquileres")
	{
		public void ejecutar()
		{
			vistaTexto.listarAlquileres();
		}
	},
	LISTAR_ALQUILERES_CLIENTE("Listar alquileres cliente")
	{
		public void ejecutar()
		{
			vistaTexto.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Listar alquileres vehiculo")
	{
		public void ejecutar()
		{
			vistaTexto.listarAlquileresVehiculo();
		}
	};
	
	private String texto;
	protected static VistaTexto vistaTexto;
	private Accion(String texto)
	{
		this.texto = texto;
	}
	
	private static boolean esOrdinalValido(int ordinal)
	{
		return (ordinal >=0 && ordinal <= values().length-1);
	}
	
	public static Accion get(int ordinal)
	{
		if (!esOrdinalValido(ordinal))
		{
			throw new IllegalArgumentException("ERROR: Ordinal de la opción no válido");
		}
		else 
			return values()[ordinal];
	}
	
	protected static void setVista(VistaTexto vistaTexto)
	{
		if ( vistaTexto == null)
		{
			throw new NullPointerException("ERROR: la vista no puede ser nula");
		}
		Accion.vistaTexto = vistaTexto;
		
	}
	
	public String toString()
	{
		return String.format("%d.- %s", ordinal(), texto);
	}

	public abstract void ejecutar();

}
