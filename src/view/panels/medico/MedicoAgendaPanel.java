package view.panels.medico;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MedicoAgendaPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private ArrayList<ActionListener> verDetalleListeners;
    private ArrayList<ActionListener> iniciarConsultaListeners;

    private static final int COL_ID = 0;
    private static final int COL_PACIENTE = 1;
    private static final int COL_FECHA = 2;
    private static final int COL_HORA = 3;
    private static final int COL_ESTADO = 4;
    private static final int COL_VER_DETALLE = 5;
    private static final int COL_INICIAR_CONSULTA = 6;

    public MedicoAgendaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        verDetalleListeners = new ArrayList<>();
        iniciarConsultaListeners = new ArrayList<>();

        String[] columnNames = {"ID", "Paciente", "Fecha", "Hora", "Estado", "Ver Detalle", "Iniciar Consulta"};
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

        table.getColumnModel().getColumn(COL_VER_DETALLE).setCellRenderer(new ButtonRenderer("Ver Detalle"));
        table.getColumnModel().getColumn(COL_INICIAR_CONSULTA).setCellRenderer(new ButtonRenderer("Iniciar Consulta"));

        // Ajustar anchos
        table.getColumnModel().getColumn(COL_ID).setPreferredWidth(50);
        table.getColumnModel().getColumn(COL_PACIENTE).setPreferredWidth(180);
        table.getColumnModel().getColumn(COL_FECHA).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_HORA).setPreferredWidth(80);
        table.getColumnModel().getColumn(COL_ESTADO).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_VER_DETALLE).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_INICIAR_CONSULTA).setPreferredWidth(120);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row < 0) return;

                if (col == COL_VER_DETALLE) {
                    if (row < verDetalleListeners.size() && verDetalleListeners.get(row) != null) {
                        verDetalleListeners.get(row).actionPerformed(null);
                    }
                } else if (col == COL_INICIAR_CONSULTA) {
                    if (row < iniciarConsultaListeners.size() && iniciarConsultaListeners.get(row) != null) {
                        iniciarConsultaListeners.get(row).actionPerformed(null);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Agenda del día"));
        add(scrollPane, BorderLayout.CENTER);
    }

    // Métodos públicos
    public void setBtnIniciarConsultaListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < iniciarConsultaListeners.size()) {
            iniciarConsultaListeners.set(fila, l);
        }
    }

    public void setBtnVerDetalleListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < verDetalleListeners.size()) {
            verDetalleListeners.set(fila, l);
        }
    }

    public void setDatos(Object[][] datos) {
        tableModel.setRowCount(0);
        verDetalleListeners.clear();
        iniciarConsultaListeners.clear();

        if (datos == null) return;

        for (Object[] fila : datos) {
            Object[] filaCompleta = new Object[7];
            System.arraycopy(fila, 0, filaCompleta, 0, Math.min(fila.length, 5));
            filaCompleta[COL_VER_DETALLE] = "";
            filaCompleta[COL_INICIAR_CONSULTA] = "";
            tableModel.addRow(filaCompleta);

            verDetalleListeners.add(null);
            iniciarConsultaListeners.add(null);
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

    // Agrega este método en la sección de métodos públicos
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

}