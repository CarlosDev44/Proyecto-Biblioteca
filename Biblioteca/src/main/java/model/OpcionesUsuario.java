package model;

import java.time.LocalDate;
import java.util.List;

public interface OpcionesUsuario {
    void solicitarPrestamo(LocalDate fecha, List<Libro> librosSolicitados, Usuario usuario, Bibliotecario bibliotecario);
    String consultarLibro(Libro libro);
}
