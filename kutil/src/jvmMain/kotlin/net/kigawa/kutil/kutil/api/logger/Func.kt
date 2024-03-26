package net.kigawa.kutil.kutil.api.logger

import java.util.logging.Level


fun LogLevel.javaLevel(): Level? {
  return Level.parse(this.name)
}

fun Level.logLevel(): LogLevel = LogLevel.values().first {
  it.name.lowercase() == this.name.lowercase()
}
