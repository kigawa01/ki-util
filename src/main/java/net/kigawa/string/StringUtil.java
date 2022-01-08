package net.kigawa.string;

import java.util.Calendar;

public class StringUtil {

    private static boolean isInt(String str) {
        return str.matches("[+-]?\\d*(\\.\\d+)?");
    }

    public static StringBuffer addYearToDate(StringBuffer stringBuffer, String interval) {
        Calendar calendar = Calendar.getInstance();
        return stringBuffer.append(interval).append(calendar.get(Calendar.YEAR)).append(interval)
                .append(calendar.get(Calendar.MONTH)).append(interval).append(calendar.get(Calendar.DAY_OF_MONTH))
                .append(interval).append(calendar.get(Calendar.HOUR_OF_DAY));
    }

    public static <T> String connectArray(T[] ts, String insert) {
        StringBuffer str = new StringBuffer(ts[0].toString());
        for (int i = 1; i < ts.length; i++) {
            str.append(insert).append(ts[i]);
        }
        return str.toString();
    }
}
