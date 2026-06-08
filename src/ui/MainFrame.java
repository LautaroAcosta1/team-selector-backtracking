package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private JTextArea txtResultado;

    public MainFrame() {
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
}