package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual object PCoroutines {
  actual val defaultIoContext: CoroutineDispatcher
    get() = Dispatchers.IO
}