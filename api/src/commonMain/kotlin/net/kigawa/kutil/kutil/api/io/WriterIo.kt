package net.kigawa.mcsm.util.io

import net.kigawa.kutil.kutil.api.io.Io

interface WriterIo<T> : Io {
  suspend fun writeLine(value: T)
}