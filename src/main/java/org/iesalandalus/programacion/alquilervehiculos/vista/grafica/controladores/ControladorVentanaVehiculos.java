package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ControladorVentanaVehiculos {
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
    private TableColumn<Vehiculo, Boolean> jColDIsponible;

    @FXML
    private TextField jTextFieldBuscar;

    // Lista de vehículos (debes inicializarla y mantenerla actualizada)
    private ObservableList<Vehiculo> listaVehiculos;

    public void initialize() {
        // Configurar las columnas de la tabla
        jColMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        jColMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        jColModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        jColTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        jColDIsponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        // Configurar la lista de vehículos (puedes obtenerla de donde corresponda)
        listaVehiculos = FXCollections.observableArrayList(
                new Turismo("Seat", "Ibiza", 1900, "1234ABC"),
                new Turismo("Renault", "Clio", 1300, "4536DEF"),
                new Furgoneta("Ford", "Transit", 700, 10, "7089GHI"));

        // Configurar la tabla con los vehículos
        jTablaV.setItems(listaVehiculos);
    }

    @FXML
    private void handleBuscarVehiculo(ActionEvent event) {
        String textoBusqueda = jTextFieldBuscar.getText().toLowerCase();

        List<Vehiculo> vehiculosFiltrados = listaVehiculos.stream()
                .filter(vehiculo -> vehiculo.getModelo().toLowerCase().contains(textoBusqueda))
                .collect(Collectors.toList());

        // Actualizar la tabla con los vehículos filtrados
        jTablaV.setItems(FXCollections.observableArrayList(vehiculosFiltrados));
    }

    @FXML
    private void handleOrdenarAscendenteVehiculo(ActionEvent event) {
        // Ordenar la lista de vehículos por modelo alfabéticamente ascendente
        listaVehiculos.sort(Comparator.comparing(Vehiculo::getModelo));

        // Actualizar la tabla con los vehículos ordenados
        jTablaV.setItems(listaVehiculos);
    }

    @FXML
    private void handleOrdenarDescendenteVehiculo(ActionEvent event) {
        // Ordenar la lista de vehículos por modelo alfabéticamente descendente
        listaVehiculos.sort(Comparator.comparing(Vehiculo::getModelo).reversed());

        // Actualizar la tabla con los vehículos ordenados
        jTablaV.setItems(listaVehiculos);
    }
}
