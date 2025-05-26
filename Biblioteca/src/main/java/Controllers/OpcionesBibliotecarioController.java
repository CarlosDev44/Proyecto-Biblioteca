package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Bibliotecario;
import model.Biblioteca;
import utils.Utils;


import static utils.Utils.cargarVentana;

//Controlador para la vista de las opciones del bibliotecario
public class OpcionesBibliotecarioController {

    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    //Metodo para registrar un nuevo usuario
    @FXML
    private void RegistrarUsuario(ActionEvent event) {
        Utils.cargarVentana("/Views/RegistrarUsuario.fxml", "Registrar Usuario");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para registrar un nuevo prestamo
    @FXML
    private void RegistrarPrestamo(ActionEvent event) {
        Utils.cargarVentana("/Views/RegistrarPrestamo.fxml", "Registrar Prestamo");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para cambiar el estado de un usuario
    @FXML
    private void CambiarEstadoUsuario(ActionEvent event) {
        cargarVentana("/Views/CambiarEstadoUsuario.fxml", "Cambiar Estado Usuario");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para agregar un nuevo libro
    @FXML
    private void AgregarLibro(ActionEvent event) {
        Utils.cargarVentana("/Views/AgregarLibro.fxml", "Agregar Libro");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para registrar una devolucion
    @FXML
    private void RegistrarDevolucion(ActionEvent event) {
        Utils.cargarVentana("/Views/RegistrarDevolucion.fxml", "Registrar Devolución");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para generar un reporte en un archivo txt (Se genera en la ruta del proyecto)
    @FXML
    private void GenerarReporte() {
        try {
            Bibliotecario.generarReporteNormalTxt(biblioteca);
            Utils.mostrarAlerta("Reporte", "Reporte generado en archivo txt en la ruta actual, exitoso.");

        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Error al generar reporte" + e.getMessage());
        }
    }

    //Metodo para enviar correos a usuarios
    @FXML
    private void EnviarCorreo(ActionEvent event)
    {
        Utils.cargarVentana("/Views/EnviarCorreo.fxml", "Enviar Correo");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para volver a la vista anterior
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        cargarVentana("/Views/Login.fxml", "Menu principal");
    }

}