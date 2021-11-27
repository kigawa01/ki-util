package net.kigawa.util;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Util {

    public static void execLog(Object o, Consumer<String> consumer) {
        if (o instanceof Object[]) {
            for (Object o1 : (Object[]) o) {
                execLog(o1, consumer);
            }
            return;
        }
        if (o instanceof Throwable) {
            execLog(((Throwable) o).getStackTrace(), consumer);
        }
        consumer.accept(o.toString());
    }

    public static <T> void executeIterable(Iterable<T> collection, Process<T> process) {
        for (T t : collection) {
            process.execute(t);
        }
    }

    /**
     * @deprecated
     */
    public static String createString(int[] ints) {
        return connectArray(castIntArray(ints, new Integer[ints.length]), ", ");
    }

    public static Integer[] castIntArray(int[] from, Integer[] to) throws ClassCastException {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
        return to;
    }

    public static <T> String connectArray(T[] ts, String insert) {
        StringBuilder str = new StringBuilder(ts[0].toString());
        for (int i = 1; i < ts.length; i++) {
            str.append(insert).append(ts[i]);
        }
        return str.toString();
    }

    public static <F, T extends F> List<T> changeListType(List<F> list,Class<T> to) throws ClassCastException {
        List<T> list1 = new ArrayList<>();
        for (F f : list) {
            list1.add((T) f);
        }
        return list1;
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

    public static void runCommand(String[] command, File dir) {
        try {
            if (dir.mkdirs()) throw new FileNotFoundException();
            System.out.println("run jar...");
            String result;
            java.lang.Process process;
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(command, null, dir);
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

    public static File getAbsolutFile() {
        return Paths.get("").toAbsolutePath().toFile();
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