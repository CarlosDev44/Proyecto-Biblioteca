package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

//Clase Administrador que extiende Empleado y permite gestionar empleados y generar reportes avanzados
public class Administrador extends Empleado {

    //Constructor
    public Administrador(String nombre, int id, String correo, String contrasena) {
        super(nombre, id, correo, contrasena);
    }

    //Registra un empleado en la biblioteca
    public static void registrarEmpleado(Biblioteca biblioteca, Empleado empleado) {
        //Valida que los parametros no sean null
        if (biblioteca == null || empleado == null) {
            throw new IllegalArgumentException("La biblioteca o el empleado no pueden ser null.");
        }
        //verifica que no exista ya un empleado con ese ID
        if (biblioteca.existeEmpleadoPorId(empleado.getId())) {
            throw new IllegalArgumentException("Ya existe un empleado con el ID: " + empleado.getId());
        }

        biblioteca.agregarEmpleado(empleado);
    }

    //Elimina un empleado de la biblioteca
    public static void eliminarEmpleado(Biblioteca biblioteca, Empleado empleado) {
        //valida que los parametros no sean null
        if (biblioteca == null || empleado == null) {
            throw new IllegalArgumentException("La biblioteca o el empleado no pueden ser null.");
        }
        biblioteca.eliminarEmpleado(empleado);
    }

    //Modifica los datos de un empleado
    public static boolean modificarEmpleado(Biblioteca biblioteca, int idEmpleado, String nuevoNombre, String nuevoCorreo, String nuevaContrasena) {
        //Valida que los parametros no sean null
        if (biblioteca == null || nuevoNombre == null || nuevoCorreo == null || nuevaContrasena == null) {
            throw new IllegalArgumentException("Ningún parámetro puede ser null.");
        }
        //valida que los nuevos datos no estén vacíos
        if (nuevoNombre.trim().isEmpty() || nuevoCorreo.trim().isEmpty() || nuevaContrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("Los datos del empleado no pueden estar vacíos.");
        }
        //busca el empleado por el ID y actualiza sus datos
        for (Empleado empleado : biblioteca.getListEmpleados()) {
            if (empleado.getId() == idEmpleado) {
                empleado.setNombre(nuevoNombre);
                empleado.setCorreo(nuevoCorreo);
                empleado.setContrasena(nuevaContrasena);
                return true;
            }
        }
        return false;
    }

    //Metodo para generar un reporte avanzado en un archivo txt
    public static void generarReporteAvanzadoTxt(Biblioteca biblioteca) {

        String rutaArchivo = "reporte_avanzado.txt"; //Nombre del archivo
        ReporteAvanzado reporte = new ReporteAvanzado(); //Crea instancia para generar datos del reporte

        try (PrintWriter writer = new PrintWriter(rutaArchivo)) {
            writer.println("===== REPORTE AVANZADO DE LA BIBLIOTECA =====\n");

            // Préstamos agrupados por usuario
            writer.println(">>> Préstamos por usuario:\n");
            Map<UsuarioPrestamo, List<Prestamo>> prestamosPorUsuario = reporte.obtenerPrestamosPorUsuario(biblioteca.getListUsuarios());

            if (prestamosPorUsuario.isEmpty()) {
                writer.println("No hay usuarios con préstamos registrados.\n");
            } else {
                for (Map.Entry<UsuarioPrestamo, List<Prestamo>> entry : prestamosPorUsuario.entrySet()) {
                    UsuarioPrestamo usuario = entry.getKey();
                    List<Prestamo> prestamos = entry.getValue();

                    writer.println("Usuario: " + usuario.getNombre());

                    if (prestamos.isEmpty()) {
                        writer.println("  (No tiene préstamos registrados)");
                    } else {
                        for (Prestamo prestamo : prestamos) {
                            writer.println("  - " + prestamo);
                        }
                    }
                    writer.println();
                }
            }

            //Escribir lista de libros que nunca han sido prestados
            writer.println(">>> Libros nunca prestados:");
            List<String> librosNuncaPrestados = reporte.librosNuncaPrestados(biblioteca);

            if (biblioteca.getListLibros().isEmpty()) {
                writer.println("No hay libros en la biblioteca.\n");
            } else if (librosNuncaPrestados.isEmpty()) {
                writer.println("Todos los libros han sido prestados al menos una vez.\n");
            } else {
                for (String titulo : librosNuncaPrestados) {
                    writer.println("- " + titulo);
                }
                writer.println();
            }

            //Escribir todos los préstamos registrados
            writer.println(">>> Todos los préstamos:");
            List<String> todosPrestamos = reporte.obtenerTodosLosPrestamos(biblioteca);
            if (todosPrestamos.isEmpty()) {
                writer.println("No hay préstamos registrados.\n");
            } else {
                for (String prestamoStr : todosPrestamos) {
                    writer.println("- " + prestamoStr);
                }
            }

            writer.println("\n===== FIN DEL REPORTE =====");


        } catch (FileNotFoundException e) {
            //manejar error al crear/escribir archivo
            throw new RuntimeException("Error al generar el reporte avanzado: " + e.getMessage(), e);
        }
    }
}