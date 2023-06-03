package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class ControladorVentanaAlquileres implements Initializable {
	
	private Modelo modelo;

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
    private Button jBotonCancelar;

    @FXML
    private AnchorPane jPaneAlquiler;

    @FXML
    private SplitPane jSplitPaneAlquiler;
    
    private ObservableList<Alquiler> listaAlquileres;
    
    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializar columnas de la tabla
        jColNombreA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("nombre"));
        jColDniA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("dni"));
        jColTelefonoA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("telefono"));
        jColMarcaA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("marca"));
        jColModeloA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("modelo"));
        jColMatriculaA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("matricula"));
        jColTipoA.setCellValueFactory(new PropertyValueFactory<>("Tipo Vehículo"));
        jColImporteA.setCellValueFactory(new PropertyValueFactory<>("importe"));
        jColEntradaA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("Fecha Ingreso Alquiler"));
        jColDevolucionA.setCellValueFactory(new PropertyValueFactory<Alquiler, String>("Fecha Devolución Alquiler"));

        // Actualizar la tabla cada 15 o 30 segundos
        // Configurar la actualización periódica de la tabla cada 15 segundos
        timeline = new Timeline(new KeyFrame(Duration.seconds(15), event -> {
            // Obtener la lista de alquileres actualizada desde tu modelo de datos
            List<Alquiler> alquileres = obtenerAlquileresActualizada();

            // Actualizar la tabla con los alquileres actualizados
            actualizarTablaAlquileres(alquileres);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        // Configurar acciones de los botones
        jBotonInsertarA.setOnAction(event -> mostrarVentanaIngresarAlquiler());
        jBotonDevolver.setOnAction(event -> mostrarCuadroDialogoDevolucion());
        jBotonCancelar.setOnAction(event -> cerrarVentanaActual());
    }
    @FXML
    private void mostrarVentanaIngresarAlquiler() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.iesalandalus.programacion.alquilervehiculos.vista.grafica.vistasfxml/VentanaIngresarAlquiler.fxml"));
            Parent root = loader.load();
            
            ControladorVentanaIngresarAlquiler controlador = loader.getController();
            controlador.setControladorVentanaAlquileres(this);
            
            Stage ventanaIngresarAlquiler = new Stage();
            ventanaIngresarAlquiler.setTitle("Ingresar Alquiler");
            ventanaIngresarAlquiler.setScene(new Scene(root));
            ventanaIngresarAlquiler.initModality(Modality.APPLICATION_MODAL);
            ventanaIngresarAlquiler.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @FXML
    private void mostrarCuadroDialogoDevolucion() {
        Alquiler alquilerSeleccionado = jTableAlquiler.getSelectionModel().getSelectedItem();
        if (alquilerSeleccionado != null) {
            // Mostrar cuadro de diálogo para ingresar la fecha de devolución
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Devolución de Alquiler");
            dialog.setHeaderText("Ingresar fecha de devolución");
            dialog.setContentText("Fecha de devolución (dd/MM/yyyy):");

            // Obtener la ventana actual para mostrar el diálogo
            Stage stage = (Stage) jBotonDevolver.getScene().getWindow();
            Optional<String> result = dialog.showAndWait();

            // Procesar la fecha de devolución ingresada
            result.ifPresent(fecha -> {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fechaDevolucion = LocalDate.parse(fecha, formatter);
                    alquilerSeleccionado.devolver(fechaDevolucion);

                    // Mostrar cuadro de diálogo informativo
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Devolución realizada");
                    alert.setHeaderText(null);
                    alert.setContentText("Devolución registrada correctamente.");
                    alert.initOwner(stage);
                    alert.showAndWait();

                    // Actualizar la tabla con los alquileres actualizados
                    List<Alquiler> alquileres = obtenerAlquileresActualizada();
                    actualizarTablaAlquileres(alquileres);
                } catch (DateTimeParseException e) {
                    // Mostrar cuadro de diálogo de error si la fecha no tiene el formato esperado
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error en la devolución");
                    alert.setHeaderText(null);
                    alert.setContentText("Fecha de devolución inválida. Utiliza el formato dd/MM/yyyy.");
                    alert.initOwner(stage);
                    alert.showAndWait();
                } catch (Exception e) {
                    // Mostrar cuadro de diálogo de error en caso de cualquier otra excepción
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error en la devolución");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.initOwner(stage);
                    alert.showAndWait();
                }
            });
        } else {
            // Mostrar cuadro de diálogo de advertencia si no se ha seleccionado ningún alquiler
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ningún alquiler seleccionado");
            alert.setHeaderText(null);
            alert.setContentText("Debes seleccionar un alquiler de la tabla para realizar la devolución.");
            alert.initOwner((Stage) jBotonDevolver.getScene().getWindow());
            alert.showAndWait();
        }
    }

    
    public void actualizarTablaAlquileres(List<Alquiler> alquileres) {
  

        // Limpiar la tabla
        jTableAlquiler.getItems().clear();

        // Agregar los alquileres a la tabla
        jTableAlquiler.getItems().addAll(alquileres);
    }
    
    public List<Alquiler> obtenerAlquileresActualizada(){
    	List<Alquiler> alquileresActualizados = new LinkedList<>();
    	
    	alquileresActualizados = modelo.getAlquileres();
    	
    	return alquileresActualizados;
    }
    @FXML
    private void cerrarVentanaActual() {
        Stage ventanaActual = (Stage) jBotonCancelar.getScene().getWindow();
        ventanaActual.close();
    }
}
   

