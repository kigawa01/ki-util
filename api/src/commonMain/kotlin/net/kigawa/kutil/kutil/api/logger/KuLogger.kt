package net.kigawa.kutil.kutil.api.logger

@Suppress("MemberVisibilityCanBePrivate", "unused")
class KuLogger(
  private val loggerHandler: LoggerHandler,
) {
  companion object {
    var defaultHandler = PKuLogger.defaultHandler
    var defaultLogger: KuLogger = KuLogger(defaultHandler)
  }

  fun info(message: Any) = loggerHandler.log(LogLevel.INFO, message)
  fun fine(message: Any) = loggerHandler.log(LogLevel.FINE, message)
  fun warning(message: Any) = loggerHandler.log(LogLevel.WARNING, message)
}