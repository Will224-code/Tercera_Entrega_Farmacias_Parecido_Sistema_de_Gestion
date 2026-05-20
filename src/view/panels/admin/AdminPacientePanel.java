package view.panels.admin;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel de administración de pacientes.
 * Solo vista – sin lógica de negocio.
 */
public class AdminPacientePanel extends JPanel {

    // Componentes de búsqueda
    private JTextField txtExpediente;   // búsqueda principal (número de expediente)
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JButton btnBuscar;

    // Botón de registro
    private JButton btnRegistrar;

    // Tabla y modelo
    private JTable table;
    private DefaultTableModel tableModel;

    // Listeners para botones por fila
    private ArrayList<ActionListener> editListeners;
    private ArrayList<ActionListener> disableListeners;

    // Índices de columnas actualizados (con ID oculto)
    private static final int COL_ID = 0;
    private static final int COL_EXPEDIENTE = 1;
    private static final int COL_NOMBRE = 2;
    private static final int COL_CURP = 3;
    private static final int COL_EDAD = 4;
    private static final int COL_ESTADO = 5;
    private static final int COL_EDITAR = 6;
    private static final int COL_DESHABILITAR = 7;

    public AdminPacientePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        editListeners = new ArrayList<>();
        disableListeners = new ArrayList<>();

        JPanel searchPanel = createSearchPanel();

        // Crear tabla y modelo con 8 columnas (ID oculto)
        String[] columnNames = {"ID", "Expediente", "Nombre", "CURP", "Edad", "Estado", "Editar", "Deshabilitar"};
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

        // Ocultar la columna ID (no se muestra al usuario)
        table.getColumnModel().getColumn(COL_ID).setMinWidth(0);
        table.getColumnModel().getColumn(COL_ID).setMaxWidth(0);
        table.getColumnModel().getColumn(COL_ID).setWidth(0);

        // Renderizadores para botones
        table.getColumnModel().getColumn(COL_EDITAR).setCellRenderer(new ButtonRenderer("Editar"));
        table.getColumnModel().getColumn(COL_DESHABILITAR).setCellRenderer(new ButtonRenderer("Deshabilitar"));

        // Ajustar anchos
        table.getColumnModel().getColumn(COL_EXPEDIENTE).setPreferredWidth(100);
        table.getColumnModel().getColumn(COL_NOMBRE).setPreferredWidth(150);
        table.getColumnModel().getColumn(COL_CURP).setPreferredWidth(120);
        table.getColumnModel().getColumn(COL_EDAD).setPreferredWidth(60);
        table.getColumnModel().getColumn(COL_ESTADO).setPreferredWidth(80);
        table.getColumnModel().getColumn(COL_EDITAR).setPreferredWidth(80);
        table.getColumnModel().getColumn(COL_DESHABILITAR).setPreferredWidth(100);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (row < 0) return;

                if (col == COL_EDITAR) {
                    if (row < editListeners.size() && editListeners.get(row) != null) {
                        editListeners.get(row).actionPerformed(null);
                    }
                } else if (col == COL_DESHABILITAR) {
                    if (row < disableListeners.size() && disableListeners.get(row) != null) {
                        disableListeners.get(row).actionPerformed(null);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Pacientes"));

        JPanel bottomPanel = new JPanel();
        btnRegistrar = new JButton("Registrar Paciente");
        bottomPanel.add(btnRegistrar);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Búsqueda"));

        panel.add(new JLabel("Expediente:"));
        txtExpediente = new JTextField(12);
        panel.add(txtExpediente);

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(10);
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido:"));
        txtApellido = new JTextField(10);
        panel.add(txtApellido);

        panel.add(new JLabel("Edad:"));
        txtEdad = new JTextField(5);
        panel.add(txtEdad);

        btnBuscar = new JButton("Buscar");
        panel.add(btnBuscar);

        return panel;
    }

    // -------------------- MÉTODOS PÚBLICOS --------------------

    public JTextField getTxtBusqueda() {
        return txtExpediente;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnEditarListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < editListeners.size()) {
            editListeners.set(fila, l);
        }
    }

    public void setBtnDeshabilitarListener(int fila, ActionListener l) {
        if (fila >= 0 && fila < disableListeners.size()) {
            disableListeners.set(fila, l);
        }
    }

    public int getFilaSeleccionada() {
        return table.getSelectedRow();
    }

    /**
     * Carga los datos en la tabla.
     * @param datos Matriz de objetos, cada fila debe contener:
     *              {id, expediente, nombre, curp, edad, estado}
     *              Las columnas de botones se añaden automáticamente.
     */
    public void setDatos(Object[][] datos) {
        tableModel.setRowCount(0);
        editListeners.clear();
        disableListeners.clear();

        if (datos == null) return;

        for (Object[] fila : datos) {
            Object[] filaCompleta = new Object[8];
            filaCompleta[COL_ID] = fila[0];                    // id
            // Copiar los siguientes 5 elementos (expediente, nombre, curp, edad, estado)
            System.arraycopy(fila, 1, filaCompleta, 1, Math.min(fila.length - 1, 5));
            filaCompleta[COL_EDITAR] = "";
            filaCompleta[COL_DESHABILITAR] = "";
            tableModel.addRow(filaCompleta);

            editListeners.add(null);
            disableListeners.add(null);
        }
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    // -------------------- RENDERIZADOR --------------------

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