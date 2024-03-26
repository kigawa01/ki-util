package net.kigawa.kutil.kutil.api.logger.formatter

import net.kigawa.kutil.kutil.api.logger.LoggerRecord

interface LoggerFormatter {
  fun format(logRecord: LoggerRecord): Any
}