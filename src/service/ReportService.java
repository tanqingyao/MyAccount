package service;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Record;
import util.DateUtil;
 
public class ReportService {
 
    /**
     * 获取某一天的消费金额
     * @param d
     * @param monthRawData
     * @return
     */
    public int getDaySpend(Date d,List<Record> monthRawData){
        int daySpend = 0;
        for (Record record : monthRawData) {
            if(record.date.equals(d))
                daySpend+=record.spend;
        }
        return daySpend;
    }
         
    /**
     * 获取一个月的消费记录集合
     * @return
     */
    public List<Record> listThisMonthRecords() {
        RecordDAO dao= new RecordDAO();
        List<Record> monthRawData= dao.listThisMonth();
        List<Record> result= new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.spend=daySpend;
            result.add(r);
        }
        return result;
 
    }
    public List<Record> listThisMonthRecords(String YearMonth) {
        RecordDAO dao= new RecordDAO();
        List<Record> monthRawData= dao.listThisMonth(YearMonth);
        List<Record> result= new ArrayList<>();
        Date monthBegin = DateUtil.monthBegin(YearMonth);
        int monthTotalDay = DateUtil.thisMonthTotalDay(YearMonth);
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.spend=daySpend;
            result.add(r);
        }
        return result;
 
    }
    public List<String> listRecordsMonth(){
        CategoryDAO categoryDAO=new CategoryDAO();
        RecordDAO recordDAO=new RecordDAO();
         SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM" );
        //列出记录中出现的月份
            List<Record> rs=recordDAO.list();
            //降序排列
//            Comparator<Record> c = new Comparator<Record>() {
//                @Override
//                public int compare(Record r1, Record r2) {
//                    if(r1.getDate().getTime()>=r2.getDate().getTime())
//                        return -1;
//                    else
//                        return 1;
//                }
//            };
//            Collections.sort(rs,c);
            Collections.sort(rs,(c1,c2)->(int) (c1.getDate().getTime()-c2.getDate().getTime()));
            List<String> ds=new ArrayList<>();
            for(Record r:rs){
                ds.add(sdf.format(r.getDate()));
            }
//            ToolUtil.removeDuplicate(ds);
            List<String> result = new ArrayList<String>(ds.size());
            for (String str : ds) {
                if (!result.contains(str)) {
                    result.add(str);
                }
            }
            ds.clear();
            ds.addAll(result);
            return ds;
    	
    }
 
}