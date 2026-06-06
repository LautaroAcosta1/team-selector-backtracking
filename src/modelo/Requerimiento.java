package modelo;

public class Requerimiento {

    private Rol rol;
    private int cantidad;

    public Requerimiento(Rol rol,
                         int cantidad) {

        this.rol = rol;
        this.cantidad = cantidad;
    }

    public Rol getRol() {
        return rol;
    }

    public int getCantidad() {
        return cantidad;
    }
}