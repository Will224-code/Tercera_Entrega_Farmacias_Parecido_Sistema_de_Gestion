package view.panels.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Panel de reportes con filtros.
 * Solo vista – sin lógica de negocio.
 */
public class AdminReportePanel extends JPanel {

    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtMedico;
    private JComboBox<String> cmbEspecialidad;
    private JTextField txtPaciente;
    private JButton btnGenerar;
    private JButton btnLimpiar;
    private JTable table;
    private DefaultTableModel tableModel;

    public AdminReportePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de filtros
        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filtros de Reporte"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridx = 0; gbc.gridy = 0;
        filterPanel.add(new JLabel("Fecha inicio (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtFechaInicio = new JTextField(10);
        filterPanel.add(txtFechaInicio, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        filterPanel.add(new JLabel("Fecha fin (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        txtFechaFin = new JTextField(10);
        filterPanel.add(txtFechaFin, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        filterPanel.add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1;
        txtMedico = new JTextField(15);
        filterPanel.add(txtMedico, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        filterPanel.add(new JLabel("Especialidad:"), gbc);
        gbc.gridx = 1;
        String[] especialidades = {"", "PEDIATRIA", "CARDIOLOGIA", "DERMATOLOGIA", "NEUROLOGIA", "GENERAL"};
        cmbEspecialidad = new JComboBox<>(especialidades);
        filterPanel.add(cmbEspecialidad, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        filterPanel.add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1;
        txtPaciente = new JTextField(15);
        filterPanel.add(txtPaciente, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.setBackground(new Color(0, 123, 255));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        btnLimpiar = new JButton("Limpiar Filtros");
        buttonPanel.add(btnGenerar);
        buttonPanel.add(btnLimpiar);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        filterPanel.add(buttonPanel, gbc);

        // Tabla de resultados
        String[] columnNames = {"Fecha", "Consultorio", "Especialidad", "Médico", "Paciente", "Motivo Consulta"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados"));

        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextField getTxtFechaInicio() { return txtFechaInicio; }
    public JTextField getTxtFechaFin() { return txtFechaFin; }
    public JTextField getTxtMedico() { return txtMedico; }
    public JComboBox<String> getCmbEspecialidad() { return cmbEspecialidad; }
    public JTextField getTxtPaciente() { return txtPaciente; }
    public JButton getBtnGenerar() { return btnGenerar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public Date getFechaInicio() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(sdf.parse(txtFechaInicio.getText().trim()).getTime());
        } catch (ParseException e) { return null; }
    }

    public Date getFechaFin() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(sdf.parse(txtFechaFin.getText().trim()).getTime());
        } catch (ParseException e) { return null; }
    }

    public void setDatos(Object[][] datos) {
        tableModel.setRowCount(0);
        if (datos == null) return;
        for (Object[] fila : datos) {
            tableModel.addRow(fila);
        }
    }
}
