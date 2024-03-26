package net.kigawa.kutil.kutil.api

@Suppress("unused")
actual object SignalHandler {
  actual fun shutdownHook(hook: () -> Unit) {
  }
}