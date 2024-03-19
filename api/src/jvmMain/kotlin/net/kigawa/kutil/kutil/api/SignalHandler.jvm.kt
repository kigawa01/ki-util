package net.kigawa.kutil.kutil.api

import kotlin.concurrent.thread

@Suppress("unused")
actual object SignalHandler {
  actual fun shutdownHook(hook: () -> Unit) {
    Runtime.getRuntime().addShutdownHook(thread(start = false) {
      hook()
    })
  }
}