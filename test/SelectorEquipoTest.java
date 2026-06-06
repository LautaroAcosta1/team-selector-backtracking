package test;

import modelo.*;
import org.junit.Test;
import servicio.SelectorEquipo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SelectorEquipoTest {

    @Test
    public void encuentraEquipoValido() {

        List<Persona> personas = new ArrayList<>();

        Persona lider =
                new Persona("Juan",
                        Rol.LIDER,
                        5);

        Persona programador =
                new Persona("Pedro",
                        Rol.PROGRAMADOR,
                        4);

        personas.add(lider);
        personas.add(programador);

        List<Incompatibilidad> incompatibilidades =
                new ArrayList<>();

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

        SelectorEquipo selector =
                new SelectorEquipo();

        Equipo equipo =
                selector.resolver(
                        personas,
                        incompatibilidades,
                        requerimientos
                );

        assertNotNull(equipo);

        assertEquals(
                2,
                equipo.getIntegrantes().size()
        );
    }

    @Test
    public void respetaIncompatibilidades() {

        Persona lider =
                new Persona(
                        "Juan",
                        Rol.LIDER,
                        5
                );

        Persona pedro =
                new Persona(
                        "Pedro",
                        Rol.PROGRAMADOR,
                        4
                );

        Persona ana =
                new Persona(
                        "Ana",
                        Rol.PROGRAMADOR,
                        5
                );

        List<Persona> personas =
                new ArrayList<>();

        personas.add(lider);
        personas.add(pedro);
        personas.add(ana);

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

        SelectorEquipo selector =
                new SelectorEquipo();

        Equipo equipo =
                selector.resolver(
                        personas,
                        incompatibilidades,
                        requerimientos
                );

        assertEquals(
                2,
                equipo.getIntegrantes().size()
        );
    }

    @Test
    public void eligeEquipoConMayorPuntaje() {

        Persona lider =
                new Persona(
                        "Juan",
                        Rol.LIDER,
                        5
                );

        Persona programador1 =
                new Persona(
                        "Pedro",
                        Rol.PROGRAMADOR,
                        3
                );

        Persona programador2 =
                new Persona(
                        "Ana",
                        Rol.PROGRAMADOR,
                        10
                );

        List<Persona> personas =
                new ArrayList<>();

        personas.add(lider);
        personas.add(programador1);
        personas.add(programador2);

        List<Incompatibilidad> incompatibilidades =
                new ArrayList<>();

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

        SelectorEquipo selector =
                new SelectorEquipo();

        Equipo equipo =
                selector.resolver(
                        personas,
                        incompatibilidades,
                        requerimientos
                );

        assertEquals(
                15,
                equipo.getPuntajeTotal()
        );
    }

    @Test
    public void devuelveNullSiNoExisteSolucion() {

        Persona lider =
                new Persona(
                        "Juan",
                        Rol.LIDER,
                        5
                );

        List<Persona> personas =
                new ArrayList<>();

        personas.add(lider);

        List<Incompatibilidad> incompatibilidades =
                new ArrayList<>();

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

        SelectorEquipo selector =
                new SelectorEquipo();

        Equipo equipo =
                selector.resolver(
                        personas,
                        incompatibilidades,
                        requerimientos
                );

        assertNull(equipo);
    }
}