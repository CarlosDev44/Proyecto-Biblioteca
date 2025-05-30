import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    private Biblioteca biblioteca;

    @BeforeEach
    public void setUp()
    {
        biblioteca = Biblioteca.getInstancia();
        biblioteca.getListEmpleados().clear();
        biblioteca.getListUsuarios().clear();
        biblioteca.getListLibros().clear();
        biblioteca.getListPrestamos().clear();

    }

    @Test
    public void testRegistrarEmpleado()
    {
        Empleado empleado = new Bibliotecario("Pepe", 2, "Pepe@gmail.com", "hola321");
        Administrador.registrarEmpleado(biblioteca, empleado);

        assertTrue(biblioteca.getListEmpleados().contains(empleado));
    }

    @Test
    public void testModificarEmpleado_Exitoso() {
        Empleado empleado = new Bibliotecario("Luis", 4, "luis@gmail.com", "12345");
        Administrador.registrarEmpleado(biblioteca, empleado);

        boolean resultado = Administrador.modificarEmpleado(biblioteca, 4, "Luis Mod", "nuevo@gmail.com", "nuevo123");

        assertTrue(resultado);
        assertEquals("Luis Mod", empleado.getNombre());
        assertEquals("nuevo@gmail.com", empleado.getCorreo());
        assertEquals("nuevo123", empleado.getContrasena());
    }

    @Test
    public void testModificarEmpleado_NoEncontrado() {
        boolean resultado = Administrador.modificarEmpleado(biblioteca, 999, "Nombre", "correo@x.com", "clave");
        assertFalse(resultado);
    }


    @Test
    public void testEliminarEmpleado()
    {
        Empleado empleado = new Bibliotecario("Luis", 3, "luis@gmail.com", "luis543");

        Administrador.registrarEmpleado(biblioteca, empleado);
        Administrador.eliminarEmpleado(biblioteca, empleado);

        assertFalse(biblioteca.getListEmpleados().contains(empleado));
    }


    @Test
    public void testGenerarReporteAvanzadoTxt() {

        List<Libro> libros = new ArrayList<>();

        Libro libro = new LibroFisico(1, "100 anios de soledad", "Gabriel Garcia", "Cualquiera", 200, "algo", Disponibilidad.DISPONIBLE, "13", "hola", "alla");

        libros.add(libro);

        UsuarioPrestamo usuarioPrestamo = new Estudiante("Carlos", 1, "Carlos.com");

        Prestamo prestamo = new Prestamo(1, libros, usuarioPrestamo, EstadoPrestamo.EN_CURSO);

        Bibliotecario.registrarPrestamo(biblioteca, prestamo);
        Bibliotecario.registrarUsuario(biblioteca, usuarioPrestamo);

        Administrador.generarReporteAvanzadoTxt(biblioteca);


        File archivo = new File("reporte_avanzado.txt");
        assertTrue(archivo.exists());

    }




}
