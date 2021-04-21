package FunctionLayer;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/* For Future we can use this class to add new SQL fixes, and implement it to the World DB, so people can update it quickly */
public class FileModifier {

    private String authDBNameToUpdate;
    private String authNewDBName;
    private String charDBNameToUpdate;
    private String charNewDBName;

    public FileModifier() {
        this.authDBNameToUpdate = "emucoach_v14_vip_auth";
        this.authNewDBName = "emucoach_v15_vip_auth";
        
        this.charDBNameToUpdate = "emucoach_v14_vip_char";
        this.charNewDBName = "emucoach_v15_vip_char";
    }
    
    public void updateContentOfAuthOrChar(String folderPath, String DbName) throws IOException {
        try {
        File file = new File(folderPath + DbName + "_export.sql"+"\\");
        String fileContext = FileUtils.readFileToString(file);
        fileContext = fileContext.replaceAll(authDBNameToUpdate, authNewDBName)
                                 .replaceAll(charDBNameToUpdate, charNewDBName);
        FileUtils.write(file, fileContext);
        System.out.println("Completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
