package gui.panel;
 
import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingx.JXDatePicker;

import entity.Category;
import entity.Record;
import gui.listener.CategoryDetailCheckBoxListener;
import gui.listener.CategoryDetailListener;
import gui.listener.CategoryListener;
import gui.model.CategoryComboBoxModel;
import gui.model.CategoryDetailTableModel;
import gui.model.CategoryTableModel;
import service.CategoryService;
import service.RecordService;
import util.ColorUtil;
import util.GUIUtil;
 
public class CategoryDetailPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();
    }
    public static CategoryDetailPanel instance = new CategoryDetailPanel();
    
    JLabel lDate = new JLabel("日期:");
    JLabel lSymbol = new JLabel("-");
    public JXDatePicker datepickStart = new JXDatePicker(new Date());
    public JXDatePicker datepickEnd = new JXDatePicker(new Date());
    JLabel lCategory = new JLabel("分类:");
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JButton bQuery = new JButton("查询");
    
    public CategoryDetailTableModel ctm = new CategoryDetailTableModel();
    public JTable t =new JTable(ctm);    

    public JCheckBox cbAllTime=new JCheckBox("所有时间");
    public JCheckBox cbAllCategory=new JCheckBox("所有分类");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    
    public CategoryDetailPanel() {
    	//默认显示所有时间和类别
    	cbAllTime.setSelected(true);
    	datepickEnd.setEnabled(false);
    	datepickStart.setEditable(false);
    	
    	cbAllCategory.setSelected(true);
    	cbCategory.setEnabled(false);  
    	GUIUtil.setColor(ColorUtil.grayColor, lSymbol);
        GUIUtil.setColor(ColorUtil.blueColor, bEdit,bDelete);
        JPanel pMenu = new JPanel();
        JScrollPane sp =new JScrollPane(t);
        JPanel pSubmit = new JPanel();

        
        pMenu.add(lDate);
        pMenu.add(datepickStart);
        pMenu.add(lSymbol);
        pMenu.add(datepickEnd);
        pMenu.add(bQuery);
        pMenu.add(lCategory);
        pMenu.add(cbCategory);
        
        pSubmit.add(cbAllTime);
        pSubmit.add(cbAllCategory);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);
         
        this.setLayout(new BorderLayout());
        this.add(pMenu,BorderLayout.NORTH);
        this.add(sp,BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);
        
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryDetailPanel.instance);
    }
    public Record getSelectedRecord() {
        int index = t.getSelectedRow();
        return ctm.rs.get(index);
    }
    public void updateData() {
    	if (cbAllTime.isSelected() && cbAllCategory.isSelected())
    		ctm.rs = new RecordService().list();
    	else if(cbAllCategory.isSelected())	//指定时间
    		ctm.rs = new RecordService().list(datepickStart.getDate(), datepickEnd.getDate());
    	else if(cbAllTime.isSelected())		//指定类型
    		ctm.rs = new RecordService().list((Category) cbModel.getSelectedItem());
    	else 								//指定时间和类型
    		ctm.rs = new RecordService().list(datepickStart.getDate(), datepickEnd.getDate(), (Category) cbModel.getSelectedItem());
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);
        cbModel.cs = new CategoryService().list();
        if(0==ctm.rs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }
    public void addListener() {
        CategoryDetailListener listener = new CategoryDetailListener();
        CategoryDetailCheckBoxListener cblistener = new CategoryDetailCheckBoxListener();
        bQuery.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
        cbAllTime.addActionListener(cblistener);
        cbAllCategory.addActionListener(cblistener);
    }
}