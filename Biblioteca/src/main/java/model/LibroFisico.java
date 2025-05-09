package model;

public class LibroFisico extends Libro {

    private String numeroPaginas;
    private String editorial;
    private String ubicacion;

    public LibroFisico(String titulo, String autor, String genero, int anoPublicacion, String portada, Disponibilidad disponibilidad, String numeroPaginas, String editorial, String ubicacion)
    {
        super(titulo, autor, genero, anoPublicacion, portada, disponibilidad);
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.ubicacion = ubicacion;
    }

    public String getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
