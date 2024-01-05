package net.kigawa.mcsm.util.io

import kotlinx.coroutines.channels.Channel
import net.kigawa.mcsm.util.concurrent.Coroutines
import java.io.BufferedReader

class ReaderStreamIo(
  coroutines: Coroutines,
  private val reader: BufferedReader,
  private val onClose: (suspend () -> Unit)? = null,
) : ChannelReaderIo<String>(Channel()), SuspendCloseable {
  init {
    coroutines.launchIo {
      try {
        reader.forEachLineSuspend {
          channel.send(it)
        }
      } finally {
        suspendClose()
      }
    }
  }

  override suspend fun suspendClose() {
    try {
      reader.run { close() }
    } finally {
      channel.close()
      onClose?.invoke()
    }
  }
}