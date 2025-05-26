import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecaTest {

    private Biblioteca biblioteca;

    @BeforeEach
    public void setUp() {
        biblioteca = Biblioteca.getInstancia();
        biblioteca.getListEmpleados().clear();
        biblioteca.getListLibros().clear();
        biblioteca.getListUsuarios().clear();
        biblioteca.getListPrestamos().clear();

    }

    @Test
    public void testAgregarLibro() {
        Libro libro = new LibroFisico(1, "El Quijote", "Cervantes", "Novela", 400, "Español", Disponibilidad.DISPONIBLE, "1234", "Tapa dura", "Editorial");
        biblioteca.agregarLibro(libro);

        assertTrue(biblioteca.getListLibros().contains(libro));
    }

    @Test
    public void testAgregarUsuario() {
        UsuarioComun usuario = new Estudiante("Ana", 101, "ana@correo.com");
        biblioteca.agregarUsuario(usuario);

        assertTrue(biblioteca.getListUsuarios().contains(usuario));
    }

    @Test
    public void testAgregarEmpleado() {
        Empleado empleado = new Bibliotecario("Laura", 2, "laura@correo.com", "clave123");
        biblioteca.agregarEmpleado(empleado);

        assertTrue(biblioteca.getListEmpleados().contains(empleado));
    }

    @Test
    public void testEliminarEmpleado() {
        Empleado empleado = new Bibliotecario("Laura", 2, "laura@correo.com", "clave123");
        biblioteca.agregarEmpleado(empleado);
        biblioteca.eliminarEmpleado(empleado);

        assertFalse(biblioteca.getListEmpleados().contains(empleado));
    }

    @Test
    public void testAgregarPrestamo() {
        UsuarioPrestamo usuario = new Estudiante("Carlos", 3, "carlos@correo.com");
        Libro libro = new LibroFisico(1, "1984", "Orwell", "Ficción", 300, "Inglés", Disponibilidad.DISPONIBLE, "5678", "Tapa blanda", "Planeta");
        Prestamo prestamo = new Prestamo(10, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);

        biblioteca.agregarPrestamo(prestamo);
        assertTrue(biblioteca.getListPrestamos().contains(prestamo));
    }

    @Test
    public void testAutenticarEmpleado_Valido() {
        Empleado empleado = new Bibliotecario("Juan", 5, "juan@correo.com", "pass123");
        biblioteca.agregarEmpleado(empleado);

        assertTrue(biblioteca.autenticar(5, "pass123"));
    }

    @Test
    public void testAutenticarEmpleado_Invalido() {
        Empleado empleado = new Bibliotecario("Juan", 5, "juan@correo.com", "pass123");
        biblioteca.agregarEmpleado(empleado);

        // Contraseña incorrecta
        assertFalse(biblioteca.autenticar(5, "malaClave"));
    }

    @Test
    public void testExisteEmpleadoPorId() {
        Empleado empleado = new Bibliotecario("Lucía", 7, "lucia@correo.com", "clave456");
        biblioteca.agregarEmpleado(empleado);

        assertTrue(biblioteca.existeEmpleadoPorId(7));
        assertFalse(biblioteca.existeEmpleadoPorId(999)); // ID que no existe
    }

    @Test
    public void testExistePrestamoPorId() {
        UsuarioPrestamo usuario = new Estudiante("Mario", 8, "mario@correo.com");
        Libro libro = new LibroFisico(3, "Cien Años de Soledad", "Gabriel García Márquez", "Realismo Mágico", 500, "Español", Disponibilidad.DISPONIBLE, "9999", "Tapa dura", "Sudamericana");
        Prestamo prestamo = new Prestamo(20, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);

        biblioteca.agregarPrestamo(prestamo);

        assertTrue(biblioteca.existePrestamoPorId(20));
        assertFalse(biblioteca.existePrestamoPorId(123)); // ID que no existe
    }

    @Test
    public void testExisteUsuarioPorId() {
        UsuarioComun usuario = new Estudiante("Mario", 15, "mario@correo.com");
        biblioteca.agregarUsuario(usuario);

        assertTrue(biblioteca.existeUsuarioPorId(15));
        assertFalse(biblioteca.existeUsuarioPorId(999));
    }

    @Test
    public void testExisteLibroPorId() {
        Libro libro = new LibroFisico(19, "El Principito", "Saint-Exupéry", "Literatura", 120, "Español", Disponibilidad.DISPONIBLE, "5555", "Tapa blanda", "Editorial X");
        biblioteca.agregarLibro(libro);

        assertTrue(biblioteca.existeLibroPorId(19));
        assertFalse(biblioteca.existeLibroPorId(999));
    }

    @Test
    public void testObtenerEmpleadoPorId_Existente() {
        Empleado empleado = new Administrador("Carlos", 1, "carlos@correo.com", "pass1");
        biblioteca.agregarEmpleado(empleado);

        Empleado resultado = biblioteca.obtenerEmpleadoPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Carlos", resultado.getNombre());
    }

    @Test
    public void testObtenerEmpleadoPorId_NoExistente() {
        Empleado resultado = biblioteca.obtenerEmpleadoPorId(999);
        assertNull(resultado);
    }

    @Test
    public void testObtenerLibroPorId_Existente() {
        Libro libro = new LibroFisico(1, "El Quijote", "Cervantes", "Novela", 400, "Español", Disponibilidad.DISPONIBLE, "1234", "Tapa dura", "Editorial");
        biblioteca.agregarLibro(libro);

        Libro resultado = biblioteca.obtenerLibroPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("El Quijote", resultado.getTitulo());
    }

    @Test
    public void testObtenerUsuarioPrestamoPorId_Existente() {
        UsuarioPrestamo usuarioPrestamo = new Estudiante("Ana", 101, "ana@correo.com");
        biblioteca.agregarUsuario(usuarioPrestamo);

        UsuarioPrestamo resultado = biblioteca.obtenerUsuarioPrestamoPorId(101);

        assertNotNull(resultado);
        assertEquals(101, resultado.getId());
        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    public void testObtenerPrestamoPorId_Existente() {
        UsuarioPrestamo usuario = new Estudiante("Luis", 10, "luis@correo.com");
        Libro libro = new LibroFisico(5, "1984", "Orwell", "Ficción", 300, "Español", Disponibilidad.DISPONIBLE, "0001", "Tapa dura", "Editorial");
        Prestamo prestamo = new Prestamo(30, List.of(libro), usuario, EstadoPrestamo.EN_CURSO);
        biblioteca.agregarPrestamo(prestamo);

        Prestamo resultado = biblioteca.obtenerPrestamoPorId(30);
        assertNotNull(resultado);
        assertEquals(30, resultado.getId());
    }

    @Test
    public void testObtenerUsuarioComunPorId_Existente() {
        UsuarioComun usuario = new Estudiante("Clara", 22, "clara@correo.com");
        biblioteca.agregarUsuario(usuario);

        UsuarioComun resultado = biblioteca.obtenerUsuarioComunPorId(22);
        assertNotNull(resultado);
        assertEquals("Clara", resultado.getNombre());
    }


}
