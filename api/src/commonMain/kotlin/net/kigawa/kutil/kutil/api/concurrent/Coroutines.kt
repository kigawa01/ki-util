@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.*
import net.kigawa.kutil.kutil.api.logger.KuLogger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class Coroutines(
  private val logger: KuLogger = KuLogger.defaultLogger,
  val mainContext: CoroutineDispatcher = defaultMainContext,
  val ioContext: CoroutineDispatcher = defaultIoContext,
) {
  companion object {
    var defaultCoroutines = Coroutines()
    val defaultMainContext: CoroutineDispatcher = Dispatchers.Main
    val defaultIoContext: CoroutineDispatcher = PCoroutines.defaultIoContext
    val defaultDefaultScope: CoroutineScope
      get() = CoroutineScope(defaultMainContext)
    val defaultIoScope: CoroutineScope
      get() = CoroutineScope(defaultIoContext)
  }

  val defaultScope
    get() = CoroutineScope(mainContext)
  val ioScope
    get() = CoroutineScope(ioContext)

  fun launchMain(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
  ) = launchDef(defaultScope, context, start, block)

  fun <T> asyncMain(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T,
  ) = defaultScope.async(context, start, block)

  suspend fun <T> withContext(
    block: suspend CoroutineScope.() -> T,
  ) = withContext(mainContext, block)

  fun launchIo(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
  ) = launchDef(ioScope, context, start, block)

  fun <T> asyncIo(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> T,
  ) = ioScope.async(context, start, block)

  suspend fun <T> withContextIo(
    block: suspend CoroutineScope.() -> T,
  ) = withContext(ioContext, block)

  private fun launchDef(
    scope: CoroutineScope,
    context: CoroutineContext,
    start: CoroutineStart,
    block: suspend CoroutineScope.() -> Unit,
  ): Job {
    return scope.launch(context, start) {
      try {
        block()
      } catch (e: Exception) {
        if (e is CancellationException) return@launch
        logger.warning("exception in coroutine")
        logger.warning(scope.toString())
        logger.warning(e.message ?: e.toString())
        throw CoroutineLaunchException("exception in coroutine", scope, e)
      }
    }
  }
}