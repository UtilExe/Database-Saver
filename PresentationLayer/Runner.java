package PresentationLayer;


import FunctionLayer.DatabaseActions;
import FunctionLayer.FileModifier;
import java.io.IOException;
import java.net.URISyntaxException;


public class Runner {
    
    private static DatabaseActions DbRunner = new DatabaseActions();
    private static FileModifier FileEditor = new FileModifier();
    public static void main(String[] args) throws URISyntaxException, IOException {
        //DbRunner.Backupdbtosql();
        //DbRunner.importDBExecutor();
        FileEditor.updateContent();
    }
}
