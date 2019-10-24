package test;
  
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
  
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;
  
public class Test {
//    public static void main(String[] args) {
//        GUIUtil.useLNF();
//        //面板
//        JPanel p = new JPanel();
//        //进度条组件
//        CircleProgressBar cpb = new CircleProgressBar();
//        cpb.setBackgroundColor(ColorUtil.blueColor);
//        cpb.setProgress(0);
//        //按钮
//        JButton b = new JButton("点击");
//        //添加组件
//        p.setLayout(new BorderLayout());
//        p.add(cpb, BorderLayout.CENTER);
//        p.add(b, BorderLayout.SOUTH);
//        //显示面板
//        GUIUtil.showPanel(p);
//          
//        //给按钮加监听
//        b.addActionListener(new ActionListener() {
//  
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new SwingWorker() {
//  
//                    @Override
//                    protected Object doInBackground() throws Exception {
//                        for (int i = 0; i < 100; i++) {
//                            cpb.setProgress(i + 1);
//                            cpb.setForegroundColor(ColorUtil.getByPercentage(i + 1));
//                            try {
//                                Thread.sleep(100);
//                            } catch (InterruptedException e1) {
//                                // TODO Auto-generated catch block
//                                e1.printStackTrace();
//                            }
//                        }
//                        return null;
//                    }
//  
//                }.execute();
//  
//            }
//        });    
//  
//    }
    public static void main(String[] args) {
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM" );
  
        String str = "2016-1";
          
        try {
            Date d = sdf.parse(str);
            System.out.printf("字符串 %s 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: %s",str,d.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          
    }
}