package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Biblioteca;
import model.Bibliotecario;
import model.Prestamo;
import model.EstadoPrestamo;
import utils.Utils;

//controlador de la vista para registrar una devolucion
public class RegistrarDevolucionController {

    @FXML
    private TextField txtId; // Campo para ingresar el ID del préstamo

    @FXML
    private ComboBox<EstadoPrestamo> comboEstado; // ComboBox para seleccionar el estado (DEVUELTO o ATRASADO)

    @FXML
    private TableView<Prestamo> tableDevolucion; // Tabla que muestra préstamos en curso

    @FXML
    private TableColumn<Prestamo, Integer> columId;

    @FXML
    private TableColumn<Prestamo, EstadoPrestamo> columEstado;

    // Lista observable para reflejar en tiempo real los cambios en la tabla
    private ObservableList<Prestamo> prestamosActualizados = FXCollections.observableArrayList();

    //Metodo que se llama automaticamente al cargar la vista
    //Inicializa el comboBox y la tabla con los prestamos en curso
    @FXML
    public void initialize() {
        //Configura las opciones del comboBox
        comboEstado.setItems(FXCollections.observableArrayList(EstadoPrestamo.DEVUELTO, EstadoPrestamo.ATRASADO));
        comboEstado.setPromptText("Selecciona estado");

        //Configura las columnas de la tabla
        columId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columEstado.setCellValueFactory(new PropertyValueFactory<>("estadoPrestamo"));

        //Carga prestamos en curso desde la biblioteca
        Biblioteca biblioteca = Biblioteca.getInstancia();
        prestamosActualizados.addAll(Bibliotecario.obtenerPrestamosEnCurso(biblioteca));

        //Asigna la lista observable a la tabla
        tableDevolucion.setItems(prestamosActualizados);
    }

    //Registra la devolucion de un prestamo, ya sea normal o con atraso
    @FXML
    private void RegistrarDevolucion() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());

            if (comboEstado.getValue() == null) {
                Utils.mostrarAlerta("Error", "Debes seleccionar un estado de devolución.");
                return;
            }

            Biblioteca biblioteca = Biblioteca.getInstancia();

            if (!biblioteca.existePrestamoPorId(id)) {
                Utils.mostrarAlerta("Error", "No se encontró ningún préstamo con ese ID.");
                return;
            }

            Prestamo prestamo = biblioteca.obtenerPrestamoPorId(id);
            EstadoPrestamo nuevoEstado = comboEstado.getValue();

            if (nuevoEstado == EstadoPrestamo.DEVUELTO) {
                Bibliotecario.registrarDevolucion(prestamo);

                Utils.mostrarAlerta("Éxito", "Préstamo devuelto correctamente.");
            } else if (nuevoEstado == EstadoPrestamo.ATRASADO) {
                Bibliotecario.registrarDevolucionAtrasada(prestamo);
                Utils.mostrarAlerta("Éxito", "Devolución atrasada registrada. Usuario marcado con deuda.");
            }

            //Refresca la talba si el prestamo ya estaba listado
            if (!prestamosActualizados.contains(prestamo)) {
                prestamosActualizados.add(prestamo);
            } else {
                tableDevolucion.refresh();
            }

            limpiarCampos();

        } catch (NumberFormatException e) {
            Utils.mostrarAlerta("Error", "El ID debe ser un número válido.");
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Ocurrió un error: " + e.getMessage());
        }
    }

    //Limpia los campos de entrada despues de registrar una devolucion
    private void limpiarCampos() {
        txtId.clear();
        comboEstado.setValue(null);
    }

    //Metodo para volver a la vista anterior
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        Utils.cargarVentana("/Views/OpcionesBibliotecario.fxml", "Panel Bibliotecario");
    }
}
