package net.kigawa.kutil.kutil.api.logger

import java.util.logging.LogRecord

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LoggerRecord(
  val logRecord: LogRecord,
) {
  actual val level: LogLevel
    get() = logRecord.level.logLevel()
  actual val message: Any
    get() = logRecord.message
}