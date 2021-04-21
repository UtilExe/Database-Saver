
import java.net.URISyntaxException;


public class Runner {
    
    private static DatabaseActions DbRunner = new DatabaseActions(); 
    public static void main(String[] args) throws URISyntaxException {
        DbRunner.Backupdbtosql();
    }
}
