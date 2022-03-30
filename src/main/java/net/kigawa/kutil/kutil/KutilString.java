package net.kigawa.kutil.kutil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.function.Function;

/**
 * utilities about string
 */
public class KutilString {

    /**
     * check is int
     *
     * @param str to test
     * @return return true when str is int
     */
    public static boolean isInt(String str) {
        return str.matches("[+-]?\\d*(\\.\\d+)?");
    }

    public static StringBuffer addYearToDate(StringBuffer stringBuffer, String interval) {
        Calendar calendar = Calendar.getInstance();
        return stringBuffer.append(interval).append(calendar.get(Calendar.YEAR)).append(interval)
                .append(calendar.get(Calendar.MONTH)).append(interval).append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(interval).append(calendar.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * @deprecated use insertSymbol()
     */
    public static <T> String connectArray(T[] ts, String insert) {
        return insertSymbol(insert, Arrays.asList((String[]) ts));
    }

    /**
     * insert symbol between string array
     *
     * @param sb      base string buffer
     * @param symbol  string symbol
     * @param strings base string array
     * @return append string buffer
     */
    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, String[] strings) {
        return insertSymbol(sb, symbol, Arrays.stream(strings).iterator());
    }

    /**
     * insert symbol between string Iterable
     *
     * @param sb             base string buffer
     * @param symbol         string symbol
     * @param stringIterable base string iterable
     * @return append string buffer
     */
    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterable<String> stringIterable) {
        return insertSymbol(sb, symbol, stringIterable.iterator());
    }

    /**
     * insert symbol between string iterator
     *
     * @param sb       base string buffer
     * @param symbol   string symbol
     * @param iterator base string iterator
     * @return append string buffer
     */
    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterator<String> iterator) {
        return insertSymbol(sb, symbol, iterator, s -> s);
    }

    /**
     * insert symbol between string that created by function
     *
     * @param sb       base string buffer
     * @param symbol   string symbol
     * @param array    base array
     * @param function to create function
     * @return append string buffer
     */
    public static <T> StringBuffer insertSymbol(StringBuffer sb, String symbol, T[] array, Function<T, String> function) {
        return insertSymbol(sb, symbol, Arrays.stream(array).iterator(), function);
    }

    /**
     * insert symbol between string that created by function
     *
     * @param sb       base string buffer
     * @param symbol   string symbol
     * @param iterable base iterable
     * @param function to create function
     * @return append string buffer
     */
    public static <T> StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterable<T> iterable, Function<T, String> function) {
        return insertSymbol(sb, symbol, iterable.iterator(), function);
    }

    /**
     * insert symbol between string that created by function
     *
     * @param sb       base string buffer
     * @param symbol   string symbol
     * @param iterator base iterator
     * @param function to create function
     * @return append string buffer
     */
    public static <T> StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterator<T> iterator, Function<T, String> function) {
        while (iterator.hasNext()) {
            sb.append(function.apply(iterator.next()));
            if (iterator.hasNext()) sb.append(symbol);
        }
        return sb;
    }

    /**
     * insert symbol between string that created by function
     *
     * @param symbol         string symbol
     * @param stringIterable base string iterable
     * @return created string
     */
    public static String insertSymbol(String symbol, Iterable<String> stringIterable) {
        return insertSymbol(new StringBuffer(), symbol, stringIterable.iterator()).toString();
    }
}
