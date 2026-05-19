package view.panels.admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel de administración de citas.
 * Solo vista – sin lógica de negocio.
 */
public class AdminCitaPanel extends JPanel {

    // Componentes
    private JButton btnAgendar;
    private JTable table;
    private DefaultTableModel tableModel;

    // Listeners para botones por fila
    private ArrayList<ActionListener> cambiarEstadoListeners;
    private ArrayList<ActionListener> cancelarListeners;

    // Índices de columnas
    private static final int COL_ID            = 0;
    private static final int COL_PACIENTE      = 1;
    private static final int COL_MEDICO        = 2;
    private static final int COL_FECHA         = 3;
    private static final int COL_HORA          = 4;
    private static final int COL_ESTADO        = 5;
    private static final int COL_CAMBIAR_ESTADO = 6;
    private static final int COL_CANCELAR      = 7;

    public AdminCitaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cambiarEstadoListeners = new ArrayList<>();
        cancelarListeners = new ArrayList<>();

        // Crear tabla y modelo (8 columnas: datos + 2 botones)
        String[] columnNames = {"ID", "Paciente", "Médico", "Fecha", "Hora", "Estado", "Cambiar Estado", "Cancelar"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   // ninguna celda editable directamente
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        // Renderizadores para las columnas de botones
        table.getColumnModel().getColumn(COL_CAMBIAR_ESTADO).setCellRenderer(new ButtonRenderer("Cambiar Estado"));
        table.getColumnModel().getColumn(COL_CANCELAR).setCellRenderer(new ButtonRenderer("Cancelar"));

        // Ajustar anchos de columna
        table.getColumnModel().getColumn(COL_ID).setPreferredWidth(50);
        table.getColumnModel().getColumn(COL_PACIENTE).setPreferredWidth(150);
        table.getColumnModel().getColumn(COL_MEDICO).setPreferredWidth(150);
        table.getColumnModel().getColumn(COL_FECHA).setPreferredWidth(90);
        table.getColumnModel().getColumn(COL_HORA).setPreferredWidth(70);
        table.getColumnModel().getColumn(COL_ESTADO).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_CAMBIAR_ESTADO).setPreferredWidth(120);
        table.getColumnModel().getColumn(COL_CANCELAR).setPreferredWidth(90);

        // Mouse listener para capturar clics en botones de la tabla
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row < 0) return;

                if (col == COL_CAMBIAR_ESTADO) {
                    if (row < cambiarEstadoListeners.size() && cambiarEstadoListeners.get(row) != null) {
                        cambiarEstadoListeners.get(row).actionPerformed(null);
                    }
                } else if (col == COL_CANCELAR) {
                    if (row < cancelarListeners.size() && cancelarListeners.get(row) != null) {
                        cancelarListeners.get(row).actionPerformed(null);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Citas"));

        // Panel inferior con botón Agendar
        JPanel bottomPanel = new JPanel();
        btnAgendar = new JButton("Agendar Cita");
        bottomPanel.add(btnAgendar);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // -------------------- MÉTODOS PÚBLICOS PARA EL CONTROLLER --------------------

    /**
     * Retorna el botón Agendar Cita.
     */
    public JButton getBtnAgendar() {
        return btnAgendar;
    }

    /**
     * Asigna un listener al botón Cambiar Estado de la fila especificada.
     * @param fila índice de la fila (base 0)
     * @param l ActionListener a ejecutar
     */
    public void setBtnCambiarEstadoListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < cambiarEstadoListeners.size()) {
            cambiarEstadoListeners.set(fila, l);
        }
    }

    /**
     * Asigna un listener al botón Cancelar de la fila especificada.
     * @param fila índice de la fila (base 0)
     * @param l ActionListener a ejecutar
     */
    public void setBtnCancelarListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < cancelarListeners.size()) {
            cancelarListeners.set(fila, l);
        }
    }

    /**
     * Retorna el índice de la fila seleccionada actualmente.
     * @return índice de fila, o -1 si no hay selección.
     */
    public int getFilaSeleccionada() {
        return table.getSelectedRow();
    }

    /**
     * Carga los datos en la tabla.
     * @param datos Matriz de objetos, cada fila debe contener:
     *              {ID, Paciente, Médico, Fecha, Hora, Estado}
     *              Las columnas de botones "Cambiar Estado" y "Cancelar" se añaden automáticamente.
     */
    public void setDatos(Object[][] datos) {
        // Limpiar modelo y listas de listeners
        tableModel.setRowCount(0);
        cambiarEstadoListeners.clear();
        cancelarListeners.clear();

        if (datos == null) return;

        for (Object[] fila : datos) {
            // Agregar las 6 columnas de datos + dos celdas vacías para los botones
            Object[] filaCompleta = new Object[8];
            System.arraycopy(fila, 0, filaCompleta, 0, Math.min(fila.length, 6));
            filaCompleta[COL_CAMBIAR_ESTADO] = "";
            filaCompleta[COL_CANCELAR] = "";
            tableModel.addRow(filaCompleta);

            // Inicializar listeners para esta fila como null
            cambiarEstadoListeners.add(null);
            cancelarListeners.add(null);
        }
    }

    // -------------------- RENDERIZADOR DE BOTONES --------------------

    /**
     * Renderizador para celdas que muestran un JButton.
     */
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