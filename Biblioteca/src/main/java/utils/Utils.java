package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

//Clase de utilidades generales
public class Utils {

    //Carga una nueva ventana a partir de un archivo fxml y le asigna un titulo
    public static void cargarVentana(String rutaFXML, String tituloVentana) {
        try {
            // Carga la vista fxml especificada
            FXMLLoader loader = new FXMLLoader(Utils.class.getResource(rutaFXML));
            Parent root = loader.load();

            //Crea y configura una nueva ventana
            Stage stage = new Stage();
            stage.setTitle(tituloVentana);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            //Imprime la excepcion si ocurre un erro al cargar el archivo
            e.printStackTrace();
        }
    }

    //Muestra una alerta informativa al usuario con un titulo y un mensaje
    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null); //No se muestra un encabezado adicional
        alert.setContentText(mensaje);
        alert.showAndWait(); // Espera a que el usuario cierre la alerta
    }
}