package model;

import java.util.ArrayList;
import java.util.List;

//Clase biblioteca que contiene las listas de las clases necesarias
public class Biblioteca {
    private static Biblioteca instancia; // La única instancia, he utilizado el patron singleton para trabajar con una sola instancia

    private String nombre;
    private List<Empleado> listEmpleados;
    private List<UsuarioComun> listUsuarios;
    private List<Libro> listLibros;
    private List<Prestamo> listPrestamos;

    // Constructor privado
    private Biblioteca(String nombre) {
        this.nombre = nombre;
        this.listEmpleados = new ArrayList<>();
        this.listUsuarios = new ArrayList<>();
        this.listLibros = new ArrayList<>();
        this.listPrestamos = new ArrayList<>();
    }

    // Metodo publico para obtener la única instancia
    public static Biblioteca getInstancia() {
        if (instancia == null) {
            instancia = new Biblioteca("Biblioteca UQ");
        }
        return instancia;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public List<Empleado> getListEmpleados()
    {
        return listEmpleados;
    }

    public void setListEmpleados(List<Empleado> listEmpleados)
    {
        this.listEmpleados = listEmpleados;
    }

    public List<UsuarioComun> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<UsuarioComun> listUsuarioComunes) {
        this.listUsuarios = listUsuarioComunes;
    }

    public List<Libro> getListLibros() {
        return listLibros;
    }

    public void setListLibros(List<Libro> listLibros) {
        this.listLibros = listLibros;
    }
    public List<Prestamo> getListPrestamos ()
    {
        return listPrestamos;
    }

    public void setListPrestamos ( List<Prestamo> listPrestamos)
    {
        this.listPrestamos = listPrestamos;
    }

    // Metodo para agregar un libro a la lista de libros
    public void agregarLibro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null.");
        }
        listLibros.add(libro);
    }

    //Metodo para agregar un usuario comun a la lista de usuarios
    public void agregarUsuario(UsuarioComun usuarioComun) {
        if (usuarioComun == null) {
            throw new IllegalArgumentException("El usuario no puede ser null.");
        }
        listUsuarios.add(usuarioComun);
    }

    //Metodo para agregar un empleado a la lista de empleados
    public void agregarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser null.");
        }
        listEmpleados.add(empleado);
    }

    //Metodo para eliminar un empleado de la lista de empleados
    public void eliminarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser null.");
        }
        listEmpleados.remove(empleado);
    }

    //Metodo para agregar un prestamo a la lista de prestamos
    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null.");
        }
        listPrestamos.add(prestamo);
    }

    //Metodo para autenticar un empleado verificando id y contraseña
    public boolean autenticar(int id, String contrasena) {
        for (Empleado empl : listEmpleados) {
            if (empl.getId() == id && empl.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    //metodo para verificar si existe un empleado por su id
    public boolean existeEmpleadoPorId(int id) {
        for (Empleado empl : listEmpleados) {
            if (empl.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //metodo para devolver un empleado por el id
    public Empleado obtenerEmpleadoPorId(int id) {
        for (Empleado empl : listEmpleados) {
            if (empl.getId() == id) {
                return empl;
            }
        }
        return null; // Si no encuentra ninguno
    }
    //metodo para devolver un libro por el id
    public Libro obtenerLibroPorId(int id) {
        for (Libro libro : listLibros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    //metodo para devolver un usuarioPrestamo por id
    public UsuarioPrestamo obtenerUsuarioPrestamoPorId(int id) {
        for (UsuarioComun usuario : listUsuarios) {
            if (usuario instanceof UsuarioPrestamo && usuario.getId() == id) {
                return (UsuarioPrestamo) usuario;
            }
        }
        return null; // Si no se encuentra
    }

    //Metodo para verificar si existe un prestamo por su id
    public boolean existePrestamoPorId(int id) {
        for (Prestamo prestamo : listPrestamos) {
            if (prestamo.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //Metodo para obtener un prestamo por su id
    public Prestamo obtenerPrestamoPorId(int id) {
        for (Prestamo prestamo : listPrestamos) {
            if (prestamo.getId() == id) {
                return prestamo;
            }
        }
        return null;
    }

    //Metodo para verificar si existe un usuario comun por su id
    public boolean existeUsuarioPorId(int id) {
        for (UsuarioComun usuario : listUsuarios) {
            if (usuario.getId() == id) {
                return true;
            }
        }
        return false;
    }

    //Metodo para devolver un UsuarioComun por ID
    public UsuarioComun obtenerUsuarioComunPorId(int id) {
        for (UsuarioComun usuario : listUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    //Metodo para verificar si existe un libro por su id
    public boolean existeLibroPorId(int id) {
        for (Libro libro : listLibros) {
            if (libro.getId() == id) {
                return true;
            }
        }
        return false;
    }


}
