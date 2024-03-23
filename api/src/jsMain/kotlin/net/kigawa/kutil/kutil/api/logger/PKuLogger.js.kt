package net.kigawa.kutil.kutil.api.logger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual object PKuLogger {
  actual val defaultHandler: LoggerHandler = JsDefaultLoggerHandler
}