package view.panels.admin;

import javax.swing.*;
import java.awt.*;

/**
 * Panel vacío para auditoría.
 * Se completará en futuros requerimientos.
 */
public class AdminAuditoriaPanel extends JPanel {
    public AdminAuditoriaPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Módulo de Auditoría (en construcción)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.CENTER);
    }
}