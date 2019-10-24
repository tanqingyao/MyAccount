package gui.listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
 
import entity.Category;
import entity.Record;
import gui.panel.CategoryDetailPanel;
import gui.panel.CategoryPanel;
import service.CategoryService;
import service.RecordService;
 
public class CategoryDetailListener implements ActionListener {
 
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryDetailPanel p = CategoryDetailPanel.instance;
        JButton b = (JButton) e.getSource();
        if (b == p.bQuery) {
//        	p.ctm.rs = new RecordService().list();
//        	Date start = p.datepickStart.getDate();
//        	Date end = p.datepickEnd.getDate();
//            String name = JOptionPane.showInputDialog(null);
//            if (0 == name.length()) {
//                JOptionPane.showMessageDialog(p, "分类名称不能为空");
//                return;
//            }
// 
//            new CategoryService().add(name);
        }
        if (b == p.bEdit) {
            Record r = p.getSelectedRecord();
            int id = r.id;
            String s = JOptionPane.showInputDialog("修改消费金额", r.spend);

            if (null== s) {	//取消
                return;
            }
            int spend = Integer.parseInt(s);
            String comment = JOptionPane.showInputDialog("修改备注", r.comment);
//            String date = JOptionPane.showInputDialog("修改消费金额", r.date);
//            if (0 > spend.length()) {
//                JOptionPane.showMessageDialog(p, "消费金额不能为负数");
//                return;
//            }
 
            new RecordService().update(id, r.cid, spend, comment, r.date);
        }
        if (b == p.bDelete) {
        	Record r = p.getSelectedRecord();
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
                return;
 
            int id = r.id;
            new RecordService().delete(id);
        }
        p.updateData();
    }
 
}