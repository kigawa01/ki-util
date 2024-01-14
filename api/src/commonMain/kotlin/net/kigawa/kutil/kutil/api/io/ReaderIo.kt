package net.kigawa.mcsm.util.io

import net.kigawa.kutil.kutil.api.io.Io

interface ReaderIo<T> : Io {
  suspend fun read(): T
  suspend fun forEach(block: suspend (T) -> Unit)
}