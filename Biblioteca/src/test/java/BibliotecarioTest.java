import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecarioTest {

    private Biblioteca biblioteca;
    private Libro libro;
    private UsuarioPrestamo usuario;

    @BeforeEach
    public void setUp() {
        biblioteca = Biblioteca.getInstancia();
        biblioteca.getListEmpleados().clear();
        biblioteca.getListLibros().clear();
        biblioteca.getListUsuarios().clear();
        biblioteca.getListPrestamos().clear();

        libro = new LibroFisico(1, "1984", "George Orwell", "Ficción", 300, "Español", Disponibilidad.DISPONIBLE, "123", "Tapa dura", "Editorial");
        usuario = new Estudiante("Carlos", 2, "carlos@correo.com");
    }

    @Test
    public void testAgregarLibro() {
        Bibliotecario.agregarLibro(biblioteca, libro);
        assertTrue(biblioteca.getListLibros().contains(libro));
    }

    @Test
    public void testRegistrarUsuario() {
        Bibliotecario.registrarUsuario(biblioteca, usuario);
        assertTrue(biblioteca.getListUsuarios().contains(usuario));
    }

    @Test
    public void testRegistrarPrestamo() {
        Prestamo prestamo = new Prestamo(1, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);
        Bibliotecario.registrarPrestamo(biblioteca, prestamo);

        assertTrue(biblioteca.getListPrestamos().contains(prestamo));
        assertEquals(Disponibilidad.PRESTADO, libro.getDisponibilidad());
        assertEquals(1, libro.getVecesPrestado());
        assertTrue(usuario.getListPrestamos().contains(prestamo));
        assertEquals(EstadoPrestamo.EN_CURSO, prestamo.getEstadoPrestamo());
    }

    @Test
    public void testRegistrarDevolucion() {
        Prestamo prestamo = new Prestamo(2, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);
        Bibliotecario.registrarDevolucion(prestamo);

        assertEquals(EstadoPrestamo.DEVUELTO, prestamo.getEstadoPrestamo());
        assertEquals(LocalDate.now(), prestamo.getFechaDevolucion());
    }

    @Test
    public void testRegistrarDevolucionAtrasada() {
        Prestamo prestamo = new Prestamo(3, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);
        Bibliotecario.registrarDevolucionAtrasada(prestamo);

        assertEquals(EstadoPrestamo.ATRASADO, prestamo.getEstadoPrestamo());
        assertEquals(LocalDate.now(), prestamo.getFechaDevolucion());
        assertEquals(EstadoUsuario.CON_DEUDA, usuario.getEstadoUsuario());
    }


    @Test
    public void testGenerarReporteNormalTxt() {
        Prestamo prestamo = new Prestamo(4, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);
        Bibliotecario.registrarUsuario(biblioteca, usuario);
        Bibliotecario.registrarPrestamo(biblioteca, prestamo);

        Bibliotecario.generarReporteNormalTxt(biblioteca);

        File archivo = new File("reporte_normal.txt");
        assertTrue(archivo.exists());
    }

    @Test
    public void testObtenerUsuariosDisponiblesParaPrestamo() {

        UsuarioPrestamo usuario1 = new Estudiante("Estudiante1", 10, "est1@correo.com");
        usuario1.setEstadoUsuario(EstadoUsuario.SIN_DEUDA);
        usuario1.setLimitePrestamos(2);
        Prestamo prestamo1 = new Prestamo(101, List.of(libro), usuario1, EstadoPrestamo.EN_CURSO);
        usuario1.getListPrestamos().add(prestamo1);

        UsuarioPrestamo usuario2 = new Estudiante("Estudiante2", 20, "est2@correo.com");
        usuario2.setEstadoUsuario(EstadoUsuario.SIN_DEUDA);
        usuario2.setLimitePrestamos(2);
        Prestamo prestamo2a = new Prestamo(102, List.of(libro), usuario2, EstadoPrestamo.EN_CURSO);
        Prestamo prestamo2b = new Prestamo(103, List.of(libro), usuario2, EstadoPrestamo.EN_CURSO);
        usuario2.getListPrestamos().add(prestamo2a);
        usuario2.getListPrestamos().add(prestamo2b);

        UsuarioPrestamo usuario3 = new Estudiante("Estudiante3", 30, "est3@correo.com");
        usuario3.setEstadoUsuario(EstadoUsuario.CON_DEUDA);
        usuario3.setLimitePrestamos(2);
        Prestamo prestamo3 = new Prestamo(104, List.of(libro), usuario3, EstadoPrestamo.EN_CURSO);
        usuario3.getListPrestamos().add(prestamo3);

        biblioteca.getListUsuarios().clear();
        biblioteca.getListUsuarios().add(usuario1);
        biblioteca.getListUsuarios().add(usuario2);
        biblioteca.getListUsuarios().add(usuario3);

        List<UsuarioPrestamo> disponibles = Bibliotecario.obtenerUsuariosDisponiblesParaPrestamo(biblioteca);

        assertEquals(1, disponibles.size());
        assertTrue(disponibles.contains(usuario1));
        assertFalse(disponibles.contains(usuario2));
        assertFalse(disponibles.contains(usuario3));
    }
    @Test
    public void testObtenerLibrosDisponibles() {
        Libro libro1 = new LibroFisico(1, "1984", "Orwell", "Ficción", 300, "Inglés", Disponibilidad.DISPONIBLE, "5678", "Tapa blanda", "Planeta");
        Libro libro2 = new LibroFisico(2, "El Señor de los Anillos", "Tolkien", "Fantasía", 500, "Español", Disponibilidad.PRESTADO, "6789", "Tapa dura", "Minotauro");
        Libro libro3 = new LibroFisico(3, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", 400, "Español", Disponibilidad.DISPONIBLE, "9999", "Tapa dura", "Sudamericana");

        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);

        List<Libro> disponibles = Bibliotecario.obtenerLibrosDisponibles(biblioteca);

        assertNotNull(disponibles);
        assertEquals(2, disponibles.size());
        assertTrue(disponibles.contains(libro1));
        assertTrue(disponibles.contains(libro3));
        assertFalse(disponibles.contains(libro2));
    }

    @Test
    public void testCambiarEstadoDeudaSinDeuda() {
        usuario.setEstadoUsuario(EstadoUsuario.CON_DEUDA);
        Bibliotecario.cambiarEstadoDeudaSinDeuda(usuario);
        assertEquals(EstadoUsuario.SIN_DEUDA, usuario.getEstadoUsuario());
    }

    @Test
    public void testCambiarEstadoDeudaConDeuda() {
        usuario.setEstadoUsuario(EstadoUsuario.SIN_DEUDA);
        Bibliotecario.cambiarEstadoDeudaConDeuda(usuario);
        assertEquals(EstadoUsuario.CON_DEUDA, usuario.getEstadoUsuario());
    }

    @Test
    public void testObtenerPrestamosEnCurso() {
        Prestamo prestamo = new Prestamo(5, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);
        biblioteca.agregarPrestamo(prestamo);
        List<Prestamo> enCurso = Bibliotecario.obtenerPrestamosEnCurso(biblioteca);
        assertTrue(enCurso.contains(prestamo));
    }


}

