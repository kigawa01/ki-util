@file:Suppress("unused")

package net.kigawa.kutil.kutil.api.debug

object KutilDebug {
  @JvmStatic
  var debug: Boolean = true
  
  @JvmStatic
  fun debug(message: Any?) = debug(message.toString(), 1, 1)
  
  @JvmStatic
  fun debug() = debug(null, 1, 1)
  
  @JvmStatic
  fun debug(message: String?) = debug(message, 1, 1)
  
  @JvmStatic
  fun debug(message: Any?, size: Int) = debug(message.toString(), 1, size)
  
  @JvmStatic
  fun debug(message: String?, traceSize: Int) = debug(message, 1, traceSize)
  
  @JvmStatic
  fun debug(message: String?, traceOffset: Int, traceSize: Int) {
    if (!debug) return
    Thread.currentThread().stackTrace.drop(traceOffset + 2).take(traceSize).forEach {
      println(" |$it")
    }
    message?.let {println(it)}
  }
}