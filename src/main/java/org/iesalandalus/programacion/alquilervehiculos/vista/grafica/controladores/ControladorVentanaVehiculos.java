package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class ControladorVentanaVehiculos {
    @FXML TableView<Vehiculo> jTablaV;

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
    
    @FXML
    private Button jBotonInsertar;

    @FXML
    private Button jBotonBorrar;
    
    private Timer tiempo;


    // Lista de vehículos 
    ObservableList<Vehiculo> listaVehiculos;

    public void initialize() {
        // Configurar las columnas de la tabla
        jColMatricula.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("matricula"));
        jColMarca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
        jColModelo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("modelo"));
        jColTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        jColDIsponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));

       
     // Configurar la actualización periódica de la tabla
        long intervalo = 15000; // Intervalo de 15 segundos (en milisegundos)
        tiempo = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                refrescarTabla();
            }
        };
        tiempo.scheduleAtFixedRate(tarea, intervalo, intervalo);


        // Configurar la tabla con los vehículos
        jTablaV.setItems(listaVehiculos);
    }
    
    void refrescarTabla() {
        // Actualizar los datos de la tabla
        jTablaV.setItems(listaVehiculos);
    }
    
    @FXML
    private void handleBuscarVehiculo(ActionEvent event) {
        String textoBusqueda = jTextFieldBuscar.getText().toLowerCase().trim();

        if (textoBusqueda.isEmpty()) {
            // Si no se ha ingresado nada, mostrar todos los vehículos
            jTablaV.setItems(listaVehiculos);
        } else {
            // Filtrar la lista de vehículos por coincidencias en matrícula, marca, modelo o tipo
            List<Vehiculo> vehiculosFiltrados = listaVehiculos.stream()
                    .filter(vehiculo -> vehiculo.getMatricula().toLowerCase().contains(textoBusqueda)
                            || vehiculo.getMarca().toLowerCase().contains(textoBusqueda)
                            || vehiculo.getModelo().toLowerCase().contains(textoBusqueda)
                            || getTipoVehiculo(vehiculo).toLowerCase().contains(textoBusqueda))
                    .collect(Collectors.toList());

            // Actualizar la tabla con los vehículos filtrados
            jTablaV.setItems(FXCollections.observableArrayList(vehiculosFiltrados));
        }
    }

    private String getTipoVehiculo(Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoConMatricula = Vehiculo.getVehiculoConMatricula(vehiculo.getMatricula());
            if (vehiculoConMatricula instanceof Turismo) {
                return "Turismo";
            } else if (vehiculoConMatricula instanceof Autobus) {
                return "Autobús";
            } else if (vehiculoConMatricula instanceof Furgoneta) {
                return "Furgoneta";
            } else {
                return "Desconocido";
            }
        } catch (OperationNotSupportedException e) {
            return "Desconocido";
        }
    }

    @FXML
    private void handleInsertarVehiculo(ActionEvent event) {
        try {
            // Cargar la vista VentanaIngresarVehiculos.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaIngresarVehiculos.fxml"));
            Parent root = loader.load();
            
            // Crear el escenario de la ventana
            Stage stage = new Stage();
            stage.setTitle("Ingresar Vehículos");
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException e) {
            // Manejar la excepción en caso de error al cargar la vista
            e.printStackTrace();
        }
    }


    @FXML
    private void handleCancelar(ActionEvent event) {
        
    	  //Se detiene el temporizador
        tiempo.cancel();
    	
    	// Obtener la referencia al botón cancelar y al escenario actual
    	
    	
        Button cancelButton = (Button) event.getSource();
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        // Cerrar la ventana actual
        stage.close();

        // Crear el escenario de la ventana principal
        Stage mainStage = new Stage();
        mainStage.setTitle("Ventana Principal");

        try {
            // Cargar la vista VentanaPrincipal.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/iesalandalus/programacion/alquilervehiculos/vista/grafica/vistasfxml/VentanaPrincipal.fxml"));
            Parent root = loader.load();

            
            // ControladorVentanaPrincipal controller = loader.getController();
            // controller.initialize();

            // Configurar la escena con la vista
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            
            //
        } catch (IOException e) {
            // Manejar la excepción en caso de error al cargar la vista
            e.printStackTrace();
        }
        
      
    }
    @FXML
    private void handleBorrarVehiculo(ActionEvent event) {
        // Obtener el vehículo seleccionado en la tabla
        Vehiculo vehiculoSeleccionado = jTablaV.getSelectionModel().getSelectedItem();

        if (vehiculoSeleccionado != null) {
            // Mostrar un cuadro de diálogo de confirmación antes de borrar el vehículo
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de borrado");
            alert.setHeaderText("Borrar vehículo");
            alert.setContentText("¿Estás seguro de que deseas borrar el vehículo seleccionado?");

            // Esperar a que se seleccione una opción en el cuadro de diálogo
            ButtonType opcion = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (opcion == ButtonType.OK) {
                // Borrar el vehículo de la lista
                listaVehiculos.remove(vehiculoSeleccionado);

                // Actualizar la tabla
                jTablaV.refresh();
            }
        } else {
            // Mostrar un mensaje de advertencia si no se ha seleccionado ningún vehículo
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Ningún vehículo seleccionado");
            alert.setContentText("Por favor, selecciona un vehículo de la tabla.");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void handleMostrarAlquilados(ActionEvent event) {
        // Filtrar la lista de vehículos para mostrar solo los que están alquilados
        List<Vehiculo> vehiculosAlquilados = listaVehiculos.stream()
                .filter(Vehiculo::isAlquilado)
                .collect(Collectors.toList());

        // Actualizar la tabla con los vehículos alquilados
        jTablaV.setItems(FXCollections.observableArrayList(vehiculosAlquilados));
    }

    @FXML
    private void handleMostrarDisponibles(ActionEvent event) {
        // Filtrar la lista de vehículos para mostrar solo los que están disponibles
        List<Vehiculo> vehiculosDisponibles = listaVehiculos.stream()
                .filter(vehiculo -> !vehiculo.isAlquilado())
                .collect(Collectors.toList());

        // Actualizar la tabla con los vehículos disponibles
        jTablaV.setItems(FXCollections.observableArrayList(vehiculosDisponibles));
    }

    private void actualizarDisponibilidadVehiculos() {
        // Obtener la lista de alquileres activos
        List<Alquiler> alquileresActivos = // Obtener la lista de alquileres activos

        // Actualizar la disponibilidad de cada vehículo en base a los alquileres activos
        for (Vehiculo vehiculo : listaVehiculos) {
            boolean alquilado = alquileresActivos.stream()
                    .anyMatch(alquiler -> alquiler.getVehiculo().equals(vehiculo));
            vehiculo.setAlquilado(alquilado);
        }

        // Actualizar la tabla
        jTablaV.refresh();
    }

    private void configurarIconoDisponibilidad() {
        jColDIsponible.setCellFactory(column -> new TableCell<Vehiculo, Boolean>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Boolean disponible, boolean empty) {
                super.updateItem(disponible, empty);

                if (empty || disponible == null) {
                    setGraphic(null);
                } else {
                    if (disponible) {
                        imageView.setImage(new Image(getClass().getResourceAsStream("disponible.png")));
                    } else {
                        imageView.setImage(new Image(getClass().getResourceAsStream("alquilado.png")));
                    }
                    setGraphic(imageView);
                }
            }
        });
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
