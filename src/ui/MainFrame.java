package ui;

import javax.swing.*;
import java.awt.*;

import modelo.Persona;
import modelo.Rol;
import modelo.Requerimiento;
import modelo.Incompatibilidad;

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

        panelBotones.add(btnPersona);
        panelBotones.add(btnIncompatibilidad);
        panelBotones.add(btnRequerimiento);
        panelBotones.add(btnResolver);

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
}