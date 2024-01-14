package net.kigawa.mcsm.util.net

import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.mcsm.util.io.KuPath
import net.kigawa.mcsm.util.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.logger.KuLogger

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SocketClient(
  path: KuPath,
  logger: KuLogger,
  coroutines: Coroutines,
) : SuspendCloseable {
  fun connect(): SocketConnection
}