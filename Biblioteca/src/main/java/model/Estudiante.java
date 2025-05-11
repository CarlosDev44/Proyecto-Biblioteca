package model;

import java.time.Duration;


public class Estudiante extends UsuarioPrestamo {

    public Estudiante(String nombre, int id, String correo)

    {
        super(nombre, id, correo, 2, 2);
    }


    @Override
    public String consultarLibro(Libro libro)
    {
        return libro.toString();
    }
}
