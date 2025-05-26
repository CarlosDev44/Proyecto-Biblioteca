package model;

//Clase Estudiante que extiende de UsuarioPrestamo
public class Estudiante extends UsuarioPrestamo {

    public Estudiante(String nombre, int id, String correo)

    {
        super(nombre, id, correo, 2, 2, EstadoUsuario.SIN_DEUDA);
    }

    //Sobreescritura del metodo consultarLibro, para mostrar la informacion de un libro en concreto
    //(metodo abstracto de la clase UsuarioComun)
    @Override
    public String consultarLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null.");
        }
        return libro.toString();
    }
}
