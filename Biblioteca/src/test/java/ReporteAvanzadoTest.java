import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteAvanzadoTest {

    private Biblioteca biblioteca;
    private ReporteAvanzado reporteAvanzado;
    private UsuarioPrestamo usuario1;
    private UsuarioPrestamo usuario2;
    private Libro libro1;
    private Libro libro2;
    private Prestamo prestamo1;
    private Prestamo prestamo2;

    @BeforeEach
    public void setUp() {
        biblioteca = Biblioteca.getInstancia();
        biblioteca.getListEmpleados().clear();
        biblioteca.getListLibros().clear();
        biblioteca.getListUsuarios().clear();
        biblioteca.getListPrestamos().clear();

        reporteAvanzado = new ReporteAvanzado();

        libro1 = new LibroFisico(1, "1984", "George Orwell", "Ficción", 300, "Español", Disponibilidad.DISPONIBLE, "123", "Tapa dura", "Editorial");
        libro2 = new LibroFisico(2, "Don Quijote", "Cervantes", "Novela", 500, "Español", Disponibilidad.DISPONIBLE, "456", "Tapa blanda", "Editorial");

        usuario1 = new Estudiante("Ana", 1, "ana@correo.com");
        usuario2 = new Estudiante("Luis", 2, "luis@correo.com");

        prestamo1 = new Prestamo(1, List.of(libro1), usuario1, EstadoPrestamo.EN_CURSO);
        prestamo2 = new Prestamo(2, List.of(libro2), usuario2, EstadoPrestamo.EN_CURSO);

        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarUsuario(usuario1);
        biblioteca.agregarUsuario(usuario2);
        biblioteca.agregarPrestamo(prestamo1);
        biblioteca.agregarPrestamo(prestamo2);

        usuario1.agregarPrestamo(prestamo1);
        usuario2.agregarPrestamo(prestamo2);

        libro1.incrementarVecesPrestamo();
    }

    @Test
    public void testObtenerTodosLosPrestamos() {
        List<String> prestamos = reporteAvanzado.obtenerTodosLosPrestamos(biblioteca);
        assertEquals(2, prestamos.size());
        assertTrue(prestamos.get(0).contains("1984"));
        assertTrue(prestamos.get(1).contains("Don Quijote"));
    }

    @Test
    public void testObtenerPrestamosPorUsuario() {
        List<UsuarioComun> usuarios = List.of(usuario1, usuario2);
        Map<UsuarioPrestamo, List<Prestamo>> mapa = reporteAvanzado.obtenerPrestamosPorUsuario(usuarios);

        assertEquals(2, mapa.size());
        assertTrue(mapa.containsKey(usuario1));
        assertTrue(mapa.containsKey(usuario2));
        assertEquals(1, mapa.get(usuario1).size());
        assertEquals(1, mapa.get(usuario2).size());
    }


    @Test
    public void testLibrosNuncaPrestados() {
        List<String> librosNuncaPrestados = reporteAvanzado.librosNuncaPrestados(biblioteca);
        assertEquals(1, librosNuncaPrestados.size());
        assertTrue(librosNuncaPrestados.contains("Don Quijote"));
    }

}
