import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class QRTableModel extends AbstractTableModel {
    private static final int PRODUCT_TYPE_COL = 0;
    private static final int PRODUCT_NAME_COL = 1;
    private static final int PRODUCT_INGREDIENTS_COL = 2;
    private static final int PRODUCT_TEMP_COL = 3;
    private static final int PRODUCT_IS_READY_COL = 4;
    private long orderID;
    private String[] columnNames = {"Product Type", "Product Name", "Ingredients", "Temp", "Is Ready"};
    private ArrayList<QRObject> objects;

    public QRTableModel(ArrayList<QRObject> orders, long orderID) {
        this.objects = orders;
        this.orderID = orderID;
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
        QRObject object = objects.get(rowIndex);
        switch (columnIndex) {
            case PRODUCT_TYPE_COL:
                return object.getType();
            case PRODUCT_NAME_COL:
                return object.getName();
            case PRODUCT_INGREDIENTS_COL:
                return object.getIngredients();
            case PRODUCT_TEMP_COL:
                return object.getTemp();
            case PRODUCT_IS_READY_COL:
                return object.isReady();
            default:
                return object.getType();
        }
    }

    public QRObject getObject(int row) {
        return objects.get(row);
    }

    public boolean isAllReady() {
        for (QRObject object : objects) {
            if (!object.isReady()) {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        objects.clear();
        fireTableDataChanged();
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }
}
