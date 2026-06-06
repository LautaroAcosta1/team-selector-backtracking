package ui;

import modelo.*;
import servicio.SelectorEquipo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Persona> personas = new ArrayList<>();

        Persona juan =
                new Persona("Juan",
                        Rol.LIDER,
                        5);

        Persona pedro =
                new Persona("Pedro",
                        Rol.PROGRAMADOR,
                        4);

        Persona ana =
                new Persona("Ana",
                        Rol.PROGRAMADOR,
                        5);

        Persona maria =
                new Persona("Maria",
                        Rol.TESTER,
                        3);

        Persona lucas =
                new Persona("Lucas",
                        Rol.TESTER,
                        5);

        personas.add(juan);
        personas.add(pedro);
        personas.add(ana);
        personas.add(maria);
        personas.add(lucas);

        List<Incompatibilidad> incompatibilidades =
                new ArrayList<>();

        incompatibilidades.add(
                new Incompatibilidad(
                        pedro,
                        ana
                )
        );

        List<Requerimiento> requerimientos =
                new ArrayList<>();

        requerimientos.add(
                new Requerimiento(
                        Rol.LIDER,
                        1
                )
        );

        requerimientos.add(
                new Requerimiento(
                        Rol.PROGRAMADOR,
                        1
                )
        );

        requerimientos.add(
                new Requerimiento(
                        Rol.TESTER,
                        1
                )
        );

        SelectorEquipo selector =
                new SelectorEquipo();

        Equipo equipo =
                selector.resolver(
                        personas,
                        incompatibilidades,
                        requerimientos
                );

        System.out.println(
                "EQUIPO SELECCIONADO"
        );

        for (Persona p :
                equipo.getIntegrantes()) {

            System.out.println(p);
        }

        System.out.println(
                "PUNTAJE TOTAL: "
                        + equipo.getPuntajeTotal()
        );
    }
}