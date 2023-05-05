package net.kigawa.kutil.kutil.err

import java.io.PrintStream

class StreamErrorHandler<T: Throwable>(
  var out: PrintStream = System.out,
): ErrorHandler<T> {
  override fun catch(e: T) {
    e.printStackTrace(out)
  }
}