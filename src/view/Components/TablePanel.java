package view.Components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public TablePanel(String[] columnNames) {
        setLayout(new BorderLayout());
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void addRow(Object[] row) {
        model.addRow(row);
    }

    public void clearRows() {
        model.setRowCount(0);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }
}
