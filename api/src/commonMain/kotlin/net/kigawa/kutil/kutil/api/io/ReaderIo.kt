package net.kigawa.mcsm.util.io

interface ReaderIo<T> : Io {
  suspend fun read(): T
  suspend fun forEach(block: suspend (T) -> Unit)
}