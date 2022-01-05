package net.kigawa.file;

import java.io.File;
import java.nio.file.Paths;

public class FileUtil {

    public static File getRelativeFile(String... path) {
        return getFile(Paths.get("").toAbsolutePath().toFile(), path);
    }

    public static File getFile(File parent, String... path) {
        File file = parent;
        for (String name : path) {
            parent = new File(parent, name);
        }
        return file;
    }

    public static File getAbsolutFile() {
        return Paths.get("").toAbsolutePath().toFile();
    }
}
