package net.kigawa.kutil.kutil.util;

import net.kigawa.kutil.kutil.file.*;
import net.kigawa.kutil.kutil.string.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

import static java.nio.file.StandardCopyOption.*;

/**
 * @deprecated use Kutil
 */
public class KiUtil {

    public static StringBuffer addYearToDate(StringBuffer stringBuffer) {
        return addYearToDate(stringBuffer, "-");
    }

    public static StringBuffer addYearToDate(StringBuffer stringBuffer, String interval) {
        Calendar calendar = Calendar.getInstance();
        return stringBuffer.append(interval).append(calendar.get(Calendar.YEAR)).append(interval)
                .append(calendar.get(Calendar.MONTH)).append(interval).append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(interval).append(calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static <L, T> void removeFromArray(List<L> list, T object, BiPredicate<L, T> biPredicate) {
        list.removeIf(l -> biPredicate.test(l, object));
    }

    public static <T> void executeIterable(Iterable<T> collection, Process<T> process) {
        for (T t : collection) {
            process.execute(t);
        }
    }

    /**
     * @param ints numbers
     * @return joined numbers
     * @deprecated
     */
    @Deprecated
    public static String createString(int[] ints) {
        return StringUtil.connectArray(castIntArray(ints, new Integer[ints.length]), ", ");
    }

    public static Integer[] castIntArray(int[] from, Integer[] to) throws ClassCastException {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
        return to;
    }

    public static <T, F extends T> List<T> changeListType(List<F> list, Class<T> to) throws ClassCastException {
        return new ArrayList<>(list);
    }

    public static int[] getIntegerArrangement(List<Integer> list) {
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    public static String[] removeStrSet(String[] strings, String s) {
        return addSet(strings, s, new NewStrArrangement());
    }

    public static String[] addStrSet(String[] strings, String s) {
        return addSet(strings, s, new NewStrArrangement());
    }

    public static <T> T[] removeSet(T[] ar, T t, NewArrangement<T> newArrangement) {
        Set<T> set = getSet(ar);
        set.remove(t);
        return getArrangement(set, newArrangement);
    }

    public static <T> T[] addSet(T[] ar, T t, NewArrangement<T> newArrangement) {
        Set<T> set = getSet(ar);
        set.add(t);
        return getArrangement(set, newArrangement);
    }

    public static String[] getStringArrangement(List<String> list) {
        return getArrangement(list, String[]::new);

    }

    public static <T> T[] getArrangement(Collection<T> collection, NewArrangement<T> newArrangement) {
        T[] ts = newArrangement.getArrangement(collection.size());
        int i = 0;
        for (T t : collection) {
            ts[i] = t;
            i++;
        }
        return ts;
    }

    public static <T> Set<T> getSet(T[] ts) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, ts);
        return set;
    }

    public static <T> List<T> getList(T[] o) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, o);
        return list;
    }

    private static void runCommand(Function<Runtime, java.lang.Process> function) {
        try {
            System.out.println("run jar...");
            String result;
            java.lang.Process process;
            Runtime runtime = Runtime.getRuntime();
            process = function.apply(runtime);
            System.out.println("out put log...");
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((result = bufferedReader.readLine()) != null) {
                System.out.println(result);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runCommand(String[] command, File dir) {
        runCommand(runtime -> {
            try {
                return runtime.exec(command, null, dir);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static void runCommand(String command, File dir) {
        runCommand(runtime -> {
            try {
                return runtime.exec(command, null, dir);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    public static void download(URL url, File file, String name) {
        try {
            File file1 = new File(file, name);

            if (file1.exists()) {
                file1.delete();
            }

            Files.copy(url.openStream(), file1.toPath(), REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return absolute file
     * @deprecated
     */
    @Deprecated
    public static File getAbsolutFile() {
        return FileUtil.getAbsolutFile();
    }

    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer().append(calendar.get(Calendar.YEAR));
        sb.append("-").append(calendar.get(Calendar.MONTH));
        sb.append("-").append(calendar.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer().append(calendar.get(Calendar.HOUR_OF_DAY));
        sb.append("-").append(calendar.get(Calendar.MINUTE));
        sb.append("-").append(calendar.get(Calendar.SECOND));
        return sb.toString();
    }

    public interface Process<T> {
        void execute(T t);
    }

    public interface NewArrangement<T> {
        T[] getArrangement(int size);
    }

    public static class NewStrArrangement implements NewArrangement<String> {
        @Override
        public String[] getArrangement(int size) {
            return new String[size];
        }
    }
}