package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import utils.Utils;

import java.io.File;

import static utils.Utils.cargarVentana;

//Controlador para la vista AgregarLibro.fxml
public class AgregarLibroController {

    // Campos para libro fisico
    @FXML private TextField txtIdFisico;
    @FXML private TextField txtTituloFisico;
    @FXML private TextField txtAutorFisico;
    @FXML private TextField txtGeneroFisico;
    @FXML private TextField txtanioFisico;
    @FXML private TextField txtPaginasFisico;
    @FXML private TextField txtEditorialFisico;
    @FXML private TextField txtUbicacionFisico;
    @FXML private ImageView imgPortadaFisico;
    private String pathPortadaFisico = null;

    // Campos para libro digital
    @FXML private TextField txtIdDigital;
    @FXML private TextField txtTituloDigital;
    @FXML private TextField txtAutorDigital;
    @FXML private TextField txtGeneroDigital;
    @FXML private TextField txtAnioDigital;
    @FXML private TextField txtEnlaceDigital;
    @FXML private ComboBox<String> combFormatodigital;
    @FXML private ImageView imgPortadaDigital;
    private String pathPortadaDigital = null;

    // Campos para libro referencia
    @FXML private TextField txtIdReferencia;
    @FXML private TextField txtTituloReferencia;
    @FXML private TextField txtAutorReferencia;
    @FXML private TextField txtGeneroReferencia;
    @FXML private TextField txtAnioReferencia;
    @FXML private TextField txtEditorialReferencia;
    @FXML private TextField txtUbicacionReferencia;
    @FXML private ImageView imgPortadaReferencia;
    private String pathPortadaReferencia = null;

    // Instancia de la biblioteca
    private final Biblioteca biblioteca = Biblioteca.getInstancia();

    //Inicializa los componentes de la vista
    //Agrega formatos al combobox y listeners a las imagenes para permitir seleccionar portadas
    @FXML
    public void initialize() {
        combFormatodigital.getItems().addAll("PDF", "EPUB", "MOB");

        imgPortadaFisico.setOnMouseClicked(e -> seleccionarImagenFisico());
        imgPortadaDigital.setOnMouseClicked(e -> seleccionarImagenDigital());
        imgPortadaReferencia.setOnMouseClicked(e -> seleccionarImagenReferencia());
    }

    //Metodos para seleccionar imagen segun el tipo de libro
    private void seleccionarImagenFisico() {
        pathPortadaFisico = seleccionarImagen(imgPortadaFisico);
    }

    private void seleccionarImagenDigital() {
        pathPortadaDigital = seleccionarImagen(imgPortadaDigital);
    }

    private void seleccionarImagenReferencia() {
        pathPortadaReferencia = seleccionarImagen(imgPortadaReferencia);
    }

    //Abre un filechooser para seleccionar una imagen y la muestra en el imageview correspondiente
    private String seleccionarImagen(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona la portada");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (selectedFile != null) {
            Image img = new Image(selectedFile.toURI().toString());
            imageView.setImage(img);
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    //Guarda un libro físico en la biblioteca usando los datos del formulario
    @FXML
    private void GuardarFisico(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIdFisico.getText());
            String titulo = txtTituloFisico.getText();
            String autor = txtAutorFisico.getText();
            String genero = txtGeneroFisico.getText();
            int anio = Integer.parseInt(txtanioFisico.getText());
            String paginas = txtPaginasFisico.getText();
            String editorial = txtEditorialFisico.getText();
            String ubicacion = txtUbicacionFisico.getText();
            String portada = pathPortadaFisico != null ? pathPortadaFisico : "";


            Disponibilidad disponibilidad = Disponibilidad.DISPONIBLE;

            LibroFisico libroFisico = new LibroFisico(id, titulo, autor, genero, anio, portada, disponibilidad, paginas, editorial, ubicacion);

            Bibliotecario.agregarLibro(biblioteca, libroFisico);

            Utils.mostrarAlerta("Éxito", "Libro físico registrado correctamente.");
            limpiarCamposFisico();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", e.getMessage());
        }
    }

    //Guarda un libro digital en la biblioteca usando los datos del formulario
    @FXML
    private void GuardarDigital(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIdDigital.getText());
            String titulo = txtTituloDigital.getText();
            String autor = txtAutorDigital.getText();
            String genero = txtGeneroDigital.getText();
            int anio = Integer.parseInt(txtAnioDigital.getText());
            String enlace = txtEnlaceDigital.getText();
            String formatoString = combFormatodigital.getValue();
            Formato formatoEnum = Formato.valueOf(formatoString.toUpperCase());
            String portada = pathPortadaDigital != null ? pathPortadaDigital : "";

            Disponibilidad disponibilidad = Disponibilidad.DISPONIBLE;

            LibroDigital libroDigital = new LibroDigital(id, titulo, autor, genero, anio, portada, disponibilidad, enlace, formatoEnum);

            Bibliotecario.agregarLibro(biblioteca, libroDigital);

            Utils.mostrarAlerta("Éxito", "Libro digital registrado correctamente.");
            limpiarCamposDigital();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", e.getMessage());
        }
    }

    //Guarda un libro de referencia en la biblioteca usando los datos del formulario
    @FXML
    private void GuardarReferencia(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtIdReferencia.getText());
            String titulo = txtTituloReferencia.getText();
            String autor = txtAutorReferencia.getText();
            String genero = txtGeneroReferencia.getText();
            int anio = Integer.parseInt(txtAnioReferencia.getText());
            String portada = pathPortadaReferencia != null ? pathPortadaReferencia : "";

            Disponibilidad disponibilidad = Disponibilidad.DISPONIBLE;

            LibroReferencia libroReferencia = new LibroReferencia(id, titulo, autor, genero, anio, portada, disponibilidad);

            Bibliotecario.agregarLibro(biblioteca, libroReferencia);

            Utils.mostrarAlerta("Éxito", "Libro de referencia registrado correctamente.");
            limpiarCamposReferencia();
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", e.getMessage());
        }
    }

    // Métodos para limpiar los campos del formulario después de guardar
    private void limpiarCamposFisico() {
        txtIdFisico.clear();
        txtTituloFisico.clear();
        txtAutorFisico.clear();
        txtGeneroFisico.clear();
        txtanioFisico.clear();
        txtPaginasFisico.clear();
        txtEditorialFisico.clear();
        txtUbicacionFisico.clear();
        imgPortadaFisico.setImage(null);
        pathPortadaFisico = null;
    }

    private void limpiarCamposDigital() {
        txtIdDigital.clear();
        txtTituloDigital.clear();
        txtAutorDigital.clear();
        txtGeneroDigital.clear();
        txtAnioDigital.clear();
        txtEnlaceDigital.clear();
        combFormatodigital.setValue(null);
        imgPortadaDigital.setImage(null);
        pathPortadaDigital = null;
    }

    private void limpiarCamposReferencia() {
        txtIdReferencia.clear();
        txtTituloReferencia.clear();
        txtAutorReferencia.clear();
        txtGeneroReferencia.clear();
        txtAnioReferencia.clear();
        txtEditorialReferencia.clear();
        txtUbicacionReferencia.clear();
        imgPortadaReferencia.setImage(null);
        pathPortadaReferencia = null;
    }

    //Cierra la ventana actual y vuelve a la vista de opciones del bibliotecario
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        cargarVentana("/Views/OpcionesBibliotecario.fxml", "Opciones Bibliotecario");
    }
}