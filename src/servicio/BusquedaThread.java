package servicio;

import modelo.Equipo;
import modelo.Incompatibilidad;
import modelo.Persona;
import modelo.Requerimiento;

import java.util.List;

public class BusquedaThread extends Thread {

    private SelectorEquipo selector;

    private List<Persona> personas;
    private List<Incompatibilidad> incompatibilidades;
    private List<Requerimiento> requerimientos;

    private Equipo resultado;

    public BusquedaThread(
            SelectorEquipo selector,
            List<Persona> personas,
            List<Incompatibilidad> incompatibilidades,
            List<Requerimiento> requerimientos) {

        this.selector = selector;
        this.personas = personas;
        this.incompatibilidades = incompatibilidades;
        this.requerimientos = requerimientos;
    }

    @Override
    public void run() {

        resultado = selector.resolver(
                personas,
                incompatibilidades,
                requerimientos
        );
    }

    public Equipo getResultado() {
        return resultado;
    }
}