package util;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
 
public class SqliteUtil {
	public static void backup(String mysqlPath, String backupfile) throws IOException {
    	File srcFile = new File(mysqlPath + "/"+DBUtil.database+".db");
    	File destFile = new File(backupfile);
    	Files.copy(srcFile.toPath(), destFile.toPath());
    }
 
    public static void recover(String mysqlPath, String recoverfile) throws IOException {
    	File destFile = new File(mysqlPath + "/"+DBUtil.database+".db");
    	File srcFile = new File(recoverfile);
    	File f = destFile;
		f.delete();
    	Files.copy(srcFile.toPath(), destFile.toPath());
 
    }
 
    public static void main(String[] args) throws IOException {
        String mysqlPath = "/usr";
        String file = "/home/tanqingyao/hutubill.sql";
 
//         backup(mysqlPath, file);
        recover(mysqlPath, file);
    }
 
}