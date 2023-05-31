package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.utilidades;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class UtilidadesXml
{
	
	
	private UtilidadesXml()
	{
		
	}
	
	/**
	 * 
	 * @param rutaXml : Ruta del XML
	 * @return : Objeto Document que representa el contenido del archivo XML
	 * @throws ParserConfigurationException : Cuando se produce un error al configurar el parser XML
	 * @throws SAXException : Cuando se produce un error al parsear un archivo XML
	 * @throws IOException : Si se produce un error de entrada/salida al leer el archivo XML
	 */
	
	//Método xmlToDom convierte un archivo XML en un objeto "Document", utilizando "DocumentBuilder";
	public static Document xmlToDom(String rutaXml) throws ParserConfigurationException, SAXException, IOException
	{
		// Creamos la instancia de "DocumentBuilderFactory". "DocumentBuilderFactory" es una clase
		//que proporciona una forma de obtener un "DocumentBuilder" quien a su vez permite crear un objeto Document
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		//Creamos la instancia de "DocumentBuilder". Esta es una interfaz que proporciona
		//los métodos para analizar y construir un "Document"
		DocumentBuilder constructor = factoria.newDocumentBuilder();
		//A continuación se analizará el archivo XML y devolverá un objeto "DOCUMENT"
		
		//Se crea un nuevo objeto "File" utilizando la ruta del XML pasada por parámetro
		// y luego utilizadmos el método "parse" del "DocumentBuilder" para analizar el archivo y ibtener un objeto de tipo 
		//"Document" para representar su contenido
	    Document documento = constructor.parse(new File(rutaXml));
	    //Devuelve el objeto 
        return documento;
	}
	
	//método que convierte un objeto "Document" en un archivo XML utilizando "Transformer" para la transformación. 
    public static void domToXml(Document dom, String rutaXml) throws TransformerException 
    {
    	//Creamos una instancia de "TransformerFactory", que es una clase que proporciona
    	//una forma de obtener un "Transformer" que es quien permite realizar las trnasformaciones en Documentos XML. 
    	TransformerFactory factoria = TransformerFactory.newInstance();
    	
    	//creamos una instancia de "Transformer" la cual es una interfaz qyue proporciona los métodos para trnasformar
    	// documentos hacia XML, en este caso, desde un "Document" a un archivo XML.
	    Transformer transformador = factoria.newTransformer();
	    
	    //Creamos un "DOMSource" utilizando el objeto "Document" ingresado en el método. 
	    //"DOMSource" es una clase que envuelkve un objeto "Document" y nos proporciona
	    //un origen de datos para la transformación XML.
	    DOMSource fuente = new DOMSource(dom);
	    
	    //Creamos un StreamResult que especifica el archivo de salida XML.
	    //"StreamResult" es una clase para escpecificar el destino de la transformación XML, en este caso
	    //creamos un nuevo objeto "File" utilizando la ruta del archivo XML pasada por parámetro.
	    //Esta última ruta se utiliza como destina de la transformación
        StreamResult resultado = new StreamResult(new File(rutaXml));
        
        //método para realizar la transformación y toma el "DOMSource" como entrada y como salida el StreamResult. 
        transformador.transform(fuente, resultado);
    }
    
    //El método creará un nuevo objeto "Document" vacío con la raíz especificada y lo devolverá.
    public static Document crearDomVacio(String raiz) throws ParserConfigurationException 
    {
    	//Creamos la instancia de "DocumentBuilderFactory"
    	DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
    	
    	//Creamos la instancia "DocumentBuilder
    	DocumentBuilder constructor = factoria.newDocumentBuilder();
    	
    	//Creamos un nuevo objeto "Document". Para ello utilizaremos el método newDocument() de DocumentBuilder para crear un archivo vacío
	    Document documento = constructor.newDocument();
	    
	    //Agregamos un elemento Raiz al documento utilizando el nombre proporcionado en "raiz"
	    
	    documento.appendChild(documento.createElement(raiz));
	    
	    //Retornamos documentos. 
	    return documento;
	}

}
