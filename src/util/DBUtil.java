package util;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gui.panel.MainPanel;
  
public class DBUtil {
	static String database = "MyAccount";
    static{
        try {
        	//使用sqlite
            Class.forName("org.sqlite.JDBC");
            DBUtil.CreateTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void CreateTable() {
    	try (
    			Connection c = DBUtil.getConnection();
    			Statement s = c.createStatement();) {
	        // 创建表
    		String createConfig = "CREATE TABLE IF NOT EXISTS config " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " key_           VARCHAR(255)   NOT NULL, " + 
                    " value          VARCHAR(255))"; 
    		String createCategory = "CREATE TABLE IF NOT EXISTS category " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name          VARCHAR(255))"; 
    		String createRecord = "CREATE TABLE IF NOT EXISTS record " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " spend           INT    NOT NULL, " + 
                    " cid            INT, " + 
                    " comment        VARCHAR(255), " + 
                    " date        DATE, " + 
                    " FOREIGN KEY(cid) REFERENCES category(id))"; 
	        s.executeUpdate(createConfig);
	        s.executeUpdate(createCategory);
	        s.executeUpdate(createRecord);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }
    public static Connection getConnection() throws SQLException {
//        String url = String.format("jdbc:mysql:///%s?characterEncoding=%s&useSSL=true", database, encoding);
//        return DriverManager.getConnection(url, loginName, password);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:"+ database +".db");
        return connection;
    }
    public static void main(String[] args) {
    	try (
    			Connection c = DBUtil.getConnection();
    			Statement s = c.createStatement();) {
    		
			String sql = "select count(*) from config";
			ResultSet rs = s.executeQuery(sql);
            int total = 0;
            while (rs.next()) {
                total = rs.getInt(1);
            } 
            rs.close();
            System.out.println("表中总共有:" + total+" 条数据");
	        //搜索数据库，将搜索的放入数据集ResultSet中
	        ResultSet rSet = s.executeQuery("select * from config order by id desc limit 0,100");
	        while (rSet.next()) { // 遍历这个数据集
	            System.out.println("id：" + rSet.getInt(1));
	            System.out.println("key_：" + rSet.getString(2));
	            System.out.println("value：" + rSet.getString(3));
	        }
	        rSet.close();// 关闭数据集
		} catch (SQLException e) {
			e.printStackTrace();
		} 

    }
}