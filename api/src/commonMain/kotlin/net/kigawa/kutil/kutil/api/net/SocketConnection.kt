package net.kigawa.kutil.kutil.api.net

import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.io.SuspendCloseable

expect class SocketConnection : SuspendCloseable {
  fun reader(): Channel<String>
  fun writer(): Channel<String>
}