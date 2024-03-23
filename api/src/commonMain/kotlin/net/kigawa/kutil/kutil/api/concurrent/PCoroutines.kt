package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineDispatcher

internal expect object PCoroutines {
  val defaultIoContext: CoroutineDispatcher
}