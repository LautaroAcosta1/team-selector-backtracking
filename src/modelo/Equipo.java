package modelo;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private List<Persona> integrantes;

    public Equipo() {
        integrantes = new ArrayList<>();
    }

    public void agregar(Persona persona) {
        integrantes.add(persona);
    }

    public List<Persona> getIntegrantes() {
        return integrantes;
    }

    public int getPuntajeTotal() {

        int total = 0;

        for (Persona p : integrantes) {
            total += p.getCalificacion();
        }

        return total;
    }

    public boolean contiene(Persona persona) {
        return integrantes.contains(persona);
    }

    public Equipo copiar() {

        Equipo copia = new Equipo();

        for (Persona p : integrantes) {
            copia.agregar(p);
        }

        return copia;
    }

    public void quitar(Persona persona) {
        integrantes.remove(persona);
    }
}