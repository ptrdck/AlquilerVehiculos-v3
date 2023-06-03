package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ControladorIngresarAlquiler implements Initializable {
	
	private Modelo modelo;

    @FXML
    private Label jLabelTituloIAl;

    @FXML
    private Label jLabelDniIAl;

    @FXML
    private Label jLabelMatriculaIAl;

    @FXML
    private Label jLabelFechaIAl;

    @FXML
    private TextField jTextFieldDni;

    @FXML
    private TextField jTextFieldMatriculaIAl;

    @FXML
    private DatePicker jDatePickerIAl;

    @FXML
    private Button jBotonIAl;

    @FXML
    private Button jBotonCancelarIAl;

    @FXML
    private AnchorPane jPaneIAl;

    @FXML
    private SplitPane jSplitPaneIAl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    	
    	String dni = jTextFieldDni.getText();
    	String matricula = jTextFieldMatriculaIAl.getText();
        LocalDate fechaAlquiler = jDatePickerIAl.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertir la fecha a cadena de texto
        String fechaAlquilerTexto = fechaAlquiler.format(formatter);
    	
      

        // Verificar que los campos obligatorios no estén vacíos
        if (matricula.isEmpty() || dni.isEmpty() || fechaAlquiler == null) {
            mostrarMensajeError("ERROR: No puede haber ningún campo nulo o vacío. ");
        
        }


        // Verificar si el vehículo existe en la lista de vehículos
        List<Vehiculo> listaVehiculos = modelo.getVehiculos();
        boolean vehiculoExistente = false;
        
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                vehiculoExistente = true;
                break;
            }
        

        
        if (!vehiculoExistente) {
            mostrarMensajeError("El vehículo no existe en la lista de vehículos.");
            return;
        }
        
     // Verificar si el cliente existe en la lista de clientes
        List<Cliente> listaClientes = modelo.getClientes();
        

        boolean clienteExistente = false;
        
        for(Cliente cliente : listaClientes) {
        	if(cliente.getDni().equals(dni))
        	{
        		clienteExistente = true;
        		break;
        	}
        }
        if (!clienteExistente) {
            mostrarMensajeError("El cliente no existe en la lista de clientes.");
            return;
        }

        // Si todas las verificaciones son exitosas, mostrar un cuadro de diálogo de éxito
        mostrarMensajeInformacion("El alquiler se ha ingresado correctamente.");

        // Cerrar la ventana actual
        jBotonIAl.getScene().getWindow().hide();
        }
  
    }

    @FXML
    private void handleBotonCancelarIAl() {
        // Cerrar la ventana actual
        jBotonCancelarIAl.getScene().getWindow().hide();

    }

    // Método para mostrar un cuadro de diálogo de error
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para mostrar un cuadro de diálogo de información
    private void mostrarMensajeInformacion(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}