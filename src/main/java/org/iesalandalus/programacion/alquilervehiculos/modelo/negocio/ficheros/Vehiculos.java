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

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IVehiculos;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades.UtilidadesXml;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Vehiculos implements IVehiculos 
{
	// inicialización de expresiones para ficheros. 
	private final String RUTA_FICHERO ="datos/vehiculos.xml";
	private final String RAIZ = "vehiculos";
	private final String VEHICULO = "vehiculo";
	private final String MARCA ="marca";
	private final String MODELO = "modelo";
	private final String MATRICULA = "matricula";	
	
	private final String CILINDRADA ="cilindrada";
	private final String PLAZAS="plazas";
	private final String PMA="pma";
	
	private final String TIPO="tipo";
	private final String TURISMO="turismo";
	private final String AUTOBUS="autobus";
	private final String FURGONETA="furgoneta";
	
	private final String TIPO_DATO="tipodato";
	
	
	//inicialización de lista (0..*)
	private List<Vehiculo> coleccionVehiculos;
	
	private static Vehiculos instancia = new Vehiculos();
	
	//Constructor por defecto, crea lista
	private Vehiculos()
	{
		
	}
	
	public static IVehiculos getInstancia()
	{
		return instancia;
	}
	
	public void comenzar()
	{
		try
		{
			coleccionVehiculos = new LinkedList<>();
			leerXml();
		}
		catch(Exception e)
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
			throw new IllegalArgumentException("ERROR: El archivo que intenta leer es incorrecto");
		}
		if (!file.canRead())
		{
			throw new IOException("ERROR: no se puede leer el archivo.");
		}
		
		try {
			//Creamos el documento
	        Document DOM = UtilidadesXml.xmlToDom(RUTA_FICHERO);
	        Element listaVehiculos = DOM.getDocumentElement();
	        
	        //Cargamos los nodos. 
	        NodeList listaNodos = listaVehiculos.getChildNodes();
	        
	        //iniciamos un bucle para obtener los elementos y atributos de uyn cliente
	        for (int i = 0; i < listaNodos.getLength(); i++) {
	            Node nodo = listaNodos.item(i);
	            
	            //Si el nodo es de tipo elemento creará un cliente nuevo. 
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Vehiculo vehiculo = elementToVehiculo((Element) nodo);
	                try {
	                    insertar(vehiculo);
	                } catch (OperationNotSupportedException e) {
	                    System.out.println(e.getMessage());
	                }
	            }
	        }
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	        System.out.println("ERROR: Ocurrió un error al leer el archivo XML: " + e);
	    }
	}
	
	private Vehiculo elementToVehiculo(Element elemento) 
	{
		
		//Declaración de variable
	    Vehiculo vehiculo = null;
	    
	    //Asignamos el valor del parámetro elemento a la variable vehiculoDOM.
	    Element vehiculoDOM = elemento;
	    //Asignamos la matrícula como atributo 
	    String matriculaAtributo = vehiculoDOM.getAttribute(MATRICULA);
	    //tipo vehículo como atributo.
	    String tipoVehiculo = vehiculoDOM.getAttribute(TIPO);
	    
	    //Pasamos la marca y el modelo como un elemento a través del casteo. 
	    Element marca = (Element) vehiculoDOM.getElementsByTagName(MARCA).item(0);
	    Element modelo = (Element) vehiculoDOM.getElementsByTagName(MODELO).item(0);

	    //Ibniciamos un switch para los distintos casos que podemos tener para Vehiculo. 
	    switch (tipoVehiculo.toLowerCase())
	    {
	        case TURISMO :
	            Element turismoDOM = (Element) vehiculoDOM.getElementsByTagName(TURISMO).item(0);
	            Element cilindrada = (Element) turismoDOM.getElementsByTagName(CILINDRADA).item(0);
	            vehiculo = new Turismo(marca.getTextContent(), modelo.getTextContent(),
	                    Integer.parseInt(cilindrada.getTextContent()), matriculaAtributo);
	            break;
	        case FURGONETA :
	            Element furgonetaDOM = (Element) vehiculoDOM.getElementsByTagName(FURGONETA).item(0);
	            Element pma = (Element) furgonetaDOM.getElementsByTagName(PMA).item(0);
	            Element plazas = (Element) furgonetaDOM.getElementsByTagName(PLAZAS).item(0);
	            vehiculo = new Furgoneta(marca.getTextContent(), modelo.getTextContent(),
	                    Integer.parseInt(pma.getTextContent()), Integer.parseInt(plazas.getTextContent()), matriculaAtributo);
	            break;
	        case AUTOBUS :
	            Element autobusDOM = (Element) vehiculoDOM.getElementsByTagName(AUTOBUS).item(0);
	            Element plazasAutobus = (Element) autobusDOM.getElementsByTagName(PLAZAS).item(0);
	            vehiculo = new Autobus(marca.getTextContent(), modelo.getTextContent(),
	                    Integer.parseInt(plazasAutobus.getTextContent()), matriculaAtributo);
	            break;
	    }

	    return vehiculo;
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
		Iterator<Vehiculo> iterador = coleccionVehiculos.iterator();
	    while (iterador.hasNext()) {
	        Vehiculo vehiculo = iterador.next();
	        
	        try 
	        //Realizamos la conversión del objeto "Cliente" en un elemento XML agregando a la lista de clientes. 
	        {
	            Element vehiculoDOM = vehiculoToElement(DOM, vehiculo);
	            clientes.appendChild(vehiculoDOM);
	        } catch (DOMException e)
	        {
	            e.printStackTrace();
	        }
	    }

	    UtilidadesXml.domToXml(DOM, RUTA_FICHERO);
	}
	
	private Element vehiculoToElement(Document DOM, Vehiculo vehiculo) {
	    String tipo = null;
	    
	    // Con el método getClass().getSimpleName obtenemos el nombre del objeto Vehiculo y realiza la comparación en el switch
	    switch (vehiculo.getClass().getSimpleName()) {
	        case "Turismo":
	            tipo = TURISMO;
	            break;
	        case "Autobus":
	            tipo = AUTOBUS;
	            break;
	        case "Furgoneta":
	            tipo = FURGONETA;
	            break;
	    }
	    
	    Element vehiculoDOM = DOM.createElement(VEHICULO);
	    vehiculoDOM.setAttribute(MATRICULA, vehiculo.getMatricula());
	    vehiculoDOM.setAttribute(TIPO, tipo);
	    
	    Element marcaE = DOM.createElement(MARCA);
	    marcaE.setTextContent(vehiculo.getMarca());
	    marcaE.setAttribute(TIPO_DATO, "String");
	    vehiculoDOM.appendChild(marcaE);
	    
	    Element modeloE = DOM.createElement(MODELO);
	    modeloE.setTextContent(vehiculo.getModelo());
	    modeloE.setAttribute(TIPO_DATO, "String");
	    vehiculoDOM.appendChild(modeloE);
	    
	    switch (tipo) {
	        case TURISMO:
	            Turismo turismo = (Turismo) vehiculo;
	            Element turismoE = DOM.createElement(TURISMO);
	            vehiculoDOM.appendChild(turismoE);
	            
	            Element cilindradaE = DOM.createElement(CILINDRADA);
	            cilindradaE.setTextContent(Integer.toString(turismo.getCilindrada()));
	            cilindradaE.setAttribute(TIPO_DATO, "Integer");
	            turismoE.appendChild(cilindradaE);
	            
	            break;
	        case AUTOBUS:
	            Autobus autobus = (Autobus) vehiculo;
	            Element autobusE = DOM.createElement(AUTOBUS);
	            vehiculoDOM.appendChild(autobusE);
	            
	            Element plazasE = DOM.createElement(PLAZAS);
	            plazasE.setTextContent(Integer.toString(autobus.getPlazas()));
	            plazasE.setAttribute(TIPO_DATO, "Integer");
	            autobusE.appendChild(plazasE);
	            
	            break;
	        case FURGONETA:
	            Furgoneta furgoneta = (Furgoneta) vehiculo;
	            Element furgonetaE = DOM.createElement(FURGONETA);
	            vehiculoDOM.appendChild(furgonetaE);
	            
	            Element plazasFurgonetaE = DOM.createElement(PLAZAS);
	            plazasFurgonetaE.setTextContent(Integer.toString(furgoneta.getPlazas()));
	            plazasFurgonetaE.setAttribute(TIPO_DATO, "Integer");
	            furgonetaE.appendChild(plazasFurgonetaE);
	            
	            Element pmaE = DOM.createElement(PMA);
	            pmaE.setTextContent(Integer.toString(furgoneta.getPma()));
	            pmaE.setAttribute(TIPO_DATO, "Integer");
	            furgonetaE.appendChild(pmaE);
	            
	            break;
	    }
	    
	    return vehiculoDOM;
	}


	
	//método get para una nueva lista con los mismos elementos
	public List<Vehiculo> get()
	{
		//Iniciamos una nueva lista para dejar la copia
		List<Vehiculo> copiaVehiculos = new LinkedList<>(coleccionVehiculos);
		
		return copiaVehiculos;
		
	}
	
	//Método para extraer la cantidad de turismos en la lista coleccionTurismos
	public int getCantidad()
	{
		return coleccionVehiculos.size();
	}
	
	//método para insertar un turismo si y solo si este existe y no es nulo
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException
	{
		if (vehiculo == null)
		{
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}
		if (!coleccionVehiculos.contains(vehiculo))
		{
			coleccionVehiculos.add(vehiculo);
		}
		else
		{
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
	}
	
	//Método para buscar un turismo si y solo este existe y no es nulo
	public Vehiculo buscar(Vehiculo vehiculo)
	{
		if (vehiculo == null)
		{
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		if (coleccionVehiculos.contains(vehiculo))
		{
			return coleccionVehiculos.get(coleccionVehiculos.indexOf(vehiculo));
		}
		return vehiculo;
	}
	
	public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException
	{
		if (vehiculo == null)
		{
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if (coleccionVehiculos.contains(vehiculo))
		{
			coleccionVehiculos.remove(vehiculo);
		}
		else
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
	}
	
	

}
