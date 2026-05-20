package view.panels.medico;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MedicoPacientePanel extends JPanel {

    private JTextField txtBusqueda;
    private JButton btnBuscar;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<ActionListener> verExpedienteListeners;

    private static final int COL_EXPEDIENTE = 0;
    private static final int COL_NOMBRE = 1;
    private static final int COL_CURP = 2;
    private static final int COL_EDAD = 3;
    private static final int COL_ESTADO = 4;
    private static final int COL_VER_EXPEDIENTE = 5;

    public MedicoPacientePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        verExpedienteListeners = new ArrayList<>();

        // Barra de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Buscar paciente"));
        searchPanel.add(new JLabel("Nombre / Expediente:"));
        txtBusqueda = new JTextField(20);
        searchPanel.add(txtBusqueda);
        btnBuscar = new JButton("Buscar");
        searchPanel.add(btnBuscar);

        // Tabla
        String[] columnNames = {"Expediente", "Nombre", "CURP", "Edad", "Estado", "Ver Expediente"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        table.getColumnModel().getColumn(COL_VER_EXPEDIENTE).setCellRenderer(new ButtonRenderer("Ver Expediente"));

        table.getColumnModel().getColumn(COL_EXPEDIENTE).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_NOMBRE).setPreferredWidth(180);
        table.getColumnModel().getColumn(COL_CURP).setPreferredWidth(120);
        table.getColumnModel().getColumn(COL_EDAD).setPreferredWidth(60);
        table.getColumnModel().getColumn(COL_ESTADO).setPreferredWidth(80);
        table.getColumnModel().getColumn(COL_VER_EXPEDIENTE).setPreferredWidth(120);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col == COL_VER_EXPEDIENTE) {
                    if (row < verExpedienteListeners.size() && verExpedienteListeners.get(row) != null) {
                        verExpedienteListeners.get(row).actionPerformed(null);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de pacientes"));

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Métodos públicos
    public JTextField getTxtBusqueda() {
        return txtBusqueda;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnVerExpedienteListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < verExpedienteListeners.size()) {
            verExpedienteListeners.set(fila, l);
        }
    }

    public void setDatos(Object[][] datos) {
        tableModel.setRowCount(0);
        verExpedienteListeners.clear();

        if (datos == null) return;

        for (Object[] fila : datos) {
            Object[] filaCompleta = new Object[6];
            System.arraycopy(fila, 0, filaCompleta, 0, Math.min(fila.length, 5));
            filaCompleta[COL_VER_EXPEDIENTE] = "";
            tableModel.addRow(filaCompleta);
            verExpedienteListeners.add(null);
        }
    }

    public int getFilaSeleccionada() {
        return table.getSelectedRow();
    }

    private static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}