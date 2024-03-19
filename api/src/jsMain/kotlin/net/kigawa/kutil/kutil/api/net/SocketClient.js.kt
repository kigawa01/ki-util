package net.kigawa.kutil.kutil.api.net

import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.io.fs.KuPath
import net.kigawa.kutil.kutil.api.logger.KuLogger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SocketClient actual constructor(
  path: KuPath, logger: KuLogger,
  coroutines: Coroutines,
) : SuspendCloseable {
  actual fun connect(): SocketConnection {
    TODO("Not yet implemented")
  }

  override suspend fun suspendClose() {
    TODO("Not yet implemented")
  }
}