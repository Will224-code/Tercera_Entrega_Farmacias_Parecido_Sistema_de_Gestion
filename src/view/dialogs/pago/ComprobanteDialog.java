package view.dialogs.pago;

import model.entity.Comprobante;
import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para visualizar un comprobante de pago.
 * Diseño tipo ticket/factura.
 */
public class ComprobanteDialog extends JDialog {

    private JLabel lblNumeroConsulta;
    private JLabel lblPaciente;
    private JLabel lblEspecialidad;
    private JLabel lblMetodoPago;
    private JLabel lblFecha;
    private JLabel lblMonto;
    private JButton btnCerrar;
    private JButton btnImprimir;

    public ComprobanteDialog(Frame parent, Comprobante comprobante) {
        super(parent, "Comprobante de Pago", true);
        setSize(400, 450);
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
        if (comprobante != null) {
            cargarDatos(comprobante);
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        //setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel tipo ticket
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBackground(Color.WHITE);
        ticketPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Título
        JLabel lblTitulo = new JLabel("FARMACIAS PARECIDO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ticketPanel.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Comprobante de Pago", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ticketPanel.add(lblSubtitulo);
        ticketPanel.add(Box.createVerticalStrut(15));

        // Datos
        ticketPanel.add(crearFilaTicket("No. Consulta:", lblNumeroConsulta = new JLabel("-")));
        ticketPanel.add(crearFilaTicket("Paciente:", lblPaciente = new JLabel("-")));
        ticketPanel.add(crearFilaTicket("Especialidad:", lblEspecialidad = new JLabel("-")));
        ticketPanel.add(crearFilaTicket("Método de Pago:", lblMetodoPago = new JLabel("-")));
        ticketPanel.add(crearFilaTicket("Fecha:", lblFecha = new JLabel("-")));
        ticketPanel.add(Box.createVerticalStrut(10));

        // Línea separadora
        JSeparator sep = new JSeparator();
        sep.setForeground(Color.BLACK);
        ticketPanel.add(sep);
        ticketPanel.add(Box.createVerticalStrut(10));

        // Monto total
        JPanel montoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        montoPanel.setBackground(Color.WHITE);
        JLabel lblMontoLabel = new JLabel("TOTAL: $");
        lblMontoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblMonto = new JLabel("0.00");
        lblMonto.setFont(new Font("Arial", Font.BOLD, 14));
        montoPanel.add(lblMontoLabel);
        montoPanel.add(lblMonto);
        ticketPanel.add(montoPanel);

        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnImprimir = new JButton("Imprimir");
        btnCerrar = new JButton("Cerrar");
        buttonPanel.add(btnImprimir);
        buttonPanel.add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());
        btnImprimir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función de impresión no implementada en esta versión.");
        });

        add(ticketPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel crearFilaTicket(String label, JLabel valor) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 11));
        valor.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(lbl, BorderLayout.WEST);
        panel.add(valor, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        return panel;
    }

    public void cargarDatos(Comprobante c) {
        lblNumeroConsulta.setText(c.getNumeroConsulta());
        lblPaciente.setText(c.getNombrePaciente());
        lblEspecialidad.setText(c.getEspecialidad());
        lblMetodoPago.setText(c.getMetodoPago());
        lblFecha.setText(c.getFecha().toString());
        lblMonto.setText(String.format("%.2f", c.getMonto()));
    }

    public JButton getBtnCerrar() { return btnCerrar; }
    public JButton getBtnImprimir() { return btnImprimir; }
}
