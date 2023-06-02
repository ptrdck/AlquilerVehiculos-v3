package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Alquileres implements IAlquileres 
{
	
	
	private final String RUTA_FICHERO ="datos/alquileres.xml";
	private final String FORMATO_FECHA = "dd/MM/yyyy";
	private final String RAIZ = "alquileres";
	
	private final String ALQUILER="alquiler";
	private final String DNI_CLIENTE ="dni";
	private final String MATRICULA_VEHICULO = "matricula";							
	private final String FECHA_ALQUILER="fechaAlquiler";
	private final String FECHA_DEVOLUCION="fechaDevolucion";
	
	private final String FORMATO ="formato";	
	
								
	private final String TIPO_DATO="tipodato";
	
	
	//Inicializacion de lista (0..*)
	private List<Alquiler> coleccionAlquileres;
	private static Alquileres instancia = new Alquileres();
	
	//constructor por defecto
	private Alquileres()
	{
		
	}
	
	public static Alquileres getInstancia()
	{
		return instancia;
	}
	
	public void comenzar() {
		try 
		{	
			coleccionAlquileres  = new LinkedList<>();
			leerXml();
		}
		catch (Exception e)
		{
			System.out.println("Error " + e);
		}
	}
	
	private void leerXml() throws IOException
	{
		//instanciamos el objeto fichero. 
		File file = new File(RUTA_FICHERO);
		
		if(!file.exists())
		{
			throw new FileNotFoundException("ERROR: El archivo que intenta leer no existe");
		}
		if (!file.isFile())
		{
			throw new IllegalArgumentException("ERROR: El archivo que intenta es incorrecto");
		}
		if (!file.canRead())
		{
			throw new IOException("ERROR: no se puede leer el archivo.");
		}
		
		try {
			//Creamos el documento
	        Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
	        Element listaAlquileres = DOM.getDocumentElement();
	        
	        //Cargamos los nodos. 
	        NodeList listaNodos = listaAlquileres.getChildNodes();
	        
	        //iniciamos un bucle para obtener los elementos y atributos de uyn cliente
	        for (int i = 0; i < listaNodos.getLength(); i++) {
	            Node nodo = listaNodos.item(i);
	            
	            //Si el nodo es de tipo elemento creará un cliente nuevo. 
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Alquiler alquiler = elementToAlquiler((Element) nodo);
	                try {
	                    insertar(alquiler);
	                } catch (OperationNotSupportedException e) {
	                    System.out.println(e.getMessage());
	                }
	            }
	        }
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        System.out.println("ERROR: Ocurrió un error al leer el archivo XML: " + e);
	    }
	}
	
	private Alquiler elementToAlquiler(Element element) 
	{
		//Inicializamos variables. 
	    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
	    Cliente cliente = null;
	    Vehiculo vehiculo = null;
	    
	    //iniciación de elementos.
	    Element alquilerDOM = element;
	    //declaración de atributos
	    String matriculaAtributo = alquilerDOM.getAttribute(MATRICULA_VEHICULO);
	    String dniAtributo = alquilerDOM.getAttribute(DNI_CLIENTE);
	    
	    //asignación de elementos 
	    NodeList fechaAlquiler = alquilerDOM.getElementsByTagName(FECHA_ALQUILER);
	    NodeList fechaDevolucion = alquilerDOM.getElementsByTagName(FECHA_DEVOLUCION);

	    List<Cliente> listaClientes = Clientes.getInstancia().get();
	    Iterator<Cliente> clienteIterador = listaClientes.iterator();
	    while (clienteIterador.hasNext()) {
	        Cliente clienteI = clienteIterador.next();
	        if (clienteI.getDni().equalsIgnoreCase(dniAtributo)) {
	            cliente = clienteI;
	            break;
	        }
	    }

	    List<Vehiculo> listaVehiculos = Vehiculos.getInstancia().get();
	    Iterator<Vehiculo> vehiculoIterador = listaVehiculos.iterator();
	    while (vehiculoIterador.hasNext()) {
	        Vehiculo vehiculoI = vehiculoIterador.next();
	        if (vehiculoI.getMatricula().equalsIgnoreCase(matriculaAtributo)) {
	            vehiculo = vehiculoI;
	            break;
	        }
	    }

	    Alquiler alquiler = new Alquiler(cliente, vehiculo, LocalDate.parse(fechaAlquiler.item(0).getTextContent(), formatoFecha));

	    if (fechaDevolucion.item(0).getTextContent() != null && !fechaDevolucion.item(0).getTextContent().isEmpty()) {
	        try {
	            alquiler.devolver(LocalDate.parse(fechaDevolucion.item(0).getTextContent(), formatoFecha));
	        } catch (OperationNotSupportedException | DOMException e) {
	            e.printStackTrace();
	        }
	    }

	    return alquiler;
	}


	public void terminar()
	{
		try
		{
			escribirXml();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private void escribirXml() throws ParserConfigurationException, TransformerException
	{
		//Creamos iun documento XML vacío utilizando el método "crearDomVacio()"
		Document DOM = UtilidadesXml.crearDomVacio(RAIZ);
		
		//Obtenemos el elemento raíz del documento
		Element alquileres = DOM.getDocumentElement();
		
		//Iniciamos un iterador
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();
	    while (iterador.hasNext()) {
	        Alquiler alquiler = iterador.next();
	        
	        try 
	        //Realizamos la conversión del objeto "Cliente" en un elemento XML agregando a la lista de clientes. 
	        {
	            Element alquilerDOM = alquilerToElement(DOM, alquiler);
	            alquileres.appendChild(alquilerDOM);
	        } catch (DOMException e)
	        {
	            e.printStackTrace();
	        }
	    }

	    UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
	}
	
	
	/*
	private Element alquilerToElement(Document DOM, Alquiler alquiler) 
	{
		//inicialización de variables y formato. 
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
	    LocalDate fechaAlquiler = alquiler.getFechaAlquiler();
	    fechaAlquiler.format(formatter);
	    LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
	    
	    
	    if (fechaDevolucion != null) 
	    {
	        fechaDevolucion.format(formatter);
	    }

	    // Crear el elemento principal 'alquilerDOM' para representar el alquiler
	    Element alquilerDOM = DOM.createElement(ALQUILER);
	    // Establecer los atributos 'DNI_CLIENTE' y 'MATRICULA_VEHICULO' en el elemento 'alquilerDOM'
	    alquilerDOM.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());
	    alquilerDOM.setAttribute(MATRICULA_VEHICULO, alquiler.getVehiculo().getMatricula());

	    // Crear el elemento 'fechaAlquilerD' para representar la fecha de alquiler
	    Element fechaAlquilerD = DOM.createElement(FECHA_ALQUILER);
	    fechaAlquilerD.setAttribute(FORMATO, FORMATO_FECHA);
	    fechaAlquilerD.setAttribute(TIPO_DATO, "LocalDate");
	    // Establecer el contenido de texto con la fecha de alquiler en el elemento 'fechaAlquilerD'
	    fechaAlquilerD.setTextContent(fechaAlquiler.toString());

	    // Agregar el elemento 'fechaAlquilerD' como hijo del elemento 'alquilerDOM'
	    alquilerDOM.appendChild(fechaAlquilerD);

	    // Crear el elemento 'fechaDevolucionD' para representar la fecha de devolución
	    Element fechaDevolucionD = DOM.createElement(FECHA_DEVOLUCION);
	    // Establecer el contenido de texto con la fecha de devolución (si está presente) en el elemento 'fechaDevolucionD'
	    if (alquiler.getFechaDevolucion() != null) {
	        fechaDevolucionD.setTextContent(fechaDevolucion.toString());
	    } else {
	        fechaDevolucionD.setTextContent("");
	    }

	    fechaDevolucionD.setAttribute(FORMATO, FORMATO_FECHA);
	    fechaDevolucionD.setAttribute(TIPO_DATO, "LocalDate");
	    // Agregar el elemento 'fechaDevolucionD' como hijo del elemento 'alquilerDOM'
	    alquilerDOM.appendChild(fechaDevolucionD);

	    // Retornar el elemento 'alquilerDOM'
	    return alquilerDOM;
	}*/
	private Element alquilerToElement(Document DOM, Alquiler alquiler) 
	{
		//inicialización de variables y formato. 
	    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(FORMATO_FECHA);
	    LocalDate fechaAlquiler = alquiler.getFechaAlquiler();
	    String fechaAlquilerFormateada = fechaAlquiler.format(formatoFecha);
	    
	    
	    LocalDate fechaDevolucion = alquiler.getFechaDevolucion();
	    String fechaDevolucionFormateada = null;
	    
	    
	    if (fechaDevolucion != null) 
	    {
	        fechaDevolucionFormateada = fechaDevolucion.format(formatoFecha);
	    }

	    // Crear el elemento principal 'alquilerDOM' para representar el alquiler
	    Element alquilerDOM = DOM.createElement(ALQUILER);
	    // Establecer los atributos 'DNI_CLIENTE' y 'MATRICULA_VEHICULO' en el elemento 'alquilerDOM'
	    alquilerDOM.setAttribute(DNI_CLIENTE, alquiler.getCliente().getDni());
	    alquilerDOM.setAttribute(MATRICULA_VEHICULO, alquiler.getVehiculo().getMatricula());

	    // Crear el elemento 'fechaAlquilerD' para representar la fecha de alquiler
	    Element fechaAlquilerD = DOM.createElement(FECHA_ALQUILER);
	    fechaAlquilerD.setAttribute(FORMATO, FORMATO_FECHA);
	    fechaAlquilerD.setAttribute(TIPO_DATO, "LocalDate");
	    // Establecer el contenido de texto con la fecha de alquiler en el elemento 'fechaAlquilerD'
	    fechaAlquilerD.setTextContent(fechaAlquilerFormateada);

	    // Agregar el elemento 'fechaAlquilerD' como hijo del elemento 'alquilerDOM'
	    alquilerDOM.appendChild(fechaAlquilerD);

	    // Crear el elemento 'fechaDevolucionD' para representar la fecha de devolución
	    Element fechaDevolucionD = DOM.createElement(FECHA_DEVOLUCION);
	    // Establecer el contenido de texto con la fecha de devolución (si está presente) en el elemento 'fechaDevolucionD'
	    if (fechaDevolucion != null) 
	    {
	        fechaDevolucionD.setTextContent(fechaDevolucionFormateada);
	    } 
	    else 
	    {
	        fechaDevolucionD.setTextContent("");
	    }

	    fechaDevolucionD.setAttribute(FORMATO, FORMATO_FECHA);
	    fechaDevolucionD.setAttribute(TIPO_DATO, "LocalDate");
	    // Agregar el elemento 'fechaDevolucionD' como hijo del elemento 'alquilerDOM'
	    alquilerDOM.appendChild(fechaDevolucionD);

	    // Retornar el elemento 'alquilerDOM'
	    return alquilerDOM;
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
		Alquiler alquilerB = null;
		
		if (alquiler == null)
		{
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		for (Alquiler buscaAlquiler: coleccionAlquileres)
		{
			if (buscaAlquiler.equals(alquiler))
			{
				buscaAlquiler = alquilerB;
			}
		}
		
		return coleccionAlquileres.get(coleccionAlquileres.indexOf(alquilerB));
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

	/*
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
        	
		
	}*/
	

	
	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException 
	{
		
		Alquiler alquiler = null;
		if (vehiculo == null || fechaDevolucion == null)
		{
			throw new NullPointerException("ERROR: No se puede devolver un alquiler Nulo");
		}
		
		alquiler = getAlquilerAbierto(vehiculo);
		
		if (alquiler == null)
		{
			throw new OperationNotSupportedException("ERROR: El vehículo no registra alquiler abierto actualmente");
		}
		
		alquiler.devolver(fechaDevolucion);
	}
	
	
	private Alquiler getAlquilerAbierto(Vehiculo vehiculo)
	{
		Alquiler alquilerAbierto = null;
		if (vehiculo == null)
		{
			throw new NullPointerException("ERROR: el automóvil no puede ser nulo");
		}
		for (Alquiler alquiler : coleccionAlquileres)
		{
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null)
			{
				alquilerAbierto = alquiler;
			}
		}
		
		return alquilerAbierto;
	}


	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException 
	{
		if (cliente == null || fechaDevolucion == null)
		{
			throw new NullPointerException("ERROR: el cliente no puede ser Nulo");
		}
		Alquiler alquiler = getAlquilerAbierto(cliente);
		
		if (alquiler == null)
		{
			throw new OperationNotSupportedException("ERROR: El cliente no registra alquiler abierto");
		}
		
		alquiler.devolver(fechaDevolucion);
	}
	

	private Alquiler getAlquilerAbierto(Cliente cliente)
	{
		Alquiler alquilerAbierto = null;
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: el automóvil no puede ser nulo");
		}
		for (Alquiler alquiler : coleccionAlquileres)
		{
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null)
			{
				alquilerAbierto = alquiler;
			}
		}
		
		return alquilerAbierto;
	}

	
}
