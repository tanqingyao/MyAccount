package gui.model;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import entity.Category;
import service.CategoryService;
import service.ReportService;
 
public class MonthComboBoxModel implements ComboBoxModel<String>{

    public List<String> cs = new ReportService().listRecordsMonth();
    
    public String c;
     
    public MonthComboBoxModel(){
        if(!cs.isEmpty())
            c=cs.get(0);
    }
    @Override
    public int getSize() {
        return cs.size();
    }
 
    @Override
    public String getElementAt(int index) {
        return cs.get(index);
    }
 
    @Override
    public void addListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void removeListDataListener(ListDataListener l) {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void setSelectedItem(Object anItem) {
        c = (String) anItem;
         
    }
 
    @Override
    public Object getSelectedItem() {
        // TODO Auto-generated method stub
    	if(!cs.isEmpty())
            return c;
        else
            return null;
    }
 
}