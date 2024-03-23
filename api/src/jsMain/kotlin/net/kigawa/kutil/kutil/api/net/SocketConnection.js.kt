package net.kigawa.kutil.kutil.api.net

import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.io.SuspendCloseable

@Suppress("unused")
actual class SocketConnection : SuspendCloseable {
  actual fun reader(): Channel<String> {
    TODO("Not yet implemented")
  }

  actual fun writer(): Channel<String> {
    TODO("Not yet implemented")
  }

  override suspend fun suspendClose() {
    TODO("Not yet implemented")
  }
}