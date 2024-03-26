package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal actual object PCoroutines {
  actual val defaultIoContext: CoroutineDispatcher
    get() = Dispatchers.Main
}