package gui.listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
 
import entity.Category;
import entity.Record;
import gui.panel.CategoryDetailPanel;
import gui.panel.CategoryPanel;
import service.CategoryService;
import service.RecordService;
 
public class CategoryDetailCheckBoxListener implements ActionListener {
 
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryDetailPanel p = CategoryDetailPanel.instance;
        JCheckBox cb = (JCheckBox) e.getSource();
        if (cb == p.cbAllTime) {
        	if(p.cbAllTime.isSelected()) {
            	p.datepickEnd.setEnabled(false);
            	p.datepickStart.setEditable(false);        		
        	} else {
            	p.datepickEnd.setEnabled(true);
            	p.datepickStart.setEditable(true);        		
        	}
        }
        if (cb == p.cbAllCategory) {
        	if(p.cbAllCategory.isSelected()) {
            	p.cbCategory.setEnabled(false);   		
        	} else {
            	p.cbCategory.setEnabled(true);      		
        	}
        }
    }
 
}