package model;

import java.util.List;

public class SolicitudPrestamo {

    private String fecha;
    private List<Libro> listLibrosSolicitados;
    private Usuario usuario;

    SolicitudPrestamo(String fecha, List<Libro> listLibrosSolicitados, Usuario usuario)
    {
        this.fecha = fecha;
        this.listLibrosSolicitados = listLibrosSolicitados;
        this.usuario = usuario;

    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
}
