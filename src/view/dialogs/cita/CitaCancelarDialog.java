package view.dialogs.cita;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para cancelar una cita con motivo obligatorio.
 */
public class CitaCancelarDialog extends JDialog {

    private JTextArea txtMotivo;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public CitaCancelarDialog(Frame parent, int idCita) {
        super(parent, "Cancelar Cita - ID: " + idCita, true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
       // setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel superior con advertencia
        JPanel warningPanel = new JPanel();
        warningPanel.setBackground(new Color(255, 243, 205));
        warningPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 234, 167)));
        JLabel lblWarning = new JLabel("⚠ Requiere mínimo 72 horas de anticipación");
        lblWarning.setForeground(new Color(133, 100, 4));
        warningPanel.add(lblWarning);

        // Panel central con motivo
        JPanel motivoPanel = new JPanel(new BorderLayout(5, 5));
        motivoPanel.setBorder(BorderFactory.createTitledBorder("Motivo de cancelación *"));
        txtMotivo = new JTextArea(5, 30);
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtMotivo);
        motivoPanel.add(scroll, BorderLayout.CENTER);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnConfirmar = new JButton("Confirmar Cancelación");
        btnConfirmar.setBackground(new Color(220, 53, 69));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnCancelar = new JButton("Volver");
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnConfirmar);

        btnCancelar.addActionListener(e -> dispose());

        add(warningPanel, BorderLayout.NORTH);
        add(motivoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTextArea getTxtMotivo() { return txtMotivo; }
    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
