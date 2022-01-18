package net.kigawa.log;

import net.kigawa.app.Terminal;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TerminalHandler extends Handler {
    private final Terminal terminal;
    private final Formatter formatter;

    public TerminalHandler(Terminal terminal, Formatter formatter) {
        this.terminal = terminal;
        this.formatter = formatter;
    }

    @Override
    public void publish(LogRecord record) {
            terminal.write(formatter.format(record));
    }

    @Override
    public void flush() {
        try {
            terminal.getWriter().flush();
        } catch (IOException e) {
            Logger.getInstance().warning(e);
        }
    }

    @Override
    public void close() throws SecurityException {
        try {
            terminal.getWriter().close();
        } catch (IOException e) {
            Logger.getInstance().warning(e);
        }
    }
}
