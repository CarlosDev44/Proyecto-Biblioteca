package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Administrador;
import model.Biblioteca;
import model.Bibliotecario;
import model.Empleado;
import utils.Utils;

//Controlador de la vista de inicio de sesión
public class LoginController {

    //Campo de texto para ingresar la identificacion del empleado
    @FXML
    private PasswordField txtContrasena;

    // Campo de contraseña para ingresar la clave del empleado
    @FXML
    private TextField txtIdentificacion;

    //metodo para ingresar como visitante
    @FXML
    private void EntrarComoVisitante(ActionEvent event) {
        Utils.cargarVentana("/Views/MostrarLibros.fxml", "Libros Disponibles");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Verifica las credenciales ingresadas por el empleado
    //Si son validas, carga la vista correspondiente al tipo de empleado (Administrador o Bibliotecario)
    @FXML
    private void VerificarEmpleado(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIdentificacion.getText().trim());
            String contrasena = txtContrasena.getText();

            Biblioteca biblioteca = Biblioteca.getInstancia();

            if (!biblioteca.existeEmpleadoPorId(id)) {
                Utils.mostrarAlerta("Error", "No se pudo verificar el empleado.");
                return;
            }

            if (biblioteca.autenticar(id, contrasena)) {
                // Obtener el empleado que hizo login
                Empleado empleadoLogueado = biblioteca.obtenerEmpleadoPorId(id);

                if (empleadoLogueado instanceof Administrador) {
                    Utils.mostrarAlerta("Éxito", "Administrador verificado correctamente.");
                    Utils.cargarVentana("/Views/OpcionesAdministrador.fxml", "Panel Administrador");
                    ((Stage) txtIdentificacion.getScene().getWindow()).close();
                } else if (empleadoLogueado instanceof Bibliotecario) {
                    Utils.mostrarAlerta("Éxito", "Bibliotecario verificado correctamente.");
                    Utils.cargarVentana("/Views/OpcionesBibliotecario.fxml", "Panel Bibliotecario");
                    ((Stage) txtIdentificacion.getScene().getWindow()).close();
                }

            } else {
                Utils.mostrarAlerta("Error", "No se pudo verificar el empleado.");
            }

        }
        catch (NumberFormatException e) {
            Utils.mostrarAlerta("Error", "El ID debe ser un número válido.");
        }


    }
}

