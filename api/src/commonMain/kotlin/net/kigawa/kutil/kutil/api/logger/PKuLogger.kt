package net.kigawa.kutil.kutil.api.logger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect object PKuLogger {
  val defaultHandler: LoggerHandler
}