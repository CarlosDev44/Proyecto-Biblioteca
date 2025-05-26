package model;

//Clase abstracta que representa todos los tipos de libros
public abstract class Libro {

    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int anoPublicacion;
    private String portada;
    private Disponibilidad disponibilidad;
    private int vecesPrestado = 0;

    public Libro(int id, String titulo, String autor, String genero, int anoPublicacion, String portada, Disponibilidad disponibilidad) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacion = anoPublicacion;
        this.portada = portada;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", anoPublicacion=" + anoPublicacion +
                ", portada='" + portada + '\'' +
                ", disponibilidad=" + disponibilidad +
                '}';
    }

    //Metodo para incrementar el atributo 'vecesPrestado' con el fin de que cuando un libro se preste, este atributo incremente en 1
    public void incrementarVecesPrestamo()
    {
        this.vecesPrestado ++;
    }
}
