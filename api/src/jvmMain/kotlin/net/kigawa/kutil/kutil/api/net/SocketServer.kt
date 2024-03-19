package net.kigawa.kutil.kutil.api.net

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.isActive
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.fs.KuPath
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.logger.KuLogger
import net.kigawa.kutil.kutil.api.net.SocketConnection
import java.net.BindException
import java.net.StandardProtocolFamily
import java.net.UnixDomainSocketAddress
import java.nio.channels.AsynchronousCloseException
import java.nio.channels.ServerSocketChannel

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SocketServer actual constructor(
  path: KuPath,
  private val logger: KuLogger?,
  private val coroutines: Coroutines,
) : SuspendCloseable {
  private val socketAddress = UnixDomainSocketAddress.of(path.javaPath())
  private val conChannel = Channel<SocketConnection>()
  private val serverSocketChannel: ServerSocketChannel
  private val bindTask: Job

  init {
    if (path.isExists()) path.removeIfExists()
    try {
      serverSocketChannel = ServerSocketChannel.open(StandardProtocolFamily.UNIX).bind(socketAddress)
    } catch (e: BindException) {
      conChannel.close()
      throw e
    }
    bindTask = coroutines.launchIo(start = CoroutineStart.LAZY) {
      try {
        while (serverSocketChannel.isOpen && isActive) {
          conChannel.send(
            net.kigawa.kutil.kutil.api.net.SocketConnection(serverSocketChannel.accept(), logger, coroutines)
          )
          logger?.fine("client connected")
        }

      } catch (_: AsynchronousCloseException) {
      } finally {
        conChannel.close()
        serverSocketChannel.close()
      }
    }
  }

  actual fun bind(): Channel<SocketConnection> {
    bindTask.start()
    return conChannel
  }

  override suspend fun suspendClose() {
    coroutines.withContextIo {
      serverSocketChannel.close()
    }

    bindTask.join()
  }
}