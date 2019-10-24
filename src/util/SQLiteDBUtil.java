package util;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gui.panel.MainPanel;
  
public class SQLiteDBUtil {
    static String ip = "localhost";
    static int port = 3306;
    static String database = "hutubill";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "root";
    
    public static SQLiteDBUtil instance = new SQLiteDBUtil();
    static{
        try {
        	//使用sqlite
            Class.forName("org.sqlite.JDBC");
            instance.CreateTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void CreateTable() {
    	try (
    			Connection c = SQLiteDBUtil.getConnection();
    			Statement s = c.createStatement();) {
    		// 判断是否有表tables的存在。有则删除
//    		s.executeUpdate("drop table if exists hero");
	        // 创建表
	        s.executeUpdate("CREATE TABLE config (\n" + 
	        		"  id int AUTO_INCREMENT,\n" + 
	        		"  key_ varchar(255) ,\n" + 
	        		"  value varchar(255) ,\n" + 
	        		"  PRIMARY KEY (id)\n" + 
	        		")");
	        s.executeUpdate("CREATE TABLE category (\n" + 
	        		"  id int AUTO_INCREMENT,\n" + 
	        		"  name varchar(255) ,\n" + 
	        		"  PRIMARY KEY (id)\n" + 
	        		")");
	        s.executeUpdate("CREATE TABLE record (\n" + 
	        		"  id int AUTO_INCREMENT,\n" + 
	        		"  spend int,\n" + 
	        		"  cid int,\n" + 
	        		"  comment varchar(255) ,\n" + 
	        		"  date Date,\n" + 
	        		"  PRIMARY KEY (id),\n" + 
	        		"  CONSTRAINT `fk_record_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)\n" + 
	        		")");
	        // 搜索数据库，将搜索的放入数据集ResultSet中
//	        ResultSet rSet = s.executeQuery("select * from category");
//	        while (rSet.next()) { // 遍历这个数据集
//	            System.out.println("id：" + rSet.getInt(1));
//	            System.out.println("种类：" + rSet.getString(2));
//	        }
//	        rSet.close();// 关闭数据集
//	        c.close();// 关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    public static Connection getConnection() throws SQLException {
//        String url = String.format("jdbc:mysql:///%s?characterEncoding=%s&useSSL=true", database, encoding);
//        return DriverManager.getConnection(url, loginName, password);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:MyAccount.db");
        return connection;
    }
    public static void main(String[] args) {
    	try (
    			Connection c = SQLiteDBUtil.getConnection();
    			Statement s = c.createStatement();) {
    		
			String sql = "select count(*) from config";
			ResultSet rs = s.executeQuery(sql);
            int total = 0;
            while (rs.next()) {
                total = rs.getInt(1);
            } 
            System.out.println("表Hero中总共有:" + total+" 条数据");

		} catch (SQLException e) {
			e.printStackTrace();
		} 

    }
}