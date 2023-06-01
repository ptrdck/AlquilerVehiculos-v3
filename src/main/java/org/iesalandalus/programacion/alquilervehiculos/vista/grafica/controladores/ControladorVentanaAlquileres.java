package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;

public class ControladorVentanaAlquileres implements Initializable {

    @FXML
    private Label jLabelTituloAlquiler;

    @FXML
    private TableView<Alquiler> jTableAlquiler;

    @FXML
    private TableColumn<Alquiler, String> jColNombreA;

    @FXML
    private TableColumn<Alquiler, String> jColDniA;

    @FXML
    private TableColumn<Alquiler, String> jColTelefonoA;

    @FXML
    private TableColumn<Alquiler, String> jColMarcaA;

    @FXML
    private TableColumn<Alquiler, String> jColModeloA;

    @FXML
    private TableColumn<Alquiler, String> jColMatriculaA;

    @FXML
    private TableColumn<Alquiler, String> jColTipoA;

    @FXML
    private TableColumn<Alquiler, Double> jColImporteA;

    @FXML
    private TableColumn<Alquiler, String> jColEntradaA;

    @FXML
    private TableColumn<Alquiler, String> jColDevolucionA;

    @FXML
    private Button jBotonInsertarA;

    @FXML
    private Button jBotonDevolver;

    @FXML
    private AnchorPane jPaneAlquiler;

    @FXML
    private SplitPane jSplitPaneAlquiler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método para inicializar el controlador
        // Puedes agregar código de inicialización aquí
    }
    
    @FXML
    private void handleBotonInsertarA() {
        // Lógica para manejar el evento del botón "Añadir Alquiler"
    }
    
    @FXML
    private void handleBotonDevolver() {
        // Lógica para manejar el evento del botón "Devolver Alquiler"
    }
}

