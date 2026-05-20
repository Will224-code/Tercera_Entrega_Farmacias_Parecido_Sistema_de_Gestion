package view.dialogs.medico;

import model.enums.Especialidad;
import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para registrar un nuevo médico.
 */
public class MedicoRegistroDialog extends JDialog {

    private JTextField txtNombre;
    private JComboBox<Especialidad> cmbEspecialidad;
    private JTextField txtTelFijo;
    private JTextField txtTelCelular;
    private JTextField txtCorreo;
    private JButton btnGuardar;
    private JButton btnCancelar;

    public MedicoRegistroDialog(Frame parent) {
        super(parent, "Registrar Médico", true);
        setSize(450, 300);
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

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre completo:*"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Especialidad:*"), gbc);
        gbc.gridx = 1;
        cmbEspecialidad = new JComboBox<>(Especialidad.values());
        formPanel.add(cmbEspecialidad, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Teléfono fijo:"), gbc);
        gbc.gridx = 1;
        txtTelFijo = new JTextField(15);
        formPanel.add(txtTelFijo, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Teléfono celular:"), gbc);
        gbc.gridx = 1;
        txtTelCelular = new JTextField(15);
        formPanel.add(txtTelCelular, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Correo electrónico:"), gbc);
        gbc.gridx = 1;
        txtCorreo = new JTextField(20);
        formPanel.add(txtCorreo, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtNombre() { return txtNombre; }
    public JComboBox<Especialidad> getCmbEspecialidad() { return cmbEspecialidad; }
    public JTextField getTxtTelFijo() { return txtTelFijo; }
    public JTextField getTxtTelCelular() { return txtTelCelular; }
    public JTextField getTxtCorreo() { return txtCorreo; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
