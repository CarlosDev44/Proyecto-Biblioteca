package model;

import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioPrestamo extends UsuarioComun{
    private int limitePrestamos;
    private int tiempoMaximoPrestamos;
    private List<Prestamo> listPrestamos = new ArrayList<>();

    UsuarioPrestamo(String nombre, int id, String correo, int limitePrestamos, int tiempoMaximoPrestamos)
    {
        super(nombre, id, correo);
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
}
