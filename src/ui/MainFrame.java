package ui;

import javax.swing.*;
import java.awt.*;

import modelo.Persona;
import modelo.Rol;

import java.util.ArrayList;
import java.util.List;

public class MainFrame {

    private JFrame frame;
    private JTextArea txtResultado;

    private List<Persona> personas;

    public MainFrame() {

        personas = new ArrayList<>();

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

        JButton btnRequerimiento =
                new JButton("Agregar Requerimiento");

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
}