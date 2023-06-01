package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class ControladorVentanaClientes implements Initializable 
{
	@FXML
    private Label jLabelTituloCl;
    @FXML
    private TableView<Cliente> jTablaCli;
    @FXML
    private TableColumn<Cliente, String> jColDni;
    @FXML
    private TableColumn<Cliente, String> jColNombre;
    @FXML
    private TableColumn<Cliente, String> jColTelefono;
    @FXML
    private Button jBotonIngresar;
    @FXML
    private Button jBotonListaCl;
    @FXML
    private Button jBotonModificar;
    @FXML
    private Button jBotonBorrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configuración de las columnas de la tabla
        jColDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        jColNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        jColTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        // Aquí puedes agregar la lógica de inicialización de tu interfaz gráfica
        // y establecer los manejadores de eventos para los botones, por ejemplo:

        jBotonIngresar.setOnAction(event -> {
            // Lógica para añadir un nuevo cliente
            // Por ejemplo, abrir una ventana o formulario para ingresar los datos del cliente
        });

        jBotonListaCl.setOnAction(event -> {
            // Lógica para listar los alquileres de un cliente
            // Por ejemplo, abrir una nueva ventana o cambiar la escena actual
        });

        jBotonModificar.setOnAction(event -> {
            // Lógica para modificar un cliente existente
            // Por ejemplo, abrir una ventana o formulario con los datos del cliente para su modificación
        });

        jBotonBorrar.setOnAction(event -> {
            // Lógica para borrar un cliente existente
            // Por ejemplo, mostrar un mensaje de confirmación y eliminar el cliente de la lista
        });
    }
}
	

