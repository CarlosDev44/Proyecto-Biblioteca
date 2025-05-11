package model;

import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Empleado{

    private List<SolicitudPrestamo> solicitudPrestamos;

    public Bibliotecario(String nombre, int id, String correo, String contrasena )
    {
        super(nombre, id, correo, contrasena);
        this.solicitudPrestamos = new ArrayList<>();
    }

    public List<SolicitudPrestamo> getSolicitudPrestamos() {
        return solicitudPrestamos;
    }

    public void setSolicitudPrestamos(List<SolicitudPrestamo> solicitudPrestamos) {
        this.solicitudPrestamos = solicitudPrestamos;
    }

    public void agregarSolicitud(SolicitudPrestamo solicitudPrestamo)
    {
        solicitudPrestamos.add(solicitudPrestamo);
    }

    public void agregarLibro(Libro libro, Biblioteca biblioteca)
    {
        biblioteca.agregarLibro(libro);
    }

    public void registrarUsuario(Usuario usuario, Biblioteca biblioteca)
    {
        biblioteca.agregarUsuario(usuario);
    }

    public void aceptarPrestamo(SolicitudPrestamo solicitudPrestamo, Usuario usuario)
    {
        Prestamo prestamo = new Prestamo(solicitudPrestamo.getFecha()
    }
}


