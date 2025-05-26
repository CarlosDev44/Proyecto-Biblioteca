import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteNormalTest {

    private Biblioteca biblioteca;
    private ReporteNormal reporteNormal;
    private Libro libro1;
    private Libro libro2;
    private Libro libro3;
    private UsuarioPrestamo usuario1;
    private UsuarioPrestamo usuario2;

    @BeforeEach
    public void setUp() {
        biblioteca = Biblioteca.getInstancia();
        biblioteca.getListEmpleados().clear();
        biblioteca.getListLibros().clear();
        biblioteca.getListUsuarios().clear();
        biblioteca.getListPrestamos().clear();

        reporteNormal = new ReporteNormal();

        libro1 = new LibroFisico(1, "1984", "George Orwell", "Ficción", 300, "Español", Disponibilidad.PRESTADO, "123", "Tapa dura", "Editorial");
        libro2 = new LibroFisico(2, "Don Quijote", "Cervantes", "Novela", 500, "Español", Disponibilidad.DISPONIBLE, "456", "Tapa blanda", "Editorial");
        libro3 = new LibroFisico(3, "Hamlet", "Shakespeare", "Drama", 200, "Inglés", Disponibilidad.PRESTADO, "789", "Tapa dura", "Editorial");

        usuario1 = new Estudiante("Ana", 1, "ana@correo.com");
        usuario2 = new Estudiante("Luis", 2, "luis@correo.com");

        usuario1.setEstadoUsuario(EstadoUsuario.CON_DEUDA);
        usuario2.setEstadoUsuario(EstadoUsuario.SIN_DEUDA);

        libro1.setVecesPrestado(5);
        libro2.setVecesPrestado(3);
        libro3.setVecesPrestado(7);

        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);

        biblioteca.agregarUsuario(usuario1);
        biblioteca.agregarUsuario(usuario2);
    }

    @Test
    public void testLibrosPrestados() {
        List<String> librosPrestados = reporteNormal.librosPrestados(biblioteca);
        assertEquals(2, librosPrestados.size());
        assertTrue(librosPrestados.contains("1984"));
        assertTrue(librosPrestados.contains("Hamlet"));
    }

    @Test
    public void testUsuariosConDeudas() {
        List<String> usuariosConDeuda = reporteNormal.usuariosConDeudas(biblioteca);
        assertEquals(1, usuariosConDeuda.size());
        assertTrue(usuariosConDeuda.contains("Ana"));
    }

    @Test
    public void testObtenerTopLibrosMasPrestados() {
        List<String> topLibros = reporteNormal.obtenerTopLibrosMasPrestados(biblioteca);
        assertEquals(3, topLibros.size());
        assertEquals("Hamlet - Veces prestado: 7", topLibros.get(0));
        assertEquals("1984 - Veces prestado: 5", topLibros.get(1));
        assertEquals("Don Quijote - Veces prestado: 3", topLibros.get(2));
    }
}
