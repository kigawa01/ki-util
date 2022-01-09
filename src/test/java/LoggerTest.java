import net.kigawa.log.Formatter;
import net.kigawa.log.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;

public class LoggerTest {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("").getHandlers()[0].setFormatter(new Formatter());
        Logger.enable("test", null, Level.INFO, new File(Paths.get("").toAbsolutePath().toFile(), "log"));
        Logger.getInstance().info("aaa");
        Logger logger = Logger.getInstance();
        Logger.enable("test1", Logger.getInstance(), Level.INFO, new File(Paths.get("").toAbsolutePath().toFile(), "log1"));
        Logger.getInstance().info("iii");
        Logger.getInstance().info("uuu");
        Logger.getInstance().info(new String[]{
                "a", "b"
        });
        Logger.getInstance().info("");

        try {
            String ab = getNull();
            ab.equals("a");
            throw new IOException();
        } catch (Exception e) {
            Logger.getInstance().warning(e);
        }

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String a = scanner.next();
            Logger.getInstance().info(a);
            if (a.equals("end")) break;
            if (a.equals("notify")) logger.notifyAll();
        }

        logger.disable();
        Logger.getInstance().disable();
    }

    public static String getNull() {
        return null;
    }
}
