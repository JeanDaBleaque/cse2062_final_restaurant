import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class OrdersTableModel extends AbstractTableModel {
    private static final int ORDER_ID_COL = 0;
    private static final int CUSTOMER_NAME_COL = 1;
    private static final int CUSTOMER_MAIL_COL = 2;
    private String[] columnNames = {"Order ID", "Customer Name", "Customer Mail"};
    private ArrayList<Order> objects;
    private Component component;
    public OrdersTableModel(ArrayList<Order> orders) {
        this.objects = orders;
    }
    @Override
    public int getRowCount() {
        return objects.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order object = objects.get(rowIndex);
        switch (columnIndex) {
            case ORDER_ID_COL:
                return object.getId();
            case CUSTOMER_NAME_COL:
                return object.getCustomerName() + " " + object.getCustomerSurname();
            case CUSTOMER_MAIL_COL:
                return object.getCustomerMail();
            default:
                return object.getId();
        }
    }

    public Order getObject(int row) {
        return objects.get(row);
    }
}
