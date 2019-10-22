package util;
 
import java.io.BufferedReader;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
 
public class MysqlUtil {

    public static void backup(String mysqlPath, String backupfile) throws IOException {
//    	在不同操作系统进行判断
    	
//        String os = System.getProperties().getProperty("os.name");
//        System.out.println(os);
//        if (os.startsWith("Win")) {
//            String commandFormat = "%s/bin/mysqldump -u%s -p%s -hlocalhost -P%d %s > %s"; 
//            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port,
//                    DBUtil.database, backupfile);        
//            String[] cmds = {"cmd.exe", "/c", command};
//        } else {
//            String commandFormat = "%s/bin/mysqldump -u%s -p%s -hlocalhost -P%d %s > %s"; 
//            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port,
//                    DBUtil.database, backupfile);        
//            String[] cmds = {"/bin/sh", "-c", command};
//        }
      String commandFormat = "%s/bin/mysqldump -u%s -p%s -hlocalhost -P%d %s > %s"; 
      String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port,
              DBUtil.database, backupfile);        
      String[] cmds = {"/bin/sh", "-c", command};
		Runtime.getRuntime().exec(cmds);
    }
 
    public static void recover(String mysqlPath, String recoverfile) {
        try {
            String commandFormat = "%s/bin/mysql -u%s -p%s %s < %s";
            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password,
                    DBUtil.database, recoverfile);        
            String[] cmds = {"/bin/sh", "-c", command};
 
            Process p = Runtime.getRuntime().exec(cmds);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getErrorStream()));
            String errorLine = null;
            while ((errorLine = br.readLine()) != null) {
                System.out.println(errorLine);
            }
            br.close();
            int result = p.waitFor();
            if (result != 0) {
                throw new Exception("Restore failure!");
            }
            
//            OutputStream out = p.getOutputStream();
//            String inStr;
//            StringBuffer sb = new StringBuffer("");
//            String outStr;
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverfile), "utf8"));
//            while ((inStr = br.readLine()) != null) {
//                sb.append(inStr + "\r\n");
//            }
//            outStr = sb.toString();
// 
//            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
//            writer.write(outStr);
//            writer.flush();
//            out.close();
//            br.close();
//            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
 
    public static void main(String[] args) throws IOException {
        String mysqlPath = "/usr";
        String file = "/home/tanqingyao/hutubill.sql";
 
//         backup(mysqlPath, file);
        recover(mysqlPath, file);
    }
 
}