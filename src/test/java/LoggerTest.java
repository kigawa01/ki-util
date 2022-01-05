import net.kigawa.util.Formatter;
import net.kigawa.util.Logger;

import java.io.File;
import java.nio.file.Paths;
import java.util.logging.Level;

public class LoggerTest {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("").getHandlers()[0].setFormatter(new Formatter());
        Logger.enable("test", null, Level.INFO, new File(Paths.get("").toAbsolutePath().toFile(), "log"));
        Logger.getInstance().info("aaa");
        Logger.enable("test1", Logger.getInstance().getJavaLogger(), Level.INFO, new File(Paths.get("").toAbsolutePath().toFile(), "log1"));
        Logger.getInstance().info("iii");
        Logger.getInstance().info("uuu");
        Logger.getInstance().info(new String[]{
                "a","b"
        });
        Logger.getInstance().info("");
    }
}
