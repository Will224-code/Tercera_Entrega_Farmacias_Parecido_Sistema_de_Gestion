package view.panels.admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel de administración de enfermeros.
 * Solo vista – sin lógica de negocio.
 */
public class AdminEnfermeroPanel extends JPanel {

    private JButton btnRegistrar;
    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<ActionListener> disableListeners;

    private static final int COL_ID = 0;
    private static final int COL_NOMBRE = 1;
    private static final int COL_CURP = 2;
    private static final int COL_MEDICO = 3;
    private static final int COL_ESTADO = 4;
    private static final int COL_DESHABILITAR = 5;

    public AdminEnfermeroPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        disableListeners = new ArrayList<>();

        String[] columnNames = {"ID", "Nombre", "CURP", "Médico Asignado", "Estado", "Deshabilitar"};
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

        table.getColumnModel().getColumn(COL_DESHABILITAR).setCellRenderer(new ButtonRenderer("Deshabilitar"));

        table.getColumnModel().getColumn(COL_ID).setPreferredWidth(50);
        table.getColumnModel().getColumn(COL_NOMBRE).setPreferredWidth(150);
        table.getColumnModel().getColumn(COL_CURP).setPreferredWidth(120);
        table.getColumnModel().getColumn(COL_MEDICO).setPreferredWidth(150);
        table.getColumnModel().getColumn(COL_ESTADO).setPreferredWidth(80);
        table.getColumnModel().getColumn(COL_DESHABILITAR).setPreferredWidth(100);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row >= 0 && col == COL_DESHABILITAR) {
                    if (row < disableListeners.size() && disableListeners.get(row) != null) {
                        disableListeners.get(row).actionPerformed(null);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Enfermeros"));

        JPanel bottomPanel = new JPanel();
        btnRegistrar = new JButton("Registrar Enfermero");
        bottomPanel.add(btnRegistrar);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getBtnRegistrar() { return btnRegistrar; }

    public void setBtnDeshabilitarListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < disableListeners.size()) {
            disableListeners.set(fila, l);
        }
    }

    public void setDatos(Object[][] datos) {
        tableModel.setRowCount(0);
        disableListeners.clear();
        if (datos == null) return;

        for (Object[] fila : datos) {
            Object[] filaCompleta = new Object[6];
            System.arraycopy(fila, 0, filaCompleta, 0, Math.min(fila.length, 5));
            filaCompleta[COL_DESHABILITAR] = "";
            tableModel.addRow(filaCompleta);
            disableListeners.add(null);
        }
    }

    public DefaultTableModel getTableModel() { return tableModel; }

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
}
