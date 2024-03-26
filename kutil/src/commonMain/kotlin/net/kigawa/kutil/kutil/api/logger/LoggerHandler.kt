package net.kigawa.kutil.kutil.api.logger

import net.kigawa.kutil.kutil.api.logger.formatter.LoggerFormatter

interface LoggerHandler {
  fun log(level: LogLevel, message: Any)
  fun setFormatter(loggerFormatter: LoggerFormatter)
}