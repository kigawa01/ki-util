package net.kigawa.mcsm.util.io

import kotlinx.coroutines.channels.Channel

open class ChannelReaderIo<T>(
  protected val channel: Channel<T>,
) : ReaderIo<T> {

  override suspend fun read(): T {
    return channel.receive()
  }

  override suspend fun forEach(block: suspend (T) -> Unit) {
    channel.forEach { block(it) }
  }

}