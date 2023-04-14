package net.kigawa.kutil.kutil.concurrent

open class ConcurrentVal<T: Any?>(
  protected var value: T,
) {
  @Synchronized
  fun synchronized(runnable: (T)->Unit) {
    runnable(value)
  }
  
  @Synchronized
  fun get(): T {
    return value
  }
}