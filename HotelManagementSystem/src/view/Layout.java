package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {

    //GUI'nin başlangıç durumunu yapılandırır. Pencere başlığı, boyutu ve kapanma davranışı gibi özellikleri ayarlar.
    public void guiInitiliaze(int width, int height) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Hotel Management System");
        this.setSize(width, height);

        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    //Bir tablo oluşturur ve tablonun sütunlarını ve satırlarını belirler.
    // Verilen model, tablo, sütunlar ve satırlar aracılığıyla tabloyu oluşturur ve gösterir.
    // Bu metot ayrıca tablonun sütun genişliğini ayarlar.
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setMaxWidth(75);
        table.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        if (rows != null) {
            for (Object[] row : rows) {
                model.addRow(row);
            }
        } else {
            rows = new ArrayList<>();
        }
    }

    // Belirtilen indeksteki tablodaki seçili satırın değerini alır.
    public int getTableSelectedRow (JTable table,int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());

    }

    //Fare hareketlerini dikkate alır, tıklanan satırı seçer ve vurgular.
    public void tableSelectedRow(JTable jtable) {
        jtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = jtable.rowAtPoint(e.getPoint());
                jtable.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });

    }
}