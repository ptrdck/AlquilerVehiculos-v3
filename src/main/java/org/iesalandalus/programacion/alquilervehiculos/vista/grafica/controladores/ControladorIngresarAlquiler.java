package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorIngresarAlquiler implements Initializable {

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
        // Método para inicializar el controlador
        // Puedes agregar código de inicialización aquí
    }

    @FXML
    private void handleBotonIAl() {
        // Lógica para manejar el evento del botón "Añadir"
    }

    @FXML
    private void handleBotonCancelarIAl() {
        // Lógica para manejar el evento del botón "Cancelar"
    }
}

