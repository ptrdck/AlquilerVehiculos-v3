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
        // Método para inicializar el controlador
        // Puedes agregar código de inicialización aquí
    }

    @FXML
    private void onBotonInsertarClicked(ActionEvent event) {
    	String matricula = jTextFieldMatricula.getText();
        String marca = jSplitMenuMarca.getText();
        String modelo = jTextFieldModelo.getText();
        String tipoVehiculo = jSplitMenuTipoV.getText();
        
        ControladorVentanaVehiculos controladorVentanaVehiculos = new ControladorVentanaVehiculos();

        // Verificar que los campos obligatorios no estén vacíos
        if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipoVehiculo.isEmpty()) {
            // Mostrar mensaje de error o realizar acciones correspondientes
            return;
        }

        // Verificar duplicados en la lista de vehículos
     
        if (matricula.equals()) {
            // Mostrar mensaje de error indicando que ya existe un vehículo con esa matrícula
            return;
        }


        // Crear el nuevo vehículo
        Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo);

        // Agregar el vehículo a la lista en ControladorVentanaVehiculos
        

        // Cerrar la ventana actual
        jBotonInsertar.getScene().getWindow().hide();

        // Refrescar la tabla de vehículos en ControladorVentanaVehiculos
        controladorVentanaVehiculos.refrescarTablaVehiculos();
        
        
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
        // Agrega la lógica correspondiente para esta acción
    }

    @FXML
    private void onTipoVehiculoMenuItemClicked(ActionEvent event) {
        // Método invocado al seleccionar un elemento del SplitMenuButton "jSplitMenuTipoV"
        MenuItem menuItem = (MenuItem) event.getSource();
        String tipoSeleccionado = menuItem.getText();
        // Agrega la lógica correspondiente para esta acción
    }
}
