package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
        // Método invocado al hacer clic en el botón "Añadir"
        // Agrega la lógica correspondiente para esta acción
    }

    @FXML
    private void onBotonCancelarClicked(ActionEvent event) {
        // Método invocado al hacer clic en el botón "Cancelar"
        // Agrega la lógica correspondiente para esta acción
    }

    @FXML
    private void onMarcaMenuItemClicked(ActionEvent event) {
        // Método invocado al seleccionar un elemento del SplitMenuButton "jSplitMenuMarca"
        // Agrega la lógica correspondiente para esta acción
    }

    @FXML
    private void onTipoVehiculoMenuItemClicked(ActionEvent event) {
        // Método invocado al seleccionar un elemento del SplitMenuButton "jSplitMenuTipoV"
        // Agrega la lógica correspondiente para esta acción
    }
}
