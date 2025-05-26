package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import utils.Utils;

//Controlador para la vista de CambiarEstadoUsuario.fxml
public class CambiarEstadoUsuarioController {

    // Campo de texto donde el bibliotecario ingresa el ID del usuario
    @FXML
    private TextField txtIdUsuario;

    // ComboBox que muestra los posibles estados del usuario (enum EstadoUsuario)
    @FXML
    private ComboBox<EstadoUsuario> combEstadoUsuario;

    // Tabla que muestra los usuarios registrados
    @FXML
    private TableView<UsuarioComun> tablaUsuarios;

    // Columnas de la tabla

    @FXML
    private TableColumn<UsuarioComun, Integer> columId;

    @FXML
    private TableColumn<UsuarioComun, String> columNombre;

    @FXML
    private TableColumn<UsuarioComun, EstadoUsuario> columEstado;

    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    //Metodo que se ejecuta automaticamente al inicializar el controlador
    //Configura las columnas de la tabla y carga los datos iniciales
    @FXML
    public void initialize() {
        // Configura la columna ID con el valor del campo getId()
        columId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        // Configura la columna nombre con el valor del campo getNombre()
        columNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));

        // Configura la columna estado con el valor del metodo getEstadoUsuario()
        // Se hace un cast porque solo UsuarioPrestamo tiene este metodo
        columEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(((UsuarioPrestamo) data.getValue()).getEstadoUsuario()));

        // Llena la tabla con la lista de usuarios
        ObservableList<UsuarioComun> usuarios = FXCollections.observableArrayList(biblioteca.getListUsuarios());
        tablaUsuarios.setItems(usuarios);
        tablaUsuarios.setEditable(false);

        // Llena el ComboBox con los valores posibles del enum EstadoUsuario
        combEstadoUsuario.setItems(FXCollections.observableArrayList(EstadoUsuario.values()));
    }

    //Metodo que cambia el estado del usuario según el ID ingresado y el estado seleccionado
    @FXML
    private void CambiarEstado() {
        String idTexto = txtIdUsuario.getText();
        EstadoUsuario nuevoEstado = combEstadoUsuario.getValue();

        if (idTexto.isEmpty() || nuevoEstado == null) {
            Utils.mostrarAlerta("Error", "Debe ingresar el ID del usuario y seleccionar un estado.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);

            UsuarioComun usuarioComun = biblioteca.obtenerUsuarioComunPorId(id);
            if (usuarioComun == null || !(usuarioComun instanceof UsuarioPrestamo)) {
                Utils.mostrarAlerta("Error", "No se encontró un usuario válido con ese ID.");
                return;
            }

            UsuarioPrestamo usuarioPrestamo = (UsuarioPrestamo) usuarioComun;
            if (nuevoEstado == EstadoUsuario.CON_DEUDA) {
                Bibliotecario.cambiarEstadoDeudaConDeuda(usuarioPrestamo);
            } else {
                Bibliotecario.cambiarEstadoDeudaSinDeuda(usuarioPrestamo);
            }

            // Actualizar tabla
            tablaUsuarios.refresh();
            Utils.mostrarAlerta("Éxito", "Estado actualizado correctamente.");

        } catch (NumberFormatException e) {
            Utils.mostrarAlerta( "Error", "El ID debe ser un número entero.");
        }
    }

    //Cierra la ventana actual y carga la ventana anterior (Opciones del bibliotecario)
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        Utils.cargarVentana("/Views/OpcionesBibliotecario.fxml", "Panel Bibliotecario");
    }

}
