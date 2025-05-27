package model;

//Clase que representan los usuarios de tipo visitante, hereda de UsuarioComun, no puede realizar prestamos.
//Solo puede consultar libros
public class Visitante extends UsuarioComun {

    Visitante(String nombre, int id, String correo) {
        super(nombre, id, correo, EstadoUsuario.SIN_DEUDA);
    }
}
