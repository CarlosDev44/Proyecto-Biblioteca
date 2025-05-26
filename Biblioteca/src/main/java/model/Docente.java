package model;

//Clase Docente que hereda de UsuarioPrestamo
public class Docente extends UsuarioPrestamo {

    public Docente(String nombre, int id, String correo)
    {
        super(nombre,id, correo, 4, 4, EstadoUsuario.SIN_DEUDA);
    }

    //Sobreescritura del metodo consultarLibro, para mostrar la informacion de un libro en concreto
    //(metodo abstracto de la clase UsuarioComun)
    @Override
    public String consultarLibro(Libro libro)
    {
        return libro.toString();
    }

}
