@file:Suppress("unused")

package net.kigawa.kutil.kutil.concurrent

import net.kigawa.kutil.kutil.err.ErrorHandler
import net.kigawa.kutil.kutil.err.StreamErrorHandler
import net.kigawa.kutil.kutil.list.KList
import net.kigawa.kutil.kutil.manager.RemoveAble
import java.util.*
import java.util.concurrent.*

class AsyncExecutor(
  var executor: ExecutorService = Executors.newCachedThreadPool(),
  var timeOutMilli: Long = 1000,
  var errorHandler: ErrorHandler<Throwable> = StreamErrorHandler(Throwable::class.java),
): AutoCloseable, RemoveAble(null) {
  private val tasks = KList.create<ExecuteTask<*>>()
  private val queues = mutableMapOf<Any, Queue<ExecuteTask<*>>>()
  
  fun <T: Any?> submit(task: Runnable, result: T): ResultTask<T> {
    return executeFuture(FutureTask(task, result))
  }
  
  fun submit(task: Runnable): Task {
    return submit(task, null)
  }
  
  fun isTerminated(): Boolean {
    return executor.isTerminated
  }
  
  fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean {
    return executor.awaitTermination(timeout, unit)
  }
  
  fun awaitTermination(): Boolean {
    return awaitTermination(timeOutMilli, TimeUnit.MILLISECONDS)
  }
  
  fun <T> submit(callable: Callable<T>): ResultTask<T> {
    return executeFuture(FutureTask(callable))
  }
  
  fun waitTask() {
    tasks.forEach(ExecuteTask<*>::wait)
  }
  
  fun execute(runnable: Runnable): Task {
    return submit(runnable)
  }
  
  private fun <T> executeFuture(futureTask: FutureTask<T>): ExecuteTask<T> {
    return ExecuteTask(this, futureTask)
      .apply(tasks::add)
      .apply {registerRemoveTask {tasks.remove(this)}}
      .apply(executor::execute)
  }
  
  fun <T> submitQueue(lock: Any, callable: Callable<T>): ResultTask<T> {
    val executeTask = ExecuteTask(this, FutureTask(callable))
    
    synchronized(queues) {
      var queue = queues[lock]
      if (queue == null) {
        queue = LinkedList()
        queue.add(executeTask)
        queues[lock] = queue
        runQueue(lock, queue)
      } else queue.add(executeTask)
    }
    
    return executeTask
  }
  
  private fun runQueue(key: Any, queue: Queue<ExecuteTask<*>>) {
    execute {
      while (true) {
        val task = synchronized(queues) {
          val futureTask = queue.poll()
          if (futureTask == null) {
            queues.remove(key)
            return@execute
          }
          futureTask
        }
        task.run()
        try {
          task.get()
        } catch (e: ExecutionException) {
          e.cause?.apply(errorHandler::catch) ?: errorHandler.catch(e)
        }
      }
    }
  }
  
  override fun close() {
    if (removed.useSelf {
        if (it) return@useSelf true
        set(true)
        false
      }) return
    
    executor.shutdown()
    awaitTermination()
  }
}