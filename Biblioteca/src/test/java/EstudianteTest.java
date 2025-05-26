import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EstudianteTest {

    private Estudiante estudiante;
    private Libro libro;

    @BeforeEach

    public void setUp()
    {
        estudiante = new Estudiante("Juan", 101, "juan@correo.com");
        libro = new LibroFisico(10, "El Principito", "Antoine de Saint-Exupéry", "Fábula", 96, "Español", Disponibilidad.DISPONIBLE, "456", "Tapa blanda", "EditorialX");

    }

    @Test

    public void testConsultarLibro()
    {
        String resultado = estudiante.consultarLibro(libro);
        assertNotNull(resultado);
        assertTrue(resultado.contains("El Principito"));
        assertTrue(resultado.contains("Antoine de Saint-Exupéry"));
    }
}
