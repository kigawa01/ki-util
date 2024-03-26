package net.kigawa.kutil.kutil.api.concurrent

class SyncVal<T>(
  private val value: T,
  coroutines: Coroutines = Coroutines.defaultCoroutines,
) : SyncQueue(coroutines) {
  suspend fun <R> useValue(task: suspend (T) -> R) = runTask { task(value) }
}