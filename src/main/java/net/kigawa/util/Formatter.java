package net.kigawa.util;

import java.util.Calendar;
import java.util.logging.LogRecord;

public class Formatter extends java.util.logging.Formatter {
    private Calendar calendar = Calendar.getInstance();

    @Override
    public String format(LogRecord record) {
        StringBuffer sb = new StringBuffer();
        calendar.setTimeInMillis(record.getMillis());
        sb.append(Calendar.MONTH).append("-").append(Calendar.DAY_OF_MONTH).append("-").append(Calendar.HOUR_OF_DAY)
                .append("-").append(Calendar.MINUTE).append("-").append(Calendar.SECOND);
        sb.append("[").append(record.getLevel().getName()).append("] |").append(record.getMessage());
        return sb.toString();
    }
}
