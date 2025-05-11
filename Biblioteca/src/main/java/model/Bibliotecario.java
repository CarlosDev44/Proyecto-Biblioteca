package model;


public class Bibliotecario extends Empleado{

    public Bibliotecario(String nombre, int id, String correo, String contrasena )
    {
        super(nombre, id, correo, contrasena);
    }

    public void agregarLibro(Biblioteca biblioteca, Libro libro)
    {
        biblioteca.agregarLibros(libro);
    }

    public void registrarUsuario(Biblioteca biblioteca, UsuarioComun usuario)
    {
        biblioteca.agregarUsuario(usuario);
    }

    public void registrarPrestamo(Biblioteca biblioteca, Prestamo prestamo)
    {
        biblioteca.agregarPrestamo(prestamo);
    }

}
