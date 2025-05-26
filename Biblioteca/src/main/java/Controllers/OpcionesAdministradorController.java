package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.Administrador;
import model.Biblioteca;
import utils.Utils;

import static utils.Utils.cargarVentana;

//Controlador para la vista de las opciones de administrador
public class OpcionesAdministradorController {

    //Instancia singleton de la biblioteca
    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    //metodo para administrar los empleados (Agregar, modificar, eliminar, actualizar)
    @FXML
    private void AdministrarEmpleados(ActionEvent event) {
        Utils.cargarVentana("/Views/AdministrarEmpleados.fxml", "Administrar Empleados");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //Metodo para generar un reporte en formato txt (se genera en la ruta donde está el proyecto)
    @FXML
    private void GenerarReporte(ActionEvent event) {
        try {
            Administrador.generarReporteAvanzadoTxt(biblioteca);
            Utils.mostrarAlerta("Reporte", "Reporte generado en archivo txt en la ruta actual, exitoso.");

        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Error al generar reporte" + e.getMessage());
        }

    }
    //Metodo para volver a la ventana anterior
    @FXML
     private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        cargarVentana("/Views/Login.fxml", "Menu principal");
    }

}
