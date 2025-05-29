package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clase que representa un reporte avanzado, solo lo puede utilizar la clase Administrador.
//Solo se utiliza para hacer calculos sobre algunas estadisticas sobre prestamos y libros
public class ReporteAvanzado {
    //No es necesario crear un constructor, Java hacer uno por defecto cuando no hay parametros que pasar

    //Obtiene una lista de todos los prestamos registrados en la biblioteca
    public List<String> obtenerTodosLosPrestamos(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }
        if (biblioteca.getListPrestamos() == null) {
            throw new IllegalArgumentException("La lista de préstamos no puede ser null.");
        }

        List<String> todosLosPrestamos = new ArrayList<>();

        //Recorre cada prestamo y lo convierte a texto para agregarlo a la lista
        for (Prestamo prestamo : biblioteca.getListPrestamos()) {
            todosLosPrestamos.add(prestamo.toString());
        }
        return todosLosPrestamos;
    }

    //Obtiene un mapa de prestamos organizados por usuario. Solo se incluyen usuarios que sean instancias de 'UsuarioPrestamo'haz
    public Map<UsuarioPrestamo, List<Prestamo>> obtenerPrestamosPorUsuario(List<UsuarioComun> listaUsuarios) {
        if (listaUsuarios == null) {
            throw new IllegalArgumentException("La lista de usuarios no puede ser null.");
        }

        Map<UsuarioPrestamo, List<Prestamo>> prestamosPorUsuario = new HashMap<>();

        //Recorre la lista de usuarios
        for (UsuarioComun usuario : listaUsuarios) {
            //Verifica si el usuario puede realizar prestamos
            if (usuario instanceof UsuarioPrestamo usuarioPrestamo) {
                List<Prestamo> prestamos = usuarioPrestamo.getListPrestamos();

                //Si no tiene prestamos, se asigna una lista vacia
                if (prestamos == null) {
                    prestamos = List.of();
                }
                //Se agrega el mapa
                prestamosPorUsuario.put(usuarioPrestamo, prestamos);
            }
        }

        return prestamosPorUsuario;
    }

    //Devuelve una lista de titulos de libros que nunca han sido prestados

    public List<String> librosNuncaPrestados(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }
        if (biblioteca.getListLibros() == null) {
            throw new IllegalArgumentException("La lista de libros no puede ser null.");
        }

        List<String> librosNuncaPrestados = new ArrayList<>();

        //Recorre cad alibro y verifica si nunca ha sido prestao
        for (Libro libro : biblioteca.getListLibros()) {
            if (libro.getVecesPrestado() == 0) {
                librosNuncaPrestados.add(libro.getTitulo());
            }
        }
        return librosNuncaPrestados;
    }

}