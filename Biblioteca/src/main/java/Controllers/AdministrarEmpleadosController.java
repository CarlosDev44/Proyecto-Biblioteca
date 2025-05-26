package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Utils;

import static utils.Utils.cargarVentana;

//Controlador de la vista AdministrarEmpleados.fxml
public class AdministrarEmpleadosController {

    //Campos para registrar o actualizar administradores
    @FXML private TextField txtIdAdministrador;
    @FXML private TextField txtNombreAdministrador;
    @FXML private TextField txtCorreoAdministrador;
    @FXML private PasswordField txtContraseniaAdministrador;

    // Campos para registrar o actualizar Bibliotecarios
    @FXML private TextField txtIdBibliotecario;
    @FXML private TextField txtNombreBibliotecario;
    @FXML private TextField txtCorreoBibliotecario;
    @FXML private PasswordField txtContraseniaBibliotecario;

    // Tabla y columnas para mostrar los empleados
    @FXML private TableView<Empleado> tableEmpleado;
    @FXML private TableColumn<Empleado, Integer> columId;
    @FXML private TableColumn<Empleado, String> columNombre;
    @FXML private TableColumn<Empleado, String> columCorreo;

    private final Biblioteca biblioteca = Biblioteca.getInstancia(); // Referencia a la instancia unica de biblioteca
    private final ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList(); // Lista observable para la tabla

    //Inicializa la tabla con los datos de empleados al cargar la vista
    @FXML
    public void initialize() {
        columId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));

        tableEmpleado.setItems(listaEmpleados);
        actualizarTabla();
    }

    //Actualiza la tabla con la lista actual de empleados desde la biblioteca
    private void actualizarTabla() {
        listaEmpleados.setAll(biblioteca.getListEmpleados());
    }

    //Registra un nuevo Administrador con los datos ingresados en los campos de texto.
    @FXML
    private void RegistrarAdministrador() {
        try {
            int id = Integer.parseInt(txtIdAdministrador.getText());
            String nombre = txtNombreAdministrador.getText();
            String correo = txtCorreoAdministrador.getText();
            String contrasenia = txtContraseniaAdministrador.getText();

            Administrador admin = new Administrador(nombre, id, correo, contrasenia);
            Administrador.registrarEmpleado(biblioteca, admin);
            actualizarTabla();
            limpiarCamposAdministrador();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error al registrar administrador", e.getMessage());
        }
    }

    //Actualiza los datos de un Administrador existente con base en el ID ingresado.
    @FXML
    private void ActualizarAdministrador() {
        try {
            int id = Integer.parseInt(txtIdAdministrador.getText());
            String nombre = txtNombreAdministrador.getText();
            String correo = txtCorreoAdministrador.getText();
            String contrasenia = txtContraseniaAdministrador.getText();

            boolean actualizado = Administrador.modificarEmpleado(biblioteca, id, nombre, correo, contrasenia);
            if (!actualizado) {
                Utils.mostrarAlerta("Actualizar", "No se encontró un administrador con ese ID.");
            } else {
                actualizarTabla();
                limpiarCamposAdministrador();
            }
        } catch (Exception e) {
            Utils.mostrarAlerta("Error al actualizar administrador", e.getMessage());
        }
    }

    //Registra un nuevo Bibliotecario con los datos ingresados en los campos de texto
    @FXML
    private void RegistrarBibliotecario() {
        try {
            int id = Integer.parseInt(txtIdBibliotecario.getText());
            String nombre = txtNombreBibliotecario.getText();
            String correo = txtCorreoBibliotecario.getText();
            String contrasenia = txtContraseniaBibliotecario.getText();

            Bibliotecario bibliotecario = new Bibliotecario(nombre, id, correo, contrasenia);
            Administrador.registrarEmpleado(biblioteca, bibliotecario);
            actualizarTabla();
            limpiarCamposBibliotecario();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error al registrar bibliotecario", e.getMessage());
        }
    }

    //Actualiza los datos de un Bibliotecario existente con base en el ID ingresado
    @FXML
    private void ActualizarBibliotecario() {
        try {
            int id = Integer.parseInt(txtIdBibliotecario.getText());
            String nombre = txtNombreBibliotecario.getText();
            String correo = txtCorreoBibliotecario.getText();
            String contrasenia = txtContraseniaBibliotecario.getText();

            boolean actualizado = Administrador.modificarEmpleado(biblioteca, id, nombre, correo, contrasenia);
            if (!actualizado) {
                Utils.mostrarAlerta("Actualizar", "No se encontró un bibliotecario con ese ID.");
            } else {
                actualizarTabla();
                limpiarCamposBibliotecario();
            }
        } catch (Exception e) {
            Utils.mostrarAlerta("Error al actualizar bibliotecario", e.getMessage());
        }
    }

    //Elimina al empleado seleccionado en la tabla
    @FXML
    private void EliminarEmpleado() {
        Empleado seleccionado = tableEmpleado.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                Administrador.eliminarEmpleado(biblioteca, seleccionado);
                actualizarTabla();
            } catch (Exception e) {
                Utils.mostrarAlerta("Error al eliminar empleado", e.getMessage());
            }
        } else {
            Utils.mostrarAlerta("Eliminar", "Debe seleccionar un empleado de la tabla.");
        }
    }

    //Metodo para limpiar los campos de texto del formulario de Administrador
    private void limpiarCamposAdministrador() {
        txtIdAdministrador.clear();
        txtNombreAdministrador.clear();
        txtCorreoAdministrador.clear();
        txtContraseniaAdministrador.clear();
    }

    //Metodo para limpiar los campos de texto del formulario de Bibliotecario
    private void limpiarCamposBibliotecario() {
        txtIdBibliotecario.clear();
        txtNombreBibliotecario.clear();
        txtCorreoBibliotecario.clear();
        txtContraseniaBibliotecario.clear();
    }

    //Cierra la ventana actual y abre la vista OpcionesAdministrador.fxml.
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        cargarVentana("/Views/OpcionesAdministrador.fxml", "Opciones Administrador");
    }
}
