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

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class ControladorVentanaClientes {
    @FXML
    private TableView<Cliente> jTablaCli;

    @FXML
    private TableColumn<Cliente, String> jColDni;

    @FXML
    private TableColumn<Cliente, String> jColNombre;

    @FXML
    private TableColumn<Cliente, String> jColTelefono;

    @FXML
    private TextField jTextFieldBuscar;

    // Lista de clientes (debes inicializarla y mantenerla actualizada)
    private ObservableList<Cliente> listaClientes;

    public void initialize() {
        // Configurar las columnas de la tabla
        jColDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        jColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        jColTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        // Configurar la lista de clientes (puedes obtenerla de donde corresponda)
        listaClientes = FXCollections.observableArrayList(
                new Cliente("12345678A", "Juan Pérez", "123456789"),
                new Cliente("87654321B", "María López", "987654321"),
                new Cliente("56789012C", "Pedro Ramírez", "456789012"));

        // Configurar la tabla con los clientes
        jTablaCli.setItems(listaClientes);
    }

    @FXML
    private void handleBuscarCliente(ActionEvent event) {
        String textoBusqueda = jTextFieldBuscar.getText().toLowerCase();

        List<Cliente> clientesFiltrados = listaClientes.stream()
                .filter(cliente -> cliente.getNombre().toLowerCase().contains(textoBusqueda))
                .collect(Collectors.toList());

        // Actualizar la tabla con los clientes filtrados
        jTablaCli.setItems(FXCollections.observableArrayList(clientesFiltrados));
    }

    @FXML
    private void handleOrdenarAscendenteCliente(ActionEvent event) {
        // Ordenar la lista de clientes alfabéticamente ascendente
        listaClientes.sort(Comparator.comparing(Cliente::getNombre));

        // Actualizar la tabla con los clientes ordenados
        jTablaCli.setItems(listaClientes);
    }

    @FXML
    private void handleOrdenarDescendenteCliente(ActionEvent event) {
        // Ordenar la lista de clientes alfabéticamente descendente
        listaClientes.sort(Comparator.comparing(Cliente::getNombre).reversed());

        // Actualizar la tabla con los clientes ordenados
        jTablaCli.setItems(listaClientes);
    }
}
