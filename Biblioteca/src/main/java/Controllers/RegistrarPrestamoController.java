package Controllers;

import java.util.ArrayList;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import utils.Utils;

//Controlador para la vista de registrar un prestamo
public class RegistrarPrestamoController {

    //Campos de entrada de datos
    @FXML private TextField txtIdPrestamo;
    @FXML private TextField txtIdLibro;
    @FXML private TextField txtIdLibro2;
    @FXML private TextField txtIdLibro3;
    @FXML private TextField txtIdUsuario;

    //Tabla de libros disponibles
    @FXML private TableView<LibroVista> tablaLibros;
    @FXML private TableColumn<LibroVista, Integer> colIdLibro;
    @FXML private TableColumn<LibroVista, String> colNombreLibro;

    //Tabla de usuarios disponibles
    @FXML private TableView<UsuarioVista> tablaUsuarios;
    @FXML private TableColumn<UsuarioVista, Integer> colIdUsuario;
    @FXML private TableColumn<UsuarioVista, String> colNombreUsuario;

    // Acceso a la instancia unica de la biblioteca
    private Biblioteca biblioteca = Biblioteca.getInstancia();

    //Inicializa el controlador configurando las columnas de las tablas
    @FXML
    private void initialize() {
        // Configurar columnas libro
        colIdLibro.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreLibro.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        // Configurar columnas usuario
        colIdUsuario.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        cargarLibrosDisponibles();
        cargarUsuariosDisponibles();

        //Las tablas son solo de visualizacion
        tablaLibros.setSelectionModel(null);
        tablaUsuarios.setSelectionModel(null);
        tablaLibros.setEditable(false);
        tablaUsuarios.setEditable(false);
    }

    //Metodo para cargar los libros disponibles para prestamo en la tabla
    private void cargarLibrosDisponibles() {
        List<Libro> libros = Bibliotecario.obtenerLibrosDisponibles(biblioteca);
        if (libros == null) libros = new ArrayList<>(); // Por si acaso

        if (libros.isEmpty()) {
            Utils.mostrarAlerta("Aviso", "No hay libros disponibles para préstamo actualmente.");
        }

        List<LibroVista> librosVista = new ArrayList<>();
        for (Libro l : libros) {
            librosVista.add(new LibroVista(l.getId(), l.getTitulo()));
        }
        ObservableList<LibroVista> data = FXCollections.observableArrayList(librosVista);
        tablaLibros.setItems(data);
    }

    //Metodo para cargar usuarios aptos para prestamo en la tabla
    private void cargarUsuariosDisponibles() {
        List<UsuarioPrestamo> usuarios = Bibliotecario.obtenerUsuariosDisponiblesParaPrestamo(biblioteca);
        List<UsuarioVista> usuariosVista = new ArrayList<>();
        for (UsuarioPrestamo u : usuarios) {
            usuariosVista.add(new UsuarioVista(u.getId(), u.getNombre()));
        }
        ObservableList<UsuarioVista> data = FXCollections.observableArrayList(usuariosVista);
        tablaUsuarios.setItems(data);
    }

    //Registra un nuevo prestamo a partir de los datos ingresados
    @FXML
    private void RegistrarPrestamo() {
        try {

            //Validacion de datos minimos requeridos
            if (tablaLibros.getItems().isEmpty()) {
                Utils.mostrarAlerta("Error", "No hay libros disponibles. No se puede registrar un préstamo.");
                return;
            }

            if (txtIdPrestamo.getText().isBlank() || txtIdLibro.getText().isBlank() || txtIdUsuario.getText().isBlank()) {
                Utils.mostrarAlerta("Error", "Campos incompletos,Debe ingresar ID prestamo, Libro 1 y Usuario.");
                return;
            }

            int idPrestamo = Integer.parseInt(txtIdPrestamo.getText().trim());
            int idLibro1 = Integer.parseInt(txtIdLibro.getText().trim());
            int idUsuario = Integer.parseInt(txtIdUsuario.getText().trim());

            // Obtener usuario
            UsuarioPrestamo usuario = biblioteca.obtenerUsuarioPrestamoPorId(idUsuario);
            if (usuario == null) {
                Utils.mostrarAlerta("Error", "No existe un usuario con ese ID o no es apto para préstamo.");
                return;
            }

            // Obtener libros
            List<Libro> librosSolicitados = new ArrayList<>();

            Libro libro1 = biblioteca.obtenerLibroPorId(idLibro1);
            if (libro1 == null || libro1.getDisponibilidad() != Disponibilidad.DISPONIBLE) {
                Utils.mostrarAlerta("Error", "El libro 1 no existe o no está disponible.");
                return;
            }
            librosSolicitados.add(libro1);

            // Libro 2 opcional
            String libro2Texto = txtIdLibro2.getText().trim();
            if (!libro2Texto.isEmpty()) {
                try {
                    int idLibro2 = Integer.parseInt(libro2Texto);
                    Libro libro2 = biblioteca.obtenerLibroPorId(idLibro2);
                    if (libro2 == null || libro2.getDisponibilidad() != Disponibilidad.DISPONIBLE) {
                        Utils.mostrarAlerta("Error", "Libro 2 inválido");
                        return;
                    }
                    librosSolicitados.add(libro2);
                } catch (NumberFormatException e) {
                    Utils.mostrarAlerta("Erro", "Libro 2 inválido");
                    return;
                }
            }

            // Libro 3 opcional
            String libro3Texto = txtIdLibro3.getText().trim();
            if (!libro3Texto.isEmpty()) {
                try {
                    int idLibro3 = Integer.parseInt(libro3Texto);
                    Libro libro3 = biblioteca.obtenerLibroPorId(idLibro3);
                    if (libro3 == null || libro3.getDisponibilidad() != Disponibilidad.DISPONIBLE) {
                        Utils.mostrarAlerta("Error", "Libro 3 inválido");
                        return;
                    }
                    librosSolicitados.add(libro3);
                } catch (NumberFormatException e) {
                    Utils.mostrarAlerta("Error" ,"Libro 3 inválido");
                    return;
                }
            }

            //Crear prestamo y registrarlo
            Prestamo nuevoPrestamo = new Prestamo(idPrestamo, librosSolicitados, usuario, null);

            Bibliotecario.registrarPrestamo(biblioteca, nuevoPrestamo);

            Utils.mostrarAlerta( "Éxito", "Préstamo registrado correctamente.");


            limpiarCampos();
            cargarLibrosDisponibles();

        } catch (NumberFormatException e) {
            Utils.mostrarAlerta("Error","Error de formato");
        } catch (Exception e) {
            Utils.mostrarAlerta("Error", "Error inesperado" + e.getMessage());
        }
    }

    //limpia los campos de texto despues de registrar un prestamo
    private void limpiarCampos() {
        txtIdPrestamo.clear();
        txtIdLibro.clear();
        txtIdLibro2.clear();
        txtIdLibro3.clear();
        txtIdUsuario.clear();
    }

    //Vuelve a la vista anterior
    @FXML
    private void VolverAtras(ActionEvent event){

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        Utils.cargarVentana("/Views/OpcionesBibliotecario.fxml", "Panel Bibliotecario");
    }

    //Clase auxiliar para mostrar libros en la tabla sin exponer la clase original
    public static class LibroVista {
        private final Integer id;
        private final String titulo;

        public LibroVista(Integer id, String titulo) {
            this.id = id;
            this.titulo = titulo;
        }

        public Integer getId() { return id; }
        public String getTitulo() { return titulo; }
    }

    //Clase auxiliar para mostrar usuarios en la tabla sin exponer la clase original
    public static class UsuarioVista {
        private final Integer id;
        private final String nombre;

        public UsuarioVista(Integer id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public Integer getId() { return id; }
        public String getNombre() { return nombre; }
    }

}