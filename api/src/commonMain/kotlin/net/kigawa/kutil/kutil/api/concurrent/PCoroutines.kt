package net.kigawa.kutil.kutil.api.concurrent

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
internal expect object PCoroutines {
  val defaultIoContext: CoroutineDispatcher
}