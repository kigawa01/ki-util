import net.kigawa.util.Logger;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;

public class LoggerTest {
    public static void main(String[] args) {
        Logger.enable("test", null, Level.INFO, new File(Paths.get("").toAbsolutePath().toFile(), "log"));
        Logger.getInstance().info("aaa");
        Logger.enable("test1", Logger.getInstance().getJavaLogger(), Level.INFO, new File(Paths.get("").toAbsolutePath().toFile(), "log1"));
        Logger.getInstance().info("iii");
        Logger.getInstance().info("uuu");
    }
}
