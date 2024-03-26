package net.kigawa.kutil.kutil.api.process

import net.kigawa.kutil.kutil.api.io.ReaderIo
import net.kigawa.kutil.kutil.api.io.SuspendCloseable
import net.kigawa.kutil.kutil.api.io.WriterIo

interface KuProcess : SuspendCloseable {
  fun reader(): ReaderIo<String>
  fun errReader(): ReaderIo<String>
  fun writer(): WriterIo<String>
  suspend fun waitFor()
}