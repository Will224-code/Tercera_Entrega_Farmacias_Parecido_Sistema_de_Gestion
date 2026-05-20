package view.panels.medico;

import javax.swing.*;
import java.awt.*;

public class MedicoConsultaPanel extends JPanel {

    private JTextField txtEstatura;
    private JTextField txtPeso;
    private JTextField txtTemperatura;
    private JTextArea txtObservaciones;
    private JTextArea txtDiagnostico;
    private JTextArea txtTratamiento;
    private JTextArea txtEstudios;
    private JTextField txtMedicamentos; // nombre y duración en un solo campo
    private JButton btnGuardar;

    public MedicoConsultaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel principal con scroll
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Sección Datos Vitales
        JPanel vitalesPanel = createSectionPanel("Datos Vitales");
        JPanel vitalesFields = new JPanel(new GridLayout(1, 6, 10, 5));
        vitalesFields.add(new JLabel("Estatura (m):"));
        txtEstatura = new JTextField(8);
        vitalesFields.add(txtEstatura);
        vitalesFields.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField(8);
        vitalesFields.add(txtPeso);
        vitalesFields.add(new JLabel("Temperatura (°C):"));
        txtTemperatura = new JTextField(5);
        vitalesFields.add(txtTemperatura);
        vitalesPanel.add(vitalesFields, BorderLayout.CENTER);

        // Sección Datos Clínicos
        JPanel clinicosPanel = createSectionPanel("Datos Clínicos");
        clinicosPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        c.gridx = 0; c.gridy = 0;
        clinicosPanel.add(new JLabel("Observaciones:"), c);
        c.gridx = 1;
        txtObservaciones = new JTextArea(3, 20);
        clinicosPanel.add(new JScrollPane(txtObservaciones), c);

        c.gridx = 0; c.gridy = 1;
        clinicosPanel.add(new JLabel("Diagnóstico:"), c);
        c.gridx = 1;
        txtDiagnostico = new JTextArea(3, 20);
        clinicosPanel.add(new JScrollPane(txtDiagnostico), c);

        c.gridx = 0; c.gridy = 2;
        clinicosPanel.add(new JLabel("Tratamiento:"), c);
        c.gridx = 1;
        txtTratamiento = new JTextArea(3, 20);
        clinicosPanel.add(new JScrollPane(txtTratamiento), c);

        c.gridx = 0; c.gridy = 3;
        clinicosPanel.add(new JLabel("Estudios solicitados:"), c);
        c.gridx = 1;
        txtEstudios = new JTextArea(3, 20);
        clinicosPanel.add(new JScrollPane(txtEstudios), c);

        c.gridx = 0; c.gridy = 4;
        clinicosPanel.add(new JLabel("Medicamentos (nombre y duración):"), c);
        c.gridx = 1;
        txtMedicamentos = new JTextField(30);
        clinicosPanel.add(txtMedicamentos, c);

        // Botón Guardar
        JPanel buttonPanel = new JPanel();
        btnGuardar = new JButton("Guardar Consulta");
        buttonPanel.add(btnGuardar);

        // Ensamblar
        gbc.gridy = 0;
        mainPanel.add(vitalesPanel, gbc);
        gbc.gridy = 1;
        mainPanel.add(clinicosPanel, gbc);

        JScrollPane scroll = new JScrollPane(mainPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    // Métodos públicos
    public JTextField getTxtEstatura() {
        return txtEstatura;
    }

    public JTextField getTxtPeso() {
        return txtPeso;
    }

    public JTextField getTxtTemperatura() {
        return txtTemperatura;
    }

    public JTextArea getTxtObservaciones() {
        return txtObservaciones;
    }

    public JTextArea getTxtDiagnostico() {
        return txtDiagnostico;
    }

    public JTextArea getTxtTratamiento() {
        return txtTratamiento;
    }

    public JTextArea getTxtEstudios() {
        return txtEstudios;
    }

    public JTextField getTxtMedicamentos() {
        return txtMedicamentos;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }
}