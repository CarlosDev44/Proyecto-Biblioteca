package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Biblioteca;
import model.Bibliotecario;
import model.Docente;
import model.Estudiante;
import utils.Utils;


import static utils.Utils.mostrarAlerta;

//Controlador para la vista de registrar un usuario
public class RegistrarUsuarioController {

    //Campos de texto para datos del estudiante
    @FXML
    private TextField txtNombreEstudiante;

    @FXML
    private TextField txtIdentificacionEstudiante;

    @FXML
    private TextField txtCorreoEstudiante;

    //Campos de texto para datos del docente
    @FXML
    private TextField txtNombreDocente;

    @FXML
    private TextField txtIdentificacionDocente;

    @FXML
    private TextField txtCorreoDocente;

    //Instancia unica de biblioteca
    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    //Metodo para registrar un nuevo estudiante
    @FXML
    private void RegistrarEstudiante() {
        try {
            String nombre = txtNombreEstudiante.getText().trim();
            int id = Integer.parseInt(txtIdentificacionEstudiante.getText().trim());
            String correo = txtCorreoEstudiante.getText().trim();

            Estudiante estudiante = new Estudiante(nombre, id, correo);
            Bibliotecario.registrarUsuario(biblioteca, estudiante);
            Utils.mostrarAlerta("Éxito", "Estudiante registrado correctamente.");

            limpiarCamposEstudiante();

        } catch (NumberFormatException e) {
            Utils.mostrarAlerta("Error", "El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            Utils.mostrarAlerta("Error", e.getMessage());
        }
    }
    //Metodo para registrar un nuevo docente
    @FXML
    private void RegistrarDocente() {
        try {
            String nombre = txtNombreDocente.getText().trim();
            int id = Integer.parseInt(txtIdentificacionDocente.getText().trim());
            String correo = txtCorreoDocente.getText().trim();

            Docente docente = new Docente(nombre, id, correo);
            Bibliotecario.registrarUsuario(biblioteca, docente);
            Utils.mostrarAlerta("Éxito", "Docente registrado correctamente.");

            limpiarCamposDocente();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }

    //Metodo para volver a la vista anterior
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        Utils.cargarVentana("/Views/OpcionesBibliotecario.fxml", "Panel Bibliotecario");
    }

    //Metodos para limpiar los campos del formulario
    private void limpiarCamposEstudiante() {
        txtNombreEstudiante.clear();
        txtIdentificacionEstudiante.clear();
        txtCorreoEstudiante.clear();
    }

    private void limpiarCamposDocente() {
        txtNombreDocente.clear();
        txtIdentificacionDocente.clear();
        txtCorreoDocente.clear();
    }

}
