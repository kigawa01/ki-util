package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.forEach

open class SyncQueue(
  protected val coroutines: Coroutines = Coroutines.defaultCoroutines,
) {
  private val tasks = Channel<Deferred<*>>()

  init {
    coroutines.launchMain {
      tasks.forEach {
        it.start()
      }
    }
  }

  suspend fun <R> runTask(task: suspend () -> R) =
    tasks.send(coroutines.asyncMain(start = CoroutineStart.LAZY) { task() })
}