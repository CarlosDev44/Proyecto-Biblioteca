package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private List<Empleado> listEmpleados;
    private List<Usuario> listUsuarios;
    private List<Libro> listLibros;

    public Biblioteca(String nombre)
    {
        this.nombre = nombre;
        this.listEmpleados = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listLibros = new ArrayList<>();

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

    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<Libro> getListLibros() {
        return listLibros;
    }

    public void setListLibros(List<Libro> listLibros) {
        this.listLibros = listLibros;
    }
}
