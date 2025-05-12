package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Empleado> listEmpleados;
    private List<UsuarioComun> listUsuarios;
    private List<Libro> listLibros;
    private List<Prestamo> listPrestamos;
    private List<Reporte> listReportes;

    public Biblioteca(String nombre)
    {
        this.nombre = nombre;
        this.listEmpleados = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listLibros = new ArrayList<>();
        this.listPrestamos = new ArrayList<>();
        this.listReportes = new ArrayList<>();
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public List<Empleado> getListEmpleados()
    {
        return listEmpleados;
    }

    public void setListEmpleados(List<Empleado> listEmpleados)
    {
        this.listEmpleados = listEmpleados;
    }

    public List<UsuarioComun> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<UsuarioComun> listUsuarioComuns) {
        this.listUsuarios = listUsuarioComuns;
    }

    public List<Libro> getListLibros() {
        return listLibros;
    }

    public void setListLibros(List<Libro> listLibros) {
        this.listLibros = listLibros;
    }
    public List<Prestamo> getListPrestamos ()
    {
        return listPrestamos;
    }

    public void setListPrestamos ( List<Prestamo> listPrestamos)
    {
        this.listPrestamos = listPrestamos;
    }

    public List<Reporte> getListReportes() {
        return listReportes;
    }

    public void setListReportes(List<Reporte> listReportes) {
        this.listReportes = listReportes;
    }

    public void agregarLibros(Libro libro)
    {
        listLibros.add(libro);
    }

    public void agregarUsuario(UsuarioComun usuarioComun)
    {
        listUsuarios.add(usuarioComun);
    }
    public void agregarEmpleado(Empleado empleado)
    {
        listEmpleados.add(empleado);
    }
    public void agregarPrestamo(Prestamo prestamo)
    {
        listPrestamos.add(prestamo);
    }
}
