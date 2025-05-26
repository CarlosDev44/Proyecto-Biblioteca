package model;

import java.util.ArrayList;
import java.util.List;

//Clase abstracta que representa solo los usuarios que pueden realizar prestamos
public abstract class UsuarioPrestamo extends UsuarioComun{
    private int limitePrestamos;
    private int tiempoMaximoPrestamos;
    private List<Prestamo> listPrestamos = new ArrayList<>();

    UsuarioPrestamo(String nombre, int id, String correo, int limitePrestamos, int tiempoMaximoPrestamos, EstadoUsuario estadoUsuario)
    {
        super(nombre, id, correo, estadoUsuario);
        this.limitePrestamos = limitePrestamos;
        this.tiempoMaximoPrestamos = tiempoMaximoPrestamos;
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    public int getTiempoMaximoPrestamos() {
        return tiempoMaximoPrestamos;
    }

    public void setTiempoMaximoPrestamos(int tiempoMaximoPrestamos) {
        this.tiempoMaximoPrestamos = tiempoMaximoPrestamos;
    }

    public List<Prestamo> getListPrestamos()
    {
        return listPrestamos;
    }

    public void setListPrestamos(List<Prestamo> listPrestamos)
    {
        this.listPrestamos = listPrestamos;
    }

    //Metodo para poder agregar un prestamo a la lista de prestamos que tiene cada usuario desde la clase Bibliotecario
    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null.");
        }
        listPrestamos.add(prestamo);
    }
}
