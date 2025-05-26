package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.util.List;

import static utils.Utils.cargarVentana;

//Controlador para la vista que muestra los libros disponibles
public class MostrarLibrosController {

    //tablas para cada tipo de libro
    @FXML private TableView<LibroFisico> tableFisicos;
    @FXML private TableView<LibroDigital> tableDigitales;
    @FXML private TableView<LibroReferencia> tableReferencias;

    //Columnas para libros fisicos
    @FXML private TableColumn<LibroFisico, Integer> columIdFisico;
    @FXML private TableColumn<LibroFisico, String> columTituloFisico;
    @FXML private TableColumn<LibroFisico, String> columAutorFisico;
    @FXML private TableColumn<LibroFisico, String> columGeneroFisico;
    @FXML private TableColumn<LibroFisico, Integer> columAnioFisico;
    @FXML private TableColumn<LibroFisico, String> columPaginasFisico;
    @FXML private TableColumn<LibroFisico, String> columUbicacionFisico;

    //Columnas para libros digitales
    @FXML private TableColumn<LibroDigital, Integer> columIdDigital;
    @FXML private TableColumn<LibroDigital, String> columTituloDigital;
    @FXML private TableColumn<LibroDigital, String> columAutorDigital;
    @FXML private TableColumn<LibroDigital, String> columGeneroDigital;
    @FXML private TableColumn<LibroDigital, Integer> columAnioDigital;
    @FXML private TableColumn<LibroDigital, String> columEnlaceDigital;
    @FXML private TableColumn<LibroDigital, Formato> columFormatoDigital;

    //Columnas para libros de referencia
    @FXML private TableColumn<LibroReferencia, Integer> columIdReferencia;
    @FXML private TableColumn<LibroReferencia, String> columTituloReferencia;
    @FXML private TableColumn<LibroReferencia, String> columAutorReferencia;
    @FXML private TableColumn<LibroReferencia, String> columGeneroReferencia;
    @FXML private TableColumn<LibroReferencia, Integer> columAnioReferencia;

    //Componente para mostrar la portada del libro seleccionado
    @FXML private ImageView imgPortada;

    //Instancia unica de la biblioteca
    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    /**
     * Metodo de inicializacion llamado automáticamente al cargar la vista
     * configura las columnas de las tablas y carga los libros disponibles
     * ademas, añaade manejadores para mostrar la portada cuando se selecciona un libro
     */

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarLibrosDisponibles();

        //Mostrar portada al seleccionar un libro fisico
        tableFisicos.setOnMouseClicked(event -> {
            LibroFisico seleccionado = tableFisicos.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                mostrarPortada(seleccionado.getPortada());
            }
        });

        //Mostrar portada al seleccionar un libro digital
        tableDigitales.setOnMouseClicked(event -> {
            LibroDigital seleccionado = tableDigitales.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                mostrarPortada(seleccionado.getPortada());
            }
        });

        //Mostrar portada al seleccionar un libro de referencia
        tableReferencias.setOnMouseClicked(event -> {
            LibroReferencia seleccionado = tableReferencias.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                mostrarPortada(seleccionado.getPortada());
            }
        });

    }
    //Configura las columnas de cada tableView asignandole las propiedades correspondientes de los libros
    private void configurarColumnas() {
        // Fisico
        columIdFisico.setCellValueFactory(new PropertyValueFactory<>("id"));
        columTituloFisico.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columAutorFisico.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columGeneroFisico.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columAnioFisico.setCellValueFactory(new PropertyValueFactory<>("anio"));
        columPaginasFisico.setCellValueFactory(new PropertyValueFactory<>("paginas"));
        columUbicacionFisico.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));

        // Digital
        columIdDigital.setCellValueFactory(new PropertyValueFactory<>("id"));
        columTituloDigital.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columAutorDigital.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columGeneroDigital.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columAnioDigital.setCellValueFactory(new PropertyValueFactory<>("anio"));
        columEnlaceDigital.setCellValueFactory(new PropertyValueFactory<>("enlace"));
        columFormatoDigital.setCellValueFactory(new PropertyValueFactory<>("formato"));

        // Referencia
        columIdReferencia.setCellValueFactory(new PropertyValueFactory<>("id"));
        columTituloReferencia.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        columAutorReferencia.setCellValueFactory(new PropertyValueFactory<>("autor"));
        columGeneroReferencia.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columAnioReferencia.setCellValueFactory(new PropertyValueFactory<>("anio"));
    }

    //Carga en las tablas los libros disponibles de la biblioteca separandolos de su tipo
    private void cargarLibrosDisponibles() {
        List<Libro> disponibles = Bibliotecario.obtenerLibrosDisponibles(biblioteca);

        for (Libro libro : disponibles) {
            if (libro instanceof LibroFisico) {
                tableFisicos.getItems().add((LibroFisico) libro);
            } else if (libro instanceof LibroDigital) {
                tableDigitales.getItems().add((LibroDigital) libro);
            } else if (libro instanceof LibroReferencia) {
                tableReferencias.getItems().add((LibroReferencia) libro);
            }
        }
    }

    //Muestra la portada del libro en el imageView
    private void mostrarPortada(String pathPortada) {
        if (pathPortada != null && !pathPortada.isBlank()) {
            File file = new File(pathPortada);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imgPortada.setImage(image);
                return;
            }
        }
        imgPortada.setImage(null);
    }

    //Vuelve a la vista anterior
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        cargarVentana("/Views/Login.fxml", "Menu principal");
    }
}
