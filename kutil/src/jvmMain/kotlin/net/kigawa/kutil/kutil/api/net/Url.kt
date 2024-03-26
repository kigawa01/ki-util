package net.kigawa.kutil.kutil.api.net

import kotlinx.coroutines.withContext
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.kutil.kutil.api.io.fs.KuPath
import java.io.FileOutputStream
import java.net.URI
import java.nio.channels.Channels

@Suppress("unused")
actual class Url actual constructor(
  strUrl: String,
) {
  private val nativeUri = URI(strUrl)
  actual suspend fun download(to: KuPath) {
    withContext(Coroutines.defaultIoContext) {
      nativeUri.toURL().openStream().let {
        Channels.newChannel(it)
      }.use { channel ->
        FileOutputStream(to.toFile().nativeFile).use {
          it.channel.transferFrom(channel, 0, Long.MAX_VALUE)
        }
      }
    }

  }


}