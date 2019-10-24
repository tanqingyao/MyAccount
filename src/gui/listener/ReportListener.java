package gui.listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
 
import entity.Category;
import entity.Record;
import gui.panel.CategoryDetailPanel;
import gui.panel.CategoryPanel;
import gui.panel.ReportPanel;
import service.CategoryService;
import service.RecordService;
 
public class ReportListener implements ActionListener {
 
    @Override
    public void actionPerformed(ActionEvent e) {
    	ReportPanel p = ReportPanel.instance;
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        if (cb == p.cbMonth) {
        	String YearMonth = (String) p.cbMonth.getSelectedItem();
        	p.SelectedMonth = YearMonth;
        }
        p.updateData();
    }
 
}