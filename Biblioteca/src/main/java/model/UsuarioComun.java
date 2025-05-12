package model;

public abstract class UsuarioComun {

    private String nombre;
    private int id;
    private String correo;

    public UsuarioComun(String nombre, int id, String correo)
    {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;

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

    public abstract String consultarLibro(Libro libro);

}

