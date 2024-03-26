package net.kigawa.kutil.kutil.api.logger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LoggerRecord(
  actual val level: LogLevel,
  actual val message: Any,
)