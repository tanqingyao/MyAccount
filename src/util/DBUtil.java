package util;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
  
public class DBUtil {
    static String ip = "localhost";
    static int port = 3306;
    static String database = "hutubill";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "root";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql:///%s?characterEncoding=%s&useSSL=true", database, encoding);
        return DriverManager.getConnection(url, loginName, password);
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
            System.out.println("表Hero中总共有:" + total+" 条数据");
            
		} catch (SQLException e) {
			e.printStackTrace();
		} 

    }
}