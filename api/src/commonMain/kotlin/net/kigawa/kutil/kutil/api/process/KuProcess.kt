package net.kigawa.mcsm.util.process

import net.kigawa.mcsm.util.io.ReaderIo
import net.kigawa.mcsm.util.io.SuspendCloseable
import net.kigawa.mcsm.util.io.WriterIo

interface KuProcess : SuspendCloseable {
  fun reader(): ReaderIo<String>
  fun errReader(): ReaderIo<String>
  fun writer(): WriterIo<String>
  suspend fun waitFor()
}