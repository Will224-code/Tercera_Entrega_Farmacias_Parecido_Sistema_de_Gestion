package view.dialogs.paciente;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para el registro de un nuevo paciente.
 * Solo vista – sin lógica de negocio.
 */
public class PacienteRegistroDialog extends JDialog {

    private JTextField txtNombre;
    private JTextField txtCURP;
    private JTextField txtDireccion;
    private JComboBox<String> cmbEstadoCivil;
    private JTextField txtEdad;
    private JButton btnGuardar;
    private JButton btnCancelar;

    /**
     * Constructor del diálogo.
     * @param parent Frame padre sobre el cual se muestra el diálogo (modal).
     */
    public PacienteRegistroDialog(Frame parent) {
        super(parent, "Registrar Paciente", true); // modal
        setSize(450, 350);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initComponents();
    }

    /**
     * Inicializa y organiza los componentes del formulario.
     */
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel central con los campos
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Nombre completo
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre completo:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        formPanel.add(txtNombre, gbc);

        // CURP
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("CURP:"), gbc);
        gbc.gridx = 1;
        txtCURP = new JTextField(20);
        formPanel.add(txtCURP, gbc);

        // Dirección
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        txtDireccion = new JTextField(20);
        formPanel.add(txtDireccion, gbc);

        // Estado civil
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Estado civil:"), gbc);
        gbc.gridx = 1;
        String[] estados = {"Soltero", "Casado", "Divorciado", "Viudo", "Unión libre"};
        cmbEstadoCivil = new JComboBox<>(estados);
        formPanel.add(cmbEstadoCivil, gbc);

        // Edad
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(5);
        formPanel.add(txtEdad, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnGuardar);
        buttonPanel.add(btnCancelar);

        // Acción del botón Cancelar
        btnCancelar.addActionListener(e -> dispose());

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // -------------------- MÉTODOS PÚBLICOS PARA EL CONTROLLER --------------------

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCURP() {
        return txtCURP;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JComboBox<String> getCmbEstadoCivil() {
        return cmbEstadoCivil;
    }

    public JTextField getTxtEdad() {
        return txtEdad;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarCampos() {
        txtNombre.setText("");
        txtCURP.setText("");
        txtDireccion.setText("");
        cmbEstadoCivil.setSelectedIndex(0);
        txtEdad.setText("");
    }
}