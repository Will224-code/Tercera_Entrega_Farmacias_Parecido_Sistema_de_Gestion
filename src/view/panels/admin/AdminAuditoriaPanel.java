package view.panels.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel de auditoría con tabla de registros.
 * Solo vista – sin lógica de negocio.
 */
public class AdminAuditoriaPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnRefrescar;

    public AdminAuditoriaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botón refrescar
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRefrescar = new JButton("🔄 Refrescar");
        topPanel.add(btnRefrescar);

        // Tabla de auditoría
        String[] columnNames = {"ID", "Usuario", "Entidad", "ID Entidad", "Acción", "Fecha y Hora"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registros de Auditoría"));

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JButton getBtnRefrescar() { return btnRefrescar; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void setDatos(Object[][] datos) {
        tableModel.setRowCount(0);
        if (datos == null) return;
        for (Object[] fila : datos) {
            tableModel.addRow(fila);
        }
    }
}
