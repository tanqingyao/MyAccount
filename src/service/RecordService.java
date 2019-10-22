package service;
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;
 
public class RecordService {
	CategoryDAO categoryDao = new CategoryDAO();
    RecordDAO recordDao = new RecordDAO();
    public void add(int spend, Category c, String comment,Date date){
        Record r = new Record();
        r.spend = spend;
        r.cid = c.id;
        r.comment = comment;
        r.date = date;
        recordDao.add(r);
    }
    public void update(int id, int cid, int spend, String comment, Date date) {
        Record r = new Record();
        r.setId(id);
        r.setCid(cid);
        r.setSpend(spend);
        r.setComment(comment);
        r.setDate(date);
        recordDao.update(r);
    }
    public void delete(int id) {
    	recordDao.delete(id);
    }
    // 列出所有分类
	public List<Record> list() {
    	List<Category> cs= categoryDao.list();
        List<Record> rs = new ArrayList<Record>();
        for (Category c : cs) {
            rs.addAll(recordDao.list(c.id));
        }
        return rs;
    }
    // 列出选定日期
	public List<Record> list(Date start, Date end) {
        List<Record> rs = new ArrayList<Record>();
        rs.addAll(recordDao.list(start, end));
        return rs;
    }
	public List<Record> list(Category c) {
        List<Record> rs = new ArrayList<Record>();
        rs.addAll(recordDao.list(c.id));
        return rs;
    }
	public List<Record> list(Date start, Date end, Category c) {
        List<Record> rs = new ArrayList<Record>();
        for(Record r: recordDao.list(start, end))
        	if(r.cid == c.id)
        		rs.add(r);
        return rs;
    }
}