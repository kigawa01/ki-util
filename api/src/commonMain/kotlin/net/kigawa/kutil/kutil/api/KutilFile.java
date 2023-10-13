package net.kigawa.kutil.kutil.api;

import java.io.File;
import java.nio.file.Paths;

/**
 * utilities about file
 */
@SuppressWarnings("unused")
public class KutilFile {

    /**
     * get child file from current
     *
     * @param path child file names
     * @return child file
     */
    public static File getRelativeFile(String... path) {
        return getFile(Paths.get("").toAbsolutePath().toFile(), path);
    }

    /**
     * get child file
     *
     * @param parent parent file
     * @param path   child file names
     * @return child file
     */
    public static File getFile(File parent, String... path) {
        File file = parent;
        for (String name : path) {
            file = new File(file, name);
        }
        return file;
    }

    /**
     * get current file as absolute
     *
     * @return current dir
     */
    public static File getAbsolutFile() {
        return Paths.get("").toAbsolutePath().toFile();
    }
}
