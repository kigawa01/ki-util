package net.kigawa.kutil.kutil.api.net

import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.io.fs.KuPath
import net.kigawa.kutil.kutil.api.logger.KuLogger

@Suppress("unused")
actual class SocketServer actual constructor(
  path: KuPath, logger: KuLogger?,
  coroutines: Coroutines,
) : SuspendCloseable {
  actual fun bind(): Channel<SocketConnection> {
    TODO("Not yet implemented")
  }

  override suspend fun suspendClose() {
    TODO("Not yet implemented")
  }
}