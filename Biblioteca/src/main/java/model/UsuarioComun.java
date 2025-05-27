package model;

//Clase abstrata para representar a todos los usuarios
public abstract class UsuarioComun {

    private String nombre;
    private int id;
    private String correo;
    private EstadoUsuario estadoUsuario;

    public UsuarioComun(String nombre, int id, String correo, EstadoUsuario estadoUsuario)
    {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.estadoUsuario = estadoUsuario;
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

    public EstadoUsuario getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(EstadoUsuario estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

}

