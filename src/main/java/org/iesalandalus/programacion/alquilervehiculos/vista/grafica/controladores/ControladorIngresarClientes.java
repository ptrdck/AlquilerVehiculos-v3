package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método para inicializar el controlador
        // Puedes agregar código de inicialización aquí
    }

    @FXML
    private void onBotonAnadirClicked() {
        // Método invocado al hacer clic en el botón "Añadir"
        // Agrega la lógica correspondiente para esta acción
    }

    @FXML
    private void onBotonCancelarClicked() {
        // Método invocado al hacer clic en el botón "Cancelar"
        // Agrega la lógica correspondiente para esta acción
    }
}
