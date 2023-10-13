package net.kigawa.kutil.kutil.api.concurrent

interface Task {
  fun waitTask(timeoutMilli: Long)
  fun waitTask()
  fun cancel(mayInterruptIfRunning: Boolean): Boolean
  fun isCancelled(): Boolean
  fun isDone(): Boolean
}