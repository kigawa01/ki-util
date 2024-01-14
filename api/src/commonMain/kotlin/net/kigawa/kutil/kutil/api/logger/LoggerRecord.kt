package net.kigawa.kutil.kutil.api.logger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class LoggerRecord {
  val level: LogLevel
  val message: Any
}