package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineScope
import net.kigawa.kutil.kutil.api.concurrent.CoroutineException

class CoroutineLaunchException(message: String?, coroutineContext: CoroutineScope, cause: Throwable?) :
  CoroutineException(message, coroutineContext, cause) {
}