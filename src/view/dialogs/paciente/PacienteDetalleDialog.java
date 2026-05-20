package view.dialogs.paciente;

import model.entity.Paciente;
import model.entity.Consulta;
import model.entity.Cita;
import model.service.PacienteService;
import model.service.ConsultaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Diálogo modal para visualizar el expediente completo de un paciente.
 * Solo accesible por MÉDICO (controlado por Controller).
 */
public class PacienteDetalleDialog extends JDialog {

    private JLabel lblExpediente;
    private JLabel lblNombre;
    private JLabel lblCURP;
    private JLabel lblEdad;
    private JLabel lblEstadoCivil;
    private JLabel lblDireccion;
    private JTable tableCitas;
    private DefaultTableModel modelCitas;
    private JTable tableConsultas;
    private DefaultTableModel modelConsultas;
    private JButton btnCerrar;

    public PacienteDetalleDialog(Frame parent, Paciente paciente) {
        super(parent, "Expediente Clínico - " + paciente.getNombreCompleto(), true);
        setSize(700, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
        cargarDatosPaciente(paciente);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        //setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de datos personales
        JPanel datosPanel = new JPanel(new GridBagLayout());
        datosPanel.setBorder(BorderFactory.createTitledBorder("Datos Personales"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        datosPanel.add(new JLabel("Expediente:"), gbc);
        gbc.gridx = 1;
        lblExpediente = new JLabel();
        datosPanel.add(lblExpediente, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        datosPanel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        lblNombre = new JLabel();
        datosPanel.add(lblNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        datosPanel.add(new JLabel("CURP:"), gbc);
        gbc.gridx = 1;
        lblCURP = new JLabel();
        datosPanel.add(lblCURP, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        datosPanel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        lblEdad = new JLabel();
        datosPanel.add(lblEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        datosPanel.add(new JLabel("Estado Civil:"), gbc);
        gbc.gridx = 1;
        lblEstadoCivil = new JLabel();
        datosPanel.add(lblEstadoCivil, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        datosPanel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        lblDireccion = new JLabel();
        datosPanel.add(lblDireccion, gbc);

        // Tabla de citas
        String[] colsCitas = {"Fecha", "Hora", "Médico", "Estado"};
        modelCitas = new DefaultTableModel(colsCitas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableCitas = new JTable(modelCitas);
        tableCitas.setRowHeight(22);
        JScrollPane scrollCitas = new JScrollPane(tableCitas);
        scrollCitas.setBorder(BorderFactory.createTitledBorder("Historial de Citas"));
        scrollCitas.setPreferredSize(new Dimension(0, 150));

        // Tabla de consultas
        String[] colsConsultas = {"Fecha", "Diagnóstico", "Tratamiento", "Medicamentos"};
        modelConsultas = new DefaultTableModel(colsConsultas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tableConsultas = new JTable(modelConsultas);
        tableConsultas.setRowHeight(22);
        JScrollPane scrollConsultas = new JScrollPane(tableConsultas);
        scrollConsultas.setBorder(BorderFactory.createTitledBorder("Historial Clínico"));
        scrollConsultas.setPreferredSize(new Dimension(0, 180));

        // Panel central con tablas
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        centerPanel.add(scrollCitas);
        centerPanel.add(scrollConsultas);

        // Botón cerrar
        JPanel buttonPanel = new JPanel();
        btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        buttonPanel.add(btnCerrar);

        add(datosPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void cargarDatosPaciente(Paciente p) {
        lblExpediente.setText(p.getNumeroExpediente());
        lblNombre.setText(p.getNombreCompleto());
        lblCURP.setText(p.getCurp());
        lblEdad.setText(p.getEdad() + " años");
        lblEstadoCivil.setText(p.getEstadoCivil());
        lblDireccion.setText(p.getDireccion());
    }

    public void cargarCitas(List<Cita> citas) {
        modelCitas.setRowCount(0);
        if (citas == null) return;
        for (Cita c : citas) {
            modelCitas.addRow(new Object[]{
                c.getFecha(),
                c.getHora(),
                c.getMedico().getNombreCompleto(),
                c.getEstadoCita().getNombre()
            });
        }
    }

    public void cargarConsultas(List<Consulta> consultas) {
        modelConsultas.setRowCount(0);
        if (consultas == null) return;
        for (Consulta c : consultas) {
            modelConsultas.addRow(new Object[]{
                c.getCita().getFecha(),
                c.getDiagnostico(),
                c.getTratamiento(),
                c.getMedicamentos()
            });
        }
    }

    public JButton getBtnCerrar() { return btnCerrar; }
}
