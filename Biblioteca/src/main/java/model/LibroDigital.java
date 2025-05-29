package model;

//Clase que representa los libros Digitales, hereda de libro
public class LibroDigital extends Libro{

    private String enlaceDescarga;
    private Formato formato;

    public LibroDigital(int id, String titulo, String autor, String genero, int anoPublicacion, String portada, Disponibilidad disponibilidad, String enlaceDescarga, Formato formato)
    {
        super(id, titulo, autor, genero, anoPublicacion, portada, disponibilidad);
        this.enlaceDescarga = enlaceDescarga;
        this.formato = formato;
    }

    public String getEnlace() {
        return enlaceDescarga;
    }

    public void setEnlace(String enlaceDescarga) {
        this.enlaceDescarga = enlaceDescarga;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return super.toString() +
                "LibroDigital{" +
                "enlaceDescarga='" + enlaceDescarga + '\'' +
                ", formato=" + formato +
                '}';
    }
}
