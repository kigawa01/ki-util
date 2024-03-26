package net.kigawa.kutil.kutil.api.net

import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.io.fs.KuPath
import net.kigawa.kutil.kutil.api.logger.KuLogger
import java.net.UnixDomainSocketAddress
import java.nio.channels.SocketChannel

@Suppress("unused")
actual class SocketClient actual constructor(
  path: KuPath,
  private val logger: KuLogger,
  private val coroutines: Coroutines,
) : SuspendCloseable {
  private val socketAddress = UnixDomainSocketAddress.of(path.javaPath())
  private val socketChannel = SocketChannel.open(socketAddress)
  actual fun connect(): SocketConnection {
    return SocketConnection(socketChannel, logger, coroutines)
  }

  override suspend fun suspendClose() {
    coroutines.withContextIo {
      socketChannel.close()
    }
  }
}