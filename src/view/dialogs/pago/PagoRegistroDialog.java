package view.dialogs.pago;

import model.enums.MetodoPago;
import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para registrar un pago.
 */
public class PagoRegistroDialog extends JDialog {

    private JTextField txtMonto;
    private JComboBox<MetodoPago> cmbMetodoPago;
    private JButton btnProcesar;
    private JButton btnCancelar;

    public PagoRegistroDialog(Frame parent) {
        super(parent, "Registrar Pago", true);
        setSize(350, 200);
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
        formPanel.add(new JLabel("Monto ($):*"), gbc);
        gbc.gridx = 1;
        txtMonto = new JTextField(10);
        formPanel.add(txtMonto, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Método de pago:*"), gbc);
        gbc.gridx = 1;
        cmbMetodoPago = new JComboBox<>(MetodoPago.values());
        formPanel.add(cmbMetodoPago, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnProcesar = new JButton("Procesar Pago");
        btnProcesar.setBackground(new Color(40, 167, 69));
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setFocusPainted(false);
        btnCancelar = new JButton("Cancelar");
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnProcesar);

        btnCancelar.addActionListener(e -> dispose());

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextField getTxtMonto() { return txtMonto; }
    public JComboBox<MetodoPago> getCmbMetodoPago() { return cmbMetodoPago; }
    public JButton getBtnProcesar() { return btnProcesar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
