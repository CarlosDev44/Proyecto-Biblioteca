package model;

import java.util.List;
import java.time.LocalDate;

public class SolicitudPrestamo {

    private LocalDate fecha;
    private List<Libro> listLibrosSolicitados;
    private Usuario usuario;
    private Bibliotecario bibliotecario;

    SolicitudPrestamo(LocalDate fecha, List<Libro> listLibrosSolicitados, Usuario usuario, Bibliotecario bibliotecario)
    {
        this.fecha = fecha;
        this.listLibrosSolicitados = listLibrosSolicitados;
        this.usuario = usuario;
        this.bibliotecario = bibliotecario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
}
