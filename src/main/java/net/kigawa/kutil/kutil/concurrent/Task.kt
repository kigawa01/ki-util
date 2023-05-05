package net.kigawa.kutil.kutil.concurrent

interface Task {
  fun wait(timeoutMilli: Long)
  fun wait()
  fun cancel(mayInterruptIfRunning: Boolean): Boolean
  fun isCancelled(): Boolean
  fun isDone(): Boolean
}