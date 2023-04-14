package net.kigawa.kutil.kutil;

import net.kigawa.kutil.kutil.file.*;
import net.kigawa.kutil.kutil.string.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.nio.file.StandardCopyOption.*;

/**
 * utilities for java
 */
public class Kutil
{
    /**
     * @deprecated not use
     */
    private static void runCommand(Function<Runtime, java.lang.Process> function)
    {
        try
        {
            System.out.println("run jar...");
            String result;
            java.lang.Process process;
            Runtime runtime = Runtime.getRuntime();
            process = function.apply(runtime);
            System.out.println("out put log...");
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((result = bufferedReader.readLine()) != null)
            {
                System.out.println(result);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @deprecated not use
     */
    public static void runCommand(String[] command, File dir)
    {
        runCommand(runtime -> {
            try
            {
                return runtime.exec(command, null, dir);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        });
    }

    /**
     * @deprecated not use
     */
    public static void runCommand(String command, File dir)
    {
        runCommand(runtime -> {
            try
            {
                return runtime.exec(command, null, dir);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        });
    }

    /**
     * @deprecated not use
     */
    public static void download(URL url, File file, String name)
    {
        try
        {
            File file1 = new File(file, name);

            if (file1.exists())
            {
                file1.delete();
            }

            Files.copy(url.openStream(), file1.toPath(), REPLACE_EXISTING);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @deprecated use FileUtil.getAbsolutFile()
     */
    public static File getAbsolutFile()
    {
        return FileUtil.getAbsolutFile();
    }

    /**
     * @deprecated not use
     */
    public static String getYearToDay()
    {
        return addYearToDay(new StringBuffer()).toString();
    }

    /**
     * @deprecated use getYearToDay()
     */
    public static String getDate()
    {
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer().append(calendar.get(Calendar.YEAR));
        sb.append("-").append(calendar.get(Calendar.MONTH));
        sb.append("-").append(calendar.get(Calendar.DAY_OF_MONTH));
        return sb.toString();
    }

    /**
     * @deprecated not use
     */
    public static StringBuffer addHourToSec(StringBuffer stringBuffer, String symbol)
    {
        Calendar calendar = Calendar.getInstance();
        stringBuffer = new StringBuffer().append(calendar.get(Calendar.HOUR_OF_DAY));
        stringBuffer.append(symbol).append(calendar.get(Calendar.MINUTE));
        stringBuffer.append(symbol).append(calendar.get(Calendar.SECOND));
        return stringBuffer;
    }

    /**
     * @deprecated not use
     */
    public static StringBuffer addHourToSec(StringBuffer sb)
    {
        return addHourToSec(sb, "-");
    }

    /**
     * @deprecated not use
     */
    public static String getHourToSec()
    {
        return addHourToSec(new StringBuffer()).toString();
    }

    /**
     * @deprecated use getHourToSec()
     */
    public static String getTime()
    {
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer().append(calendar.get(Calendar.HOUR_OF_DAY));
        sb.append("-").append(calendar.get(Calendar.MINUTE));
        sb.append("-").append(calendar.get(Calendar.SECOND));
        return sb.toString();
    }

    /**
     * @deprecated use SimpleDefaultFormatter
     */
    public static StringBuffer addYearToDay(StringBuffer stringBuffer)
    {
        return addYearToDay(stringBuffer, "-");
    }

    /**
     * @deprecated use SimpleDefaultFormatter
     */
    public static StringBuffer addYearToDay(StringBuffer stringBuffer, String interval)
    {
        Calendar calendar = Calendar.getInstance();
        return stringBuffer.append(interval).append(calendar.get(Calendar.YEAR)).append(interval)
                .append(calendar.get(Calendar.MONTH)).append(interval).append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(interval).append(calendar.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * @deprecated use addYearToDay()
     */
    public static StringBuffer addYearToDate(StringBuffer stringBuffer)
    {
        return addYearToDay(stringBuffer);
    }

    /**
     * @deprecated use addYearToDay()
     */
    public static StringBuffer addYearToDate(StringBuffer stringBuffer, String interval)
    {
        return addYearToDay(stringBuffer, interval);
    }

    /**
     * @deprecated use stream removeIf
     */
    public static <L, T> void removeFromArray(List<L> list, T object, BiPredicate<L, T> biPredicate)
    {
        list.removeIf(l -> biPredicate.test(l, object));
    }

    /**
     * @deprecated use stream forEach
     */
    public static <T> void executeIterable(Iterable<T> collection, Process<T> process)
    {
        for (T t : collection)
        {
            process.execute(t);
        }
    }

    /**
     * @deprecated use StringUtil.connectArray(castIntArray(ints, new Integer[ints.length]), ", ")
     */
    public static String createString(int[] ints)
    {
        return StringUtil.connectArray(castIntArray(ints, new Integer[ints.length]), ", ");
    }

    /**
     * @deprecated use Arrays.stream(from).boxed().toArray(Integer[]::new)
     */
    public static Integer[] castIntArray(int[] from, Integer[] to)
    {
        return Arrays.stream(from).boxed().toArray(Integer[]::new);
    }

    /**
     * @deprecated use new ArrayList<>(list)
     */
    public static <T, F extends T> List<T> changeListType(List<F> list, Class<T> to)
    {
        return new ArrayList<>(list);
    }

    /**
     * @deprecated use list.stream().mapToInt(value -> value).toArray()
     */
    public static int[] getIntegerArrangement(List<Integer> list)
    {
        return list.stream().mapToInt(value -> value).toArray();
    }

    /**
     * @deprecated need refactoring
     */
    public static String[] removeStrSet(String[] strings, String s)
    {
        return addSet(strings, s, String[]::new);
    }

    /**
     * @deprecated need refactoring
     */
    public static String[] addStrSet(String[] strings, String s)
    {
        return addSet(strings, s, String[]::new);
    }

    /**
     * @deprecated need refactoring
     */
    public static <T> T[] removeSet(T[] ar, T t, NewArrangement<T> newArrangement)
    {
        Set<T> set = getSet(ar);
        set.remove(t);
        return getArrangement(set, newArrangement);
    }

    /**
     * @deprecated need refactoring
     */
    public static <T> T[] addSet(T[] ar, T t, NewArrangement<T> newArrangement)
    {
        Set<T> set = getSet(ar);
        set.add(t);
        return getArrangement(set, newArrangement);
    }

    /**
     * @deprecated use list.toArray(String[]::new)
     */
    public static String[] getStringArrangement(List<String> list)
    {
        return list.toArray(String[]::new);

    }

    /**
     * @deprecated use list.toArray(generator)
     */
    public static <T> T[] getArrangement(Collection<T> collection, NewArrangement<T> newArrangement)
    {
        T[] ts = newArrangement.getArrangement(collection.size());
        int i = 0;
        for (T t : collection)
        {
            ts[i] = t;
            i++;
        }
        return ts;
    }

    /**
     * @deprecated use KutilArray.toSet(ts)
     */
    public static <T> Set<T> getSet(T[] ts)
    {
        return KutilArray.toSet(ts);
    }

    /**
     * @deprecated use Arrays.stream(o).collect(Collectors.toList())
     */
    public static <T> List<T> getList(T[] o)
    {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, o);
        return Arrays.stream(o).collect(Collectors.toList());
    }

    /**
     * @param <T>
     * @deprecated use Runnable
     */
    public interface Process<T>
    {
        void execute(T t);
    }

    /**
     * create array function
     *
     * @param <T> class type
     */
    public interface NewArrangement<T>
    {
        T[] getArrangement(int size);
    }

}