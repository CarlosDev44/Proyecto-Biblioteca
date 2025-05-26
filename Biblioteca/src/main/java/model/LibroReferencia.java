package model;

//Clase que representa los libros de referencia, hereda de libro, no puede ser prestado, solo consultado
public class LibroReferencia extends Libro {

    public LibroReferencia(int id, String titulo, String autor, String genero, int anoPublicacion, String portada, Disponibilidad disponibilidad)
    {
        super(id, titulo, autor, genero, anoPublicacion, portada, disponibilidad);
    }



}
