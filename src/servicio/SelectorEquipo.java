package servicio;

import modelo.Equipo;
import modelo.Incompatibilidad;
import modelo.Persona;
import modelo.Requerimiento;

import java.util.List;

public class SelectorEquipo {

    private Equipo mejorEquipo;
    private int mejorPuntaje;

    public Equipo resolver(
            List<Persona> personas,
            List<Incompatibilidad> incompatibilidades,
            List<Requerimiento> requerimientos) {

        mejorEquipo = null;
        mejorPuntaje = -1;

        backtracking(
                personas,
                incompatibilidades,
                requerimientos,
                0,
                new Equipo()
        );

        return mejorEquipo;
    }

    private void backtracking(
            List<Persona> personas,
            List<Incompatibilidad> incompatibilidades,
            List<Requerimiento> requerimientos,
            int indice,
            Equipo equipoActual) {

        if (indice == personas.size()) {

            if (esSolucion(equipoActual, requerimientos)) {

                int puntaje =
                        equipoActual.getPuntajeTotal();

                if (puntaje > mejorPuntaje) {

                    mejorPuntaje = puntaje;
                    mejorEquipo = equipoActual.copiar();
                }
            }

            return;
        }

        Persona persona =
                personas.get(indice);

        // rama 1: no agregar

        backtracking(
                personas,
                incompatibilidades,
                requerimientos,
                indice + 1,
                equipoActual
        );

        // rama 2: agregar

        if (!hayIncompatibilidad(
                persona,
                equipoActual,
                incompatibilidades)
                &&
                cumpleRequerimientosParciales(
                        persona,
                        equipoActual,
                        requerimientos)) {

            equipoActual.agregar(persona);

            backtracking(
                    personas,
                    incompatibilidades,
                    requerimientos,
                    indice + 1,
                    equipoActual
            );

            equipoActual.quitar(persona);
        }
    }

    private boolean hayIncompatibilidad(
            Persona persona,
            Equipo equipo,
            List<Incompatibilidad> incompatibilidades) {

        for (Incompatibilidad inc : incompatibilidades) {

            Persona p1 = inc.getPersona1();
            Persona p2 = inc.getPersona2();

            if (p1.equals(persona) && equipo.contiene(p2)) {
                return true;
            }

            if (p2.equals(persona) && equipo.contiene(p1)) {
                return true;
            }
        }

        return false;
    }

    private boolean cumpleRequerimientosParciales(
            Persona persona,
            Equipo equipo,
            List<Requerimiento> requerimientos) {

        int cantidadActual = 0;

        for (Persona integrante : equipo.getIntegrantes()) {

            if (integrante.getRol().equals(persona.getRol())) {
                cantidadActual++;
            }
        }

        cantidadActual++;

        for (Requerimiento req : requerimientos) {

            if (req.getRol().equals(persona.getRol())) {

                return cantidadActual <= req.getCantidad();
            }
        }

        return false;
    }

    private boolean esSolucion(
            Equipo equipo,
            List<Requerimiento> requerimientos) {

        for (Requerimiento req : requerimientos) {

            int cantidad = 0;

            for (Persona persona : equipo.getIntegrantes()) {

                if (persona.getRol().equals(req.getRol())) {
                    cantidad++;
                }
            }

            if (cantidad != req.getCantidad()) {
                return false;
            }
        }

        return true;
    }
}