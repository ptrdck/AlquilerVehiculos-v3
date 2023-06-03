package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ControladorIngresarVehiculo implements Initializable {
	
	
    @FXML
    private Label jTituloInsV;

    @FXML
    private Label jLabelMatricula;

    @FXML
    private Label jLabelMarca;

    @FXML
    private Label jLabelModelo;

    @FXML
    private Label jLabelTipoV;

    @FXML
    private TextField jTextFieldMatricula;

    @FXML
    private SplitMenuButton jSplitMenuMarca;

    @FXML
    private TextField jTextFieldModelo;

    @FXML
    private SplitMenuButton jSplitMenuTipoV;

    @FXML
    private Button jBotonInsertar;

    @FXML
    private Button jBotonCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    private void onBotonInsertarClicked(ActionEvent event) throws OperationNotSupportedException {
    	
    	//se obtienen los valores ingresados por el usuario en los campos de texto jTextFieldMatricula
    	//y jTextFieldModelo, así como los valores seleccionados en los SplitMenuButton: jSplitMenuMarca y jSplitMenuTipoV.
    	//Estos valores se almacenan en variables correspondientes para su posterior uso.
    	String matricula = jTextFieldMatricula.getText();
        String marca = jSplitMenuMarca.getText();
        String modelo = jTextFieldModelo.getText();
        String tipoVehiculo = jSplitMenuTipoV.getText();
        
        //Se crea una instancia del controlador ControladorVentanaVehiculos, que será utilizada más adelante
        //para refrescar la tabla de vehículos.
        ControladorVentanaVehiculos controladorVentanaVehiculos = new ControladorVentanaVehiculos();
        

        // Verificar que los campos obligatorios no estén vacíos
        if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipoVehiculo.isEmpty()) {
            // Mostrar mensaje de error o realizar acciones correspondientes
            return;
        }
        
        for (Vehiculo vehiculoExistente : controladorVentanaVehiculos.listaVehiculos) {
            if (vehiculoExistente.getMatricula().equals(matricula)) {
                throw new OperationNotSupportedException("ERROR: No se puede insertar un mismo vehículo mas de una vez");
            }
        }
        
        


        // Crear el nuevo vehículo
        Vehiculo vehiculo = new Turismo(marca, modelo, 1900, matricula);
        
        

        // Agregar el vehículo a la lista en ControladorVentanaVehiculos
        controladorVentanaVehiculos.listaVehiculos.add(vehiculo);
        
        //agregar vehiculo a la tabla
        controladorVentanaVehiculos.jTablaV.getItems().add(vehiculo);

        // Cerrar la ventana actual
        jBotonInsertar.getScene().getWindow().hide();

        // Refrescar la tabla de vehículos en ControladorVentanaVehiculos
        controladorVentanaVehiculos.refrescarTabla();
        
        
    }


    @FXML
    private void onBotonCancelarClicked(ActionEvent event) {
    	// Cerrar la ventana actual
        jBotonCancelar.getScene().getWindow().hide();
    }

    @FXML
    private void onMarcaMenuItemClicked(ActionEvent event) {
        // Método invocado al seleccionar un elemento del SplitMenuButton "jSplitMenuMarca"
        MenuItem menuItem = (MenuItem) event.getSource();
        String marcaSeleccionada = menuItem.getText();
        
        System.out.println("Marca: " + marcaSeleccionada);
        
        
    }

    @FXML
    private void onTipoVehiculoMenuItemClicked(ActionEvent event) {
        // Método invocado al seleccionar un elemento del SplitMenuButton "jSplitMenuTipoV"
        MenuItem menuItem = (MenuItem) event.getSource();
        String tipoSeleccionado = menuItem.getText();
        
        
        System.out.println("Tipo de vehículo: " + tipoSeleccionado);
    }
}
