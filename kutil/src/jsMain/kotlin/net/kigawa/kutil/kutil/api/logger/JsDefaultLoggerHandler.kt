package net.kigawa.kutil.kutil.api.logger

import net.kigawa.kutil.kutil.api.logger.formatter.DefaultLoggerFormatter
import net.kigawa.kutil.kutil.api.logger.formatter.LoggerFormatter

internal object JsDefaultLoggerHandler : LoggerHandler {
  private var formatter: LoggerFormatter = DefaultLoggerFormatter()

  override fun log(level: LogLevel, message: Any) {
    val formattedMsg = formatter.format(LoggerRecord(level, message))
    when (level) {
      LogLevel.WARNING -> console.warn(formattedMsg)
      LogLevel.INFO -> console.info(formattedMsg)
      LogLevel.FINE -> console.log(formattedMsg)
    }
  }

  override fun setFormatter(loggerFormatter: LoggerFormatter) {
    formatter = loggerFormatter
  }
}