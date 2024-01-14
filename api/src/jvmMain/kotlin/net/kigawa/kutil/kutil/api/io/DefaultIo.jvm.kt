package net.kigawa.kutil.kutil.api.io

import kotlinx.coroutines.channels.Channel
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.mcsm.util.io.WriterIo

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object DefaultIo {
  actual val error: WriterIo<String>
  actual val out: WriterIo<String>

  init {
    val infoChannel = Channel<String>()
    val errorChannel = Channel<String>()

    out = ChannelWriterIo(infoChannel)
    error = ChannelWriterIo(errorChannel)

    infoChannel.dispatchForEach(Coroutines.defaultIoContext) {
      println(it)
    }
    errorChannel.dispatchForEach(Coroutines.defaultIoContext) {
      System.err.println(it)
    }
  }
}