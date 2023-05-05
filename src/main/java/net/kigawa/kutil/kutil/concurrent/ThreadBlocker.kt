package net.kigawa.kutil.kutil.concurrent

open class ThreadBlocker {
  @Synchronized
  fun synchronized(runnable: ()->Unit) {
    runnable()
  }
}