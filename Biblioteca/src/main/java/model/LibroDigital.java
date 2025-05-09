package model;

public class LibroDigital extends Libro{

    private String enlaceDescarga;
    private Formato formato;

    public LibroDigital(String titulo, String autor, String genero, int anoPublicacion, String portada, Disponibilidad disponibilidad, String enlaceDescarga, Formato formato)
    {
        super(titulo, autor, genero, anoPublicacion, portada, disponibilidad);
        this.enlaceDescarga = enlaceDescarga;
        this.formato = formato;
    }

    public String getEnlaceDescarga() {
        return enlaceDescarga;
    }

    public void setEnlaceDescarga(String enlaceDescarga) {
        this.enlaceDescarga = enlaceDescarga;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }
}
