package ui;

import javax.swing.*;
import java.awt.*;

import modelo.Persona;
import modelo.Rol;
import modelo.Requerimiento;
import modelo.Incompatibilidad;

import modelo.Equipo;
import servicio.BusquedaThread;
import servicio.SelectorEquipo;

import java.util.ArrayList;
import java.util.List;

public class MainFrame {

    private JFrame frame;
    private JTextArea txtResultado;

    private List<Persona> personas;
    private List<Requerimiento> requerimientos;
    private List<Incompatibilidad> incompatibilidades;

    public MainFrame() {

        personas = new ArrayList<>();
        requerimientos = new ArrayList<>();
        incompatibilidades = new ArrayList<>();

        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        frame.setTitle("Selector de Equipos");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();

        JButton btnPersona =
                new JButton("Agregar Persona");

        btnPersona.addActionListener(e -> agregarPersona());

        JButton btnIncompatibilidad =
                new JButton("Agregar Incompatibilidad");

        btnIncompatibilidad.addActionListener(
                e -> agregarIncompatibilidad()
        );

        JButton btnRequerimiento =
                new JButton("Agregar Requerimiento");

        btnRequerimiento.addActionListener(
                e -> agregarRequerimiento()
        );

        JButton btnResolver =
                new JButton("Buscar Equipo");

        btnResolver.addActionListener(
                e -> buscarEquipo()
        );

        panelBotones.add(btnPersona);
        panelBotones.add(btnIncompatibilidad);
        panelBotones.add(btnRequerimiento);
        panelBotones.add(btnResolver);

        JButton btnVerPersonas =
                new JButton("Ver Personas");

        btnVerPersonas.addActionListener(
                e -> verPersonas()
        );

        JButton btnVerIncompatibilidades =
                new JButton("Ver Incompatibilidades");

        btnVerIncompatibilidades.addActionListener(
                e -> verIncompatibilidades()
        );

        JButton btnVerRequerimientos =
                new JButton("Ver Requerimientos");

        btnVerRequerimientos.addActionListener(
                e -> verRequerimientos()
        );

        panelBotones.add(btnVerPersonas);
        panelBotones.add(btnVerIncompatibilidades);
        panelBotones.add(btnVerRequerimientos);

        frame.add(panelBotones, BorderLayout.NORTH);

        txtResultado = new JTextArea();

        txtResultado.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(txtResultado);

        frame.add(scroll, BorderLayout.CENTER);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    private void agregarPersona() {

        String nombre =
                JOptionPane.showInputDialog(
                        frame,
                        "Nombre:"
                );

        if (nombre == null || nombre.isBlank()) {
            return;
        }

        Rol rol = (Rol) JOptionPane.showInputDialog(
                frame,
                "Rol:",
                "Seleccionar Rol",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Rol.values(),
                Rol.LIDER
        );

        if (rol == null) {
            return;
        }

        String textoCalificacion =
                JOptionPane.showInputDialog(
                        frame,
                        "Calificación:"
                );

        if (textoCalificacion == null) {
            return;
        }

        int calificacion;

        try {

            calificacion =
                    Integer.parseInt(textoCalificacion);

            if (calificacion < 1 || calificacion > 5) {

                JOptionPane.showMessageDialog(
                        frame,
                        "La calificación debe estar entre 1 y 5."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "La calificación debe ser un número."
            );

            return;
        }

        Persona persona =
                new Persona(
                        nombre,
                        rol,
                        calificacion
                );

        personas.add(persona);

        txtResultado.append(
                "Persona agregada: "
                        + persona
                        + "\n"
        );
    }

    private void agregarRequerimiento() {

        Rol rol = (Rol) JOptionPane.showInputDialog(
                frame,
                "Rol:",
                "Seleccionar Rol",
                JOptionPane.QUESTION_MESSAGE,
                null,
                Rol.values(),
                Rol.LIDER
        );

        if (rol == null) {
            return;
        }

        String textoCantidad =
                JOptionPane.showInputDialog(
                        frame,
                        "Cantidad requerida:"
                );

        if (textoCantidad == null) {
            return;
        }

        int cantidad;

        try {

            cantidad =
                    Integer.parseInt(textoCantidad);

            if (cantidad <= 0) {

                JOptionPane.showMessageDialog(
                        frame,
                        "La cantidad debe ser mayor a cero."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Debe ingresar un número."
            );

            return;
        }

        Requerimiento requerimiento =
                new Requerimiento(
                        rol,
                        cantidad
                );

        requerimientos.add(requerimiento);

        txtResultado.append(
                "Requerimiento agregado: "
                        + rol
                        + " -> "
                        + cantidad
                        + "\n"
        );
    }

    private void agregarIncompatibilidad() {

        if (personas.size() < 2) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Debe haber al menos 2 personas."
            );

            return;
        }

        Persona persona1 = (Persona)
                JOptionPane.showInputDialog(
                        frame,
                        "Primera persona:",
                        "Seleccionar",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        personas.toArray(),
                        personas.get(0)
                );

        if (persona1 == null) {
            return;
        }

        Persona persona2 = (Persona)
                JOptionPane.showInputDialog(
                        frame,
                        "Segunda persona:",
                        "Seleccionar",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        personas.toArray(),
                        personas.get(0)
                );

        if (persona2 == null) {
            return;
        }

        if (persona1.equals(persona2)) {

            JOptionPane.showMessageDialog(
                    frame,
                    "No puede elegir la misma persona."
            );

            return;
        }

        incompatibilidades.add(
                new Incompatibilidad(
                        persona1,
                        persona2
                )
        );

        txtResultado.append(
                "Incompatibilidad: "
                        + persona1.getNombre()
                        + " <-> "
                        + persona2.getNombre()
                        + "\n"
        );
    }

    private void buscarEquipo() {

        txtResultado.setText("");

        SelectorEquipo selector =
                new SelectorEquipo();

        BusquedaThread hilo =
                new BusquedaThread(
                        selector,
                        personas,
                        incompatibilidades,
                        requerimientos
                );

        hilo.start();

        try {

            hilo.join();

        } catch (InterruptedException e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Error al ejecutar la búsqueda."
            );

            return;
        }

        Equipo equipo =
                hilo.getResultado();

        if (equipo == null) {

            JOptionPane.showMessageDialog(
                    frame,
                    "No existe una solución válida."
            );

            return;
        }

        mostrarEquipo(equipo);
    }

    private void mostrarEquipo(Equipo equipo) {

        txtResultado.append(
                "\n====================\n"
        );

        txtResultado.append(
                "EQUIPO ENCONTRADO\n"
        );

        txtResultado.append(
                "====================\n"
        );

        for (Persona p :
                equipo.getIntegrantes()) {

            txtResultado.append(
                    p.toString()
                            + "\n"
            );
        }

        txtResultado.append(
                "\nPUNTAJE TOTAL: "
                        + equipo.getPuntajeTotal()
                        + "\n\n"
        );
    }

    private void verPersonas() {

        StringBuilder sb = new StringBuilder();

        for (Persona p : personas) {
            sb.append(p).append("\n");
        }

        JOptionPane.showMessageDialog(
                frame,
                sb.toString(),
                "Personas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void verIncompatibilidades() {

        StringBuilder sb = new StringBuilder();

        for (Incompatibilidad i : incompatibilidades) {

            sb.append(i.getPersona1().getNombre())
                    .append(" <-> ")
                    .append(i.getPersona2().getNombre())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(
                frame,
                sb.toString(),
                "Incompatibilidades",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void verRequerimientos() {

        StringBuilder sb = new StringBuilder();

        for (Requerimiento r : requerimientos) {

            sb.append(r.getRol())
                    .append(" -> ")
                    .append(r.getCantidad())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(
                frame,
                sb.toString(),
                "Requerimientos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}