package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IClientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Clientes implements IClientes 
{
	
	//Iniciación de String para trabajar con ficheros. 
	
	private final String RUTA_FICHERO ="datos/clientes.xml";
	private final String RAIZ = "clientes";
	private final String CLIENTE = "cliente";
	private final String NOMBRE ="nombre";
	private final String DNI = "dni";
	private final String TELEFONO = "telefono";
	private final String TIPO_DATO = "tipodato";
	
	//Inicialización de lista (0..*)
	private List<Cliente> coleccionClientes;
	
	private static Clientes instancia = new Clientes();
	
	//Constructor por defecto, crea lista
	private Clientes()
	{
		
	}
	
	public static IClientes getInstancia()
	{
		return instancia;
	}
	
	public void comenzar()
	{
		try
		{
			coleccionClientes = new LinkedList<>();
			leerXml();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
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
	        Element listaClientes = DOM.getDocumentElement();
	        
	        //Cargamos los nodos. 
	        NodeList listaNodos = listaClientes.getChildNodes();
	        
	        //iniciamos un bucle para obtener los elementos y atributos de uyn cliente
	        for (int i = 0; i < listaNodos.getLength(); i++) {
	            Node nodo = listaNodos.item(i);
	            
	            //Si el nodo es de tipo elemento creará un cliente nuevo. 
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Cliente cliente = elementToCliente((Element) nodo);
	                try {
	                    insertar(cliente);
	                } catch (OperationNotSupportedException e) {
	                    System.out.println(e.getMessage());
	                }
	            }
	        }
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        System.out.println("ERROR: Ocurrió un error al leer el archivo XML: " + e);
	    }
	}
	
	//Método que recibe un objeto "ELEMENT" y lo devuelve como un objeto "Cliente"
	private Cliente elementToCliente(Element elemento) 
	{
		// Creamos la variable. Asignamos el valor elemento, que representa un cliente en formato XML
	    Element elementoCliente = elemento;
		    
	    // Obtenemos el valor del atributo, en este caso, la cadena DNI		   
	    String dni = elementoCliente.getAttribute(DNI);
		    
	    // Obtenemos el primer elemento con la etiqueta Nombre dentro de elementoCliente.
	    //al llamar a ".item(0)" obtenemos el primer nodo de la lista de elementos coincidentes. Luego casteamos a Element. 		    //el valor se asignará a nombre. Repetimos lo mismo con telefono.
		Element nombre = (Element) elementoCliente.getElementsByTagName(NOMBRE).item(0);
	    Element telefono = (Element) elementoCliente.getElementsByTagName(TELEFONO).item(0);
		    
	    //Creamos la instancia de la clase Cliente utilizando los valores obtenidos. 
	    //Utiulizamos el método .getTextContent() para obtener el contenido de los elementos requeridos. 
	    Cliente cliente = new Cliente(nombre.getTextContent(), dni, telefono.getTextContent());
		
	    return cliente;
		    
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
		Element clientes = DOM.getDocumentElement();
		
		//Iniciamos un iterador
		Iterator<Cliente> iterador = coleccionClientes.iterator();
	    while (iterador.hasNext()) {
	        Cliente cliente = iterador.next();
	        
	        try 
	        //Realizamos la conversión del objeto "Cliente" en un elemento XML agregando a la lista de clientes. 
	        {
	            Element clienteDOM = clienteToElement(DOM, cliente);
	            clientes.appendChild(clienteDOM);
	        } catch (DOMException e)
	        {
	            e.printStackTrace();
	        }
	    }

	    UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
	}
	
	
	
	//Método que recibe un objeto "Document" y un objeto "Cliente" como parámetros y devuelve un 
	// elemento "Element" que representa al cliente en formato XML. 
	private Element clienteToElement(Document DOM, Cliente cliente)
	{
		//Creamos un nuevo elemento con el método ".createElement()"
	    Element clienteDOM = DOM.createElement(CLIENTE);
	    
	    //Le asignamos al elemento clienteDOM un atributo que será un DNI
	    clienteDOM.setAttribute(DNI, cliente.getDni());

	    //Utilizando el método "createElement" del objeto DOM creamos el nombre 
	    Element nombre = DOM.createElement(NOMBRE);
	    
	    //Establecemos el contenido de texto del elemento extrayendo el nombre. 
	    nombre.setTextContent(cliente.getNombre());
	    
	    //Agregamos un atributo que representa el tipo de dato que será tomado
	    nombre.setAttribute(TIPO_DATO, "String");
	    
	    //agregamos nombre como hijo de elemento "clienteDOM" utilizando ".appendChild()"
	    clienteDOM.appendChild(nombre);
	    
	    
	    //Analógicamente hacemos lo mismo con "tELEFONO"
	    Element telefono = DOM.createElement(TELEFONO);
	    telefono.setTextContent(cliente.getTelefono());
	    telefono.setAttribute(TIPO_DATO, "String");
	    clienteDOM.appendChild(telefono);

	    return clienteDOM;
	}

	
	//Creación de copia de clientes
	public List<Cliente> get()
	{
		// INiciamos una nueva lista
		List<Cliente> copiaClientes = new LinkedList<>(coleccionClientes);
		
		return copiaClientes;
	}
	
	/*
	//a través de la herramienta .size() calculamos la cantidad de elementos en la lista coleccionClientes
	public int getCantidad()
	{
		return coleccionClientes.size();
	}
	*/
	
	
	//método para insertar cliente si y solo si este no existe o no es nulo.
	public void insertar(Cliente cliente) throws OperationNotSupportedException
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		if (buscar(cliente) == null)
		{
			coleccionClientes.add(new Cliente(cliente));
		}
		else 
		{
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
		
	}
	
	
	
	//Método que buscar un cliente ingresando por parámetro 
	public Cliente buscar(Cliente cliente)
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		//Condición que busca si el cliente existe en la colección.
		if (coleccionClientes.indexOf(cliente) != -1)
		{
			return coleccionClientes.get(coleccionClientes.indexOf(cliente));
		}
		//si no encuentra al cliente devuelve un null lo cual comunicará al insertar cliente
		else 
			return null;
	}
	
	//método para borrar un cliente pasado por parámetro
	public void borrar(Cliente cliente) throws OperationNotSupportedException
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		//usamos .remove para quitar al cliente de la lista
		if (coleccionClientes.contains(cliente))
		{
			coleccionClientes.remove(cliente);
		}
		else
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
	}
	
	//método para modificar nombre y/o teléfono
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException
	{
		if (cliente == null)
		{
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		if (buscar(cliente) == null)
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		if (cliente.getNombre() != null || cliente.getNombre().isEmpty())
		{
			cliente.setNombre(nombre);
		}
		if (cliente.getTelefono() != null || cliente.getTelefono().isEmpty())
		{
			cliente.setTelefono(telefono);
		}
	}

	

}
