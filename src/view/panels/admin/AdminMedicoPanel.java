package view.panels.admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel de administración de médicos.
 * Solo vista – sin lógica de negocio.
 */
public class AdminMedicoPanel extends JPanel {

    // Componentes
    private JButton btnRegistrar;
    private JTable table;
    private DefaultTableModel tableModel;

    // Listeners para botones Deshabilitar por fila
    private ArrayList<ActionListener> disableListeners;

    // Índices de columnas
    private static final int COL_ID            = 0;
    private static final int COL_NOMBRE        = 1;
    private static final int COL_ESPECIALIDAD  = 2;
    private static final int COL_TELEFONO      = 3;
    private static final int COL_ESTADO        = 4;
    private static final int COL_DESHABILITAR  = 5;

    public AdminMedicoPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        disableListeners = new ArrayList<>();

        // Crear tabla y modelo
        String[] columnNames = {"ID", "Nombre", "Especialidad", "Teléfono", "Estado", "Deshabilitar"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   // ninguna celda editable
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        // Renderizador para la columna de botón Deshabilitar
        table.getColumnModel().getColumn(COL_DESHABILITAR).setCellRenderer(new ButtonRenderer("Deshabilitar"));

        // Ajustar anchos
        table.getColumnModel().getColumn(COL_ID).setPreferredWidth(50);
        table.getColumnModel().getColumn(COL_NOMBRE).setPreferredWidth(150);
        table.getColumnModel().getColumn(COL_ESPECIALIDAD).setPreferredWidth(120);
        table.getColumnModel().getColumn(COL_TELEFONO).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_ESTADO).setPreferredWidth(80);
        table.getColumnModel().getColumn(COL_DESHABILITAR).setPreferredWidth(100);

        // Mouse listener para capturar clics en el botón Deshabilitar
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Médicos"));

        // Panel inferior con botón Registrar
        JPanel bottomPanel = new JPanel();
        btnRegistrar = new JButton("Registrar Médico");
        bottomPanel.add(btnRegistrar);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // -------------------- MÉTODOS PÚBLICOS PARA EL CONTROLLER --------------------

    /**
     * Retorna el botón Registrar Médico.
     */
    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    /**
     * Asigna un listener al botón Deshabilitar de la fila especificada.
     * @param fila índice de la fila (base 0)
     * @param l ActionListener a ejecutar
     */
    public void setBtnDeshabilitarListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < disableListeners.size()) {
            disableListeners.set(fila, l);
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
     *              {ID, Nombre, Especialidad, Teléfono, Estado}
     *              La columna de botón "Deshabilitar" se añade automáticamente.
     */
    public void setDatos(Object[][] datos) {
        // Limpiar modelo y lista de listeners
        tableModel.setRowCount(0);
        disableListeners.clear();

        if (datos == null) return;

        for (Object[] fila : datos) {
            // Agregar las 5 columnas de datos + una celda vacía para el botón
            Object[] filaCompleta = new Object[6];
            System.arraycopy(fila, 0, filaCompleta, 0, Math.min(fila.length, 5));
            filaCompleta[COL_DESHABILITAR] = "";
            tableModel.addRow(filaCompleta);

            // Inicializar listener para esta fila como null
            disableListeners.add(null);
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