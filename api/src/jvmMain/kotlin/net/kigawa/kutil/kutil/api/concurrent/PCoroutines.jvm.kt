package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object PCoroutines {
  actual val defaultIoContext: CoroutineDispatcher
    get() = Dispatchers.IO
}