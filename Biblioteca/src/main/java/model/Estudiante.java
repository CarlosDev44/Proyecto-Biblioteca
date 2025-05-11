package model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public class Estudiante extends Usuario implements OpcionesUsuario{
    private int limitePrestamos = 2;
    private Duration tiempoMaximo = Duration.ofDays(2);

    public Estudiante(String nombre, int id, String correo)
    {
        super(nombre, id, correo);
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    public Duration getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(Duration tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    @Override
    public void solicitarPrestamo(LocalDate fecha, List<Libro> librosSolicitados, Usuario usuario, Bibliotecario bibliotecario)
    {
        SolicitudPrestamo solicitud = new SolicitudPrestamo(fecha, librosSolicitados, usuario, bibliotecario);

        bibliotecario.agregarSolicitud(solicitud);

    }
    @Override
    public String consultarLibro(Libro libro)
    {
        return libro.toString();
    }
}
