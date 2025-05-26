package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Clase para generar reportes basicos
//Solo utilizada por bibliotecario para obtener informacion general
public class ReporteNormal {
    //Constructor por defecto generado por Java

    //Metodo para devolver una lista de los titulos que están prestados actualmente
    public List<String> librosPrestados(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }

        List<String> librosPrestados = new ArrayList<>();

        //Recorre la lista de libros y guarda los que estan prestados
        for (Libro libro : biblioteca.getListLibros()) {
            if (libro.getDisponibilidad() == Disponibilidad.PRESTADO) {
                librosPrestados.add(libro.getTitulo());
            }
        }

        return librosPrestados;
    }

    //Metodo para devolver una lista con los nombres de los usuarios que tienen deudas
    public List<String> usuariosConDeudas(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }

        List<String> usuariosConDeudas = new ArrayList<>();

        //Recorre los usuario y guarda los que tienen deuda
        for (UsuarioComun usuario : biblioteca.getListUsuarios()) {
            if (usuario.getEstadoUsuario() == EstadoUsuario.CON_DEUDA) {
                usuariosConDeudas.add(usuario.getNombre());
            }
        }

        return usuariosConDeudas;
    }

    //Metodo que devuelve una lista con los 3 libros má prestados de la biblioteca
    public List<String> obtenerTopLibrosMasPrestados(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }

        //Copia la lista de libros y la ordena por numero de prestamos (de mayor a menor)
        List<Libro> todosLosLibros = new ArrayList<>(biblioteca.getListLibros());

        todosLosLibros.sort((l1, l2) -> Integer.compare(l2.getVecesPrestado(), l1.getVecesPrestado()));

        //Toma los 3 primeros libros y devuelve su titulo junto al numero de prestamos
        return todosLosLibros.stream()
                .limit(3)
                .map(libro -> libro.getTitulo() + " - Veces prestado: " + libro.getVecesPrestado())
                .collect(Collectors.toList());
    }
}