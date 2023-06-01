package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ControladorVentanaVehiculos implements Initializable {

    @FXML
    private Label jTituloV;

    @FXML
    private TableView<Vehiculo> jTablaV;

    @FXML
    private TableColumn<Vehiculo, String> jColMatricula;

    @FXML
    private TableColumn<Vehiculo, String> jColMarca;

    @FXML
    private TableColumn<Vehiculo, String> jColModelo;

    @FXML
    private TableColumn<Vehiculo, String> jColTipo;

    @FXML
    private TableColumn<Vehiculo, Boolean> jColDisponible;

    @FXML
    private Button jBotonInsertar;

    @FXML
    private Button jBotonBorrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Método para inicializar el controlador
        // Puedes agregar código de inicialización aquí
    }

    @FXML
    private void onBotonInsertarClicked() {
        // Método invocado al hacer clic en el botón "Añadir"
        // Agrega la lógica correspondiente para esta acción
    }

    @FXML
    private void onBotonBorrarClicked() {
        // Método invocado al hacer clic en el botón "Borrar"
        // Agrega la lógica correspondiente para esta acción
    }
}
