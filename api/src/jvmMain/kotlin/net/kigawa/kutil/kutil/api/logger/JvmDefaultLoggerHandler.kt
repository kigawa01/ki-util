package net.kigawa.kutil.kutil.api.logger

import net.kigawa.kutil.kutil.api.logger.formatter.DefaultLoggerFormatter
import net.kigawa.kutil.kutil.api.logger.formatter.LoggerFormatter
import net.kigawa.kutil.kutil.api.logger.formatter.LoggerFormatterBridge
import net.kigawa.kutil.kutil.api.string.objectformatter.DefaultObjFormatter
import java.util.logging.ConsoleHandler
import java.util.logging.Logger

internal object JvmDefaultLoggerHandler : LoggerHandler {
  private val logger = Logger.getLogger("")
  private val objectFormatter = DefaultObjFormatter()

  init {
    val consoleHandler = ConsoleHandler()
    consoleHandler.formatter = LoggerFormatterBridge(DefaultLoggerFormatter())
    logger.addHandler(consoleHandler)
  }

  override fun log(level: LogLevel, message: Any) {
    logger.log(level.javaLevel(), objectFormatter.format(message))
  }

  override fun setFormatter(loggerFormatter: LoggerFormatter) {
    val formatter = LoggerFormatterBridge(loggerFormatter)
    logger.handlers.forEach {
      it.formatter = formatter
    }
  }
}