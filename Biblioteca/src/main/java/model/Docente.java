package model;

public class Docente extends UsuarioPrestamo {

    public Docente(String nombre, int id, String correo)
    {
        super(nombre,id, correo, 4, 4);
    }

    @Override
    public String consultarLibro(Libro libro)
    {
        return libro.toString();
    }

}
