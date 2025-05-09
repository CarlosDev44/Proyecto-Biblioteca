package model;

import java.time.Duration;


public class Estudiante extends Usuario{
    private int limitePrestamos = 2;
    private Duration tiempoMaximo = Duration.ofDays(2);

    public Estudiante(String nombre, int id, String correo)
    {
        super(nombre, id, correo);
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        this.limitePrestamos = limitePrestamos;
    }

    public Duration getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(Duration tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }
}
