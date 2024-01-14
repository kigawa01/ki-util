package net.kigawa.mcsm.util.process

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import net.kigawa.kutil.kutil.api.concurrent.Coroutines
import net.kigawa.mcsm.util.io.ReaderIo
import net.kigawa.mcsm.util.io.ReaderStreamIo
import net.kigawa.mcsm.util.io.WriterIo
import net.kigawa.mcsm.util.io.WriterStreamIo

class JvmProcess(
  private val process: Process,
  private val coroutines: Coroutines,
) : KuProcess {
  private val isClosed = MutableStateFlow(false)
  private val writer = WriterStreamIo(coroutines, process.outputWriter()) {
    if (!isClosed.value) return@WriterStreamIo suspendClose()
    try {
      process.destroy()
    } finally {
      reader.suspendClose()
    }
  }
  private val reader = ReaderStreamIo(coroutines, process.inputReader()) {
    if (!isClosed.value) return@ReaderStreamIo suspendClose()
    errReader.suspendClose()
  }
  private val errReader = ReaderStreamIo(coroutines, process.errorReader()) {
    if (!isClosed.value) return@ReaderStreamIo suspendClose()
  }

  override fun reader(): ReaderIo<String> {
    return reader
  }

  override fun errReader(): ReaderIo<String> {
    return errReader
  }

  override suspend fun waitFor() {
    withContext(coroutines.ioContext) {
      process.waitFor()
    }
  }

  override suspend fun suspendClose() {
    if (!isClosed.compareAndSet(expect = false, update = true)) return
    writer.suspendClose()
  }

  override fun writer(): WriterIo<String> {
    return writer
  }
}