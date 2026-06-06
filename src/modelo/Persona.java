package modelo;

public class Persona {

    private String nombre;
    private Rol rol;
    private int calificacion;

    public Persona(String nombre,
                   Rol rol,
                   int calificacion) {

        this.nombre = nombre;
        this.rol = rol;
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public int getCalificacion() {
        return calificacion;
    }

    @Override
    public String toString() {
        return nombre +
                " (" + rol +
                ", " + calificacion + ")";
    }
}