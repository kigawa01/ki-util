package net.kigawa.kutil.kutil.api.io

import kotlinx.coroutines.channels.Channel

open class ChannelWriterIo<T>(
  protected val channel: Channel<T>,
) : WriterIo<T> {
  override suspend fun writeLine(value: T) {
    channel.send(value)
  }
}