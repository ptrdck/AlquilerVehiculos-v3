package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.ficheros.Clientes;

public class ControladorVentanaClientes implements Initializable {
	
	private Modelo modelo;

    @FXML
    private AnchorPane JPaneCli;
    @FXML
    private Label jLabelTituloCl;
    @FXML
    private TableView<Cliente> jTablaCli;
    @FXML
    private TableColumn<Cliente, String> jColDni;
    @FXML
    private TableColumn<Cliente, String> jColNombre;
    @FXML
    private TableColumn<Cliente, String> jColTelefono;
    @FXML
    private Button jBotonIngresar;
    @FXML
    private Button jBotonListaCl;
    @FXML
    private Button jBotonModificar;
    @FXML
    private Button jBotonBorrar;
    @FXML
    private Label jLabelBuscar;
    @FXML
    private TextField jTextFieldBuscar;
    
    private ObservableList<Cliente> listaClientes;
    
   
    
    private void actualizarVentanaClientes() {
    	jTablaCli.setItems(listaClientes);
    	this.jTablaCli.refresh();
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla
        jColDni.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        jColNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        jColTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));

        
        listaClientes = FXCollections.observableArrayList();
        
        this.jTablaCli.setItems(listaClientes);
       

    }
    
    @FXML
    private void handleBotonIngresar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaIngresarCliente.fxml"));
            Parent root = loader.load();


            // Crear una nueva ventana y mostrarla
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ingresar Cliente");
            stage.showAndWait();
            
      
            // Actualizar la tabla con la lista de clientes actualizada
            jTablaCli.setItems(listaClientes);
            actualizarVentanaClientes();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleBotonListaCl(ActionEvent event) {
        Cliente clienteSeleccionado = jTablaCli.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            List<Alquiler> alquileresCliente = obtenerAlquileresCliente(clienteSeleccionado);
            if (alquileresCliente.isEmpty()) {
                mostrarMensaje("ERROR:", "El cliente seleccionado no tiene alquileres registrados.");
            } else {
                mostrarAlquileresCliente();
            }
        } else {
            mostrarMensaje("ERROR:", "Debe seleccionar un cliente antes de ver sus alquileres.");
        }
    }
    
    private List<Alquiler> obtenerAlquileresCliente(Cliente cliente) {
    	List<Alquiler> listaAlquileres = modelo.getAlquileres();
    	List<Alquiler> listaAlquilerCliente = new LinkedList<>();
    	for(Alquiler a : listaAlquileres)
    	{
    		if (a.getCliente().getDni().equals(cliente.getDni()))
    				{
    			listaAlquilerCliente.add(a);
    			
    			
    				}
    	}
    	
        return listaAlquilerCliente;
    }
    @FXML
    private void mostrarAlquileresCliente() {
        Cliente clienteSeleccionado = jTablaCli.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            // Llamar al modelo para obtener los alquileres del cliente seleccionado
            List<Alquiler> alquileresCliente = modelo.getAlquileres(clienteSeleccionado);
            if (alquileresCliente.isEmpty()) {
                mostrarMensaje("ERROR:", "El cliente seleccionado no tiene alquileres registrados.");
            } else {
                // Mostrar los alquileres en un cuadro de diálogo
                mostrarCuadroDialogoAlquileres(alquileresCliente);
            }
        } else {
            mostrarMensaje("ERROR:", "Debe seleccionar un cliente antes de ver sus alquileres.");
        }
    }


    private void mostrarCuadroDialogoAlquileres(List<Alquiler> alquileresCliente) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Alquileres del Cliente");
        alerta.setHeaderText(null);

        // Crear el contenido del cuadro de diálogo con los alquileres
        StringBuilder mensaje = new StringBuilder();
        for (Alquiler alquiler : alquileresCliente) {
            mensaje.append("Fecha: ").append(alquiler.getFechaAlquiler()).append("\n");
            
            // Agregar más detalles del alquiler si es necesario
            mensaje.append("\n");
        }
        alerta.setContentText(mensaje.toString());

        alerta.showAndWait();
    }

    
    @FXML
    private void handleBotonModificar(ActionEvent event) {
        Cliente clienteSeleccionado = jTablaCli.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            // Crear el cuadro de diálogo para la modificación
            TextInputDialog dialogo = new TextInputDialog();
            dialogo.setTitle("Modificar Cliente");
            dialogo.setHeaderText(null);
            dialogo.setContentText("Ingrese los nuevos datos del cliente:");

            // Establecer los valores actuales del cliente en el cuadro de diálogo
            dialogo.getEditor().setText(clienteSeleccionado.getNombre());

            // Mostrar el cuadro de diálogo y esperar la respuesta del usuario
            Optional<String> resultado = dialogo.showAndWait();

            resultado.ifPresent(nuevoNombre -> {
                try {
                    // Validar el nuevo nombre (puedes agregar más validaciones según tus necesidades)
                    if (nuevoNombre.trim().isEmpty()) {
                        mostrarMensaje("ERROR:", "El nombre no puede estar vacío.");
                        return;
                    }

                    // Actualizar el nombre del cliente en la tabla
                    clienteSeleccionado.setNombre(nuevoNombre);
                    actualizarVentanaClientes();
                    
                    // Mostrar mensaje de éxito
                    mostrarMensaje("Cliente modificado", "El cliente ha sido modificado exitosamente.");
                } catch (Exception e) {
                    // Manejar cualquier excepción que pueda ocurrir durante la modificación
                    mostrarMensaje("ERROR:", "Ocurrió un error al modificar el cliente.");
                    e.printStackTrace();
                }
            });
        } else {
            mostrarMensaje("ERROR:", "Debe seleccionar un cliente antes de modificar sus datos.");
        }
    }
    
    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
 

    
    @FXML
    private void handleBotonBorrar(ActionEvent event) {
        Cliente clienteSeleccionado = jTablaCli.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            try {
                listaClientes.remove(clienteSeleccionado);
                mostrarMensaje("Cliente borrado", "El cliente ha sido borrado satisfactoriamente.");
                actualizarVentanaClientes();
            } catch (Exception e) {
                mostrarMensaje("ERROR: ", "No se pudo borrar el cliente seleccionado.");
            }
        } else {
            mostrarMensaje("ERROR:", "Debe seleccionar un cliente antes de intentar borrarlo.");
        }
    }
    
    
    
    @FXML
    private void handleBuscarCliente(ActionEvent event) {
        String textoBusqueda = jTextFieldBuscar.getText().toLowerCase();

        if (textoBusqueda.isEmpty()) {
            // Mostrar la tabla completa si no hay texto de búsqueda
            jTablaCli.setItems(listaClientes);
        } else {
            List<Cliente> clientesFiltrados = listaClientes.stream()
                    .filter(cliente -> cliente.getNombre().toLowerCase().contains(textoBusqueda))
                    .collect(Collectors.toList());

            // Actualizar la tabla con los clientes filtrados
            jTablaCli.setItems(FXCollections.observableArrayList(clientesFiltrados));
        }
    }



    @FXML
    private void handleOrdenarAscendenteCliente(ActionEvent event) {
        // Ordenar la lista de clientes alfabéticamente ascendente
        listaClientes.sort(Comparator.comparing(Cliente::getNombre));

        // Actualizar la tabla con los clientes ordenados
        jTablaCli.setItems(listaClientes);
    }

    @FXML
    private void handleOrdenarDescendenteCliente(ActionEvent event) {
        // Ordenar la lista de clientes alfabéticamente descendente
        listaClientes.sort(Comparator.comparing(Cliente::getNombre).reversed());

        // Actualizar la tabla con los clientes ordenados
        jTablaCli.setItems(listaClientes);
    }
    
    public void agregarCliente(Cliente cliente) {
        if (cliente == null)
        {
        	throw new NullPointerException("ERROR: no se puede añadir un cliente nulo");
        }
    	
    	try {
        	listaClientes.add(cliente);
        }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	
    }
}