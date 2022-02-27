package net.kigawa.kutil.kutil.app;

import net.kigawa.kutil.kutil.interfaces.Logger;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TerminalHandler extends Handler {
    private final Terminal terminal;
    private final Formatter formatter;
    private final Logger logger;

    public TerminalHandler(Terminal terminal, Formatter formatter, Logger logger) {
        this.logger = logger;
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
            logger.warning(e);
        }
    }

    @Override
    public void close() throws SecurityException {
        try {
            terminal.getWriter().close();
        } catch (IOException e) {
            logger.warning(e);
        }
    }
}
