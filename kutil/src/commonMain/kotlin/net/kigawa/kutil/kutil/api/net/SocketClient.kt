package net.kigawa.kutil.kutil.api.net

import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.fs.KuPath
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.logger.KuLogger

expect class SocketClient(
  path: KuPath,
  logger: KuLogger,
  coroutines: Coroutines,
) : SuspendCloseable {
  fun connect(): SocketConnection
}