package model;

import java.util.List;
import java.time.LocalDate;

public class Prestamo {

    private int id;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private List<Libro> listLibrosSolicitados;
    private UsuarioPrestamo usuarioPrestamo;
    private EstadoPrestamo estadoPrestamo;

    public Prestamo(int id, List<Libro> listLibrosSolicitados, UsuarioPrestamo usuarioPrestamo, EstadoPrestamo estadoPrestamo) {
        this.id = id;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = this.fechaPrestamo.plusDays(usuarioPrestamo.getTiempoMaximoPrestamos());
        this.listLibrosSolicitados = listLibrosSolicitados;
        this.estadoPrestamo = estadoPrestamo;
        this.usuarioPrestamo = usuarioPrestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public List<Libro> getListLibrosSolicitados() {
        return listLibrosSolicitados;
    }

    public void setListLibrosSolicitados(List<Libro> listLibrosSolicitados) {
        this.listLibrosSolicitados = listLibrosSolicitados;
    }

    public UsuarioPrestamo getUsuarioPrestamo() {
        return usuarioPrestamo;
    }

    public void setUsuarioPrestamo(UsuarioPrestamo usuarioPrestamo) {
        this.usuarioPrestamo = usuarioPrestamo;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
}
