package net.kigawa.kutil.kutil.string;

import java.util.*;
import java.util.function.*;

/**
 * @deprecated use KutilString
 */
public class StringUtil {

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
     * @param <T>    array type
     * @param insert insert str
     * @param ts     array
     * @return str
     * @deprecated
     */
    @Deprecated
    public static <T> String connectArray(T[] ts, String insert) {
        return insertSymbol(insert, Arrays.asList((String[]) ts));
    }

    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, String[] strings) {
        return insertSymbol(sb, symbol, Arrays.stream(strings).iterator());
    }

    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterable<String> stringList) {
        return insertSymbol(sb, symbol, stringList.iterator());
    }

    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterator<String> iterator) {
        return insertSymbol(sb, symbol, iterator, s -> s);
    }

    public static <T> StringBuffer insertSymbol(StringBuffer sb, String symbol, T[] array, Function<T, String> function) {
        return insertSymbol(sb, symbol, Arrays.stream(array).iterator(), function);
    }

    public static <T> StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterator<T> iterator, Function<T, String> function) {
        while (iterator.hasNext()) {
            sb.append(function.apply(iterator.next()));
            if (iterator.hasNext()) sb.append(symbol);
        }
        return sb;
    }

    public static String insertSymbol(String symbol, Iterable<String> stringList) {
        return insertSymbol(new StringBuffer(), symbol, stringList.iterator()).toString();
    }
}
