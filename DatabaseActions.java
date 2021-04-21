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

public class DatabaseActions {
    
    private static Scanner scanner = new Scanner(System.in);
    private String dbName;
    private String dbUser;
    private String dbPass;
    private String savePath;
    private String executeCmd;
    private String folderPath;

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getExecuteCmd() {
        return executeCmd;
    }

    public void setExecuteCmd(String executeCmd) {
        this.executeCmd = executeCmd;
    }
    
    // ToDo: Use scanner, instead of hard-coding the values.
    public void Backupdbtosql() throws URISyntaxException {
    try {

        /* Not using below */
        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        // CodeSource codeSource = MysqlRestoreProgram.class.getProtectionDomain().getCodeSource();
        //File jarFile = new File(codeSource.getLocation().toURI().getPath());
        // String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
        /*
        System.out.println("Enter MySQL path: (Default: C:\\Users\\Exo\\Downloads\\_Server - EmuCoach Version v13.1 - VIP\\_Server\\mysql\\bin\\mysql");
        System.out.println("Note: Make sure to change the start to _Server location");
        String mysqlPath = scanner.nextLine();;
        System.out.println("Enter database user: (Default: root)");
        String dbUser = scanner.nextLine();
        System.out.println("Enter database password: (Default: ascent)");
        String dbPassword = scanner.nextLine();
        */
        System.out.println("Enter the database name you want to save/export");
        setDbName(scanner.nextLine());
        System.out.println("Enter the database user (Default: root)");
        setDbUser(scanner.nextLine());
        System.out.println("Enter database password: (Default: ascent)");
        setDbPass(scanner.nextLine());
        
        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
        System.out.println("Enter the folder path to where to save the sql files. Dev: \\C:\\Users\\Emil\\Desktop\\Emulation\\backup_dev\\");
        setFolderPath(scanner.nextLine());

        /*NOTE: Creating Folder if it does not exist*/
        File f1 = new File(getFolderPath());
        f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
        setSavePath("C:\\Users\\Emil\\Desktop\\Emulation\\backup_dev\\" + getDbName()+"_export.sql\"");
        /*NOTE: Used to create a cmd command*/
        setExecuteCmd("C:\\Users\\Emil\\Desktop\\Emulation\\_Server - EmuCoach Version v14_Blizz_VIP\\_Server\\mysql\\bin\\mysqldump -u" + getDbUser() + " -p" + getDbPass() + " --database " + getDbName() + " -r " + getSavePath());

        /*NOTE: Executing the command here*/
        Process runtimeProcess = Runtime.getRuntime().exec(getExecuteCmd());
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
    
    // Seems to work, just need validation and testing
    public static void restoreDatabase(String mysqlExe, String dbUserName, String dbPassword, String source) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String[] executeCmd = new String[]{mysqlExe, "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};

        Process runtimeProcess;
        try {
            System.out.println("Processing.. " + "STARTED " + sdf.format(new Date()));
            Date sDate = new Date();

            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = 0;
            try {
                processComplete = runtimeProcess.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Processing.. " + "END " + sdf.format(new Date()));
            Date eDate = new Date();
            long duration = eDate.getTime() - sDate.getTime();
            int seconds = (int) ((duration / 1000) % 60);
            long minutes = ((duration - seconds) / 1000) / 60;
            System.err.println("TOTAL TIME : " + minutes + " minutes :: ");
            System.err.print(seconds + " seconds :: ");
            System.err.print(duration + " milliseconds");

            if (processComplete == 0) {
                System.out.println("Backup restored successfully with " + source);
            } else {
                System.out.println("Could not restore the backup " + source);
            }
            scanner.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}