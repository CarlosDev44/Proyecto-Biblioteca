package model;

//Clase Estudiante que extiende de UsuarioPrestamo
public class Estudiante extends UsuarioPrestamo {

    public Estudiante(String nombre, int id, String correo)

    {
        super(nombre, id, correo, 2, 2, EstadoUsuario.SIN_DEUDA);
    }

}
