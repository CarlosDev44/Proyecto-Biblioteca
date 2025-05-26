package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Biblioteca;
import model.Bibliotecario;
import model.UsuarioComun;
import utils.Utils;

//Controlador de la vista para enviar correos a los usuarios de la biblioteca
public class EnviarCorreoController {

    // Campo de texto para ingresar el ID del usuario
    @FXML
    private TextField txtId;

    // Campo de texto para el asunto del correo
    @FXML
    private TextField txtAsunto;

    // Área de texto para el cuerpo del mensaje
    @FXML
    private TextArea txtMensaje;

    // Tabla que muestra los usuarios registrados
    @FXML
    private TableView<UsuarioComun> tableCorreo;

    // Columnas de la tabla
    @FXML
    private TableColumn<UsuarioComun, Integer> columId;

    @FXML
    private TableColumn<UsuarioComun, String> columNombre;

    @FXML
    private TableColumn<UsuarioComun, String> columCorreo;

    // Lista observable con los datos de los usuarios
    private ObservableList<UsuarioComun> datosUsuario = FXCollections.observableArrayList();

    //Metodo que se ejecuta al iniciar el controlador.
    @FXML
    public void initialize() {

        // Configura columnas usando nombres de atributos del modelo
        columId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        // Cargar usuarios desde el modelo y mostrarlos en la tabla
        Biblioteca biblioteca = Biblioteca.getInstancia();
        datosUsuario.addAll(biblioteca.getListUsuarios());
        tableCorreo.setItems(datosUsuario);
    }

    //Maneja el evento de enviar un mensaje al correo del usuario especificado.
    @FXML
    private void EnviarMensaje() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String asunto = txtAsunto.getText().trim();
            String mensaje = txtMensaje.getText().trim();

            if (asunto.isEmpty() || mensaje.isEmpty()) {
                Utils.mostrarAlerta("Error", "Debe llenar todos los campos.");
                return;
            }

            Biblioteca biblioteca = Biblioteca.getInstancia();

            if (!biblioteca.existeUsuarioPorId(id)) {
                Utils.mostrarAlerta("Error", "No se encontró un usuario con ese ID.");
                return;
            }

            UsuarioComun usuario = biblioteca.obtenerUsuarioComunPorId(id);
            String correo = usuario.getCorreo();

            Bibliotecario.enviarCorreo(correo, asunto, mensaje);
            Utils.mostrarAlerta("Éxito", "Correo enviado correctamente.");

            limpiarCampos();

        } catch (NumberFormatException e) {
            Utils.mostrarAlerta("Error", "El ID debe ser un número válido.");
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Error al enviar correo: " + e.getMessage());
        }
    }

    //Limpia campos del formulario
    private void limpiarCampos() {
        txtId.clear();
        txtAsunto.clear();
        txtMensaje.clear();
    }

    //Cierra la ventana actual y vuelve al panel del bibliotecario
    @FXML
    private void VolverAtras(javafx.event.ActionEvent event) {
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();
        Utils.cargarVentana("/Views/OpcionesBibliotecario.fxml", "Panel Bibliotecario");
    }
}
