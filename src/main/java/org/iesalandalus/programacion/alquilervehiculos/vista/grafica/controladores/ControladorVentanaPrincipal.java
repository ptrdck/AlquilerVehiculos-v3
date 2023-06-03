package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorVentanaPrincipal extends Application {

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
    public void start(Stage ventanaPrincipal) {
        
    	try {
    		AnchorPane root = new AnchorPane();
    		Scene escena = new Scene(root, 400, 400);
			ventanaPrincipal.setTitle("Ventana Principal");
			ventanaPrincipal.setScene(escena);
			ventanaPrincipal.show();
    	} catch(Exception e) {
			e.printStackTrace();
		}
    	
    	
        jBotonSalir.setOnAction(event -> {
            // Lógica para salir del programa
            System.exit(0);
        });

        jBotonCl.setOnAction(event -> 
        {
        	// Lógica para abrir la ventana de clientes
        	try 
        	{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaClientes.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Ventana de Clientes");
                stage.setScene(new Scene(root));
                stage.show();
            } 
        	catch (IOException e) 
        	{
                e.printStackTrace();
            }
        }
        )
        ;
    

        jBotonVe.setOnAction(event -> 
        {
            // Lógica para abrir la ventana de vehículos
            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaVehiculos.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Ventana de Vehículos");
                stage.setScene(new Scene(root));
                stage.show();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        )
        ;
        
        jBotonAl.setOnAction(event -> 
        {
            // Lógica para abrir la ventana de alquileres
            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaAlquileres.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Ventana de Alquileres");
                stage.setScene(new Scene(root));
                stage.show();
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        )
        ;
        
        
    	}
    public static void main(String[] args) {
		launch(args);
        
    }

	
}
