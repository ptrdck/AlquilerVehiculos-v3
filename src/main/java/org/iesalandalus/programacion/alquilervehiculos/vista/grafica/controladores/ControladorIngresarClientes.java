package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class ControladorIngresarClientes implements Initializable {

    @FXML
    private TextField jCampoNombre;

    @FXML
    private TextField jCampoDni;

    @FXML
    private TextField jCampoTelefono;

    @FXML
    private Button jBotonAnadir;

    @FXML
    private Button jBotonCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) 
    {
        
    }

    @FXML
    private void onBotonAnadirClicked() {
        String nombre = jCampoNombre.getText();
        String dni = jCampoDni.getText();
        String telefono = jCampoTelefono.getText();

        // Validar los campos
        if (nombre.isEmpty() || dni.isEmpty() || telefono.isEmpty()) {
            mostrarMensajeError("ERROR", "Debe completar todos los campos.");
            return;
        }

        // Validar la lógica de negocio
        // Por ejemplo, verificar si el DNI ya existe en la lista de clientes

        // Crear el cliente
        Cliente nuevoCliente = new Cliente(dni, nombre, telefono);

        // Agregar el cliente a la lista de la ventana VentanaClientes
        // Puedes obtener la referencia a la instancia de VentanaClientes y usar un método para agregar el cliente
        ControladorVentanaClientes controladorVentanaClientes = new ControladorVentanaClientes();
        controladorVentanaClientes.getInstance().agregarCliente(nuevoCliente);

        // Mostrar mensaje de éxito
        mostrarMensajeExito("Cliente agregado", "El cliente ha sido agregado con éxito.");

        // Cerrar la ventana actual y volver a VentanaClientes
        Stage stage = (Stage) jBotonAnadir.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void onBotonCancelarClicked() {
        // Cerrar la ventana actual y volver a VentanaClientes
        Stage stage = (Stage) jBotonCancelar.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensajeExito(String titulo, String mensaje) {
        // Implementar la lógica para mostrar un cuadro de diálogo de éxito
    }

    private void mostrarMensajeError(String titulo, String mensaje) {
        // Implementar la lógica para mostrar un cuadro de diálogo de error
    }
}