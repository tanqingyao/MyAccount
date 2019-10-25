package gui.panel;
 
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Category;
import entity.Record;
import gui.listener.CategoryDetailCheckBoxListener;
import gui.listener.ReportListener;
import gui.model.CategoryComboBoxModel;
import gui.model.MonthComboBoxModel;
import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;
 
public class ReportPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }
 
    public static ReportPanel instance = new ReportPanel();
    
    public MonthComboBoxModel cbModel = new MonthComboBoxModel();
    public JComboBox<String> cbMonth = new JComboBox<>(cbModel);
    public String SelectedMonth = null;
    public JLabel l = new JLabel();
 
    public ReportPanel() {
    	JPanel pMenu = new JPanel();
    	pMenu.add(cbMonth);
        
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i =ChartUtil.getImage(rs, 500, 300);
        ImageIcon icon= new ImageIcon(i);
        l.setIcon(icon);

        this.setLayout(new BorderLayout());
        this.add(pMenu,BorderLayout.NORTH);
        this.add(l, BorderLayout.CENTER);
        
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

	@Override
	public void updateData() {
		List<Record> rs;
		if(null == SelectedMonth)
			rs = new ReportService().listThisMonthRecords();
		else
			rs = new ReportService().listThisMonthRecords(SelectedMonth);
        Image i = ChartUtil.getImage(rs, 500, 300);
        ImageIcon icon = new ImageIcon(i);
        l.setIcon(icon);		
        //更新下拉框月份信息
        cbModel.cs = new ReportService().listRecordsMonth();
        
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		ReportListener listener = new ReportListener();
		cbMonth.addActionListener(listener);
	}
 
}