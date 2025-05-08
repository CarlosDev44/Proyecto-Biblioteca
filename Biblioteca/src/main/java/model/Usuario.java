package model;

import java.util.List;
import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private int id;
    private String correo;
    private List<SolicitudPrestamo> listSolicitudPrestamos;
    private List<Prestamo> listPrestamos;

    public Usuario (String nombre, int id, String correo)
    {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.listSolicitudPrestamos = new ArrayList<>();
        this.listPrestamos = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<SolicitudPrestamo> getListSolicitudPrestamos() {
        return listSolicitudPrestamos;
    }

    public void setListSolicitudPrestamos(List<SolicitudPrestamo> listSolicitudPrestamos) {
        this.listSolicitudPrestamos = listSolicitudPrestamos;
    }

    public List<Prestamo> getListPrestamos() {
        return listPrestamos;
    }

    public void setListPrestamos(List<Prestamo> listPrestamos) {
        this.listPrestamos = listPrestamos;
    }

}

