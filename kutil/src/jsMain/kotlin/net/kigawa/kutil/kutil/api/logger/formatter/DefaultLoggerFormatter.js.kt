package net.kigawa.kutil.kutil.api.logger.formatter

import net.kigawa.kutil.kutil.api.logger.LoggerRecord

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DefaultLoggerFormatter : LoggerFormatter {
  actual override fun format(logRecord: LoggerRecord): Any {
    return logRecord.message
  }
}