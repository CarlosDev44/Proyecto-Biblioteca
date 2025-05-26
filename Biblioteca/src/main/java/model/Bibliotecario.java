package model;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Clase bibliotecario que representa a un empleado con funciones especificas.
public class Bibliotecario extends Empleado {

    public Bibliotecario(String nombre, int id, String correo, String contrasena) {
        super(nombre, id, correo, contrasena);
    }

    //Metodo para agregar un libro a la biblioteca
    public static void agregarLibro(Biblioteca biblioteca, Libro libro) {
        if (biblioteca == null || libro == null) {
            throw new IllegalArgumentException("Biblioteca y libro no pueden ser null.");
        }

        if (biblioteca.existeLibroPorId(libro.getId())) {
            throw new IllegalArgumentException("Ya existe un libro con el mismo ID.");
        }

        biblioteca.agregarLibro(libro);
    }

    //Metodo para registrar un usuario en la lista de usuarios de la biblioteca
    public static void registrarUsuario(Biblioteca biblioteca, UsuarioPrestamo usuario) {
        if (biblioteca == null || usuario == null) {
            throw new IllegalArgumentException("Biblioteca y usuario no pueden ser null.");
        }

        if (biblioteca.existeUsuarioPorId(usuario.getId())) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo ID.");
        }

        biblioteca.agregarUsuario(usuario);
    }

    //Metodo para registrar un prestamo en la lista de prestamos de la biblioteca
    public static void registrarPrestamo(Biblioteca biblioteca, Prestamo prestamo) {
        if (biblioteca == null || prestamo == null) {
            throw new IllegalArgumentException("Biblioteca y préstamo no pueden ser null.");
        }

        // Validar que el ID no esté repetido
        if (biblioteca.existePrestamoPorId(prestamo.getId())) {
            throw new IllegalArgumentException("Ya existe un préstamo con el ID: " + prestamo.getId());
        }

        // Validar que todos los libros estén disponibles
        for (Libro libro : prestamo.getListLibrosSolicitados()) {
            if (libro.getDisponibilidad() != Disponibilidad.DISPONIBLE) {
                throw new IllegalArgumentException("El libro '" + libro.getTitulo() + "' no está disponible para préstamo.");
            }
        }

        biblioteca.agregarPrestamo(prestamo);

        //Marca los libros como prestados y actualiza su contador de prestamos
        for (Libro libro : prestamo.getListLibrosSolicitados()) {
            libro.setDisponibilidad(Disponibilidad.PRESTADO);
            libro.incrementarVecesPrestamo();
        }

        // Asigna el prestamo al usuario y cambia su estado a EN_CURSO
        prestamo.getUsuarioPrestamo().agregarPrestamo(prestamo);
        prestamo.setEstadoPrestamo(EstadoPrestamo.EN_CURSO);
    }

    //Metodo para registrar una devolucion normal de un prestamo
    public static void registrarDevolucion(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null.");
        }
        prestamo.setEstadoPrestamo(EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDate.now());
    }

    //Metodo para registrar una devolucion atrasada y marcar el usuario con deuda
    public static void registrarDevolucionAtrasada(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null.");
        }
        prestamo.setEstadoPrestamo(EstadoPrestamo.ATRASADO);
        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.getUsuarioPrestamo().setEstadoUsuario(EstadoUsuario.CON_DEUDA);
    }

    //Metodo para cambiar el estado de un usuario con deuda a sin deuda
    public static void cambiarEstadoDeudaSinDeuda(UsuarioPrestamo usuarioPrestamo) {
        if (usuarioPrestamo == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }
        usuarioPrestamo.setEstadoUsuario(EstadoUsuario.SIN_DEUDA);
    }

    //Metodo para cambiar el estado de un usuario sin deuda a con deuda
    public static void cambiarEstadoDeudaConDeuda(UsuarioPrestamo usuarioPrestamo) {
        if (usuarioPrestamo == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }
        usuarioPrestamo.setEstadoUsuario(EstadoUsuario.CON_DEUDA);
    }

    //Metodo para generar un reporte de libros prestados, usuarios con deudas y top 3 libros más prestados
    public static void generarReporteNormalTxt(Biblioteca biblioteca) {
        if (biblioteca == null) {
            throw new IllegalArgumentException("La biblioteca no puede ser null.");
        }

        String rutaArchivo = "reporte_normal.txt";
        ReporteNormal reporte = new ReporteNormal();

        try (PrintWriter writer = new PrintWriter(rutaArchivo)) {
            writer.println("===== REPORTE NORMAL DE LA BIBLIOTECA =====\n");

            writer.println(">>> Libros Prestados:");
            for (String libro : reporte.librosPrestados(biblioteca)) {
                writer.println("- " + libro);
            }
            writer.println();

            writer.println(">>> Usuarios con Deudas:");
            for (String usuario : reporte.usuariosConDeudas(biblioteca)) {
                writer.println("- " + usuario);
            }
            writer.println();

            writer.println(">>> Top 3 Libros más Prestados:");
            for (String libro : reporte.obtenerTopLibrosMasPrestados(biblioteca)) {
                writer.println("- " + libro);
            }
            writer.println();

            writer.println("===== FIN DEL REPORTE =====");


        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error al generar el reporte normal: " + e.getMessage(), e);
        }
    }

    //Metodo para obtener los usuarios aptos para prestamos
    public static List<UsuarioPrestamo> obtenerUsuariosDisponiblesParaPrestamo(Biblioteca biblioteca) {
        List<UsuarioPrestamo> disponibles = new ArrayList<>();

        for (UsuarioComun usuario : biblioteca.getListUsuarios()) {
            if (usuario instanceof UsuarioPrestamo) {
                UsuarioPrestamo usuarioPrestamo = (UsuarioPrestamo) usuario;

                if (usuarioPrestamo.getEstadoUsuario() != EstadoUsuario.SIN_DEUDA) {
                    continue; // Si tiene deuda, no se agrega
                }

                int prestamosActivos = 0;
                for (Prestamo prestamo : usuarioPrestamo.getListPrestamos()) {
                    if (prestamo.getEstadoPrestamo() == EstadoPrestamo.EN_CURSO) {
                        prestamosActivos++;
                    }
                }

                if (prestamosActivos < usuarioPrestamo.getLimitePrestamos()) {
                    disponibles.add(usuarioPrestamo);
                }
            }
        }

        return disponibles;
    }
    //Metodo para obtener los libros disponibles
    public static List<Libro> obtenerLibrosDisponibles(Biblioteca biblioteca) {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : biblioteca.getListLibros()) {
            if (libro.getDisponibilidad() == Disponibilidad.DISPONIBLE) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }

    //Metodo para obtener una lista de Prestamos en curso
    public static List<Prestamo> obtenerPrestamosEnCurso(Biblioteca biblioteca)
    {
        List<Prestamo> prestamosEnCurso = new ArrayList<>();

        for(Prestamo prestamo : biblioteca.getListPrestamos())
        {
            if (prestamo.getEstadoPrestamo() == EstadoPrestamo.EN_CURSO)
            {
                prestamosEnCurso.add(prestamo);
            }
        }
        return prestamosEnCurso;
    }

    //Metodo para enviar correos electronicos
    public static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        final String remitente = "bibliotecauq@gmail.com";
        final String contrasena = "fhbbidmwvbjfogvl";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contrasena);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente a " + destinatario);

        } catch (MessagingException e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

