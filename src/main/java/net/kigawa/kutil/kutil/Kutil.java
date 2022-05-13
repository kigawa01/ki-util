package net.kigawa.kutil.kutil;

import net.kigawa.kutil.kutil.file.FileUtil;
import net.kigawa.kutil.kutil.string.StringUtil;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * utilities for java
 */
public class Kutil {
    /**
     * add year, month and day to string buffer
     *
     * @param stringBuffer string buffer to add
     * @return added string buffer
     */
    public static StringBuffer addYearToDay(StringBuffer stringBuffer) {
        return addYearToDay(stringBuffer, "-");
    }

    /**
     * add year, month and day to string buffer
     *
     * @param stringBuffer string buffer to add
     * @param interval     string that insert between dates
     * @return added string buffer
     */
    public static StringBuffer addYearToDay(StringBuffer stringBuffer, String interval) {
        Calendar calendar = Calendar.getInstance();
        return stringBuffer.append(interval).append(calendar.get(Calendar.YEAR)).append(interval)
                .append(calendar.get(Calendar.MONTH)).append(interval).append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(interval).append(calendar.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * @deprecated use addYearToDay()
     */
    public static StringBuffer addYearToDate(StringBuffer stringBuffer) {
        return addYearToDay(stringBuffer);
    }

    /**
     * @deprecated use addYearToDay()
     */
    public static StringBuffer addYearToDate(StringBuffer stringBuffer, String interval) {
        return addYearToDay(stringBuffer, interval);
    }

    /**
     * @param list        source list
     * @param object      object for testing
     * @param biPredicate remove L object when return true
     * @param <L>         List type
     * @param <T>         object type for test
     */
    public static <L, T> void removeFromArray(List<L> list, T object, BiPredicate<L, T> biPredicate) {
        list.removeIf(l -> biPredicate.test(l, object));
    }

    /**
     * @param collection collection for execute
     * @param process    to execute process
     * @param <T>        arg to process
     */
    public static <T> void executeIterable(Iterable<T> collection, Process<T> process) {
        for (T t : collection) {
            process.execute(t);
        }
    }

    /**
     * @deprecated use StringUtil.connectArray(castIntArray(ints, new Integer[ints.length]), ", ")
     */
    public static String createString(int[] ints) {
        return StringUtil.connectArray(castIntArray(ints, new Integer[ints.length]), ", ");
    }

    /**
     * change array type from int to Integer
     *
     * @param from int array
     * @param to   Integer array base
     * @return created Integer array
     */
    public static Integer[] castIntArray(int[] from, Integer[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
        return to;
    }

    /**
     * change list to super class
     *
     * @param list from list
     * @param to   changed class
     * @param <T>  changed class type
     * @param <F>  from class type
     * @return changed list
     */
    public static <T, F extends T> List<T> changeListType(List<F> list, Class<T> to) {
        return new ArrayList<>(list);
    }

    /**
     * get int array from Integer list
     *
     * @param list from list
     * @return in array
     */
    public static int[] getIntegerArrangement(List<Integer> list) {
        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

    /**
     * @deprecated need refactoring
     */
    public static String[] removeStrSet(String[] strings, String s) {
        return addSet(strings, s, String[]::new);
    }

    /**
     * @deprecated need refactoring
     */
    public static String[] addStrSet(String[] strings, String s) {
        return addSet(strings, s, String[]::new);
    }

    /**
     * @deprecated need refactoring
     */
    public static <T> T[] removeSet(T[] ar, T t, NewArrangement<T> newArrangement) {
        Set<T> set = getSet(ar);
        set.remove(t);
        return getArrangement(set, newArrangement);
    }

    /**
     * @deprecated need refactoring
     */
    public static <T> T[] addSet(T[] ar, T t, NewArrangement<T> newArrangement) {
        Set<T> set = getSet(ar);
        set.add(t);
        return getArrangement(set, newArrangement);
    }

    /**
     * get String array from list
     *
     * @param list base list
     * @return created array
     */
    public static String[] getStringArrangement(List<String> list) {
        return getArrangement(list, String[]::new);

    }

    /**
     * create array from collection
     *
     * @param collection     base collection
     * @param newArrangement create array function
     * @param <T>            class type
     * @return created array
     */
    public static <T> T[] getArrangement(Collection<T> collection, NewArrangement<T> newArrangement) {
        T[] ts = newArrangement.getArrangement(collection.size());
        int i = 0;
        for (T t : collection) {
            ts[i] = t;
            i++;
        }
        return ts;
    }

    /**
     * creat set from array
     *
     * @param ts  base array
     * @param <T> class type
     * @return created set
     */
    public static <T> Set<T> getSet(T[] ts) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, ts);
        return set;
    }

    /**
     * create list from array
     *
     * @param o   base array
     * @param <T> class type
     * @return created list
     */
    public static <T> List<T> getList(T[] o) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, o);
        return list;
    }

    /**
     * execute command
     *
     * @param function execute function
     */
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * execute command
     *
     * @param command command args
     * @param dir     current dir
     */
    public static void runCommand(String[] command, File dir) {
        runCommand(runtime -> {
            try {
                return runtime.exec(command, null, dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    /**
     * execute command
     *
     * @param command command
     * @param dir     current dir
     */
    public static void runCommand(String command, File dir) {
        runCommand(runtime -> {
            try {
                return runtime.exec(command, null, dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    /**
     * download file
     *
     * @param url  url
     * @param file download dir
     * @param name file name
     */
    public static void download(URL url, File file, String name) {
        try {
            File file1 = new File(file, name);

            if (file1.exists()) {
                file1.delete();
            }

            Files.copy(url.openStream(), file1.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @deprecated use FileUtil.getAbsolutFile()
     */
    public static File getAbsolutFile() {
        return FileUtil.getAbsolutFile();
    }

    /**
     * get date string
     *
     * @return string
     */
    public static String getYearToDay() {
        return addYearToDay(new StringBuffer()).toString();
    }

    /**
     * @deprecated use getYearToDay()
     */
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer().append(calendar.get(Calendar.YEAR));
        sb.append("-").append(calendar.get(Calendar.MONTH));
        sb.append("-").append(calendar.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    /**
     * add time of hour, min and sec to string buffer
     *
     * @param stringBuffer string buffer to add
     * @param symbol       symbol to append
     * @return added string buffer
     */
    public static StringBuffer addHourToSec(StringBuffer stringBuffer, String symbol) {
        Calendar calendar = Calendar.getInstance();
        stringBuffer = new StringBuffer().append(calendar.get(Calendar.HOUR_OF_DAY));
        stringBuffer.append(symbol).append(calendar.get(Calendar.MINUTE));
        stringBuffer.append(symbol).append(calendar.get(Calendar.SECOND));
        return stringBuffer;
    }

    /**
     * add time of hour, min and sec to string buffer
     *
     * @param sb string buffer to add
     * @return added string buffer
     */
    public static StringBuffer addHourToSec(StringBuffer sb) {
        return addHourToSec(sb, "-");
    }

    /**
     * get time of hour, min and sec to string buffer
     *
     * @return string
     */
    public static String getHourToSec() {
        return addHourToSec(new StringBuffer()).toString();
    }

    /**
     * @deprecated use getHourToSec()
     */
    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer().append(calendar.get(Calendar.HOUR_OF_DAY));
        sb.append("-").append(calendar.get(Calendar.MINUTE));
        sb.append("-").append(calendar.get(Calendar.SECOND));
        return sb.toString();
    }

    /**
     * @param <T>
     * @deprecated use Runnable
     */
    public interface Process<T> {
        void execute(T t);
    }

    /**
     * create array function
     *
     * @param <T> class type
     */
    public interface NewArrangement<T> {
        T[] getArrangement(int size);
    }
}