package view.dialogs.cita;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Diálogo modal para agendar una nueva cita.
 */
public class CitaAgendarDialog extends JDialog {

    private JComboBox<String> cmbPaciente;
    private JComboBox<String> cmbMedico;
    private JTextField txtFecha;
    private JComboBox<String> cmbHora;
    private JTextField txtConsultorio;
    private JButton btnGuardar;
    private JButton btnCancelar;

    public CitaAgendarDialog(Frame parent) {
        super(parent, "Agendar Cita", true);
        setSize(500, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        //setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Paciente
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1;
        cmbPaciente = new JComboBox<>();
        cmbPaciente.setPreferredSize(new Dimension(250, 25));
        formPanel.add(cmbPaciente, gbc);

        // Médico
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1;
        cmbMedico = new JComboBox<>();
        cmbMedico.setPreferredSize(new Dimension(250, 25));
        formPanel.add(cmbMedico, gbc);

        // Fecha
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Fecha (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtFecha = new JTextField(12);
        txtFecha.setToolTipText("Formato: YYYY-MM-DD");
        formPanel.add(txtFecha, gbc);

        // Hora
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Hora:"), gbc);
        gbc.gridx = 1;
        String[] horas = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        cmbHora = new JComboBox<>(horas);
        formPanel.add(cmbHora, gbc);

        // Consultorio
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Consultorio:"), gbc);
        gbc.gridx = 1;
        txtConsultorio = new JTextField(15);
        formPanel.add(txtConsultorio, gbc);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Getters para el controller
    public JComboBox<String> getCmbPaciente() { return cmbPaciente; }
    public JComboBox<String> getCmbMedico() { return cmbMedico; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JComboBox<String> getCmbHora() { return cmbHora; }
    public JTextField getTxtConsultorio() { return txtConsultorio; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public Date getFechaSeleccionada() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = sdf.parse(txtFecha.getText().trim());
            return new Date(fecha.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public Time getHoraSeleccionada() {
        String horaStr = (String) cmbHora.getSelectedItem();
        return Time.valueOf(horaStr + ":00");
    }
}
