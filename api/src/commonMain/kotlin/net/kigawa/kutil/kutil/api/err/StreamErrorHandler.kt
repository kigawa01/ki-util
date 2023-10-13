package net.kigawa.kutil.kutil.api.err

import java.io.PrintStream

class StreamErrorHandler<T: Throwable>(
  private val errorClass: Class<T>,
  var out: PrintStream = System.out,
): ErrorHandler<T> {
  override fun catch(e: T) {
    e.printStackTrace(out)
  }
  
  override fun <R> tryCatch(task: ()->R) {
    return tryCatch(errorClass, task)
  }
}