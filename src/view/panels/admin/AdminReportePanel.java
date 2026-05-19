package view.panels.admin;

import javax.swing.*;
import java.awt.*;

/**
 * Panel vacío para reportes.
 * Se completará en futuros requerimientos.
 */
public class AdminReportePanel extends JPanel {
    public AdminReportePanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Módulo de Reportes (en construcción)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.CENTER);
    }
}