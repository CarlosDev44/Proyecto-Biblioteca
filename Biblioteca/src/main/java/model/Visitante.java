package model;

public class Visitante extends UsuarioComun{

    Visitante(String nombre, int id, String correo)
    {
        super(nombre,id,correo);
    }

    @Override
    public String consultarLibro( Libro libro)
    {
        return libro.toString();
    }

}
