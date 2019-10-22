package gui.model;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import entity.Category;
import entity.Record;
import service.CategoryService;
import service.RecordService;
 
public class CategoryDetailTableModel implements TableModel{
 
    String[] columnNames = new String[]{"分类", "金额", "备注", "日期"};
    
    // 使用从Service返回的List作为TableModel的数据    
    public List<Record> rs = new RecordService().list();

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rs.size();
    }
 
    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }
 
    @Override
    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        return String.class;
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return false;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
    	Record r = rs.get(rowIndex);
    	Category c = new CategoryService().get(r.cid);
        if(0==columnIndex) {
            return c.name;
        }
        if(1==columnIndex)
            return r.spend;
        if(2==columnIndex)
            return r.comment;
        if(3==columnIndex)
            return r.date;
        return null;
    }
 
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void addTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void removeTableModelListener(TableModelListener l) {
        // TODO Auto-generated method stub
         
    }
 
}