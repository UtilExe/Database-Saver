
import java.awt.GraphicsEnvironment;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;


public class MysqlRestoreProgram {

    private static Scanner scanner = new Scanner(System.in);
  // Works
  /*  public static void main(String[] args) throws IOException {
                Console console = System.console();
            if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = MysqlRestoreProgram.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
            }else{
        }
        System.out.println("Enter MySQL path: (Default: C:\\Users\\Exo\\Downloads\\_Server - EmuCoach Version v13.1 - VIP\\_Server\\mysql\\bin\\mysql");
        System.out.println("Note: Make sure to change the start to _Server location");
        String mysqlPath = scanner.nextLine();;
        System.out.println("Enter database user: (Default: root)");
        String dbUser = scanner.nextLine();
        System.out.println("Enter database password: (Default: ascent)");
        String dbPassword = scanner.nextLine();
        System.out.println("Enter where the .sql file you just exported before, is located. For example: C:\\Users\\Exo\\Downloads\\_Server - EmuCoach Version v13.1 - VIP\\_Serverbackup1604960288551.sql");
        String backupFile = scanner.nextLine();

        
        restoreDatabase(mysqlPath, dbUser, dbPassword, backupFile);
    }*/
    
    public static void main(String[] args) throws URISyntaxException, IOException {
        //Backupdbtosql();
        updateContent();
    }
    
    // ToDo: Use scanner, instead of hard-coding the values.
    public static void Backupdbtosql() throws URISyntaxException {
    try {

        /* Not using below */
        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        // CodeSource codeSource = MysqlRestoreProgram.class.getProtectionDomain().getCodeSource();
        //File jarFile = new File(codeSource.getLocation().toURI().getPath());
        // String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        String dbName = "emucoach_v14_vip_auth";
        String dbUser = "root";
        String dbPass = "ascent";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        String folderPath = "\\C:\\Users\\Emil\\Desktop\\Emulation\\backup_dev\\";

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(folderPath);
        f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
         String savePath = "C:\\Users\\Emil\\Desktop\\Emulation\\backup_dev\\" + dbName+"_export.sql\"";
        /*NOTE: Used to create a cmd command*/
        String executeCmd = "C:\\Users\\Emil\\Desktop\\Emulation\\_Server - EmuCoach Version v14_Blizz_VIP\\_Server\\mysql\\bin\\mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

        /*NOTE: Executing the command here*/
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
            System.out.println("Backup Complete");
        } else {
            System.out.println("Backup Failure");
        }

    } catch (IOException | InterruptedException ex) {
        JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
    }
}
    
    public static void updateContent() throws IOException {
        File file = new File("C:\\Users\\Emil\\Desktop\\Emulation\\backup_dev\\emucoach_v14_vip_auth_export.sql");
        String fileContext = FileUtils.readFileToString(file);
        fileContext = fileContext.replaceAll("USE `emucoach_v14_vip_auth`", "USE `emucoach_v15_vip_auth`");
        FileUtils.write(file, fileContext);
    }
    
}
