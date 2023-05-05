package net.kigawa.kutil.kutil.err

interface ErrorHandler<T: Throwable> {
  fun catch(e: T)
}