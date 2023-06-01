package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorVentanaPrincipal implements Initializable {

    @FXML
    private AnchorPane jPane;
    @FXML
    private Button jBotonSalir;
    @FXML
    private Label jLabelTitulo;
    @FXML
    private Button jBotonCl;
    @FXML
    private Button jBotonVe;
    @FXML
    private Button jBotonAl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Aquí puedes agregar la lógica de inicialización de tu interfaz gráfica
        // y establecer los manejadores de eventos para los botones, por ejemplo:
        jBotonSalir.setOnAction(event -> {
            // Lógica para salir del programa
            System.exit(0);
        });

        jBotonCl.setOnAction(event -> {
            // Lógica para gestionar los clientes
            // Por ejemplo, abrir una nueva ventana o cambiar la escena actual
        });

        jBotonVe.setOnAction(event -> {
            // Lógica para gestionar los vehículos
            // Por ejemplo, abrir una nueva ventana o cambiar la escena actual
        });

        jBotonAl.setOnAction(event -> {
            // Lógica para gestionar los alquileres
            // Por ejemplo, abrir una nueva ventana o cambiar la escena actual
        });
    }
}
