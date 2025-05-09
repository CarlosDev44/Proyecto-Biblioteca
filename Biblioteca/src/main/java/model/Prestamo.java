package model;

import java.util.List;

public class Prestamo {

    private int id;
    private String fechaPrestamo;
    private String fechaDevolucion;
    private List<Libro> listLibrosSolicitados;
    private Usuario usuario;
    private EstadoPrestamo estadoPrestamo;

    public Prestamo(int id, String fechaPrestamo, String fechaDevolucion, List<Libro> listLibrosSolicitados, Usuario usuario, EstadoPrestamo estadoPrestamo) {
        this.id = id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.listLibrosSolicitados = listLibrosSolicitados;
        this.estadoPrestamo = estadoPrestamo;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public List<Libro> getListLibrosSolicitados() {
        return listLibrosSolicitados;
    }

    public void setListLibrosSolicitados(List<Libro> listLibrosSolicitados) {
        this.listLibrosSolicitados = listLibrosSolicitados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoPrestamo getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }
}
