package model;

public abstract class Empleado {
    private String nombre;
    private int id;
    private String correo;
    private String contrasena;

    public Empleado(String nombre, int id, String correo, String contrasena)
    {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.contrasena = contrasena;

    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
