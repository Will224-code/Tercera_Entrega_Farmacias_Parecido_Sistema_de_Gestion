package view.panels.admin;

import javax.swing.*;
import java.awt.*;

/**
 * Panel vacío para administración de enfermeros.
 * Se completará en futuros requerimientos.
 */
public class AdminEnfermeroPanel extends JPanel {
    public AdminEnfermeroPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Módulo de Enfermeros (en construcción)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.CENTER);
    }
}