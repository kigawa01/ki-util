package net.kigawa.kutil.kutil.api.logger.formatter

import net.kigawa.kutil.kutil.api.logger.LoggerRecord
import net.kigawa.kutil.kutil.api.str.objectformatter.DefaultObjFormatter
import net.kigawa.kutil.kutil.api.str.objectformatter.ObjectFormatter
import java.util.logging.Formatter
import java.util.logging.LogRecord

class LoggerFormatterBridge(
  private val loggerFormatter: LoggerFormatter,
  private val objectFormatter: ObjectFormatter = DefaultObjFormatter(),
) : Formatter() {
  override fun format(record: LogRecord): String = objectFormatter.format(
    loggerFormatter.format(LoggerRecord(record))
  )

}