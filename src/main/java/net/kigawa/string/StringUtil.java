package net.kigawa.string;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;

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
     * @deprecated
     */
    public static <T> String connectArray(T[] ts, String insert) {
        return insertSymbol(insert, Arrays.asList((String[]) ts));
    }

    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, String[] strings) {
        return insertSymbol(sb, symbol, Arrays.stream(strings).iterator());
    }

    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterable<String> stringList) {
        return insertSymbol(sb, symbol, stringList.iterator());
    }

    public static StringBuffer insertSymbol(StringBuffer sb, String symbol, Iterator<String> stringIterator) {
        while (stringIterator.hasNext()) {
            sb.append(stringIterator.next());
            if (stringIterator.hasNext()) sb.append(symbol);
        }
        return sb;
    }

    public static String insertSymbol(String symbol, Iterable<String> stringList) {
        return insertSymbol(new StringBuffer(), symbol, stringList.iterator()).toString();
    }
}
