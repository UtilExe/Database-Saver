package FunctionLayer;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* For Future we can use this class to add new SQL fixes, and implement it to the World DB, so people can update it quickly */
public class FileModifier {

    private String DBNameToUpdate;
    private String NewDBName;

    public FileModifier() {
        this.DBNameToUpdate = "emucoach_v14_vip_auth";
        this.NewDBName = "emucoach_v15_vip_auth";
    }
    
    public void updateContent() throws IOException {
        try {
        File file = new File("C:\\Users\\Emil\\Desktop\\Emulation\\backup_dev\\emucoach_v14_vip_auth_export.sql");
        String fileContext = FileUtils.readFileToString(file);
        fileContext = fileContext.replaceAll(DBNameToUpdate, NewDBName);
                                 // For multiple text:.. .replaceAll("test", "test1");
        FileUtils.write(file, fileContext);
        System.out.println("Completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
