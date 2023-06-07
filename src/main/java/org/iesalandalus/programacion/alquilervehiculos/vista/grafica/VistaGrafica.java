package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.ControladorVentanaPrincipal;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class VistaGrafica extends Vista {

	protected Controlador controlador; 
	private Stage stage; 
	
	public VistaGrafica() {
		
		super.setControlador(controlador);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AlquilerVehiculos-v3/src/main/java/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaPrincipal.fxml"));
		Parent raiz = fxmlLoader.load();
		Scene escena = new Scene(raiz);
		
		ControladorVentanaPrincipal controller = fxmlLoader.getController();
		
		Image icono = new Image("file:imagenes/coche_alquiler.jpeg"); 
		stage.getIcons().add(icono); 
		
		stage.setTitle("Gestión de Alquiler de Vehículos");
		stage.setScene(escena); 
		stage.show();
	}

	@Override
	public void comenzar() throws Exception {
		
		launch(); 
		stage = new Stage(); 
		start(stage); 
		
	}

	@Override
	public void terminar() {
		
		
	}

}