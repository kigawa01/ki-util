package net.kigawa.mcsm.util.io

import kotlinx.coroutines.channels.Channel
import net.kigawa.mcsm.util.concurrent.DefaultCoroutines

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object DefaultIo {
  actual val out: WriterIo<String>
  actual val error: WriterIo<String>

  init {
    val infoChannel = Channel<String>()
    val errorChannel = Channel<String>()

    out = ChannelWriterIo(infoChannel)
    error = ChannelWriterIo(errorChannel)

    infoChannel.dispatchForEach(DefaultCoroutines.ioContext) {
      println(it)
    }
    errorChannel.dispatchForEach(DefaultCoroutines.ioContext) {
      System.err.println(it)
    }
  }
}